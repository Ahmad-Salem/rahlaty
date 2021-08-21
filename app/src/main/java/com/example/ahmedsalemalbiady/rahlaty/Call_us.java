package com.example.ahmedsalemalbiady.rahlaty;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Call_us extends AppCompatActivity {

    private Button tele1,tele2,tele3,tele4,tele5,tele6;
    private TextView toolbar_title,Cv_title;
    private Typeface tf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_us);

        // Font path
        String fontPath = "simpo.ttf";


        // Loading Font Face
        tf = Typeface.createFromAsset(getResources().getAssets(), fontPath);



        Toolbar toolbar_actual = (Toolbar)findViewById(R.id.call_us_id);
        setSupportActionBar(toolbar_actual);


        // for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_chevron_left_black_24dp);

        toolbar_title=(TextView) findViewById(R.id.toolbar_title_reg_add_offer_22);
        Cv_title=(TextView) findViewById(R.id.Title_call);
        tele1=(Button)findViewById(R.id.tele1);
        tele2=(Button)findViewById(R.id.tele2);
        tele3=(Button)findViewById(R.id.tele3);
        tele4=(Button)findViewById(R.id.tele4);

        //setting type face
        toolbar_title.setTypeface(tf);
        Cv_title.setTypeface(tf);
        tele1.setTypeface(tf);
        tele2.setTypeface(tf);
        tele3.setTypeface(tf);



        tele1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                dialIntent.setData(Uri.parse("tel:" + "01150800095"));
                if (dialIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(dialIntent);
                }else{
                    Toast.makeText(getApplicationContext(),"You Device Dosen't Support This Service",Toast.LENGTH_LONG).show();
                }
            }
        });
        tele2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                dialIntent.setData(Uri.parse("tel:" + "01015830651"));
                if (dialIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(dialIntent);
                }else{
                    Toast.makeText(getApplicationContext(),"You Device Dosen't Support This Service",Toast.LENGTH_LONG).show();
                }
            }
        });
        tele3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                dialIntent.setData(Uri.parse("tel:" + "01026286353d"));
                if (dialIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(dialIntent);
                }else{
                    Toast.makeText(getApplicationContext(),"You Device Dosen't Support This Service",Toast.LENGTH_LONG).show();
                }
            }
        });

        tele4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                dialIntent.setData(Uri.parse("tel:" + "01090838962"));
                if (dialIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(dialIntent);
                }else{
                    Toast.makeText(getApplicationContext(),"You Device Dosen't Support This Service",Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}
