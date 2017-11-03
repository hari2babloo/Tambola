package com.a3x3conect.tambola;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResetPass extends AppCompatActivity {

    Button submit;
    TextView resettext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_pass);

        resettext = (TextView)findViewById(R.id.resettext);


        Typeface face = Typeface.createFromAsset(getAssets(),
                "fonts/segoeuil.ttf");
        resettext.setTypeface(face);

        submit = (Button)findViewById(R.id.resetpass);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResetPass.this,SigninForm.class);

                startActivity(intent);
            }
        });
    }
}
