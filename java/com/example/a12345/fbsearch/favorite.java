package com.example.a12345.fbsearch;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.okhttp.*;

import java.util.ArrayList;
import java.util.List;
public class favorite extends AppCompatActivity{
    private static final String TAG = "Favorites";

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int[] tabIcons = {
            R.drawable.ic_users,
            R.drawable.ic_pages,
            R.drawable.ic_events
    };
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        String jsonMyObject = null;
        String jsonMyObjectPages=null;
        String jsonMyObjectEvents=null;
        String jsonMyObjectGroups=null;
        setTitle("Favorites");
        //new MyLayoutInflator().inflate(this,R.layout.activity_favorite, getSupportActionBar(),getIntent());
        //getLayoutInflater().inflate(R.layout.activity_main, );

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            jsonMyObject = extras.getString("User");
            jsonMyObjectPages=extras.getString("Pages");
            jsonMyObjectEvents=extras.getString("Events");
            jsonMyObjectGroups=extras.getString("Groups");

        }


        setContentView(R.layout.activity_favorites);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_name);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        //  getLayoutInflater().inflate(R.layout.activity_main,frameLayout);
        setupTabIcons();

    }
    @Override
    public void onRestart(){
        super.onRestart();
      /*  Fragment frg = null;
        frg = getSupportFragmentManager().findFragmentByTag("ONE");
        //Log.d(TAG, "buleya = " + + "]");

        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.detach(frg);
        ft.attach(frg);
        ft.commit();

*/    Log.d(TAG, "buleya = " + "]");
        tabLayout.setupWithViewPager(viewPager);
        setupViewPager(viewPager);

        viewPager.getAdapter().notifyDataSetChanged();
        setupTabIcons();


    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        Fragment user=new user();
        Fragment pages=new pages();
        Fragment places=new places();

        Fragment event=new events();
        Fragment groups=new groups();

        //oneF.setArguments( getIntent().getExtras() );
       adapter.addFragment(user, "ONE");


        adapter.addFragment(pages, "TWO");
        //  threeF.setArguments( getIntent().getExtras() );
        adapter.addFragment(event, "Three");
        // adapter.addFragment(new TwoFragment(), "TWO");
        // adapter.addFragment(new ThreeFragment(),"Three");
        //adapter.addFragment(new FourFragment(), "Four");
        // fourF.setArguments( getIntent().getExtras() );

        //bundle.putString("typo","Events3");
        adapter.addFragment(places, "Four");

        adapter.addFragment(groups, "Five");
        //adapter.addFragment(new FiveFragment(), "Five");


        viewPager.setAdapter(adapter);
    }
    private void setupTabIcons() {


        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabOne.setText("Users");
        tabOne.setTextColor(Color.BLACK);

        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_users, 0, 0);
        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabTwo.setText("Pages");
        tabTwo.setTextColor(Color.BLACK);
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_pages, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        TextView tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabThree.setText("Events");
        tabThree.setTextColor(Color.BLACK);
        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_events, 0, 0);
        tabLayout.getTabAt(2).setCustomView(tabThree);

        TextView tabFour = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabFour.setText("Places");
        tabFour.setTextColor(Color.BLACK);
        tabFour.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_places, 0, 0);
        tabLayout.getTabAt(3).setCustomView(tabFour);

        TextView tabFive = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabFive.setText("Groups");
        tabFive.setTextColor(Color.BLACK);
        tabFive.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_groups, 0, 0);
        tabLayout.getTabAt(4).setCustomView(tabFive);


    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
