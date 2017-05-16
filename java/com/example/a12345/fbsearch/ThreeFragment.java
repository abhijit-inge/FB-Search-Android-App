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
public class ThreeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private static final String TAG = "ThreeFragment";
    Response myObject=null;
    Response responseObj;
    Context context;
    ListView listViewonclick;
    Gson gson;
    CustomAdapter customAdapter;
    String typo;
    private OnFragmentInteractionListener mListener;

    public ThreeFragment() {
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
        Bundle extras = getArguments();
        String jsonMyObject = null;

        //Bundle extras = getIntent().getExtras();
        if (extras != null) {
            jsonMyObject = extras.getString("Events");
        }
        typo= extras.getString("typo");

        View view =inflater.inflate(R.layout.fragment_three, container, false);
        myObject = new Gson().fromJson(jsonMyObject, Response.class);
        if(myObject.getName()==null){
            final Button btnnext = (Button) view.findViewById(R.id.UserNext);
            final Button btnprev = (Button) view.findViewById(R.id.UserPrevious);
            btnprev.setClickable(false);
            btnprev.setEnabled(false);
            btnnext.setClickable(false);
            btnnext.setEnabled(false);
        }
        //Log.d(TAG, "doInBackground() called with: params = [" + myObject.getProfile_pic() +getView()+ "]");
        if(myObject.getName()!=null) {


            ListView listView = (ListView) view.findViewById(R.id.events_content);
            listViewonclick = (ListView) view.findViewById(R.id.events_content);

            Log.d(TAG, "doInBackground() called with: params = [" + myObject.getPrevious() + "next" + myObject.getNext() + "]");
            //CustomAdapter customAdapter=new CustomAdapter(this.getContext(),myObject.getName(),myObject.getProfile_pic(),myObject.getId());
            //listView.setAdapter(customAdapter);
            final Button btnnext = (Button) view.findViewById(R.id.UserNext);
            final Button btnprev = (Button) view.findViewById(R.id.UserPrevious);
            context = this.getContext();
            if (myObject.getPrevious().equals("")) {

                btnprev.setClickable(false);
                btnprev.setEnabled(false);

            } else {
                Log.d(TAG, "so not true ");
                btnprev.setEnabled(true);


                btnprev.setClickable(true);
            }

            if (myObject.getNext().equals("")) {

                btnnext.setClickable(false);
                btnnext.setEnabled(false);
            } else {
                btnnext.setEnabled(true);

                btnnext.setClickable(true);
            }
            if(myObject.getName().size()<10){
                btnnext.setEnabled(false);

                btnnext.setClickable(false);
            }
            btnnext.setOnClickListener(new View.OnClickListener() {
                URL url = null;
                URI uri = null;


                @Override
                public void onClick(View v) {

                    String url = "http://sample-env.7aw77efvjh.us-west-2.elasticbeanstalk.com/?url=" + myObject.getNext() + "&selType=paginate";

                    Log.d(TAG, "next button clicked in user = [" + myObject.getPrevious() + "next" + url + "]");

                    new AsyncTask<Void, Void, String>() {
                        com.squareup.okhttp.Response response1;

                        @Override
                        protected String doInBackground(Void... params) {
                            OkHttpClient client = new OkHttpClient();
                            String result = null;
                            Request request = new Request.Builder()
                                    .url("http://sample-env.7aw77efvjh.us-west-2.elasticbeanstalk.com/?url=" + myObject.getNext() + "&selType=paginate")
                                    .build();

                            try {
                                response1 = client.newCall(request).execute();
                                result = response1.body().string();
                                // Log.d(TAG, "doInBackground() called with: params = [" + response1.body().string() + "]");
                                //  gson=new Gson();
                                //  responseObj=gson.fromJson(response1.body().string(), com.example.a12345.fbsearch.Response.class);

                            } catch (IOException e) {
                                e.printStackTrace();
                            }


                            return result;

                        }

                        protected void onPostExecute(String response1) {
                            //showDialog("Downloaded " + result + " bytes");
                            gson = new Gson();
                            //   Log.d(TAG, "doInForeground() called with: params = [" + response1 + "]");

                            try {
                                responseObj = gson.fromJson(response1, com.example.a12345.fbsearch.Response.class);
                                Log.d(TAG, "button clicked: params = [" + responseObj.getName() + "]");
                                myObject = responseObj;
                                if (context == null) {
                                    Log.d(TAG, "button clicked: params = [" + "context is null" + "]");

                                }
                                if (responseObj.getPrevious() != "") {
                                    btnprev.setEnabled(true);
                                    btnprev.setClickable(true);
                                } else {
                                    btnprev.setEnabled(false);
                                    btnprev.setClickable(false);
                                }
                                if (responseObj.getNext() != "") {
                                    btnnext.setEnabled(true);
                                    btnnext.setClickable(true);
                                } else {
                                    btnnext.setEnabled(false);
                                    btnnext.setClickable(false);
                                }
                                customAdapter = new CustomAdapter(context, myObject.getName(), myObject.getProfile_pic(), myObject.getId(), typo);

                                listViewonclick.setAdapter(customAdapter);
                            }
                            catch (Exception e){
                                ;
                            }
                        }


                    }.execute();


                }


            });


            btnprev.setOnClickListener(new View.OnClickListener() {
                URL url = null;
                URI uri = null;


                @Override
                public void onClick(View v) {
                    String url = "http://sample-env.7aw77efvjh.us-west-2.elasticbeanstalk.com/?url=" + myObject.getPrevious() + "&selType=paginate";

                    Log.d(TAG, "next button clicked in user = [" + myObject.getPrevious() + "next" + url + "]");

                    new AsyncTask<Void, Void, String>() {
                        com.squareup.okhttp.Response response1;

                        @Override
                        protected String doInBackground(Void... params) {
                            OkHttpClient client = new OkHttpClient();
                            String result = null;
                            Request request = new Request.Builder()
                                    .url("http://sample-env.7aw77efvjh.us-west-2.elasticbeanstalk.com/?url=" + myObject.getPrevious() + "&selType=paginate")
                                    .build();

                            try {
                                response1 = client.newCall(request).execute();
                                result = response1.body().string();
                                // Log.d(TAG, "doInBackground() called with: params = [" + response1.body().string() + "]");
                                //  gson=new Gson();
                                //  responseObj=gson.fromJson(response1.body().string(), com.example.a12345.fbsearch.Response.class);

                            } catch (IOException e) {
                                e.printStackTrace();
                            }


                            return result;

                        }

                        protected void onPostExecute(String response1) {
                            //showDialog("Downloaded " + result + " bytes");
                            gson = new Gson();
                            //   Log.d(TAG, "doInForeground() called with: params = [" + response1 + "]");
                           try{
                            responseObj = gson.fromJson(response1, com.example.a12345.fbsearch.Response.class);
                            Log.d(TAG, "button clicked: params = [" + responseObj.getName() + "]");
                            myObject = responseObj;
                            if (context == null) {
                                Log.d(TAG, "button clicked: params = [" + "context is null" + "]");

                            }
                            if (responseObj.getPrevious() != "") {
                                btnprev.setEnabled(true);
                                btnprev.setClickable(true);
                            } else {
                                btnprev.setEnabled(false);
                                btnprev.setClickable(false);
                            }
                            if (responseObj.getNext() != "") {
                                btnnext.setEnabled(true);
                                btnnext.setClickable(true);
                            } else {
                                btnnext.setEnabled(false);
                                btnnext.setClickable(false);
                            }
                            customAdapter = new CustomAdapter(context, myObject.getName(), myObject.getProfile_pic(), myObject.getId(), typo);

                            listViewonclick.setAdapter(customAdapter);}
                            catch (Exception e){
                                ;
                            }
                        }


                    }.execute();


                }


            });


            customAdapter = new CustomAdapter(this.getContext(), myObject.getName(), myObject.getProfile_pic(), myObject.getId(), typo);

            listView.setAdapter(customAdapter);
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
