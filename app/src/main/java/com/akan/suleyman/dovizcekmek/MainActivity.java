package com.akan.suleyman.dovizcekmek;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    public void goster(View view){

        DowlandData dowlandData=new DowlandData();
        try {
            String url= "http://www.apilayer.net/api/live?access_key=089510b7ce4fc020786aed1282ea599a&format=1";
            dowlandData.execute(url);

        }catch(Exception e){

        }

    }
    private class DowlandData extends AsyncTask<String,Void,String>{



        @Override
        protected String doInBackground(String... strings) {

            String result="";
            URL url;
            HttpURLConnection httpURLConnection;
            try {
                url= new URL(strings[0]);
                httpURLConnection=(HttpURLConnection)url.openConnection();
                InputStream ınputStream=httpURLConnection.getInputStream(); //gelen baglantıyı okumak için
                InputStreamReader ınputStreamReader=new InputStreamReader(ınputStream);

                int data = ınputStreamReader.read();
                while (data>0){
                    char caracter=(char) data;
                    result += caracter;
                    data=ınputStreamReader.read();
                }

               return result;
            }catch(Exception e){
                return null;
            }


        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            TextView trl=(TextView)findViewById(R.id.txtTurk);
            try {
                JSONObject jsonObject= new JSONObject(s);
                String source= jsonObject.getString("source");

                String quotes= jsonObject.getString("quotes");
                JSONObject jsonObject1= new JSONObject(quotes);

                String türkLirasi= jsonObject1.getString("USDTRY");
                trl.setText("Türk Lirası: "+türkLirasi);
              //  System.out.println("Türk Lirası :"+türkLirasi);
            } catch (Exception e) {
               // e.printStackTrace();
            }


             //System.out.println("gelen data "+s);
        }
    }
}
