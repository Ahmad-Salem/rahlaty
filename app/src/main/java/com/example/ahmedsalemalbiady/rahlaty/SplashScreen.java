package com.example.ahmedsalemalbiady.rahlaty;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    private int SPLASH_TIME_OUT=4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*linking javaclass to the xml*/
        setContentView(R.layout.activity_splash_screen);


        final Thread SplashScreen = new Thread(){
            @Override
            public void run() {
                try{
                    sleep(SPLASH_TIME_OUT);
//                    Intent i=new Intent(getApplicationContext(),MainActivity.class);
                    SharedPreferences user_setting=getSharedPreferences("uesr_setting", Context.MODE_PRIVATE);
                    final String first_check_sh=user_setting.getString("firsttime","");
                    if(first_check_sh.equals("1")){
                        Intent i=new Intent(getApplicationContext(),MainActivity.class);
//                        Intent i=new Intent(getApplicationContext(),startupscreens.class);
                        startActivity(i);
                    }else{
                        Intent i=new Intent(getApplicationContext(),OnBoard.class);
                        startActivity(i);
                    }


                    finish();
                }catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        };
        SplashScreen.start();

    }
}
