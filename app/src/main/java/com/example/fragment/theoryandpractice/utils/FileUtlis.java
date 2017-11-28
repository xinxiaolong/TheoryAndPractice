package com.example.fragment.theoryandpractice.utils;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;

/**
 * Created by xiaolong on 16/4/18.
 */
public class FileUtlis {

    /**
     * 项目图片统一路径
     */
    public final static String IMG_PATH = getSDPath() + "WarehouseRobotTemp/";

    /**
     * 获取SD卡根目录
     *
     * @return
     */
    public static String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();
        }
        return sdDir.toString() + "/";
    }


    public static void copy(File source, File target) throws IOException {
        FileInputStream fis = new FileInputStream(source);
        FileOutputStream fos = new FileOutputStream(target);
        copy(fis, fos);
        fis.close();
        fos.close();
    }

    public static void copy(InputStream is, String file) {
        copy(is, new File(file));
    }
    public static void copy(InputStream is, File file) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            copy(is, fos);
            fos.close();
        } catch (Exception e) {
            Log.e(FileUtlis.class.getName(),e.getMessage());
        }
    }
    public static void copy(InputStream is, OutputStream os) {
        if (is == null || os == null) return ;
        try {
            byte[] bs = new byte[1024];
            int len;
            while((len = is.read(bs)) > 0){
                os.write(bs, 0, len);
            }
        } catch (Exception e) {
            Log.e(FileUtlis.class.getName(),e.getMessage());
        }
    }

}
