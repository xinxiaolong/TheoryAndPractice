package com.example.fragment.theoryandpractice.notificationPractice;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class TestActivity extends Activity {

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		TextView text = new TextView(this);
		text.setText("activity");
		setContentView(text);

	}
}
