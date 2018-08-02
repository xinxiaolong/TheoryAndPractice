package com.example.fragment.theoryandpractice;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fragment.theoryandpractice.advert.AdvConfigModel;
import com.example.fragment.theoryandpractice.advert.AdvertLoader;
import com.example.fragment.theoryandpractice.bitmapPractice.BitmapTestActivity;
import com.example.fragment.theoryandpractice.bitmapPractice.GridView_LruCache;
import com.example.fragment.theoryandpractice.daggerPractice.AppComponent;
import com.example.fragment.theoryandpractice.daggerPractice.AppleDrink;
import com.example.fragment.theoryandpractice.daggerPractice.Bottle;
import com.example.fragment.theoryandpractice.daggerPractice.DrinkModule;
import com.example.fragment.theoryandpractice.eventPractice.EventTestActivity;
import com.example.fragment.theoryandpractice.glidePractice.RequestManagerFragment;
import com.example.fragment.theoryandpractice.httpPractice.httpActivity;
import com.example.fragment.theoryandpractice.measAndLayoutPractice.MeasureAndLayoutActivity;
import com.example.fragment.theoryandpractice.servicePractice.ServicePracticeActivity;
import com.example.fragment.theoryandpractice.stepviewPractice.HorizontalStepView;
import com.example.fragment.theoryandpractice.takePhotoPractice.CameraActivity;
import com.example.fragment.theoryandpractice.threadPractice.ThreadPracticeActivity;
import com.example.fragment.theoryandpractice.threadPractice.UploadImageService;
import com.example.fragment.theoryandpractice.utils.ApUtils;
import com.example.fragment.theoryandpractice.utils.HttpUtils;
import com.example.fragment.theoryandpractice.widget.FlikerProgressBar;
import com.example.fragment.theoryandpractice.widget.IndicatorPopWindow;
import com.example.fragment.theoryandpractice.widget.LoopViewPager;
import com.example.fragment.theoryandpractice.widget.MyButton;
import com.example.fragment.theoryandpractice.widget.MyRatingBar;
import com.example.fragment.theoryandpractice.widget.MyTextView;
import com.example.fragment.theoryandpractice.widget.ScrollerLayout;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.util.ConvertUtils;

/**
 * _oo0oo_
 * o8888888o
 * 88" . "88
 * (| -_- |)
 * 0\  =  /0
 * ___/`---'\___
 * .' \\|     |// '.
 * / \\|||  :  |||// \
 * / _||||| -:- |||||- \
 * |   | \\\  -  /// |   |
 * | \_|  ''\---/''  |_/ |
 * \  .-\__  '-'  ___/-. /
 * ___'. .'  /--.--\  `. .'___
 * ."" '<  `.___\_<|>_/___.' >' "".
 * | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 * \  \ `_.   \_ __\ /__ _/   .-` /  /
 * =====`-.____`.___ \_____/___.-`___.-'=====
 * `=---='
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * 佛祖保佑         永无BUG
 */

public class MainActivity extends Activity {


    public static final String TAG=MainActivity.class.getName();


    @InjectView(R.id.scrollerLayout)
     ScrollerLayout scrollerLayout;
    @InjectView(R.id.linear_content)
     LinearLayout linear_content;
    @InjectView(R.id.img_bitmpTest)
    ImageView img_bitmpTest;
    @InjectView(R.id.labelTextView)
    TextView labelTextView;
    @InjectView(R.id.myBtn1)
    MyButton myBtn1;
    @InjectView(R.id.myBtn2)
    MyTextView myTextView;
    @InjectView(R.id.to_eventTest)
    Button toEventTest;
    @InjectView(R.id.to_measureAndLayoutTest)
    Button to_measureAndLayoutTest;
    @InjectView(R.id.to_threadTest)
    Button toThreadTest;
    @InjectView(R.id.to_bitmapTest)
    Button to_bitmapTest;
    @InjectView(R.id.to_takePhoto)
    Button to_takePhoto;
    @InjectView(R.id.to_scroll)
    Button to_scroll;
    @InjectView(R.id.to_LruCache)
    Button to_LruCache;
    @InjectView(R.id.group)
    RadioGroup radioGroup;
    @InjectView(R.id.ratingbar)
    MyRatingBar ratingbar;
    @InjectView(R.id.to_service)
    Button toService;
    @InjectView(R.id.btn_switchViewPosition)
    Button btnSwitchViewPosition;
    @InjectView(R.id.verticalStepView)
    HorizontalStepView verticalStepView;
    @InjectView(R.id.btn_fragment)
    Button btn_fragment;
    @InjectView(R.id.btn_RecyclerView)
    Button btn_RecyclerView;
    @InjectView(R.id.btn_coordinatorLayout)
    Button btn_coordinatorLayout;
    @InjectView(R.id.to_httpTest)
    Button to_httpTest;
    @InjectView(R.id.round_flikerbar)
    FlikerProgressBar roundFlikerbar;
    @InjectView(R.id.loopViewPager)
    LoopViewPager loopViewPager;

    @Inject
    AppleDrink appleDrink;

    @Inject
    Bottle bottle;

    Thread downLoadThread;

    Context context;

    MyOnclickListener onclickListener = new MyOnclickListener();


    private int[] pics={R.drawable.img3,R.drawable.img6,R.drawable.img7,R.drawable.img9};


    IndicatorPopWindow indicatorPopWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        context = this;
        initEvent();
        ViewTreeObserver vto = getWindow().getDecorView().getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                printViewInfo();
            }
        });

        String netTypeText = "";
        int netType = HttpUtils.getNetType(context);
        switch (netType) {
            case HttpUtils.NET_TYPE_NO:
                netTypeText = "无网络";
                break;
            case HttpUtils.NET_TYPE_WIFI:
                netTypeText = "WIFI网络";
                break;
            case HttpUtils.NET_TYPE_M_2G:
                netTypeText = "2G网络";
                break;
            case HttpUtils.NET_TYPE_M_3G:
                netTypeText = "3G网络";
                break;
            case HttpUtils.NET_TYPE_M_4G:
                netTypeText = "4G网络";
                break;
            default:
                netTypeText = "手机移动网络";
                break;
        }
        ratingbar.setStar(0);
        initRadioGroup();

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.id.action_bar);
        labelTextView.setError("错误提示", null);

        initStepView();


        //roundFlikerbar.toggle();
        RequestManagerFragment requestManagerFragment = new RequestManagerFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(requestManagerFragment, "123").commit();


//        AppComponent appComponent = DaggerAppComponent.builder().drinkModule(new DrinkModule(this)).build();
//        appComponent.inject(this);
//        appleDrink.print();
//        bottle.print();

        loopViewPager.setPagerAdapterData(pics);

        indicatorPopWindow=new IndicatorPopWindow(this);

        onYearMonthDayPicker();

        AdvertLoader advertLoader=new AdvertLoader(this,null);
        advertLoader.startLoad(new AdvertLoader.NeedPlayAdvListener() {
            @Override
            public void OnNeedPlay(AdvConfigModel advConfigModel) {

            }

            @Override
            public void OnNeedNotPlay() {

            }
        });


        Log.e("info",getDeviceInfo(this) );

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                screenShot();
            }
        },1000);
    }


    private void screenShot(){
        View dView = getWindow().getDecorView();
        dView.setDrawingCacheEnabled(true);
        dView.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(dView.getDrawingCache());
        if (bitmap != null) {
            try {
                // 获取内置SD卡路径
                String sdCardPath = Environment.getExternalStorageDirectory().getPath();
                // 图片文件路径
                String filePath = sdCardPath + File.separator + "screenshot.png";
                File file = new File(filePath);
                FileOutputStream os = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
                os.flush();
                os.close();
                Log.e(TAG,"保存成功");
            } catch (Exception e) {
                Log.e(TAG,"保存失败"+e.getMessage());
            }
        }
    }

    public void onYearMonthDayPicker() {

        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH)+1;//月份是从0开始的所以要加以
        int day=calendar.get(Calendar.DAY_OF_MONTH);

        final DatePicker picker = new DatePicker(this);
        picker.setCanceledOnTouchOutside(true);
        picker.setUseWeight(true);
        picker.setTopPadding(ConvertUtils.toPx(this, 20));
        picker.setRangeEnd(year, month, day);
        picker.setRangeStart(2000, 1, 1);
        picker.setSelectedItem(year, month, day);
        picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {

            }
        });
        picker.setOnWheelListener(new DatePicker.OnWheelListener() {
            @Override
            public void onYearWheeled(int index, String year) {
                picker.setTitleText(year + "-" + picker.getSelectedMonth() + "-" + picker.getSelectedDay());
            }

            @Override
            public void onMonthWheeled(int index, String month) {
                picker.setTitleText(picker.getSelectedYear() + "-" + month + "-" + picker.getSelectedDay());
            }

            @Override
            public void onDayWheeled(int index, String day) {
                picker.setTitleText(picker.getSelectedYear() + "-" + picker.getSelectedMonth() + "-" + day);
            }
        });
        picker.show();
    }


    private void initStepView() {
        List<String> list0 = new ArrayList<>();
        list0.add("接单咯\n了啊啊");
        list0.add("打包");
        list0.add("出发");
        list0.add("送单");
        list0.add("完成");
        list0.add("支付");
        list0.add("支付");
        verticalStepView.setStepsViewIndicatorComplectingPosition(2)//设置完成的步数
                .setStepViewTexts(list0)//总步骤
                .setStepsViewIndicatorCompletedLineColor(Color.RED)
                .setStepsViewIndicatorUnCompletedLineColor(Color.GRAY)
                .setStepsViewIndicatorCompleteIcon(getResources().getDrawable(R.drawable.step_point_complete))
                .setStepViewComplectedTextColor(R.color.color_c9)
                .setStepsViewIndicatorAttentionIcon(getResources().getDrawable(R.drawable.step_point_complete));

    }

    private void initRadioGroup() {
        for (int i = 0; i < 5; i++) {
            RadioButton radioButton = (RadioButton) LayoutInflater.from(context).inflate(R.layout.include_chloice_sku_radiobtn_view, null);
            String text = "";

            if (i == 6) {
                radioButton.setEnabled(false);
            }

            for (int n = 0; n < (Math.random()) * 10; n++) {
                text += "**";
            }

            radioButton.setText("第" + (i + 1) + "个" + text);

//            radioButton.setTextColor(context.getResources().getColorStateList(R.color.choice_sku_btn_text_bg));
//            radioButton.setBackgroundResource(R.drawable.choice_sku_btn_backgroud);
//            radioButton.setButtonDrawable(new ColorDrawable(Color.TRANSPARENT));
//            radioButton.setPadding(20, 10, 20, 10);

            RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(10, 10, 10, 10);
            params.gravity = Gravity.CENTER;
            radioButton.setLayoutParams(params);
            radioGroup.addView(radioButton);
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                ApUtils.showToast("点击了" + checkedId);
            }
        });
    }

    private void printViewInfo() {
        int left = myTextView.getLeft();
        int width = myTextView.getWidth();
        int measuredHeight = myTextView.getMeasuredWidth();
    }

    private void initEvent() {
        toEventTest.setOnClickListener(onclickListener);
        to_measureAndLayoutTest.setOnClickListener(onclickListener);
        toThreadTest.setOnClickListener(onclickListener);
        to_bitmapTest.setOnClickListener(onclickListener);
        to_takePhoto.setOnClickListener(onclickListener);
        to_scroll.setOnClickListener(onclickListener);
        to_LruCache.setOnClickListener(onclickListener);
        toService.setOnClickListener(onclickListener);
        btnSwitchViewPosition.setOnClickListener(onclickListener);
        btn_fragment.setOnClickListener(onclickListener);
        btn_RecyclerView.setOnClickListener(onclickListener);
        btn_coordinatorLayout.setOnClickListener(onclickListener);
        to_httpTest.setOnClickListener(onclickListener);
    }

    class MyOnclickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.to_eventTest:

                     indicatorPopWindow.showAsDropDown(toEventTest);
                    //startActivity(new Intent(context, EventTestActivity.class));
                    break;
                case R.id.to_measureAndLayoutTest:
                    startActivity(new Intent(context, MeasureAndLayoutActivity.class));
                    break;
                case R.id.to_threadTest:
                    startActivity(new Intent(context, ThreadPracticeActivity.class));
                    break;
                case R.id.to_bitmapTest:
                    startActivity(new Intent(context, BitmapTestActivity.class));
                    break;
                case R.id.to_takePhoto:
                    startActivity(new Intent(context, CameraActivity.class));
                    break;
                case R.id.to_scroll:
                    if (isFirst) {
                        calculateInSampleSize();
                    } else {
                        recycleImageView();
                        isFirst = true;
                        ApUtils.showToast("释放资源");
                    }

                    break;
                case R.id.to_LruCache:
                    startActivity(new Intent(context, GridView_LruCache.class));
                    break;
                case R.id.to_service:
//                    startService();
//                    startActivity(new Intent(context, com.example.fragment.theoryandpractice.notificationPractice.MainActivity.class));
                    startActivity(new Intent(context, ServicePracticeActivity.class));
                    break;
                case R.id.btn_switchViewPosition:
                    if (isSwitch) {
                        isSwitch = false;
                        switchViewPosition(toService, to_LruCache);
                    } else {
                        isSwitch = true;
                        switchViewPosition(to_LruCache, toService);
                    }
                    Log.e(MainActivity.class.getName(), System.currentTimeMillis() + "");

                    downLoadThread = new Thread(runnable);
                    downLoadThread.start();
                    break;
                case R.id.btn_fragment:
                    break;
                case R.id.btn_RecyclerView:
                    startActivity(new Intent(context, com.example.fragment.theoryandpractice.recyclerViewPractice.MainActivity.class));
                    break;
                case R.id.btn_coordinatorLayout:
                    startActivity(new Intent(context, com.example.fragment.theoryandpractice.coordinatorLayoutPractice.MainActivity.class));
                    break;
                case R.id.to_httpTest:
                    startActivity(new Intent(context, httpActivity.class));
                    break;

            }
        }
    }


    boolean isSwitch = false;

    private void switchViewPosition(View firstView, View secondView) {
        int firstIndex = linear_content.indexOfChild(firstView);
        int secondIndex = linear_content.indexOfChild(secondView);

        linear_content.removeView(firstView);
        linear_content.removeView(secondView);

        linear_content.addView(secondView, firstIndex);
        linear_content.addView(firstView, secondIndex);

    }


    boolean isFirst = false;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }


    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Log.e(MainActivity.class.getName(), "onTrimMemory level=" + level);
        switch (level) {
            case TRIM_MEMORY_UI_HIDDEN:
                // 进行资源释放操作
                break;
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void recycleImageView() {
        Drawable drawable = img_bitmpTest.getBackground();
        Bitmap bmp = ((BitmapDrawable) drawable).getBitmap();
        bmp.recycle();
        bmp = null;
        System.gc();
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void calculateInSampleSize() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.img2, options);
        //原图大小
        int width = options.outWidth;
        int height = options.outHeight;

        //压缩
        options.inJustDecodeBounds = false;
        options.inSampleSize = 10;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img2, options);

        width = bitmap.getWidth();
        height = bitmap.getHeight();

        Drawable drawables = new BitmapDrawable(bitmap);
        img_bitmpTest.setBackground(drawables);
    }

    private void startService() {
        Intent intent = new Intent(this, UploadImageService.class);
        ArrayList<String> list = new ArrayList<>();
        list.add("123");
        list.add("124");
        list.add("12344");
        list.add("1233ss");
        list.add("123fffff");
        intent.putStringArrayListExtra("imageUrlList", list);
        startService(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                Log.e(MainActivity.class.getName(), "-----");
                return true;
            case KeyEvent.KEYCODE_VOLUME_UP:
                Log.e(MainActivity.class.getName(), "+++++");
                return true;
            case KeyEvent.KEYCODE_VOLUME_MUTE:
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && 100 == requestCode) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");
            String[] str = scanResult.split(";");
            //这里为何传的是数组？
            ApUtils.showToast(str[0]);
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            roundFlikerbar.setProgress(msg.arg1);
            roundFlikerbar.setProgress(msg.arg1);
            if (msg.arg1 == 100) {
                roundFlikerbar.finishLoad();
                roundFlikerbar.finishLoad();
            }
        }
    };


    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {
                while (!downLoadThread.isInterrupted()) {
                    float progress = roundFlikerbar.getProgress();
                    progress += 2;
                    Thread.sleep(200);
                    Message message = handler.obtainMessage();
                    message.arg1 = (int) progress;
                    handler.sendMessage(message);
                    if (progress == 100) {
                        break;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };


    public <Y extends Object> Y into(Y target) {


        return null;
    }



    public static boolean checkPermission(Context context, String permission) {
        boolean result = false;
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                Class<?> clazz = Class.forName("android.content.Context");
                Method method = clazz.getMethod("checkSelfPermission", String.class);
                int rest = (Integer) method.invoke(context, permission);
                if (rest == PackageManager.PERMISSION_GRANTED) {
                    result = true;
                } else {
                    result = false;
                }
            } catch (Exception e) {
                result = false;
            }
        } else {
            PackageManager pm = context.getPackageManager();
            if (pm.checkPermission(permission, context.getPackageName()) == PackageManager.PERMISSION_GRANTED) {
                result = true;
            }
        }
        return result;
    }

    public static String getDeviceInfo(Context context) {
        try {
            org.json.JSONObject json = new org.json.JSONObject();
            android.telephony.TelephonyManager tm = (android.telephony.TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            String device_id = null;
            if (checkPermission(context, Manifest.permission.READ_PHONE_STATE)) {
                device_id = tm.getDeviceId();
            }
            String mac = null;
            FileReader fstream = null;
            try {
                fstream = new FileReader("/sys/class/net/wlan0/address");
            } catch (FileNotFoundException e) {
                fstream = new FileReader("/sys/class/net/eth0/address");
            }
            BufferedReader in = null;
            if (fstream != null) {
                try {
                    in = new BufferedReader(fstream, 1024);
                    mac = in.readLine();
                } catch (IOException e) {
                } finally {
                    if (fstream != null) {
                        try {
                            fstream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            json.put("mac", mac);
            if (TextUtils.isEmpty(device_id)) {
                device_id = mac;
            }
            if (TextUtils.isEmpty(device_id)) {
                device_id = android.provider.Settings.Secure.getString(context.getContentResolver(),
                        android.provider.Settings.Secure.ANDROID_ID);
            }
            json.put("device_id", device_id);
            return json.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
