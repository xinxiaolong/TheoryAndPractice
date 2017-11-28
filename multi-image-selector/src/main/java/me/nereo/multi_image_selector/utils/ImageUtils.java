package me.nereo.multi_image_selector.utils;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;

/**
 * Created by xiaolong on 16/4/18.
 */
public class ImageUtils {

    /**
     * 跟新媒体库
     * @param context
     * @param fileUrl
     */
    public static void updateScanMedia(Context context, String fileUrl){
        MediaScannerConnection.scanFile(
                context,
                new String[]{fileUrl},
                null,
                new MediaScannerConnection.OnScanCompletedListener() {
                    @Override
                    public void onScanCompleted(String path, Uri uri) {

                    }
                }
        );
    }
}
