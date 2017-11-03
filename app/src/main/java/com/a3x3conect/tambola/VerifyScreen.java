package com.a3x3conect.tambola;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class VerifyScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verify_screen);
    }

    /**
     * Created by b on 26/10/17.
     */

    public static class Data {
        public int imageId;
        public String txt;

        Data(int imageId, String text) {

            this.imageId = imageId;
            this.txt = text;
        }

    }
}
