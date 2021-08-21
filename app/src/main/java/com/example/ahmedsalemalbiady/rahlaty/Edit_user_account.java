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

public class Edit_user_account extends AppCompatActivity {

    private String MY_PREFS_NAME="ACCOUNT_DATA";
    private EditText unique_user_name, user_password,confirm_user_password,phone_number;
    private Button edit_btn;
    private TextView Toolbar_title,Cvtitle;
    private final String JSON_URL="http://www.ma7laty.com/rahlaty/API/users_registeration/edit_user_account.php";
    private StringRequest request;
    private RequestQueue requestQueue;
    private ProgressDialog progress;
    private Typeface tf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_account);

        // Font path
        String fontPath = "simpo.ttf";


        // Loading Font Face
        tf = Typeface.createFromAsset(getResources().getAssets(), fontPath);

        Toolbar toolbar_actual = (Toolbar)findViewById(R.id.edit_user_id);
        setSupportActionBar(toolbar_actual);


        // for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_chevron_left_black_24dp);

        //casting android component
        // casting android component
        unique_user_name=(EditText)findViewById(R.id.user_name_edit_text);
        user_password=(EditText)findViewById(R.id.password_edit_text);
        confirm_user_password=(EditText)findViewById(R.id.confirm_password_edit_text);
        phone_number=(EditText)findViewById(R.id.phone_number_et);
        edit_btn=(Button)findViewById(R.id.send_btn);
        Toolbar_title=(TextView) findViewById(R.id.toolbar_title_reg_add_offer_22);
        Cvtitle=(TextView)findViewById(R.id.Title_user);


        //seting type face
        unique_user_name.setTypeface(tf);
        user_password.setTypeface(tf);
        confirm_user_password.setTypeface(tf);
        phone_number.setTypeface(tf);
        edit_btn.setTypeface(tf);
        Toolbar_title.setTypeface(tf);
        Cvtitle.setTypeface(tf);


        //get user information
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String user_name = prefs.getString("user_name", null);
        String password = prefs.getString("password", null);
        String phone_number1 = prefs.getString("phone_number", null);
        String user_id = prefs.getString("user_id", null);

        //setting values to edit texts
        unique_user_name.setText(""+user_name);
        user_password.setText(""+password);
        confirm_user_password.setText(""+password);
        phone_number.setText(""+phone_number1);

        edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(unique_user_name.getText().toString()==null||user_password.getText().toString()==null||confirm_user_password.getText().toString()==null||phone_number.getText().toString()==null){

                    Toast.makeText(getApplicationContext(),"All values Are Required.",Toast.LENGTH_LONG).show();
                }else if(!user_password.getText().toString().equals(confirm_user_password.getText().toString()))
                {
                    Toast.makeText(getApplicationContext(),"Passwords Doesn't Matched.",Toast.LENGTH_LONG).show();
                }else{
                    //jsonrequest
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
                        editor.putString("user_name", unique_user_name.getText().toString());
                        editor.putString("password", user_password.getText().toString());
                        editor.putString("phone_number", phone_number.getText().toString());

                        editor.apply();
//                        intent to activation code activity
                        Intent activation=new Intent(getApplicationContext(),user_profile.class);
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
                //get user information
                SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                String user_name = prefs.getString("user_name", null);
                String password = prefs.getString("password", null);
                String phone_number1 = prefs.getString("phone_number", null);
                String user_id = prefs.getString("user_id", null);

                HashMap<String, String> params = new HashMap<>();
                params.put("doAction", "edit_account");
                params.put("user_id", user_id);
                params.put("user_unique_name", user_name);
                params.put("user_password", password);
                params.put("user_phone_number", phone_number1);
                params.put("user_rank", "normal user");
                params.put("user_number_of_points", "1");


                return params;
            }
        };

        requestQueue= Volley.newRequestQueue(Edit_user_account.this);
        requestQueue.add(request);
    }
}
