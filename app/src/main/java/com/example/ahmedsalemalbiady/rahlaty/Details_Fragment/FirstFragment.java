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

public class FirstFragment extends Fragment {
    private static final String TAG = "Tab1_Fragment";
    private TextView main_image_title,photo1_title,photo2_title,photo3_title,under_photo1_title,under_photo2_title,under_photo3_title;
    private ImageView Main_image_IV,img_photo1_title,img_photo2_title,img_photo3_title,img_under_photo1_title,img_under_photo2_title,img_under_photo3_title;
    private Typeface tf;
    private String id,Main_image_url,destination_city,photo_1_value,photo_2_value,photo_3_value,photo_4_value,photo_5_value,photo_6_value,photo_title_1_value,photo_title_2_value,photo_title_3_value,photo_title_4_value,photo_title_5_value,photo_title_6_value;
    RequestOptions option;
    private Button Book_now;
    private final String JSON_URL="http://www.ma7laty.com/rahlaty/API/subscribe/subscribe.php";
    private StringRequest request;
    private RequestQueue requestQueue;
    private ProgressDialog progress;
    private CoordinatorLayout coordinatorLayout;
    private String MY_PREFS_NAME="ACCOUNT_DATA";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_fragment, container, false);


        // Font path
        String fontPath = "simpo.ttf";


        // Loading Font Face
        tf = Typeface.createFromAsset(getResources().getAssets(), fontPath);

        //casting fragment component
        main_image_title=(TextView)view.findViewById(R.id.city_text);
        coordinatorLayout=(CoordinatorLayout) view.findViewById(R.id.first_fragment);
        photo1_title=(TextView)view.findViewById(R.id.trip_city1);
        photo2_title=(TextView)view.findViewById(R.id.trip_city2);
        photo3_title=(TextView)view.findViewById(R.id.trip_city3);
        under_photo1_title=(TextView)view.findViewById(R.id.under_trip_city1);
        under_photo2_title=(TextView)view.findViewById(R.id.under_trip_city2);
        under_photo3_title=(TextView)view.findViewById(R.id.under_trip_city3);
        img_photo1_title=(ImageView) view.findViewById(R.id.photo1);
        img_photo2_title=(ImageView) view.findViewById(R.id.photo2);
        img_photo3_title=(ImageView) view.findViewById(R.id.photo3);
        img_under_photo1_title=(ImageView) view.findViewById(R.id.under_photo1);
        img_under_photo2_title=(ImageView) view.findViewById(R.id.under_photo2);
        img_under_photo3_title=(ImageView) view.findViewById(R.id.under_photo3);
        Main_image_IV=(ImageView) view.findViewById(R.id.trip_photo1);
        Book_now=(Button) view.findViewById(R.id.book_now);

        //setting type face
        main_image_title.setTypeface(tf);
        photo1_title.setTypeface(tf);
        photo2_title.setTypeface(tf);
        photo3_title.setTypeface(tf);
        under_photo1_title.setTypeface(tf);
        under_photo2_title.setTypeface(tf);
        under_photo3_title.setTypeface(tf);
        Book_now.setTypeface(tf);

        try{
            //recieve data from intent
            id=getActivity().getIntent().getExtras().getString("trip_id");

            //recieve data from intent
            Main_image_url=getActivity().getIntent().getExtras().getString("trop_photo");
            destination_city=getActivity().getIntent().getExtras().getString("destination_city");
//            photo_1_value,photo_2_value,photo_3_value,photo_4_value,photo_5_value,photo_6_value,photo_title_1_value,photo_title_2_value,photo_title_3_value,photo_title_4_value,photo_title_5_value,photo_title_6_value
            photo_1_value=getActivity().getIntent().getExtras().getString("photo_1_value");
            photo_2_value=getActivity().getIntent().getExtras().getString("photo_2_value");
            photo_3_value=getActivity().getIntent().getExtras().getString("photo_3_value");
            photo_4_value=getActivity().getIntent().getExtras().getString("photo_4_value");
            photo_5_value=getActivity().getIntent().getExtras().getString("photo_5_value");
            photo_6_value=getActivity().getIntent().getExtras().getString("photo_6_value");
            photo_title_1_value=getActivity().getIntent().getExtras().getString("photo_title_1_value");
            photo_title_2_value=getActivity().getIntent().getExtras().getString("photo_title_2_value");
            photo_title_3_value=getActivity().getIntent().getExtras().getString("photo_title_3_value");
            photo_title_4_value=getActivity().getIntent().getExtras().getString("photo_title_4_value");
            photo_title_5_value=getActivity().getIntent().getExtras().getString("photo_title_5_value");
            photo_title_6_value=getActivity().getIntent().getExtras().getString("photo_title_6_value");


            //request option for Gilde
            option=new RequestOptions().centerCrop().placeholder(R.drawable.logo1).error(R.drawable.logo1);

            main_image_title.setText(destination_city);






            photo1_title.setText(photo_title_1_value);
            photo2_title.setText(photo_title_2_value);
            photo3_title.setText(photo_title_3_value);
            under_photo1_title.setText(photo_title_4_value);
            under_photo2_title.setText(photo_title_5_value);
            under_photo3_title.setText(photo_title_6_value);
            //loading image from the internet and set it into imageview using Glide
            Glide.with(getContext()).load(Main_image_url).apply(option).into(Main_image_IV);

            Glide.with(getContext()).load(photo_1_value).apply(option).into(img_photo1_title);
            Glide.with(getContext()).load(photo_2_value).apply(option).into(img_photo2_title);
            Glide.with(getContext()).load(photo_3_value).apply(option).into(img_photo3_title);
            Glide.with(getContext()).load(photo_4_value).apply(option).into(img_under_photo1_title);
            Glide.with(getContext()).load(photo_5_value).apply(option).into(img_under_photo2_title);
            Glide.with(getContext()).load(photo_6_value).apply(option).into(img_under_photo3_title);
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
            Book_now.setVisibility(View.GONE);
        }
        Book_now.setOnClickListener(new View.OnClickListener() {
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
