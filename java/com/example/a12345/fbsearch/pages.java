package com.example.a12345.fbsearch;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.a12345.fbsearch.R;
import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.io.IOException;
import  java.net.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OneFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OneFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class pages extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "User";
    Response myObject=null;
    Response responseObj;
    Context context;
    ListView listViewonclick;
    Gson gson;
    favadaptor customAdapter;
    String typo;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private userInteractionListener mListener;



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OneFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static user newInstance(String param1, String param2) {
        user fragment = new user();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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







    public pages() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Bundle extras = getArguments();
        ArrayList<ToPass> temp=null;
        String jsonMyObject = null;
        List<String> name=new ArrayList<String>();
        List<String> profile_pic=new ArrayList<String>();;
        List<String> id=new ArrayList<String>();
        //Bundle extras = getIntent().getExtras();
        if (extras != null) {
            jsonMyObject = extras.getString("User");
        }
        SharedPreferences sharedPreferences=this.getActivity().getSharedPreferences("com.example.a12345.fbsearch", Context.MODE_PRIVATE);
        //String jsonText = sharedPreferences.getString("user", null);
        SharedPreferences.Editor editor=sharedPreferences.edit();

        View view =inflater.inflate(R.layout.pages, container, false);
        ListView listView = (ListView) view.findViewById(R.id.users_content);


        try {
            temp = (ArrayList<ToPass>) ObjectSerializer.deserialize(sharedPreferences.getString("pages", ObjectSerializer.serialize(new ArrayList<ToPass>())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(temp!=null) {
            Iterator itr = temp.iterator();
            for (int i = 0; i < temp.size(); i++) {
                ToPass tempted = temp.get(i);
                name.add(tempted.getName());
                id.add(tempted.getId());
                profile_pic.add(tempted.getProfile_pic());

            }

            customAdapter = new favadaptor(this.getContext(), name, profile_pic, id, "pages");


            //Log.d(TAG, "AbhijitInge" +temp.get(1).getName() +"]");
            listView.setAdapter(customAdapter);

        }
        //myObject = new Gson().fromJson(jsonMyObject, Response.class);
        //Log.d(TAG, "doInBackground() called with: params = [" + myObject.getProfile_pic() +getView()+ "]");

        return view;

        // customAdapter = new CustomAdapter(this.getContext(), myObject.getName(), myObject.getProfile_pic(), myObject.getId(), typo);

        // listView.setAdapter(customAdapter);
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
    interface userInteractionListener {
        // TODO: Update argument type and name
        String jsonMyObject = null;


        void onFragmentInteraction(Uri uri);
    }
}
