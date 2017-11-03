package com.a3x3conect.tambola;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class Testpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testpage);


        TextView tv = (TextView)findViewById(R.id.textView8);

        Typeface face = Typeface.createFromAsset(getAssets(),
                "fonts/segoeuil.ttf");

        tv.setTypeface(face);

//        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
//                .setDefaultFontPath("fonts/fonts.ttf")
//                .setFontAttrId(R.attr.fontPath)
//                .build()
//        );
    }
}
