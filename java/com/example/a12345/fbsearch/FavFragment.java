package com.example.a12345.fbsearch;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FavFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FavFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FavFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FavFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavFragment newInstance(String param1, String param2) {
        FavFragment fragment = new FavFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_favorites);

        //toolbar = (Toolbar) findViewById(R.id.toolbar);
        // setSupportActionBar(toolbar);

        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       // viewPager = (ViewPager) findViewById(R.id.viewpager);
       // setupViewPager(viewPager);

       // tabLayout = (TabLayout) findViewById(R.id.tabs);
       // tabLayout.setupWithViewPager(viewPager);
      //  setupTabIcons();

    }
   /* private void setupViewPager(ViewPager viewPager) {
        Favorites.ViewPagerAdapter adapter = new Favorites.ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new OneFragment(), "ONE");
        adapter.addFragment(new TwoFragment(), "TWO");
        adapter.addFragment(new ThreeFragment(),"Three");
        adapter.addFragment(new FourFragment(), "Four");
        adapter.addFragment(new FiveFragment(), "Five");


        viewPager.setAdapter(adapter);
    }*/
    /*private void setupTabIcons() {


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


    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_favorites, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
