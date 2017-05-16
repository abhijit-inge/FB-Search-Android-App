package com.example.a12345.fbsearch;

import android.graphics.Color;
import android.support.v4.app.FragmentTransaction;
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
public class Favorites extends AppCompatActivity{
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
       // new MyLayoutInflator().inflate(this,R.layout.activity_favorites, getSupportActionBar(),getIntent());
       // getLayoutInflater().inflate(R.layout.activity_main, frameLayout);
        String jsonMyObject = null;
        String jsonMyObjectPages=null;
        String jsonMyObjectEvents=null;
        String jsonMyObjectGroups=null;
        setTitle("Results");
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            jsonMyObject = extras.getString("User");
            jsonMyObjectPages=extras.getString("Pages");
            jsonMyObjectEvents=extras.getString("Events");
            jsonMyObjectGroups=extras.getString("Groups");

        }
        Response myObject = new Gson().fromJson(jsonMyObject, Response.class);


        setContentView(R.layout.activity_favorites);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
        Log.d(TAG, "tunne = "  + "]");
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        Fragment oneF=new OneFragment();

        Bundle bundle= new Bundle();
        Bundle bundle1= new Bundle();
        Bundle bundle2= new Bundle();
        Bundle bundle3= new Bundle();
        Bundle bundle4= new Bundle();

        bundle.putAll(getIntent().getExtras());
        bundle1.putAll(getIntent().getExtras());
        bundle2.putAll(getIntent().getExtras());
        bundle3.putAll(getIntent().getExtras());
        bundle4.putAll(getIntent().getExtras());

        //oneF.setArguments( getIntent().getExtras() );
        bundle.putString("typo","users");
        oneF.setArguments(bundle);
        adapter.addFragment(oneF, "ONE");

        Fragment twoF=new TwoFragment();
        bundle1.putString("typo","pages");
        twoF.setArguments(bundle1);

        adapter.addFragment(twoF, "TWO");
        Fragment threeF=new ThreeFragment();
      //  threeF.setArguments( getIntent().getExtras() );
        bundle2.putString("typo","event");
        threeF.setArguments(bundle2);
        adapter.addFragment(threeF, "Three");
       // adapter.addFragment(new TwoFragment(), "TWO");
       // adapter.addFragment(new ThreeFragment(),"Three");
        //adapter.addFragment(new FourFragment(), "Four");
        Fragment fourF=new FourFragment();
       // fourF.setArguments( getIntent().getExtras() );
        bundle3.putString("typo","places");

        //bundle.putString("typo","Events3");
        fourF.setArguments(bundle3);
        adapter.addFragment(fourF, "Four");

        Fragment fiveF=new FiveFragment();
        //fiveF.setArguments( getIntent().getExtras() );
        bundle4.putString("typo","groups");
        fiveF.setArguments(bundle4);
        adapter.addFragment(fiveF, "Five");
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
