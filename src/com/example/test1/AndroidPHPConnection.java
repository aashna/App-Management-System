package com.example.test1;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
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
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
 
public class AndroidPHPConnection extends Activity {
    Button b;
    EditText et;
    TextView tv;
    HttpPost httppost;
    StringBuffer buffer;
    HttpResponse response;
    HttpClient httpclient;
    List<NameValuePair> nameValuePairs;
  
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phpconnection);
  
        b = (Button)findViewById(R.id.Button01);
        et= (EditText)findViewById(R.id.EditText01);
        tv= (TextView)findViewById(R.id.tv);
  
        b.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                 
                final ProgressDialog p = new ProgressDialog(v.getContext()).show(v.getContext(),"Waiting for Server", "Accessing Server");
                Thread thread = new Thread()
                {
                    @Override
                    public void run() {
                         try{
                        	 
                             httpclient=new DefaultHttpClient();
                             httppost= new HttpPost("http://10.0.2.2/connections.php"); // make sure the url is correct.
                             //add your data
                             nameValuePairs = new ArrayList<NameValuePair>(1);
                             // Always use the same variable name for posting i.e the android side variable name and php side variable name should be similar,
                             nameValuePairs.add(new BasicNameValuePair("Edittext_value",et.getText().toString().trim()));  // $Edittext_value = $_POST['Edittext_value'];
                             httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                             //Execute HTTP Post Request
                             response=httpclient.execute(httppost);
                             HttpEntity httpEntity=response.getEntity();
                             InputStream is=httpEntity.getContent();
                             //BufferedReader reader=new BufferedReader(new InputStreamReader(is,"iso-B859-1"),B);
                             
                             ResponseHandler<String> responseHandler = new BasicResponseHandler();
                             final String response = httpclient.execute(httppost, responseHandler);
                             StringBuilder builder=new StringBuilder();
                             String line=null;
                             
                           /*  while((line=response)!=null)
                             {
                            	 builder.append(line+"\n");
                            	 
                             }
                             is.close();*/
                             //json=builder.toString();                           
                                            
                             System.out.println("Response : " + response);//192.168.102.158
                             runOnUiThread(new Runnable() {
                                    public void run() {
                                        p.dismiss();
                                         tv.setText("Response from PHP : " + response);
                                    }
                                });
                             
                         }catch(Exception e){
                              
                             runOnUiThread(new Runnable() {
                                public void run() {
                                    p.dismiss();
                                }
                            });
                             System.out.println("Exception : " + e.getMessage());
                         }
                    }
                };
 
                thread.start();
                 
                
            }
        });
    }
}