package com.a3x3conect.tambola;

import android.content.Intent;
import android.content.SharedPreferences;
import android.drm.DrmStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SignupForm extends AppCompatActivity {

    TextView login,fname,lname,email,phno,pass,cnfpass;
    AwesomeValidation awesomeValidation;
    Button submit;

    public static final MediaType MEDIA_TYPE =
            MediaType.parse("application/json");

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_form);



        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        submit = (Button)findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (awesomeValidation.validate()){

                   Signup();
//

                }

            }
        });



    login = (TextView)findViewById(R.id.loginlink);
        fname=(TextView)findViewById(R.id.fname);
        lname=(TextView)findViewById(R.id.lname);
        email=(TextView)findViewById(R.id.email);
        phno=(TextView)findViewById(R.id.phno);
        pass=(TextView)findViewById(R.id.pass);
        cnfpass=(TextView)findViewById(R.id.cnfpass);

        addValidationToViews();



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


//                Intent in = new Intent(getApplicationContext(),SigninForm.class);
//                startActivity(in);
            }
        });


    }



    private void Signup() {



        final OkHttpClient client = new OkHttpClient();

        JSONObject postdata = new JSONObject();
        JSONArray postdata2 = new JSONArray();
postdata2.put("ROLE_PLAYER_SILVER");


        try {

        postdata.put("email", email.getText().toString());
            postdata.put("password", pass.getText().toString());
            postdata.put("first_name", fname.getText().toString());
            postdata.put("last_name", lname.getText().toString());
            postdata.put("phone_no", phno.getText().toString());
            postdata.put("role",postdata2);


        } catch(JSONException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(MEDIA_TYPE,
                postdata.toString());


        final Request request = new Request.Builder()
                .url("http://game-dev.techmech.men:8080/api/user/signup")
                .post(body)

                .addHeader("Content-Type", "application/json")



                .build();


        Log.e("Value",postdata.toString());
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
                                Intent intent = new Intent(SignupForm.this,SigninForm.class);
                                startActivity(intent);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                            Toast.makeText(SignupForm.this, "Account Succesfully Created", Toast.LENGTH_SHORT).show();
                            Toast.makeText(SignupForm.this, "Now Please Login", Toast.LENGTH_SHORT).show();
                        }
                    });

                }

                else {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(SignupForm.this, "Email Already Exists", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });
    }

    private void addValidationToViews() {

        String regexPassword = "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{8,}";
        awesomeValidation.addValidation(SignupForm.this,R.id.fname, "[a-zA-Z\\s]+",R.string.errfname);
        awesomeValidation.addValidation(SignupForm.this,R.id.lname,"[a-zA-Z\\s]+",R.string.errlname);
        awesomeValidation.addValidation(SignupForm.this,R.id.email, Patterns.EMAIL_ADDRESS,R.string.erremail);
        awesomeValidation.addValidation(SignupForm.this,R.id.phno,RegexTemplate.TELEPHONE, R.string.errphne);
        awesomeValidation.addValidation(SignupForm.this,R.id.pass,regexPassword, R.string.errpass);
        awesomeValidation.addValidation(SignupForm.this,R.id.cnfpass,R.id.pass,R.string.errcnfpass);
    }
}
