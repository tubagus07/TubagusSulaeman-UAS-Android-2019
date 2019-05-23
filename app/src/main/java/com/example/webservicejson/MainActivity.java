package com.example.webservicejson;

import android.app.DownloadManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.koneksiwebservice.R;
import com.example.webservicejson.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView textHasilJSON;
    private RequestQueue mQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQueue = Volley.newRequestQueue(this);
        Button btnJson = findViewById(R.id.bt_1);

        bt_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uraiJSON();

            }
        });
    }
    private void uraiJSON(){
        String url = "http://192.168.5.56/jsonuas.json";
        JsonObjectRequest request = new JsonObjectRequest(GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("jsonuas");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonuas = jsonArray.getJSONObject(i);

                        String namaDepan = jsonuas.getString("id");
                        String namaBelakang = jsonuas.getString("nama");
                        String asalDaerah = jsonuas.getString("asal_daerah");
                        String kamar = jsonuas.getString("kamar");

                        textHasilJSON.append(namaDepan + "," + namaBelakang +"," + asalDaerah + "," + kamar + "\n\n");
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        });
        mQueue.add(request);

    }


    private class RequestQueue {
    }
}