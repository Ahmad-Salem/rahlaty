package com.example.ahmedsalemalbiady.rahlaty.Fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.*;
import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ahmedsalemalbiady.rahlaty.Adapters.List_Of_Trips_Main;
import com.example.ahmedsalemalbiady.rahlaty.Model.Trip_model;
import com.example.ahmedsalemalbiady.rahlaty.R;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Completed extends Fragment {
    private static final String TAG = "Tab1_Fragment";
    private Button tab3_button;
    private final String JSON_URL="http://www.ma7laty.com/rahlaty/API/main_screen/get_trips_info.php";
    private StringRequest request;
    private RequestQueue requestQueue;
    private List<Trip_model> ListTrips;
    private RecyclerView recyclerView;
    private GridLayoutManager lLayout;
    private String MY_PREFS_NAME="ACCOUNT_DATA";
    private Button Retry;
    private RelativeLayout loading_layout;
    private FrameLayout frameLayout;
    private ProgressBar progressBar_h;
    private Typeface tf;
    private TextView loading_tv;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.completed_fragemnt,container,false);


        // Font path
        String fontPath = "simpo.ttf";


        // Loading Font Face
        tf = Typeface.createFromAsset(getResources().getAssets(), fontPath);


        lLayout = new GridLayoutManager(getContext(), 2);
        //casting the recycle view
        recyclerView=view.findViewById(R.id.recycler_view);

        ListTrips=new ArrayList<>();

        int resId = R.anim.layout_animation_fall_down;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), resId);

        recyclerView.setLayoutAnimation(animation);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(lLayout);

        //casting loading layout
        loading_layout=(RelativeLayout)view.findViewById(R.id.loading_layout);
        progressBar_h=(ProgressBar) view.findViewById(R.id.progress_horz);
        Retry=(Button) view.findViewById(R.id.retry);
        loading_tv=(TextView) view.findViewById(R.id.loading_tv);
        progressBar_h.setMax(100);
        progressBar_h.setIndeterminate(true);

        Retry=(Button) view.findViewById(R.id.retry);

        //setting type face
        Retry.setTypeface(tf);
        loading_tv.setTypeface(tf);

        //calling the json request
        jsonRequest();


        Retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Check Your Internet Connection",Toast.LENGTH_LONG).show();
                jsonRequest();

            }
        });

        return view;
    }

    private void jsonRequest() {

        request=new StringRequest(Request.Method.POST,JSON_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = null;
                JSONObject jsonObject2 = null;
                try {
                    jsonObject = new JSONObject(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try{
                    for (int i = 0; i < response.length(); i++) {

                        jsonObject2 = jsonObject.getJSONObject(i + "");

                        Trip_model trip = new Trip_model();
                        trip.setId(jsonObject2.getString("trip_id"));
                        trip.setTrvale_date(jsonObject2.getString("trip_travel_date"));
                        trip.setDestination_city(jsonObject2.getString("trip_destination_city"));
                        trip.setDuration_days(jsonObject2.getString("trip_duration_days"));
                        trip.setResidence(jsonObject2.getString("trip_residence"));
                        trip.setBus_info(jsonObject2.getString("trip_bus_info"));
                        trip.setNumber_of_subscriber(jsonObject2.getString("trip_number_of_subscriber"));
                        trip.setTrip_program(jsonObject2.getString("trip_trip_program"));
                        trip.setBus_pas_complete(jsonObject2.getString("trip_bus_pas_complete"));
                        trip.setPrice(jsonObject2.getString("trip_price"));
                        trip.setTrop_photo(jsonObject2.getString("trip_image_url"));
                        trip.setPhoto_1_value(jsonObject2.getString("trip_photo1"));
                        trip.setPhoto_2_value(jsonObject2.getString("trip_photo2"));
                        trip.setPhoto_3_value(jsonObject2.getString("trip_photo3"));
                        trip.setPhoto_4_value(jsonObject2.getString("trip_photo4"));
                        trip.setPhoto_5_value(jsonObject2.getString("trip_photo5"));
                        trip.setPhoto_6_value(jsonObject2.getString("trip_photo6"));
                        trip.setPhoto_title_1_value(jsonObject2.getString("trip_photo_title1"));
                        trip.setPhoto_title_2_value(jsonObject2.getString("trip_photo_title2"));
                        trip.setPhoto_title_3_value(jsonObject2.getString("trip_photo_title3"));
                        trip.setPhoto_title_4_value(jsonObject2.getString("trip_photo_title4"));
                        trip.setPhoto_title_5_value(jsonObject2.getString("trip_photo_title5"));
                        trip.setPhoto_title_6_value(jsonObject2.getString("trip_photo_title6"));
                        trip.setTrip_status(jsonObject2.getString("trip_status"));

                        Log.i("ahmed_salem_ali", jsonObject2.getString("trip_id"));

                        ListTrips.add(trip);
                    }

                    loading_layout.setVisibility(View.GONE);
                    progressBar_h.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }catch (JSONException e)
                {
                    loading_layout.setVisibility(View.GONE);
                    progressBar_h.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
//                    Toast.makeText(getApplicationContext(),"error occurred",Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }


                SettingUpRecycleView(ListTrips);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getApplicationContext(),"error occurred",Toast.LENGTH_LONG).show();

                loading_layout.setVisibility(View.VISIBLE);
                progressBar_h.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("doAction", "get_trip_info");
                params.put("trip_status", "2");


                return params;
            }
        };

        requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(request);
    }




    private void SettingUpRecycleView(List<Trip_model> TripList) {
        List_Of_Trips_Main mRecycleViewAdapter=new List_Of_Trips_Main(getContext(),TripList);
        recyclerView.setLayoutManager(lLayout);
        recyclerView.setAdapter(mRecycleViewAdapter);
    }
}
