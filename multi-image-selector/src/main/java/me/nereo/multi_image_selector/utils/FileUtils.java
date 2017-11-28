package me.nereo.multi_image_selector.utils;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 文件操作类
 * Created by Nereo on 2015/4/8.
 */
public class FileUtils {

    public static File createTmpFile(Context context){

        String state = Environment.getExternalStorageState();
        if(state.equals(Environment.MEDIA_MOUNTED)){
            // 已挂载
            File pic = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA).format(new Date());
            String fileName = "multi_image_"+timeStamp+"";
            File tmpFile = new File(getCropPath(), fileName+".jpg");
            return tmpFile;
        }else{
            File cacheDir = context.getCacheDir();
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA).format(new Date());
            String fileName = "multi_image_"+timeStamp+"";
            File tmpFile = new File(cacheDir, fileName+".jpg");
            return tmpFile;
        }
    }

    /**
     * 社区图片保存路径
     *
     * @return
     */
    public static String getCropPath() {
        String fileDir = getBasePath() + "/DCIM/洋码头/";
        File file = new File(fileDir);
        if (!file.exists()) {
            mkdir(new File(fileDir));
        }
        return fileDir;
    }

    /**
     * 基础了路径
     *
     * @return
     */

    public static String getBasePath() {
        String BASE_PATH;
        String sdcardState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(sdcardState)) {
            BASE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
        } else {
            BASE_PATH ="";
        }
        return BASE_PATH;
    }

    public static void mkdir(File dir) {
        try {
            if (dir == null) return;
            if (!dir.exists()) {
                mkdir(dir.getParentFile());
                dir.mkdir();
            }
        } catch (Exception e) {
            String msg=e.getMessage();
        }
    }

}
