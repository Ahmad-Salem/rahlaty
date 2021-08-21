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

public class Sign_up extends AppCompatActivity {

    private String MY_PREFS_NAME="ACCOUNT_DATA";
    private EditText user_name,password;
    private Button signUp_btn;
    private final String JSON_URL="http://www.ma7laty.com/rahlaty/API/users_registeration/login.php";
    private StringRequest request;
    private RequestQueue requestQueue;
    private ProgressDialog progress;
    private TextView Toolbar_title,Cvtitle;
    private Typeface tf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Font path
        String fontPath = "simpo.ttf";


        // Loading Font Face
        tf = Typeface.createFromAsset(getResources().getAssets(), fontPath);

        Toolbar toolbar_actual = (Toolbar)findViewById(R.id.sign_up_user_id);
        setSupportActionBar(toolbar_actual);


        // for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_chevron_left_black_24dp);


        //casting android component
        user_name=(EditText)findViewById(R.id.user_name_edit_text);
        password=(EditText)findViewById(R.id.password_edit_text);
        signUp_btn=(Button)findViewById(R.id.send_btn);
        Toolbar_title=(TextView) findViewById(R.id.toolbar_title_reg_add_offer_22);
        Cvtitle=(TextView)findViewById(R.id.Title_user);

        //setting type face
        user_name.setTypeface(tf);
        password.setTypeface(tf);
        signUp_btn.setTypeface(tf);
        Toolbar_title.setTypeface(tf);
        Cvtitle.setTypeface(tf);






        signUp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user_name.getText().toString()==null||password.getText().toString()==null)
                {
                    Toast.makeText(getApplicationContext(),"All Value Are Required.",Toast.LENGTH_LONG).show();
                }else{
                    //json request
                    jsonRequest();
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
                        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                        editor.putString("user_name", jsonObject.getString("user_unique_name"));
                        editor.putString("password", jsonObject.getString("user_password"));
                        editor.putString("phone_number", jsonObject.getString("user_phone_number"));
                        editor.putString("user_id", jsonObject.getString("user_id"));

                        editor.apply();
//                        intent to activation code activity
                        Intent activation=new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(activation);
                    }else{
                        progress.hide();
                        Toast.makeText(getApplicationContext(),""+jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(),"Please Try Again.",Toast.LENGTH_LONG).show();
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


                HashMap<String, String> params = new HashMap<>();
                params.put("doAction", "login");
                params.put("user_unique_name", user_name.getText().toString());
                params.put("user_password", password.getText().toString());



                return params;
            }
        };

        requestQueue= Volley.newRequestQueue(Sign_up.this);
        requestQueue.add(request);
    }
}
