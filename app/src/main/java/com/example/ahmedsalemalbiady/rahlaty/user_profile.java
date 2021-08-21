package com.example.ahmedsalemalbiady.rahlaty;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class user_profile extends AppCompatActivity {

    private String MY_PREFS_NAME="ACCOUNT_DATA";
    private TextView user_name,password,phone_number,user_name_demo,password_demo,phone_number_demo,toolbar_title,cardview_title;
    private Button Edit_account,Logout;
    private Typeface tf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        // Font path
        String fontPath = "simpo.ttf";


        // Loading Font Face
        tf = Typeface.createFromAsset(getResources().getAssets(), fontPath);


        Toolbar toolbar_actual = (Toolbar)findViewById(R.id.profile_user_id);
        setSupportActionBar(toolbar_actual);


        // for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_chevron_left_black_24dp);

        //casting android component
        user_name=(TextView)findViewById(R.id.name_value);
        password=(TextView)findViewById(R.id.password_value);
        phone_number=(TextView)findViewById(R.id.phone_value);

        user_name_demo=(TextView)findViewById(R.id.name_label);
        password_demo=(TextView)findViewById(R.id.password_label);
        phone_number_demo=(TextView)findViewById(R.id.phone_label);
        toolbar_title=(TextView)findViewById(R.id.toolbar_title_reg_add_offer_22);
        cardview_title=(TextView)findViewById(R.id.Title_user);

        Edit_account=(Button)findViewById(R.id.Edit_btn);
        Logout=(Button)findViewById(R.id.delete_btn);


        //setting type face
        user_name.setTypeface(tf);
        password.setTypeface(tf);
        phone_number.setTypeface(tf);
        user_name_demo.setTypeface(tf);
        password_demo.setTypeface(tf);
        phone_number_demo.setTypeface(tf);
        toolbar_title.setTypeface(tf);
        cardview_title.setTypeface(tf);
        Edit_account.setTypeface(tf);
        Logout.setTypeface(tf);


        //get user information
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String user_name1 = prefs.getString("user_name", null);
        String password1 = prefs.getString("password", null);
        String phone_number1 = prefs.getString("phone_number", null);
        String user_id1 = prefs.getString("user_id", null);

        //setting the information
        user_name.setText(""+user_name1);
        password.setText(""+password1);
        phone_number.setText(""+phone_number1);

        //edit button
        Edit_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Edit_user_account.class);
                startActivity(intent);

            }
        });

        //logout
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //delete account
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putString("user_name", null);
                editor.putString("password", null);
                editor.putString("phone_number", null);
                editor.putString("user_id", null);
                editor.apply();

                //go to main screen
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
