package com.example.test1;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;

import com.google.ads.AdRequest;
import com.google.ads.AdView;
import com.google.ads.e;

import android.app.ListActivity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Menu extends ListActivity{

	String classes[]={"Camera","Music","Email","MainActivity"};
	TextView label;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	    setListAdapter(new ArrayAdapter<String>(Menu.this,R.layout.menu,R.id.label,classes));
	    //label=(TextView)findViewById(R.id.label);
	   // label.setVisibility(TextView.GONE);    
	
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		
		super.onListItemClick(l, v,position , id);
		String pos=classes[position];
		
	
		try {
			if(pos=="Music")
			{				
				Class ourClass = Class.forName("com.example.test1.Music");
				Intent ourIntent=new Intent(Menu.this,ourClass);
				startActivity(ourIntent);				
				
				//File musicFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
				//MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.);
				//mediaPlayer.start();		
			}
			else{
			Class ourClass = Class.forName("com.example.test1."+pos);
			Intent ourIntent=new Intent(Menu.this,ourClass);
			startActivity(ourIntent);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	/*public View getView(ListView l, View v, int position, long id,View convertView){
		TextView textview=(TextView)super.getView(l,v,position,id,convertView);
		//String currentLocation=ListActivity.this.getResources().getColor(textColor));
		//int textColor=TextView.getText().toString().equals(currentLocation)?"#0000FF":"#FF0000";
		textview.setTextColor("#0000FF");
		return textview;}
	*/
	
	
	
	
}




