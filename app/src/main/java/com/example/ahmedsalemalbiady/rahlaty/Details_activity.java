package com.example.ahmedsalemalbiady.rahlaty;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.ahmedsalemalbiady.rahlaty.Adapters.MainTabSectionPageAdapter;
import com.example.ahmedsalemalbiady.rahlaty.Details_Fragment.SecondFreagment;
import com.example.ahmedsalemalbiady.rahlaty.Details_Fragment.FirstFragment;

public class Details_activity extends AppCompatActivity {

    private Typeface tf;
    private CoordinatorLayout coordinatorLayout;
    private TextView ToolbarTitle;
    private MainTabSectionPageAdapter mSectionPageAdapter;
    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_activity);

        // Font path
        String fontPath = "simpo.ttf";


        // Loading Font Face
        tf = Typeface.createFromAsset(getResources().getAssets(), fontPath);




        Toolbar toolbar_actual = (Toolbar)findViewById(R.id.activity_toolbar);
        setSupportActionBar(toolbar_actual);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.details);
        ToolbarTitle = (TextView) findViewById(R.id.toolbar_main_screen);


        //setting typeface
        ToolbarTitle.setTypeface(tf);

        mSectionPageAdapter=new MainTabSectionPageAdapter(getSupportFragmentManager());
        //setup  the ViewPager with the section Adapter
        mViewPager=(ViewPager)findViewById(R.id.container);
        setupViewPager(mViewPager);

        // for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_chevron_left_black_24dp);



        //create tabed layout
        final TabLayout tabLayout=(TabLayout)findViewById(R.id.tabs);
        tabLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.side_nav_bar));
        tabLayout.setTabTextColors(Color.parseColor("#ffffff"),Color.parseColor("#ffffff"));
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#ffffff"));
        tabLayout.setupWithViewPager(mViewPager);


        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    ((TextView) tabViewChild).setTypeface(tf);
                }
            }
        }



    }




    private void setupViewPager(ViewPager viewPager)
    {
        MainTabSectionPageAdapter adapter=new MainTabSectionPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new FirstFragment(),"Program");
        adapter.addFragment(new SecondFreagment(),"Details");
        viewPager.setAdapter(adapter);
    }




}
