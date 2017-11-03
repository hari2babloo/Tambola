package com.a3x3conect.tambola;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.a3x3conect.tambola.WalletTabs.PagerAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Wallet extends AppCompatActivity {


    public static final MediaType MEDIA_TYPE =
            MediaType.parse("application/json");


    TextView money;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wallet);

        money = (TextView)findViewById(R.id.money);



        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Purchases Games"));
        tabLayout.addTab(tabLayout.newTab().setText("Created Games"));
        tabLayout.addTab(tabLayout.newTab().setText("My History"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        GetBalance();

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void GetBalance() {
        final OkHttpClient client = new OkHttpClient();
        JSONObject postdata = new JSONObject();
        RequestBody body = RequestBody.create(MEDIA_TYPE,
                postdata.toString());
        final Request request = new Request.Builder()
                .url("http://game-dev.techmech.men:8080/api/wallet")
                .get()
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization","eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhYmNAYWJjLmNvbSIsImF1ZGllbmNlIjoid2ViIiwiY3JlYXRlZCI6MTUwOTM1NzkwOTcyOCwiZXhwIjoxNTA5OTYyNzA5fQ.cGCnOd7G_2ZfRX6jhhJpuSVqasi5h_83D7Fi97Y_mFXOMWNHu4_QjSIDv0SXe6NgRaXeLG9ZJvAA_AWtwquoAQ")                .build();
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
                Log.w("Response", mMessage);
                if (response.isSuccessful()){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONObject json = new JSONObject(mMessage);
                                String s = json.getJSONObject("data").getString("money");
                                Log.e("MOney",s);

                                money.setText("Rs  "+  s);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
//                            Toast.makeText(SigninForm.this, "Success", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                else {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Wallet.this, "Fail", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });
    }
    }



