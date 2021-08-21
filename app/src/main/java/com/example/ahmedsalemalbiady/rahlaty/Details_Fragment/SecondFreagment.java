package com.example.ahmedsalemalbiady.rahlaty.Details_Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.ahmedsalemalbiady.rahlaty.R;
import com.example.ahmedsalemalbiady.rahlaty.Register_new_user;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class SecondFreagment extends Fragment {
    private static final String TAG = "Tab1_Fragment";
    private String MY_PREFS_NAME="ACCOUNT_DATA";
    private String id,trvale_date,destination_city,duration_days,residence,bus_info,number_of_subscriber,trip_program,bus_pas_complete,trop_photo,price,percentage;
    private TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9,tv10,tv11,tv12,tv13,tv14,tv15,tv16,tv17;
    private ImageView Main_image;
    private Button book_now;
    RequestOptions option;
    private final String JSON_URL="http://www.ma7laty.com/rahlaty/API/subscribe/subscribe.php";
    private StringRequest request;
    private RequestQueue requestQueue;
    private ProgressDialog progress;
    private CoordinatorLayout coordinatorLayout;
    private Typeface tf;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_fragment, container, false);


        // Font path
        String fontPath = "simpo.ttf";


        // Loading Font Face
        tf = Typeface.createFromAsset(getResources().getAssets(), fontPath);

        //castring android component;
        //travel date
        tv1=(TextView)view.findViewById(R.id.body_days);
        //destination city
        tv2=(TextView)view.findViewById(R.id.body_days2);
        //days
        tv3=(TextView)view.findViewById(R.id.body_days3);
        //staying
        tv4=(TextView)view.findViewById(R.id.body_days4);
        //bus information
        tv5=(TextView)view.findViewById(R.id.body_days5);
        //trip program
        tv6=(TextView)view.findViewById(R.id.body_days6);
        //price
        tv7=(TextView)view.findViewById(R.id.body_days7);
        //percentage completion
        tv8=(TextView)view.findViewById(R.id.body_days8);
        //main city
        tv9=(TextView)view.findViewById(R.id.city_text);

        /********************************************/
        tv10=(TextView)view.findViewById(R.id.header_days);
        tv11=(TextView)view.findViewById(R.id.header_days2);
        tv12=(TextView)view.findViewById(R.id.header_days3);
        tv13=(TextView)view.findViewById(R.id.header_days4);
        tv14=(TextView)view.findViewById(R.id.header_days5);
        tv15=(TextView)view.findViewById(R.id.header_days6);
        tv16=(TextView)view.findViewById(R.id.header_days7);
        tv17=(TextView)view.findViewById(R.id.header_days8);


        //setting type face
        tv1.setTypeface(tf);
        tv2.setTypeface(tf);
        tv3.setTypeface(tf);
        tv4.setTypeface(tf);
        tv5.setTypeface(tf);
        tv6.setTypeface(tf);
        tv7.setTypeface(tf);
        tv8.setTypeface(tf);
        tv9.setTypeface(tf);
        tv10.setTypeface(tf);
        tv11.setTypeface(tf);
        tv12.setTypeface(tf);
        tv13.setTypeface(tf);
        tv14.setTypeface(tf);
        tv15.setTypeface(tf);
        tv16.setTypeface(tf);
        tv17.setTypeface(tf);

        coordinatorLayout=(CoordinatorLayout) view.findViewById(R.id.first_fragment);

        Main_image=(ImageView)view.findViewById(R.id.trip_photo1);
        book_now=(Button)view.findViewById(R.id.Book_now);

        try{
            //recieve data from intent
            id=getActivity().getIntent().getExtras().getString("trip_id");
            trvale_date=getActivity().getIntent().getExtras().getString("trvale_date");
            destination_city=getActivity().getIntent().getExtras().getString("destination_city");
            duration_days=getActivity().getIntent().getExtras().getString("duration_days");
            residence=getActivity().getIntent().getExtras().getString("residence");
            bus_info=getActivity().getIntent().getExtras().getString("bus_info");
            number_of_subscriber=getActivity().getIntent().getExtras().getString("number_of_subscriber");
            trip_program=getActivity().getIntent().getExtras().getString("trip_program");
            bus_pas_complete=getActivity().getIntent().getExtras().getString("bus_pas_complete");
            trop_photo=getActivity().getIntent().getExtras().getString("trop_photo");
            price=getActivity().getIntent().getExtras().getString("price");
            percentage=getActivity().getIntent().getExtras().getString("percentage");

            tv1.setText("Starting From: "+trvale_date);
            tv2.setText(destination_city);
            tv9.setText(destination_city);
            tv3.setText("Number Of Day For Trip: "+duration_days+" days");
            tv4.setText("The Staying Place: "+residence);
            tv5.setText(bus_info);
            tv6.setText(trip_program);
            tv7.setText(price+" L.E");
            tv8.setText("Trip Completion: "+percentage+"%");
            //request option for Gilde
            option=new RequestOptions().centerCrop().placeholder(R.drawable.logo1).error(R.drawable.logo1);

            //loading image from the internet and set it into imageview using Glide
            Glide.with(getContext()).load(trop_photo).apply(option).into(Main_image);

        }catch(Exception ex)
        {

        }


        //get user information
        SharedPreferences prefs = getActivity().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String user_name = prefs.getString("user_name", null);
        String password = prefs.getString("password", null);
        String phone_number1 = prefs.getString("phone_number", null);
        final String user_id = prefs.getString("user_id", null);

        if(getActivity().getIntent().getExtras().getString("trip_status").equals("2"))
        {
            book_now.setVisibility(View.GONE);
        }

        book_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(user_id==null||id==null)
                {
//                    Snackbar snackbar = Snackbar
//                            .make(coordinatorLayout, "You Must Register Before Booking.", Snackbar.LENGTH_LONG);
//                    snackbar.show();

                    Toast.makeText(getContext(),"You Must Register Or Sign Up Before Booking.",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(getContext(),Register_new_user.class);
                    startActivity(intent);

                }else{

                    //json request
                    jsonRequest();
                }


            }
        });



        return view;

    }


    private void jsonRequest() {


        progress = new ProgressDialog(getContext());
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

                        Snackbar snackbar = Snackbar
                                .make(coordinatorLayout, ""+jsonObject.getString("message"), Snackbar.LENGTH_LONG);
                        snackbar.show();
                        Toast.makeText(getContext(),""+jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                        progress.hide();
                    }else{
                        progress.hide();
                        Toast.makeText(getContext(),""+jsonObject.getString("message"),Toast.LENGTH_LONG).show();
//                        Toast.makeText(getContext(),"Please Try Again.",Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }




            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"error occurred",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //get user information
                SharedPreferences prefs = getActivity().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                String user_name = prefs.getString("user_name", null);
                String password = prefs.getString("password", null);
                String phone_number1 = prefs.getString("phone_number", null);
                String user_id = prefs.getString("user_id", null);

                HashMap<String, String> params = new HashMap<>();
                params.put("doAction", "subscribe");
                params.put("user_id", user_id);
                params.put("trip_id", id);



                return params;
            }
        };

        requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(request);
    }

}
