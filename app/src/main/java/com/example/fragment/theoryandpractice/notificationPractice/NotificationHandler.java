package com.example.fragment.theoryandpractice.notificationPractice;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.widget.Toast;

import com.example.fragment.theoryandpractice.R;

import java.util.Random;

public class NotificationHandler {


	private static NotificationHandler nHandler;
	private static NotificationManager mNotificationManager;


	private NotificationHandler () {}


	/**
	 * Singleton pattern implementation
	 * @return
	 */
	public static  NotificationHandler getInstance(Context context) {
		if(nHandler == null) {
			nHandler = new NotificationHandler();
			mNotificationManager =
					(NotificationManager) context.getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
		}

		return nHandler;
	}


	/**
	 * Shows a simple notification
	 * @param context aplication context
	 */
	public void createSimpleNotification(Context context) {
		// Creates an explicit intent for an Activity
		Intent resultIntent = new Intent(context, TestActivity.class);

		// Creating a artifical activity stack for the notification activity
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
		stackBuilder.addParentStack(TestActivity.class);
		stackBuilder.addNextIntent(resultIntent);

		// Pending intent to the notification manager
		PendingIntent resultPending = stackBuilder
				.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

		// Building the notification
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
				.setSmallIcon(R.drawable.ic_launcher) // notification icon
				.setContentTitle("I'm a simple notification") // main title of the notification
				.setContentText("I'm the text of the simple notification") // notification text
				.setContentIntent(resultPending); // notification intent

		// mId allows you to update the notification later on.
		mNotificationManager.notify(10, mBuilder.build());
	}


	public void createExpandableNotification (Context context) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
			// Building the expandable content
			NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
			String lorem ="我也不太知道这里面是啥东西";
			String [] content = lorem.split("\\.");

			inboxStyle.setBigContentTitle("This is a big title");
			for (String line : content) {
				inboxStyle.addLine(line);
			}

			// Building the notification
			NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(context)
					.setSmallIcon(R.drawable.ic_launcher) // notification icon
					.setContentTitle("Expandable notification") // title of notification
					.setContentText("This is an example of an expandable notification") // text inside the notification
					.setStyle(inboxStyle); // adds the expandable content to the notification

			mNotificationManager.notify(11, nBuilder.build());

		} else {
			Toast.makeText(context, "Can't show", Toast.LENGTH_LONG).show();
		}
	}


	/**
	 * 带有进度条的通知栏 我需要这个
	 *
	 */
	NotificationCompat.Builder nBuilder;
	public void createProgressNotification (final Context context) {
		final int progresID = new Random().nextInt(1000);
		// building the notification
		nBuilder = new NotificationCompat.Builder(context)
				.setSmallIcon(R.drawable.ic_upload)
				.setContentTitle("正在后台上传图片")
				.setContentText("请勿急躁")
				.setTicker("正在后台上传图片...")
				.setUsesChronometer(true)
				.setProgress(100, 0, true);


		AsyncTask<Integer, Integer, Integer> downloadTask = new AsyncTask<Integer, Integer, Integer>() {
			@Override
			protected void onPreExecute () {
				super.onPreExecute();
				mNotificationManager.notify(progresID, nBuilder.build());
			}

			@Override
			protected Integer doInBackground (Integer... params) {
				try {
					// Sleeps 2 seconds to show the undeterminated progress
					Thread.sleep(2000);
					// update the progress
					for (int i = 0; i < 101; i+=5) {
						nBuilder
							.setContentTitle("上传中...")
							.setContentText("当前进度...")
							.setProgress(100, i, false)
							.setSmallIcon(R.drawable.ic_upload)
							.setContentInfo(i + " %");
						// use the same id for update instead created another one
						mNotificationManager.notify(progresID, nBuilder.build());
						Thread.sleep(500);
					}

				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				return null;
			}


			@Override
			protected void onPostExecute (Integer integer) {
				super.onPostExecute(integer);
				nBuilder.setContentText("照片已经上传完成了")
						.setContentTitle("照片已经上传完成!")
						.setTicker("照片已经上传完成!")
						.setSmallIcon(R.drawable.ic_upload_complete)
						.setUsesChronometer(false);
				mNotificationManager.notify(progresID, nBuilder.build());
			}
		};
		// Executes the progress task
		downloadTask.execute();
	}


	public void createButtonNotification (Context context) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
			// Prepare intent which is triggered if the  notification button is pressed
			Intent intent = new Intent(context, TestActivity.class);
			PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent, 0);

			// Building the notifcation
			NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(context)
					.setSmallIcon(R.drawable.ic_launcher) // notification icon
					.setContentTitle("Button notification") // notification title
					.setContentText("Expand to show the buttons...") // content text
					.setTicker("Showing button notification") // status bar message
					.addAction(R.drawable.camara_icon, "Accept", pIntent) // accept notification button
					.addAction(R.drawable.close_icon, "Cancel", pIntent); // cancel notification button

			mNotificationManager.notify(1001, nBuilder.build());

		} else {
			Toast.makeText(context, "You need a higher version", Toast.LENGTH_LONG).show();
		}
	}
}
