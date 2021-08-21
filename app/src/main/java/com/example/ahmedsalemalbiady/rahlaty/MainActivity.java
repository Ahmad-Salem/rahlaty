package com.example.ahmedsalemalbiady.rahlaty;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.TypefaceSpan;
import android.util.Log;
import android.view.*;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ahmedsalemalbiady.rahlaty.Adapters.List_Of_Trips_Main;
import com.example.ahmedsalemalbiady.rahlaty.Adapters.MainTabSectionPageAdapter;
import com.example.ahmedsalemalbiady.rahlaty.Fragments.Completed;
import com.example.ahmedsalemalbiady.rahlaty.Fragments.PrepareLater;
import com.example.ahmedsalemalbiady.rahlaty.Fragments.PrepareNow;
import com.example.ahmedsalemalbiady.rahlaty.Model.Trip_model;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



    private String MY_PREFS_NAME="ACCOUNT_DATA";
    private TextView toolbar_main_screen,nav_header1,nav_header2;
    private Typeface tf;
    private MainTabSectionPageAdapter mSectionPageAdapter;
    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_toolbar);
        setSupportActionBar(toolbar);



        toolbar_main_screen=(TextView)findViewById(R.id.toolbar_main_screen);


        mSectionPageAdapter=new MainTabSectionPageAdapter(getSupportFragmentManager());
        //setup  the ViewPager with the section Adapter
        mViewPager=(ViewPager)findViewById(R.id.container);
        setupViewPager(mViewPager);


        // Font path
        String fontPath = "simpo.ttf";


        // Loading Font Face
        tf = Typeface.createFromAsset(getResources().getAssets(), fontPath);

        //setting typeface
        toolbar_main_screen.setTypeface(tf);



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



        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
//                if(tab.getPosition()==1)
//                {
//                    tabLayout.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.white));
//                }else if(tab.getPosition()==2){
//                    tabLayout.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.white));
//                }else if(tab.getPosition()==3){
//                    tabLayout.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.white));
//                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });







//        Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent call_us =new Intent(getApplicationContext(),Call_us.class);
                startActivity(call_us);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
//        TextView nav_header1 = (TextView) headerView.findViewById(R.id.title1);
//        TextView nav_header2 = (TextView) headerView.findViewById(R.id.textView);
//        nav_header1.setTypeface(tf);
//        nav_header2.setTypeface(tf);
        navigationView.setNavigationItemSelectedListener(this);

        final Menu navMenu = navigationView.getMenu();
        navigationView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {


                ArrayList<View> menuItems = new ArrayList<>(); // save Views in this array
                navigationView.getViewTreeObserver().removeOnGlobalLayoutListener(this); // remove the global layout listener
                for (int i = 0; i < navMenu.size(); i++) {// loops over menu items  to get the text view from each menu item
                    final MenuItem item = navMenu.getItem(i);
                    navigationView.findViewsWithText(menuItems, item.getTitle(), View.FIND_VIEWS_WITH_TEXT);
                    //for applying a font to subMenu ...
                    SubMenu subMenu = item.getSubMenu();
                    if (subMenu!=null && subMenu.size() >0 ) {
                        for (int j=0; j <subMenu.size();j++) {
                            MenuItem subMenuItem = subMenu.getItem(j);
                            SpannableString s = new SpannableString(subMenuItem.getTitle());
                            s.setSpan(new TypefaceSpan("simpo.ttf"), 0, s.length(),
                                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                            subMenuItem.setTitle(s);
                        }
                    }
                }
                for (final View menuItem : menuItems) {// loops over the saved views and sets the font
                    ((TextView) menuItem).setTypeface(tf);



                }



            }
        });


        //setting visibilty
//        navigationView.getMenu().findItem(R.id.).setVisible(true);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_my_account) {

            SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
            String user_name = prefs.getString("user_name", null);
            String password = prefs.getString("password", null);
            String phone_number = prefs.getString("phone_number", null);
            String user_id = prefs.getString("user_id", null);

            if(user_name==null||password==null||phone_number==null||user_id==null)
            {
                //go to registeration activity
                Intent new_account_reg=new Intent(getApplicationContext(),Register_new_user.class);
                startActivity(new_account_reg);

            }else{
                //go to his account
                Intent my_account=new Intent(getApplicationContext(),user_profile.class);
                startActivity(my_account);

            }

//            Toast.makeText(getApplicationContext(),"handel my account",Toast.LENGTH_LONG).show();
        }else if (id == R.id.nav_about_us)
        {

            Intent about_us=new Intent(getApplicationContext(),AboutUs.class);
            startActivity(about_us);

        } else if (id == R.id.nav_manage) {
            Toast.makeText(getApplicationContext(),"handel my setting",Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_share) {
            //code to share the appllication
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Download Rahlaty Now.");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        } else if (id == R.id.nav_call_us) {
            //code to open call us activity
            Intent call_us=new Intent(getApplicationContext(),Call_us.class);
            startActivity(call_us);
        } else if (id == R.id.nav_send) {

            SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
            String user_name = prefs.getString("user_name", null);
            String password = prefs.getString("password", null);
            String phone_number = prefs.getString("phone_number", null);
            String user_id = prefs.getString("user_id", null);

            if(user_name==null||password==null||phone_number==null||user_id==null)
            {
                //go to registeration activity
                Intent new_account_reg=new Intent(getApplicationContext(),Register_new_user.class);
                startActivity(new_account_reg);

            }else{
                //go to  message activity
                Intent message_us=new Intent(getApplicationContext(),Sen_us_messgae.class);
                startActivity(message_us);

            }

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }




    private void setupViewPager(ViewPager viewPager)
    {
        MainTabSectionPageAdapter adapter=new MainTabSectionPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new PrepareNow(),"Prepare now");
        adapter.addFragment(new PrepareLater(),"Prepare later");
        adapter.addFragment(new Completed(),"Completed");
        viewPager.setAdapter(adapter);
    }

}
