package com.example.ahmedsalemalbiady.rahlaty;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class OnBoard extends AppCompatActivity {
    private ViewPager mySliderViewPager;
    private LinearLayout mDotLayout;
    private SilderAdapter silderAdapter;
    private Button GetStarted;
    private TextView[] mDots;
    private int currentPage;
    private Typeface tf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board);

        //casting android component
        mySliderViewPager=(ViewPager) findViewById(R.id.sliderviewpage);
        mDotLayout=(LinearLayout)findViewById(R.id.dotslayout);
        GetStarted=(Button)findViewById(R.id.get_started1_btn);

        silderAdapter=new SilderAdapter(this);
        mySliderViewPager.setAdapter(silderAdapter);

        // Font path
        String fontPath = "Harabara.ttf";


        // Loading Font Face
        tf = Typeface.createFromAsset(getResources().getAssets(), fontPath);

        //setting typeface
        GetStarted.setTypeface(tf);

        //onclick get strarted
        GetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent get_start=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(get_start);
            }
        });

        //add indicators
        addDotsIndicators(0);

        //caling the view pager listener
        mySliderViewPager.addOnPageChangeListener(viewListener);



    }



    public void addDotsIndicators( int pos)
    {
        mDots=new TextView[3];
        mDotLayout.removeAllViews();
        for(int i=0; i<mDots.length; i++)
        {
            mDots[i]=new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(TypedValue.COMPLEX_UNIT_SP,80);
            mDots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));

            mDotLayout.addView(mDots[i]);
        }

        if(mDots.length > 0 )
        {
            mDots[pos].setTextColor(getResources().getColor(R.color.colorWhite));
        }
    }


    //view pager handler
    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            //active indicator
            addDotsIndicators(position);
            currentPage=position;

            if(position == 0 )
            {


            }else if(position == mDots.length-1){

            }else if(position==1){

            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
