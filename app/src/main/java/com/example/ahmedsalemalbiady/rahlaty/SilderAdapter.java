package com.example.ahmedsalemalbiady.rahlaty;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.example.ahmedsalemalbiady.rahlaty.startupScreens.StartUp;

/**
 * Created by Ahmad_Elbayadi on 10/01/2018.
 */

public class SilderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SilderAdapter(Context context) {
        this.context = context;
    }

    // Arrays
    public int[] slide_images=
            {
              R.drawable.slider_quote,
              R.drawable.slider_quote2,
              R.drawable.slider_quote3
            };
    public int[] backgrounds=
            {
                    R.drawable.slider_background,
                    R.drawable.slider_bg2,
                    R.drawable.slider_bg3
            };

    @Override
    public int getCount() {
        return slide_images.length;
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==(RelativeLayout) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.slide_layout,container,false);

        //casting silder component
        ImageView SildeImageView=(ImageView) view.findViewById(R.id.icon);
        ImageView SildeImageView_background=(ImageView) view.findViewById(R.id.backgrounds);
        //Button get_started=(Button) view.findViewById(R.id.get_started1_btn);

        //setting android view components
        SildeImageView.setImageResource(slide_images[position]);
        SildeImageView_background.setImageResource(backgrounds[position]);
/*
        get_started.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,MainActivity.class);
                context.startActivity(intent);
            }
        });
*/
        container.addView(view);
        return view;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}
