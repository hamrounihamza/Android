package com.example.tpjson;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listview;
    Button btn_tel;

    String msg = null;
    ArrayList<Adresse> data = new ArrayList<Adresse>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = findViewById(R.id.listview);
        btn_tel =findViewById(R.id.btn_telecharger);

        btn_tel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Telechargement t = new Telechargement(MainActivity.this);
                t.execute();
            }
        });

        };


    class Telechargement extends AsyncTask {

        Context con;
        Telechargement(Context con)
        {
            this.con=con;

        }

        AlertDialog alert;
        @Override
        protected void onPreExecute() {
            AlertDialog.Builder builder = new AlertDialog.Builder(con);
            builder.setTitle("Telechargement");
            builder.setMessage("Veuillez patientez");
            alert = builder.create();
            alert.show();
        }


        @Override
        protected Object doInBackground(Object[] objects) {//méthode run : 2éme thread
            /**
             * avd : 10.0.2.2
             * genymotion : 10.0.2.3
             * LAN : ip v4 : 192.168.1.9
             * Internet : www.nomsite.com
             *
             * */

            String ip="192.168.1.9";
            String url ="https://"+ip+"/servicephp/get_all_adresses.php";
            JSONObject result=JSONParser.makeRequest(url);

            data.clear();

            try {
                int s=result.getInt("success");
                if (s==0)
                    msg = result.getString("message");
                else {
                    JSONArray tab = result.getJSONArray("adresse");
                    //convertir json array to array list

                    for (int i=0;i<tab.length();i++)
                    {
                        JSONObject ligne = tab.getJSONObject(i);
                        int id =ligne.getInt("IdAdresse");
                        String lon =ligne.getString("Longitude");
                        String lat =ligne.getString("Latitude");
                        String type=ligne.getString("Type");

                        data.add(new Adresse(id,lon,lat,type));

                    }

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            alert.dismiss();
        }

    }
}


