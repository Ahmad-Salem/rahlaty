package com.example.ahmedsalemalbiady.rahlaty.Adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.ahmedsalemalbiady.rahlaty.Call_us;
import com.example.ahmedsalemalbiady.rahlaty.Details_activity;
import com.example.ahmedsalemalbiady.rahlaty.Model.Trip_model;
import com.example.ahmedsalemalbiady.rahlaty.R;
import com.example.ahmedsalemalbiady.rahlaty.Register_new_user;
import com.squareup.picasso.Picasso;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Ahmad_Elbayadi on 25/04/2018.
 */

public class List_Of_Trips_Main extends  RecyclerView.Adapter<List_Of_Trips_Main.MyViewHolder>{

    RequestOptions option;
    private final String JSON_URL="http://www.ma7laty.com/rahlaty/API/subscribe/subscribe.php";
    private StringRequest request;
    private RequestQueue requestQueue;
    private ProgressDialog progress;

    private Context mContext;
    private List<Trip_model> Trip_properties;
    private String MY_PREFS_NAME="ACCOUNT_DATA";

    public List_Of_Trips_Main(Context mContext, List<Trip_model> Trip_properties) {
        this.mContext = mContext;
        this.Trip_properties = Trip_properties;

        //request option for Gilde
        option=new RequestOptions().centerCrop().placeholder(R.drawable.logo1).error(R.drawable.logo1);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.city_destination.setText(Trip_properties.get(position).getDestination_city());
        int number_subscribers = Integer.parseInt(Trip_properties.get(position).getNumber_of_subscriber());
        int number_complete = Integer.parseInt(Trip_properties.get(position).getBus_pas_complete());
        int percent=0;
        if(number_complete==0)
        {
            percent=0;
        }else
        {
            percent=(number_subscribers/number_complete)*100;

        }
        holder.precent_complete.setText(number_complete+"%");
        holder.date.setText(Trip_properties.get(position).getTrvale_date());
        holder.days.setText(Trip_properties.get(position).getDuration_days()+" Days");
        holder.price.setText(Trip_properties.get(position).getPrice()+" L.E");



        //loading image from the internet and set it into imageview using Glide
        Glide.with(mContext).load(Trip_properties.get(position).getTrop_photo()).apply(option).into(holder.trip_photo);
//        Picasso.get()
//                .load(Trip_properties.get(position).getTrop_photo())
//                .placeholder(R.drawable.logo1)
//                .error(R.drawable.logo1)
//                .into(holder.trip_photo);
    }


    @Override
    public int getItemCount() {
        return  Trip_properties.size();
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.main_card_view_list, parent, false);
        final MyViewHolder viewHolder = new MyViewHolder(view);

        SharedPreferences prefs = mContext.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);

        String user_name = prefs.getString("user_name", null);
        String password = prefs.getString("password", null);
        String phone_number1 = prefs.getString("phone_number", null);
        final String user_id = prefs.getString("user_id", null);
//        Log.d("ahmedsalem123456",Trip_properties.get(viewHolder.getAdapterPosition()).getTrip_status());
        try{

            /***********************************************************************/

//            if(Trip_properties.get(viewHolder.getAdapterPosition()).getTrip_status().equals("2"))
//            {
//                Intent intent=new Intent(mContext.getApplicationContext(),Call_us.class);
//                mContext.startActivity(intent);
//                Toast.makeText(mContext,"Trip Completed !!",Toast.LENGTH_LONG).show();
//
//
//            }else{
//
//
//
//
//
//            }

            /***********************************************************************/


            /**********************************************************************/
            viewHolder.rv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(user_id==null||Trip_properties.get(viewHolder.getAdapterPosition()).getId()==null)
                    {

//                        Toast.makeText(mContext.getApplicationContext(),"You Must Register Or Sign Up Before Booking.",Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(mContext.getApplicationContext(),Call_us.class);
                        mContext.startActivity(intent);

                    }else{

                        //json request
//                        jsonRequest(Trip_properties.get(viewHolder.getAdapterPosition()).getId());
                        Intent intent=new Intent(mContext.getApplicationContext(),Call_us.class);
                        mContext.startActivity(intent);
                    }
                }
            });
            /**********************************************************************/




        }catch (Exception ex)
        {
            viewHolder.rv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(user_id==null||Trip_properties.get(viewHolder.getAdapterPosition()).getId()==null)
                    {

                        Toast.makeText(mContext.getApplicationContext(),"You Must Register Or Sign Up Before Booking.",Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(mContext.getApplicationContext(),Register_new_user.class);
                        mContext.startActivity(intent);

                    }else{

                        //json request
                        jsonRequest(Trip_properties.get(viewHolder.getAdapterPosition()).getId());
                    }
                }
            });
        }

        //on click  listener for the list
        viewHolder.View_trip_info_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(mContext, Details_activity.class);
                i.putExtra("trip_id",Trip_properties.get(viewHolder.getAdapterPosition()).getId());
                i.putExtra("trvale_date",Trip_properties.get(viewHolder.getAdapterPosition()).getTrvale_date());
                i.putExtra("destination_city",Trip_properties.get(viewHolder.getAdapterPosition()).getDestination_city());
                i.putExtra("duration_days",Trip_properties.get(viewHolder.getAdapterPosition()).getDuration_days());
                i.putExtra("residence",Trip_properties.get(viewHolder.getAdapterPosition()).getResidence());
                i.putExtra("bus_info",Trip_properties.get(viewHolder.getAdapterPosition()).getBus_info());
                i.putExtra("number_of_subscriber",Trip_properties.get(viewHolder.getAdapterPosition()).getNumber_of_subscriber());
                i.putExtra("trip_program",Trip_properties.get(viewHolder.getAdapterPosition()).getTrip_program());
                i.putExtra("bus_pas_complete",Trip_properties.get(viewHolder.getAdapterPosition()).getBus_pas_complete());
                i.putExtra("trop_photo",Trip_properties.get(viewHolder.getAdapterPosition()).getTrop_photo());
                i.putExtra("price",Trip_properties.get(viewHolder.getAdapterPosition()).getPrice());
//                photo_1_value,photo_2_value,photo_3_value,photo_4_value,photo_5_value,photo_6_value,photo_title_1_value,photo_title_2_value,photo_title_3_value,photo_title_4_value,photo_title_5_value,photo_title_6_value
                i.putExtra("photo_1_value",Trip_properties.get(viewHolder.getAdapterPosition()).getPhoto_1_value());
                i.putExtra("photo_2_value",Trip_properties.get(viewHolder.getAdapterPosition()).getPhoto_2_value());
                i.putExtra("photo_3_value",Trip_properties.get(viewHolder.getAdapterPosition()).getPhoto_3_value());
                i.putExtra("photo_4_value",Trip_properties.get(viewHolder.getAdapterPosition()).getPhoto_4_value());
                i.putExtra("photo_5_value",Trip_properties.get(viewHolder.getAdapterPosition()).getPhoto_5_value());
                i.putExtra("photo_6_value",Trip_properties.get(viewHolder.getAdapterPosition()).getPhoto_6_value());
                i.putExtra("photo_title_1_value",Trip_properties.get(viewHolder.getAdapterPosition()).getPhoto_title_1_value());
                i.putExtra("photo_title_2_value",Trip_properties.get(viewHolder.getAdapterPosition()).getPhoto_title_2_value());
                i.putExtra("photo_title_3_value",Trip_properties.get(viewHolder.getAdapterPosition()).getPhoto_title_3_value());
                i.putExtra("photo_title_4_value",Trip_properties.get(viewHolder.getAdapterPosition()).getPhoto_title_4_value());
                i.putExtra("photo_title_5_value",Trip_properties.get(viewHolder.getAdapterPosition()).getPhoto_title_5_value());
                i.putExtra("photo_title_6_value",Trip_properties.get(viewHolder.getAdapterPosition()).getPhoto_title_6_value());
                i.putExtra("trip_status",Trip_properties.get(viewHolder.getAdapterPosition()).getTrip_status());




                int number_subscribers = Integer.parseInt(Trip_properties.get(viewHolder.getAdapterPosition()).getNumber_of_subscriber());
                int number_complete = Integer.parseInt(Trip_properties.get(viewHolder.getAdapterPosition()).getBus_pas_complete());
                int percent=0;
                if(number_complete==0)
                {
                    percent=0;
                }else
                {
                    percent=(number_subscribers/number_complete)*100;

                }

                i.putExtra("percentage",number_complete+"");


                mContext.startActivity(i);
            }
        });



        return viewHolder;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView city_destination;
        TextView precent_complete;
        TextView date;
        TextView days;
        TextView price;
        ImageView trip_photo;
        RelativeLayout View_trip_info_container;
        RelativeLayout rv;

        public MyViewHolder(View itemView) {
            super(itemView);


            // Font path
            String fontPath = "simpo.ttf";


            // Loading Font Face
            Typeface tf = Typeface.createFromAsset(itemView.getResources().getAssets(), fontPath);

            //casting android component in the adapter

            city_destination=itemView.findViewById(R.id.trip_city);
            precent_complete=itemView.findViewById(R.id.trip_compelte_percentage);
            date=itemView.findViewById(R.id.trip_date);
            days=itemView.findViewById(R.id.trip_days);
            price=itemView.findViewById(R.id.trip_price);
            trip_photo=itemView.findViewById(R.id.trip_photo);
            View_trip_info_container=itemView.findViewById(R.id.card_view_trip);
            rv=itemView.findViewById(R.id.book_now_outside);

            //setting type face
            city_destination.setTypeface(tf);
            precent_complete.setTypeface(tf);
            date.setTypeface(tf);
            days.setTypeface(tf);
            price.setTypeface(tf);




        }
    }



    private void jsonRequest(final String trip_id) {

        final String t_id=trip_id;
        progress = new ProgressDialog(mContext);
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


                        Toast.makeText(mContext.getApplicationContext(),""+jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                        progress.hide();
                    }else{
                        progress.hide();
                        Toast.makeText(mContext.getApplicationContext(),""+jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                        Toast.makeText(mContext.getApplicationContext(),"Please Try Again.",Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }




            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mContext.getApplicationContext(),"error occurred",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //get user information
                SharedPreferences prefs = mContext.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                String user_name = prefs.getString("user_name", null);
                String password = prefs.getString("password", null);
                String phone_number1 = prefs.getString("phone_number", null);
                String user_id = prefs.getString("user_id", null);

                HashMap<String, String> params = new HashMap<>();
                params.put("doAction", "subscribe");
                params.put("user_id", user_id);
                params.put("trip_id", t_id);



                return params;
            }
        };

        requestQueue= Volley.newRequestQueue(mContext.getApplicationContext());
        requestQueue.add(request);
    }

}
