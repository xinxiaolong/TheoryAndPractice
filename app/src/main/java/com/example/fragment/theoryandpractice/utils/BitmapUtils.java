package com.example.fragment.theoryandpractice.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.media.ExifInterface;
import android.media.MediaScannerConnection;
import android.net.Uri;

import com.example.fragment.theoryandpractice.PracticeApplication;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Created by wangzheng on 2015/12/11.
 */
public class BitmapUtils {

    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return degree;
    }

    public static String saveToSDCard(Bitmap bitmap) {
        if (bitmap == null) return "";
        String folder = FileUtlis.IMG_PATH;
        String name = System.currentTimeMillis() + ".jpg";
        File file = new File(folder, name);
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            MediaScannerConnection.scanFile(PracticeApplication.getContext(),
                    new String[]{file.getAbsolutePath()}, null,
                    new MediaScannerConnection.OnScanCompletedListener() {
                        @Override
                        public void onScanCompleted(String path, Uri uri) {
                        }
                    }
            );
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file.getPath();
    }

    public static Bitmap rotateBitmap(Bitmap bitmap, int degrees) {
        if (degrees == 0 || null == bitmap) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        matrix.setRotate(degrees, bitmap.getWidth(), bitmap.getHeight());
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return bitmap;
    }

    public static Bitmap scaleBitmap(String path, float minWidth) {
        return scaleBitmap(BitmapFactory.decodeFile(path), minWidth);
    }

    public static Bitmap scaleBitmap(Bitmap bitmap, float minWidth) {
        try {
            Matrix matrix = new Matrix();
            float scale = 1;
            int minCell = Math.min(bitmap.getHeight(), bitmap.getWidth());
            if (minCell > minWidth) {
                scale = minWidth / minCell;
            }
            matrix.postScale(scale, scale);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public static InputStream compressImage(Bitmap image, long maxSize) {
        if (image == null) return null;
        InputStream is = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            int options = 100;
            while (baos.toByteArray().length / 1024 > maxSize) { // 循环判断如果压缩后图片是否大于maxSize kb,大于继续压缩
                baos.reset();// 重置baos即清空baos
                image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
                options -= 5;// 每次都减少5
            }
            is = new ByteArrayInputStream(baos.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return is;
    }

    /**
     * 找出最适合的预览界面分辨率
     *
     * @return
     */
    public static  Camera.Size getPictureSize(Camera.Parameters cameraParameters) {
        List<Camera.Size> supportedPicResolutions = cameraParameters.getSupportedPictureSizes(); // 至少会返回一个值
        Camera.Size defaultPictureResolution = cameraParameters.getPictureSize();
        // 排序
        List<Camera.Size> sortedSupportedPicResolutions = new ArrayList<Camera.Size>(
                supportedPicResolutions);
        Collections.sort(sortedSupportedPicResolutions, new Comparator<Camera.Size>() {
            @Override
            public int compare(Camera.Size a, Camera.Size b) {
                int aPixels = a.height * a.width;
                int bPixels = b.height * b.width;
                if (bPixels < aPixels) {
                    return -1;
                }
                if (bPixels > aPixels) {
                    return 1;
                }
                return 0;
            }
        });

        // 移除不符合条件的分辨率
        double screenAspectRatio = (double) DensityHelper.getWidthOfTheScreen()
                / (double) DensityHelper.getHeightOfTheScreen();
        Iterator<Camera.Size> it = sortedSupportedPicResolutions.iterator();
        while (it.hasNext()) {
            Camera.Size supportedPreviewResolution = it.next();
            int width = supportedPreviewResolution.width;
            int height = supportedPreviewResolution.height;

            // 在camera分辨率与屏幕分辨率宽高比不相等的情况下，找出差距最小的一组分辨率
            // 由于camera的分辨率是width>height，我们设置的portrait模式中，width<height
            // 因此这里要先交换然后在比较宽高比
            boolean isCandidatePortrait = width > height;
            int maybeFlippedWidth = isCandidatePortrait ? height : width;
            int maybeFlippedHeight = isCandidatePortrait ? width : height;
            double aspectRatio = (double) maybeFlippedWidth / (double) maybeFlippedHeight;
            double distortion = Math.abs(aspectRatio - screenAspectRatio);
            if (distortion >  0.15) {
                it.remove();
                continue;
            }
        }

        // 如果没有找到合适的，并且还有候选的像素，对于照片，则取其中最大比例的，而不是选择与屏幕分辨率相同的
        if (!sortedSupportedPicResolutions.isEmpty()) {
            return sortedSupportedPicResolutions.get(0);
        }

        // 没有找到合适的，就返回默认的
        return defaultPictureResolution;
    }
    public static Camera.Size getPreviewSize(Camera.Parameters cameraParameters) {
        Camera.Size defaultPreviewResolution = cameraParameters.getPreviewSize();
        List<Camera.Size> previewSizes = cameraParameters.getSupportedPreviewSizes();
        List<Camera.Size> backupPreviewSizes = new ArrayList<>(previewSizes);
        if (previewSizes == null) {
            return defaultPreviewResolution;
        }
        int screenWidth = DensityHelper.getWidthOfTheScreen();
        int screenHeight = DensityHelper.getHeightOfTheScreen();
        // 移除不符合条件的分辨率
        double screenAspectRatio = (double) screenWidth / (double) screenHeight;
        for (Camera.Size previewSize : backupPreviewSizes) {
            int width = previewSize.width;
            int height = previewSize.height;
            // 移除低于下限的分辨率，尽可能取高分辨率
            if (width * height < 480 * 320) {
                previewSizes.remove(previewSize);
                continue;
            }
            // 在camera分辨率与屏幕分辨率宽高比不相等的情况下，找出差距最小的一组分辨率
            // 由于camera的分辨率是width>height，我们设置的portrait模式中，width<height
            // 因此这里要先交换然preview宽高比后在比较
            boolean isCandidatePortrait = width > height;
            int maybeFlippedWidth = isCandidatePortrait ? height : width;
            int maybeFlippedHeight = isCandidatePortrait ? width : height;
            double aspectRatio = (double) maybeFlippedWidth / (double) maybeFlippedHeight;
            double distortion = Math.abs(aspectRatio - screenAspectRatio);
            if (distortion > 0.15) {
                previewSizes.remove(previewSize);
                continue;
            }
            // 找到与屏幕分辨率完全匹配的预览界面分辨率直接返回
            if (maybeFlippedWidth == screenWidth && maybeFlippedHeight == screenHeight) {
                return previewSize;
            }
        }
        // 如果没有找到合适的，并且还有候选的像素，则设置其中最大比例的，对于配置比较低的机器不太合适
        if (!previewSizes.isEmpty()) return previewSizes.get(0);
        // 没有找到合适的，就返回默认的
        return defaultPreviewResolution;
    }

    public static Bitmap compressImage(String path, int maxSide) {
        Bitmap bitmap = null;
        try {
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(path, opts);
            final int originalWidth = opts.outWidth;
            final int originalHeight = opts.outHeight;
            int originalDim = Math.max(originalWidth, originalHeight) / maxSide;
            opts = new BitmapFactory.Options();
            opts.inSampleSize = originalDim <= 1 ? 1 : originalDim;
            opts.inJustDecodeBounds = false;
            bitmap = BitmapFactory.decodeFile(path, opts);
            bitmap = rotaingImageView(readPictureDegree(path), bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }


    public static Bitmap compressImage(byte[] data, int minSide) {
        Bitmap bitmap = null;
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(data, 0, data.length, options);

            final int originalWidth = options.outWidth;
            final int originalHeight = options.outHeight;
            int originalDim = Math.min(originalWidth, originalHeight) / minSide;
            options.inSampleSize = originalDim <= 1 ? 1 : originalDim;
            options.inJustDecodeBounds = false;
            bitmap = BitmapFactory.decodeStream(new ByteArrayInputStream(data), null, options);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }


    public static Bitmap compressMinEdgeImage(String path, int minEdge) {
        Bitmap bitmap = null;
        try {
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(path, opts);
            final int originalWidth = opts.outWidth;
            final int originalHeight = opts.outHeight;
            int originalDim = Math.min(originalWidth, originalHeight) / minEdge;
            opts = new BitmapFactory.Options();
            opts.inSampleSize = originalDim <= 1 ? 1 : originalDim;
            opts.inJustDecodeBounds = false;
            bitmap = BitmapFactory.decodeFile(path, opts);
            bitmap = rotaingImageView(readPictureDegree(path), bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }


    private static Bitmap ratingImage(String filePath, Bitmap bitmap) {
        int degree = readPictureDegree(filePath);
        return rotaingImageView(degree, bitmap);
    }

    /**
     * 旋转图片
     *
     * @param angle
     * @param bitmap
     * @return Bitmap
     */
    public static Bitmap rotaingImageView(int angle, Bitmap bitmap) {
        //旋转图片 动作
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        System.out.println("angle2=" + angle);
        // 创建新的图片
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizedBitmap;
    }
}
