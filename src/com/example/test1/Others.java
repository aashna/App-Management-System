package com.example.test1;



import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseInstallation;
import com.parse.PushService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class Others extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		Parse.initialize(this, "PoEIYYbeTw8bPNw1TkdzxF5uliAsbgHYxtHaWSbI", "YFuYFOXq6iIa0auoE8Hj4Bx0VdyMgKOScPBhjLJH"); 
		PushService.setDefaultPushCallback(this, Others.class);
		ParseInstallation.getCurrentInstallation().saveInBackground();
		ParseAnalytics.trackAppOpened(getIntent());
		
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.back_image);
		Thread timer=new Thread(){
			
			public void run(){
			
				try{
					sleep(3000);
					
				}catch(InterruptedException e){
					e.printStackTrace();
				}finally{
					Intent openMainActivity=new Intent("com.example.test1.OPTIONS");
					startActivity(openMainActivity);
				}
			}
			
		};
		timer.start();	
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

}
