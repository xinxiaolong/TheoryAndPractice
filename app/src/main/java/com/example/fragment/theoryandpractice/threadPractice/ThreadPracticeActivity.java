package com.example.fragment.theoryandpractice.threadPractice;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.fragment.theoryandpractice.R;
import com.example.fragment.theoryandpractice.glidePractice.RequestManagerFragment;

import java.lang.ref.WeakReference;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by xiaolong.
 * on 2016-01-13 下午1:28.
 */

public class ThreadPracticeActivity extends Activity {

    public static final String TAG = ThreadPracticeActivity.class.getName();

    Looper threadLooper;
    @InjectView(R.id.content_view)
    LinearLayout contentView;
    FragmentTransaction transaction;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_layout);
        ButterKnife.inject(this);

        final RequestManagerFragment fragment=new RequestManagerFragment();
        final FragmentManager fragmentManager=getFragmentManager();


        transaction=fragmentManager.beginTransaction();
        transaction.add(R.id.content_view,new RequestManagerFragment(),"1");
        transaction.add(R.id.content_view,new RequestManagerFragment(),"2");
        transaction.commit();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                createThreadHandler(threadLooper);

                transaction=fragmentManager.beginTransaction();
                //transaction.hide(fragmentManager.findFragmentByTag("1"));
                transaction.detach(fragmentManager.findFragmentByTag("1"));
                transaction.commit();
            }
        }, 2000);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                transaction=fragmentManager.beginTransaction();
                //transaction.show(fragmentManager.findFragmentByTag("1"));
                transaction.attach(fragmentManager.findFragmentByTag("1"));
                transaction.commit();
            }
        }, 3000);



        Thread thread = new Thread(runnable);
        thread.setName("子线程");
        thread.start();


        thread = new Thread(runnable2);
        thread.setName("子线程2");
        thread.start();

        thread = new Thread(runnable3);
        thread.setName("子线程3");
        thread.start();

//        mainHandler1.sendEmptyMessage(0);
//        mainHandler2.sendEmptyMessage(0);


        AsyncTask asyncTask=new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {
                return null;
            }
        };

        MyHandler myHandler=new MyHandler(this);
        myHandler.sendEmptyMessage(0);
    }

    Handler mainHandler1=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            int i=0;
            while (i<10){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
                Log.e(TAG,"in the mainHandler1");
            }
        }
    };

    Handler mainHandler2=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            int i=0;
            while (i<10){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
                Log.e(TAG,"in the mainHandler2");
            }
        }
    };

    private void createThreadHandler(Looper threadLooper) {
        Handler handler = new Handler(threadLooper) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.w(TAG, Thread.currentThread().getName());
            }
        };
        handler.sendEmptyMessage(1);
    }


    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Looper.prepare();
            threadLooper = Looper.myLooper();
            Looper.loop();
        }
    };



    Runnable runnable2 = new Runnable() {
        @Override
        public void run() {
           while (true){
               try {
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               Log.w(TAG,Thread.currentThread().getName()+"在运行");
           }
        }
    };

    Handler handler;
    Runnable runnable3 = new Runnable() {
        @Override
        public void run() {
            //handler=new Handler();
        }
    };


    static class MyHandler extends  Handler {
        //静态内部类代替，并使用若引用持有DetailActivity
        private WeakReference<Activity> weakReference;
        public MyHandler(Activity activity) {
            this.weakReference = new WeakReference(activity);
        }

        public void handleMessage(android.os.Message msg) {
            int what = msg.what;
            Activity activity = weakReference.get();
            if (activity == null){
                return;
            }
            Log.w(TAG,"静态的Handler收到了消息");

        }
    };

}
