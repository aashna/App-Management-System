package com.example.test1;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Options extends Activity implements View.OnClickListener{

	    HttpPost httppost;
	    StringBuffer buffer;
	    HttpResponse response;
	    HttpClient httpclient;
	    List<NameValuePair> nameValuePairs; 	    
	    Button bdone;
		CheckBox cam,mus,em,main;
		TextView tv;		 
		 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.options);
		
		cam=(CheckBox)findViewById(R.id.checkBox1);
		 mus=(CheckBox)findViewById(R.id.checkBox2);
		 em=(CheckBox)findViewById(R.id.checkBox3);
		 main=(CheckBox)findViewById(R.id.checkBox4);		
		 bdone=(Button)findViewById(R.id.button1);
		 tv=(TextView)findViewById(R.id.textView1);
		 //bdone.setOnClickListener(this);	
		 cam.setOnClickListener(this);
		 em.setOnClickListener(this);
		 mus.setOnClickListener(this);
		 main.setOnClickListener(this);
		
		
		 
		 bdone.setOnClickListener(new OnClickListener() {
	          @Override
	          public void onClick(View v) {
	               
	              final ProgressDialog p = new ProgressDialog(v.getContext()).show(v.getContext(),"Waiting for Server", "Accessing Server");
	              Thread thread = new Thread()
	              {
	                  @Override
	                  public void run() {
	                       try{
	                    	   
	                    	   StringBuffer result_cam = new StringBuffer();
	                    	   StringBuffer result_music = new StringBuffer();
	                    	   StringBuffer result_email = new StringBuffer();
	                    	   StringBuffer result_main = new StringBuffer();
	               			   result_cam.append(cam.isChecked());
	               			   result_music.append(mus.isChecked());
	               			   result_email.append(em.isChecked());
	               			   result_main.append(main.isChecked());
	          
	               		       StringBuffer result = new StringBuffer();
	               		       result=result_cam.append(result_music).append(result_email).append(result_main);	               			
	                           
	                           httpclient=new DefaultHttpClient();
	                           httppost= new HttpPost("http://10.0.2.2/connections.php"); // make sure the url is correct.
	                           //add your data
	                           nameValuePairs = new ArrayList<NameValuePair>(1);
	                           // Always use the same variable name for posting i.e the android side variable name and php side variable name should be similar,
	                           nameValuePairs.add(new BasicNameValuePair("value",result.toString().trim()));  // $Edittext_value = $_POST['Edittext_value'];
	                           httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	                           //Execute HTTP Post Request
	                           response=httpclient.execute(httppost);
	                            
	                           ResponseHandler<String> responseHandler = new BasicResponseHandler();
	                           final String response = httpclient.execute(httppost, responseHandler);
	                           System.out.println("Response : " + response);
	                           runOnUiThread(new Runnable()
	                           {
	                                  public void run() 
	                                  {
	                                      p.dismiss();
	                                      tv.setText("Response from PHP : " + response);
	                                  }
	                           });
	                           
	                         }
	                       catch(Exception e){	                            
	                                           runOnUiThread(new Runnable() 
	                                          {
	                                          public void run() 
	                                           {p.dismiss();}
	                               	                       
	                                            });
	                                          System.out.println("Exception : " + e.getMessage());
	                                         }
	                       
	                       finally{
	                    	   
	                    	   Intent openmenu=new Intent("com.example.test1.MENU");
	               			startActivity(openmenu);
	                       }
	                  }
	              };
	              thread.start();                 
	          }
	      });  
		 
		 
		
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
    switch(arg0.getId()){
	    
		case R.id.checkBox1:
			
			if (((CheckBox) arg0).isChecked()) {				
				
				Toast.makeText(Options.this,
			 	   "Camera :)", Toast.LENGTH_LONG).show();
			}
			break;
			
		case R.id.checkBox2:
	if (((CheckBox) arg0).isChecked()) {
				
				
				Toast.makeText(Options.this,
			 	   "Music :)", Toast.LENGTH_LONG).show();
			}
			break;
		case R.id.checkBox3:
	if (((CheckBox) arg0).isChecked()) {
				
				
				Toast.makeText(Options.this,
			 	   "Email :)", Toast.LENGTH_LONG).show();
			}
			
			break;
		case R.id.checkBox4:
	if (((CheckBox) arg0).isChecked()) {		
				
				Toast.makeText(Options.this,
			 	   "MainActivity :)", Toast.LENGTH_LONG).show();
			}
			break;
		/*case R.id.button1:
			CheckBox cam,mus,em,main;
			cam=(CheckBox)findViewById(R.id.checkBox1);
			 mus=(CheckBox)findViewById(R.id.checkBox2);
			 em=(CheckBox)findViewById(R.id.checkBox3);
			 main=(CheckBox)findViewById(R.id.checkBox4);
			StringBuffer result = new StringBuffer();
			result.append("Camera check : ").append(cam.isChecked());
			result.append("\nMusic check : ").append(mus.isChecked());
			result.append("\nEmail check :").append(em.isChecked());
			result.append("\nMain check :").append(main.isChecked());
			Toast.makeText(Options.this, result.toString(),
					Toast.LENGTH_LONG).show();
			break;	*/		
			
		
	}
		
	}
}



