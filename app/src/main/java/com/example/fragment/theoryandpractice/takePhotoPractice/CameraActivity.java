package com.example.fragment.theoryandpractice.takePhotoPractice;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PictureCallback;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.OrientationEventListener;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fragment.theoryandpractice.R;
import com.example.fragment.theoryandpractice.takePhotoPractice.adapter.CameraPictureAdapter;
import com.example.fragment.theoryandpractice.utils.ApUtils;
import com.example.fragment.theoryandpractice.utils.BitmapUtils;
import com.example.fragment.theoryandpractice.utils.DensityHelper;
import com.example.fragment.theoryandpractice.utils.FileUtlis;
import com.example.fragment.theoryandpractice.widget.LoadingDialog;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import it.sephiroth.android.library.widget.HListView;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

@SuppressWarnings("deprecation")
public class CameraActivity extends Activity {

    LoadingDialog mLoadingDialog;
    @InjectView(R.id.surface_view)
    SurfaceView surfaceView;
    @InjectView(R.id.list_view)
    HListView listView;
    @InjectView(R.id.camera_button)
    ImageView cameraButton;

    private CameraPictureAdapter adapter = null;
    private List<String> productUrls = new ArrayList<String>();
    private Camera camera = null;
    public int mOrientationCompensation = 0;
    private int maxUploadPictureCount = 0;
    private boolean isCheckPictureSize = false;
    private OrientationEventListener mOrientationListener = null;
    private LinearLayout.LayoutParams params = null;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_layout);
        ButterKnife.inject(this);
        init();
    }

    private void init() {
        initArgs();
        initView();
    }

    private void initArgs() {
        Bundle bundle = getIntent().getExtras();
        maxUploadPictureCount=10;
    }

    private void initView() {
        mLoadingDialog=new LoadingDialog(this);
        surfaceView.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        surfaceView.getHolder().addCallback(new SurfaceCallback());
        mOrientationListener = new OrientationEventListener(this) {
            private int mOrientation = OrientationEventListener.ORIENTATION_UNKNOWN;

            public void onOrientationChanged(int orientation) {
                if (orientation == ORIENTATION_UNKNOWN) return;
                mOrientation = ((orientation + 45) / 90 * 90) % 360;
                int orientationCompensation = mOrientation + getDisplayRotation();
                mOrientationCompensation = orientationCompensation;
            }
        };

        adapter = new CameraPictureAdapter(this) {
            public void notifyDataSetChanged() {
                super.notifyDataSetChanged();
                listView.setVisibility(getCount() == 0 ? View.GONE : View.VISIBLE);
            }
        };
        listView.setAdapter(adapter);

        params = new LinearLayout.LayoutParams(
                DensityHelper.dip2px(CameraActivity.this, 100),
                DensityHelper.dip2px(CameraActivity.this, 100));
    }

    private class SurfaceCallback implements Callback {
        public void surfaceCreated(SurfaceHolder holder) {
            try {
                camera = Camera.open();
                Parameters params = camera.getParameters();
                Camera.Size pictureSize = BitmapUtils.getPictureSize(params);
                params.setPictureSize(pictureSize.width, pictureSize.height);
                Camera.Size size = BitmapUtils.getPreviewSize(params);
                params.setPreviewSize(size.width, size.height);
                params.setJpegQuality(100);
                camera.setDisplayOrientation(90);
                params.setRotation(90);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                    params.setFocusMode(Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);//1连续对焦
                } else {
                    params.setFocusMode(Parameters.FOCUS_MODE_AUTO);
                }
                camera.setParameters(params);
                camera.setPreviewDisplay(surfaceView.getHolder());
                camera.startPreview();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void surfaceDestroyed(SurfaceHolder holder) {
            if (camera == null) return;
            try {
                camera.stopPreview();
                camera.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
            camera = null;
        }

        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        }
    }

    @OnClick(R.id.camera_button)
    public void camera(View view) {
        if (checkMaxPictureCount()) return;
        mLoadingDialog.show();
        mLoadingDialog.setText("处理中...");
        cameraButton.setEnabled(false);
        try {
            camera.takePicture(null, null, pictureCallback);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private PictureCallback pictureCallback = new PictureCallback() {
        public void onPictureTaken(byte[] data, Camera camera) {
            camera.startPreview();
            cameraButton.setEnabled(true);
            String path = savePicture(data);
            if (!TextUtils.isEmpty(path)) {
                adapter.appendOneList(path);
                listView.setSelection(adapter.getCount());
                mLoadingDialog.dismiss();
            }
        }
    };

    private String savePicture(byte[] data) {
        int degree = 0;
        File file = new File(FileUtlis.IMG_PATH, System.currentTimeMillis() + ".jpg");
        if (file != null) {
            FileUtlis.copy(new ByteArrayInputStream(data), file);
            degree = BitmapUtils.readPictureDegree(file.getAbsolutePath());
            file.delete();
        }
        Bitmap mBitmap = BitmapUtils.compressImage(data, 800);
        mBitmap = BitmapUtils.rotateBitmap(mBitmap, mOrientationCompensation + degree);
        String path = BitmapUtils.saveToSDCard(mBitmap);
        if (mBitmap != null && !mBitmap.isRecycled()) {
            mBitmap.recycle();
        }
        return path;
    }

    @OnClick(R.id.choose_picture_button)
    public void choosePicture(View view) {
        if (checkMaxPictureCount()) return;
        //TODO 跳入选照片界面
        ArrayList<String> selectedPicList=new ArrayList<>();
        selectedPicList.addAll(adapter.getList());
        MultiImageSelectorActivity.open(this,false,maxUploadPictureCount,MultiImageSelectorActivity.MODE_MULTI,selectedPicList);
    }

    private boolean checkMaxPictureCount() {
        if (adapter.getCount() > maxUploadPictureCount - 1) {
            ApUtils.showToast( "最多只可上传" + maxUploadPictureCount + "张图片");
            return true;
        }
        return false;
    }


    private void doUpload(List<String> paths) {
//        mLoadingDialog.setText("上传中...");
//        mLoadingDialog.setCanceledOnTouchOutside(false);
//        mLoadingDialog.show();
//        UpLoadProductPictureManager.uploadPicture(paths, new UpLoadProductPictureManager.UploadCallBack() {
//            @Override
//            public void onUploadSuccess() {
//                mLoadingDialog.dismiss();
//                GlobalUtil.shortToast(CameraActivity.this, "上传成功!");
//                productUrls.addAll(getUrls());
//                if (product != null) {
//                    if (TextUtils.isEmpty(product.mId)) {
//                        notifyChanged();
//                    } else {
//                        addProductPicture();
//                    }
//                } else {
//                    Intent intent = new Intent();
//                    intent.putStringArrayListExtra(AddProductActivity.EXTRAS_CAMERA_IMAGEURLS, (ArrayList<String>) productUrls);
//                    setResult(Activity.RESULT_OK, intent);
//                    finish();
//                }
//            }
//
//            @Override
//            public void onUploadFailed() {
//                mLoadingDialog.dismiss();
//                productUrls.addAll(getUrls());
//
//                DialogHolder dialogHolder = DialogHolder.create(null, null, ViewHolder.create(CameraActivity.this, R.layout.dialog_upload_pics_fail,
//                        new ViewHolder.OnViewCreatedHandler() {
//                            @Override
//                            public void onViewCreated(View view) {
//                                TextView tvUploadInfo = ViewHolder.get(view, R.id.tvUploadInfo).getView();
//                                tvUploadInfo.setText(TextHolder.get(R.string.upload_pics_info, getUrls().size(), getPaths().size()).getText());
//                                HorPlainListView gvUploadFailedImages = ViewHolder.get(view, R.id.hplvUploadFailImages).getView();
//                                DataList<String> uploadFailedImages = new DataList<String>(getPaths());
//                                hplvFailedImagesBinder.bind(gvUploadFailedImages, uploadFailedImages);
//                            }
//                        }),
//
//                        TextHolder.get("重传失败图片"), new IEventHandler<EventArgs>() {
//                            @Override
//                            public void process(Object sender, EventArgs args) {
//                                doUpload(getPaths());
//                            }
//                        },
//
//                        product != null ? TextHolder.get("跳过") : getUrls().size() > 0 ? TextHolder.get("直接添加商品") : null,
//
//                        new IEventHandler<EventArgs>() {
//                            @Override
//                            public void process(Object sender, EventArgs args) {
//                                if (product != null) {
//                                    if (productUrls.size() > 0) {
//                                        if (TextUtils.isEmpty(product.mId)) {
//                                            notifyChanged();
//                                        } else {
//                                            addProductPicture();
//                                        }
//                                    } else {
//                                        finish();
//                                    }
//                                } else {
//                                    Intent intent = new Intent();
//                                    intent.putStringArrayListExtra(AddProductActivity.EXTRAS_CAMERA_IMAGEURLS, (ArrayList<String>) productUrls);
//                                    setResult(Activity.RESULT_OK, intent);
//                                    finish();
//                                }
//                            }
//                        },
//                        true
//                );
//                dialogHolder.show(CameraActivity.this);
//            }
//        });
    }


    @OnClick(R.id.next_step_button)
    public void cofirmUpload(View view) {
        //确认上传按钮
//        if (adapter.getCount() == 0) {
//            new AlertDialog.Builder(view.getContext()).setMessage(R.string.product_picture_not_null)
//                    .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.cancel();
//                        }
//                    }).show();
//            return;
//        }
        doUpload(adapter.getList());
    }

    private void addProductPicture() {
         //上传图片
//        AboutLiveRequest.addProductPictures(product.mId, productUrls, new DataCallBack() {
//            public void onSuccess(Object data) {
//                notifyChanged();
//            }
//        });
    }

    private void notifyChanged() {
        onBackPressed();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == MultiImageSelectorActivity.REQUEST_IMAGE){
            if(resultCode == RESULT_OK){
                ArrayList<String> mSelectPath = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                adapter.clear();
                adapter.appendToList(mSelectPath);
            }
        }
    }


    public int getDisplayRotation() {
        int rotation = getWindowManager().getDefaultDisplay().getRotation();
        switch (rotation) {
            case Surface.ROTATION_0:
                return 0;
            case Surface.ROTATION_90:
                return 90;
            case Surface.ROTATION_180:
                return 180;
            case Surface.ROTATION_270:
                return 270;
        }
        return 0;
    }

    @Override
    protected void onPause() {
        mOrientationListener.disable();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mOrientationListener.enable();
        super.onResume();
    }

    @Override
    @OnClick(R.id.back_button)
    public void onBackPressed() {
        if (camera != null) {
            try {
                camera.stopPreview();
                camera.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
            camera = null;
        }
        super.onBackPressed();
    }

    public static final String CURR_PRODUCT = "curr_product";
    public static final String REQUEST_TYPE = "request_type";
    public static final int REQUEST_FROM_ADD_PRODUCT = 1;//来自发布商品的请求
}