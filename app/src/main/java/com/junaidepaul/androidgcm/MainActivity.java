package com.junaidepaul.androidgcm;

import android.util.Log;
import android.view.View;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.os.AsyncTask;
import android.view.View.OnClickListener;
import android.widget.TextView;
import java.io.IOException;
import com.google.android.gms.gcm.GoogleCloudMessaging;


public class MainActivity extends Activity{

    Button btnRegId;
    TextView idDisplay;
    GoogleCloudMessaging gcm;
    String regid;
    // Sender ID (Project No from Google console)
    String projectno = "447895585243";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRegId = (Button) findViewById(R.id.RegID);
        idDisplay = (TextView) findViewById(R.id.DisplayText);
        btnRegId.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                getRegID();

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void getRegID(){
        new AsyncTask<Void,Void,String>(){
            @Override
            protected String doInBackground(Void... params){
                String msg = "";
                try {
                    if (gcm == null){
                        gcm = GoogleCloudMessaging.getInstance(getApplicationContext());
                    }
                    regid = gcm.register(projectno);
                    msg = "Device registered : "+ regid;
                    Log.i("GCM", msg);
                }catch (IOException ex){
                    msg = "Error : "+ex.getMessage();

                }
                return msg;
            }
            @Override
            protected void onPostExecute(String msg){
                idDisplay.setText(msg + "\n");
            }


        }.execute(null, null, null);

    }
}
