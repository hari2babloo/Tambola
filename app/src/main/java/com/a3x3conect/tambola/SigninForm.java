package com.a3x3conect.tambola;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

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

public class SigninForm extends AppCompatActivity {


    TextView  forgotpass,signup;
    SharedPreferences sp;


    AwesomeValidation awesomeValidation;

    EditText username,pass;
    Button login;
    public static final MediaType MEDIA_TYPE =
            MediaType.parse("application/json");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_form);

        sp=getSharedPreferences("login",MODE_PRIVATE);

        login =(Button)findViewById(R.id.submit);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        username = (EditText)findViewById(R.id.username);
        pass = (EditText)findViewById(R.id.pass);


        forgotpass = (TextView)findViewById(R.id.forgotpass);
        signup = (TextView)findViewById(R.id.signup);


        Typeface face = Typeface.createFromAsset(getAssets(),
                "fonts/segoeuil.ttf");

        String regexPassword = "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{8,}";
        awesomeValidation.addValidation(SigninForm.this,R.id.pass,regexPassword, R.string.errpass);
        awesomeValidation.addValidation(SigninForm.this,R.id.username,android.util.Patterns.EMAIL_ADDRESS,R.string.errfname);

        forgotpass.setTypeface(face);
        signup.setTypeface(face);





        logincheck();


        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(SigninForm.this,ForgotPass.class);
                startActivity(intent);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(SigninForm.this,SignupForm.class);
                startActivity(intent);
            }
        });



    }

    private void logincheck() {



        String pass = sp.getString("token",null);
        if (pass!=null && !pass.isEmpty()){

            Toast.makeText(this, "Welcome Back Gamer", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,HomeScreen.class));

        }

        else {

            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    if (awesomeValidation.validate()){
                        Authenticate();
                    }


                }
            });
            Toast.makeText(this, "Signin", Toast.LENGTH_SHORT).show();
        }

    }

    private void Authenticate() {


        final OkHttpClient client = new OkHttpClient();
        JSONObject postdata = new JSONObject();
        try {
            postdata.put("username", username.getText().toString());
            postdata.put("password", pass.getText().toString());
        } catch(JSONException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(MEDIA_TYPE,
                postdata.toString());


        final Request request = new Request.Builder()
                .url("http://game-dev.techmech.men:8080/auth")
                .post(body)
                .addHeader("Content-Type", "application/json")

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


                 Log.w("Response", mMessage);
                if (response.isSuccessful()){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            try {
                                JSONObject json = new JSONObject(mMessage);
                                String s = json.getJSONObject("data").getString("token");




                             //   Toast.makeText(SigninForm.this, s, Toast.LENGTH_SHORT).show();

                                SharedPreferences.Editor e = sp.edit();
                                e.putString("token",s);

                                e.commit();
                                Intent in = new Intent(SigninForm.this,HomeScreen.class);
                                startActivity(in);
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
                            Toast.makeText(SigninForm.this, "Fail", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });
    }
}
