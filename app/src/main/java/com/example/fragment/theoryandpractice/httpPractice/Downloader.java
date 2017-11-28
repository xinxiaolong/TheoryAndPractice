package com.example.fragment.theoryandpractice.httpPractice;

import android.os.Environment;
import android.os.Handler;
import android.os.Message;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by xiaolong.
 * on 2016-01-01 下午2:44.
 */
public class Downloader implements Runnable{

    private String saveFileUrl;
    private String downloadUrl;
    private String fileName="test.jpg";

    private DownloaderListenner downloaderListenner;

    public Downloader(){
        saveFileUrl= Environment.getExternalStorageDirectory() + "/"+fileName;
    }

    @Override
    public void run() {
        download(downloadUrl);
    }

    interface DownloaderListenner{
        void onStart();
        void onError(String msg);
        void onProcess(int totalLenght,int curLenght);
        void onComplete();
    }


     Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case -1:
                    downloaderListenner.onError(msg.obj.toString());
                    break;
                case 0:
                    downloaderListenner.onStart();
                    break;
                case 1:
                    downloaderListenner.onProcess(msg.arg1, msg.arg2);
                    break;
                case 2:
                    downloaderListenner.onComplete();
                    break;
            }
        }
    };

    public void toDownload(String downloadUrl,DownloaderListenner downloaderListenner){
        this.downloadUrl=downloadUrl;
        this.downloaderListenner=downloaderListenner;
        new Thread(this).start();
    }

    private void download(String downloadUrl){

        downloadStart();

        FileOutputStream fos=null;
        try {
            URL url=new URL(downloadUrl);
            HttpURLConnection connection=(HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");

            File file=new File(saveFileUrl);
            if(!file.exists()){
                file.createNewFile();
            }
            fos=new FileOutputStream(file);
            int responseCode=connection.getResponseCode();
            if(responseCode==200){
                InputStream inputStream=connection.getInputStream();
                byte[] buf = new byte[1024];
                int len=-1;
                while ((len=inputStream.read(buf)) != -1) {
                    //fos.write(buf);
                    fos.write(buf,0,len);
                    processDownload(connection.getContentLength(),(int)file.length());
                }
                inputStream.close();
                downlaodComplete();
            }

        } catch (Exception e) {
            e.printStackTrace();
            downloadError(e.getMessage());
        }finally {
            try {
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void downloadStart(){
        handler.sendEmptyMessage(0);
    }

    private void processDownload(int totalLenght,int curLenght){
        Message msg=new Message();
        msg.what=1;
        msg.arg1=totalLenght;
        msg.arg2=curLenght;
        handler.sendMessage(msg);
    }

    private void downloadError(String error){
        Message  errorMsg=new Message();
        errorMsg.what=-1;
        errorMsg.obj=error;
        handler.sendMessage(errorMsg);
    }
    private void downlaodComplete(){
        handler.sendEmptyMessage(2);
    }
}
