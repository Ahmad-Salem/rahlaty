package com.example.ahmedsalemalbiady.rahlaty;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class AboutUs extends AppCompatActivity {
    private TextView ToolbarTitle,tv1,tv2,tv3,tv4,tv5;
    private Typeface tf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);


        // Font path
        String fontPath = "simpo.ttf";


        // Loading Font Face
        tf = Typeface.createFromAsset(getResources().getAssets(), fontPath);

        Toolbar toolbar_actual = (Toolbar)findViewById(R.id.activity_toolbar);
        setSupportActionBar(toolbar_actual);


        // for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_chevron_left_black_24dp);

        //casting android component
        ToolbarTitle=(TextView)findViewById(R.id.toolbar_main_screen);

        tv1=(TextView)findViewById(R.id.city_text);
        tv2=(TextView)findViewById(R.id.city_text2);
        tv3=(TextView)findViewById(R.id.city_text3);
        tv4=(TextView)findViewById(R.id.Title_user);
        tv5=(TextView)findViewById(R.id.Title_user_value);


        //setting typeface
        ToolbarTitle.setTypeface(tf);
        tv1.setTypeface(tf);
        tv2.setTypeface(tf);
        tv3.setTypeface(tf);
        tv4.setTypeface(tf);
        tv5.setTypeface(tf);

    }
}
