package com.example.a12345.fbsearch;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.io.IOException;
import java.net.URI;
import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TwoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TwoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PostsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    AlbumsResponse myObject;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private static final String TAG = "AlbumsFragment";


    private OnFragmentInteractionListener mListener;

    public PostsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TwoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ThreeFragment newInstance(String param1, String param2) {
        ThreeFragment fragment = new ThreeFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Bundle mBundle = new Bundle();
        mBundle = getArguments();
        mBundle.getString("Details");

        String jsonMyObject = null;

        //Bundle extras = getIntent().getExtras();

        jsonMyObject = mBundle.getString("Details");

        Log.d(TAG, "usershuhihiuuhiuihuihuiuiuiiuhioioihi = [" + jsonMyObject + "]");

        View view= inflater.inflate(R.layout.fragment_posts, container, false);
        ListView listView = (ListView) view.findViewById(R.id.users_content);

        myObject = new Gson().fromJson(jsonMyObject, AlbumsResponse.class);
        if(myObject.getPosts()==null){
            ViewGroup parent = (ViewGroup) listView.getParent();
            parent.removeView(listView);
            View newview= inflater.inflate(R.layout.diplay_message_posts, container, false);

            parent.addView(newview);

        }
       else if(myObject.getPosts()!=null&&myObject.getPosts().isEmpty()){
            ViewGroup parent = (ViewGroup) listView.getParent();
            parent.removeView(listView);
            View newview= inflater.inflate(R.layout.diplay_message_posts, container, false);

            parent.addView(newview);

        }
        else {
            PostsAdapter postsAdapter = new PostsAdapter(this.getContext(), myObject.getMessagetime(), myObject.getPosts(), myObject.getProfile_pic(), myObject.getUname());

            listView.setAdapter(postsAdapter);
        }

        return view;
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

