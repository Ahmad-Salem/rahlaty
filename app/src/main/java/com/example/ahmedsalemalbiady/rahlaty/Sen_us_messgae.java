package com.example.ahmedsalemalbiady.rahlaty;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Sen_us_messgae extends AppCompatActivity {
    private String MY_PREFS_NAME="ACCOUNT_DATA";
    private EditText messgae;
    private Button send_message;
    private TextView Toolbar_title,Cvtitle;
    private final String JSON_URL="http://www.ma7laty.com/rahlaty/API/message/message.php";
    private StringRequest request;
    private RequestQueue requestQueue;
    private ProgressDialog progress;
    private Typeface tf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sen_us_messgae);

        // Font path
        String fontPath = "simpo.ttf";


        // Loading Font Face
        tf = Typeface.createFromAsset(getResources().getAssets(), fontPath);


        Toolbar toolbar_actual = (Toolbar)findViewById(R.id.Message_us_id);
        setSupportActionBar(toolbar_actual);


        // for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_chevron_left_black_24dp);

        // casting android component
        messgae=(EditText)findViewById(R.id.message_body);
        send_message=(Button) findViewById(R.id.send_btn);
        Toolbar_title=(TextView) findViewById(R.id.toolbar_title_reg_add_offer_22);
        Cvtitle=(TextView) findViewById(R.id.Title_messgae);

        //setting font
        messgae.setTypeface(tf);
        send_message.setTypeface(tf);
        Toolbar_title.setTypeface(tf);
        Cvtitle.setTypeface(tf);


        send_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(messgae.getText().toString()!=null)
                {
                    //json requsets
                    jsonRequest();
                }else
                    {
                        Toast.makeText(getApplicationContext(),"Enter Your Message Please",Toast.LENGTH_LONG).show();
                    }
            }
        });
    }



    private void jsonRequest() {


        progress = new ProgressDialog(this);
        progress.getWindow().setGravity(Gravity.RIGHT);
        progress.setMessage("Loading ....");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.show();




        request=new StringRequest(Request.Method.POST,JSON_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response);
//                    Toast.makeText(getApplicationContext(),""+jsonObject.getString("add_shop_flag"),Toast.LENGTH_LONG).show();
                    if(response!=null)
                    {
//                        Toast.makeText(getApplicationContext(),"response is not null",Toast.LENGTH_LONG).show();
                        Log.i("jsonopopop",jsonObject+"");
                    }
                    if(jsonObject.getString("reg_flag").equals("1"))
                    {
                        Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                        progress.hide();
                    }else{
                        progress.hide();
                        Toast.makeText(getApplicationContext(),""+jsonObject.getString("message"),Toast.LENGTH_LONG).show();
//                        Toast.makeText(getApplicationContext(),"Please Try Again.",Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }




            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"error occurred",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                //get user information
                SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);

                String user_id = prefs.getString("user_id", null);


                HashMap<String, String> params = new HashMap<>();
                params.put("doAction", "messgage");
                params.put("user_id", user_id);
                params.put("message", messgae.getText().toString());



                return params;
            }
        };

        requestQueue= Volley.newRequestQueue(Sen_us_messgae.this);
        requestQueue.add(request);
    }


}
