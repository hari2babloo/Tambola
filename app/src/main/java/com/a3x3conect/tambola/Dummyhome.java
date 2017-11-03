package com.a3x3conect.tambola;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.a3x3conect.tambola.GameDetails.Datum;
import com.a3x3conect.tambola.GameDetails.Prize;
import com.a3x3conect.tambola.GameDetails.Tambola;
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;
import com.google.gson.Gson;
import com.google.gson.internal.Streams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Dummyhome extends AppCompatActivity {


    public static final MediaType MEDIA_TYPE =
            MediaType.parse("application/json");
    SharedPreferences sp;



    List<Datum> datt=new ArrayList<>();

    String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummyhome);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.horizontal_recycler_view);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(mRecyclerView);


        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));

        SnapHelper snapHelperStart = new GravitySnapHelper(Gravity.START);
        snapHelperStart.attachToRecyclerView(mRecyclerView);

        sp=getSharedPreferences("login",MODE_PRIVATE);


        token = sp.getString("token",null);

        getdata();


    }

    private void getdata() {
        final OkHttpClient client = new OkHttpClient();
        JSONObject postdata = new JSONObject();

                RequestBody body = RequestBody.create(MEDIA_TYPE,
                postdata.toString());


        final Request request = new Request.Builder()
                .url("http://game-dev.techmech.men:8080/api/game")
                .get()
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization","eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhYmNAYWJjLmNvbSIsImF1ZGllbmNlIjoid2ViIiwiY3JlYXRlZCI6MTUwOTM1NzkwOTcyOCwiZXhwIjoxNTA5OTYyNzA5fQ.cGCnOd7G_2ZfRX6jhhJpuSVqasi5h_83D7Fi97Y_mFXOMWNHu4_QjSIDv0SXe6NgRaXeLG9ZJvAA_AWtwquoAQ")
                .build();


        Log.e("dasdasd", body.toString());
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                String mMessage = e.getMessage().toString();
                Log.w("failure Response", mMessage);

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String mMessage = response.body().string();


//                Log.w("Response", mMessage);
                if (response.isSuccessful()){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            try {
                                JSONObject json = new JSONObject(mMessage);
//                                JSONObject s = json.getJSONObject("data");

                                JSONArray jsonArray = json.getJSONArray("data");





                                for (int i = 0; i < jsonArray.length(); i++) {

                                    Datum datum = new Datum();


                                   JSONObject json_data = jsonArray.getJSONObject(i);

                                   datum.setId(json_data.getInt("id"));

                                    datum.setName(json_data.getString("name"));




                                    Log.e("sdfdsf", datum.getName());

//                                    datum.setId(jsonobject.getInt("id"));

                                    datt.add(datum);
                                }

                                Log.w("Sdadas", String.valueOf(datt));
 //                               Prize prize = gson.fromJson(mMessage,Prize.class);
//                                Tambola tambola = gson.fromJson(mMessage,Tambola.class);

//                                Log.e("Datum",datum.getName());
//                                Log.e("Datum",prize.toString());
 //                               Log.e("Datum",tambola.toString());



                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    });

                }

                else {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Dummyhome.this, "Fail", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });


    }
}
