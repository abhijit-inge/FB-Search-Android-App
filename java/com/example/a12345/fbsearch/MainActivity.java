package com.example.a12345.fbsearch;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.support.v4.app.Fragment;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    com.example.a12345.fbsearch.Response responseObj;
    com.example.a12345.fbsearch.Response responseObjPages;
    com.example.a12345.fbsearch.Response responseObjGroups;
    com.example.a12345.fbsearch.Response responseObjPlaces;
    com.example.a12345.fbsearch.Response responseObjEvents;

    Gson gson;
    EditText edit;
    private static final String TAG = "MainActivity";
    Context context=this;
    String lat,lon,dist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        /*fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();









        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        setTitle("Search on FB");
        edit=(EditText)findViewById(R.id.searchText);
        //crap starts here
       /* Fragment newFragment;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        newFragment = new FavFrag();
        transaction.replace(R.id.content_frame, newFragment,"frag2");
        transaction.addToBackStack(null);
        transaction.commit();
        */
        /*LinearLayout dynamicContent = (LinearLayout) findViewById(R.id.dynamicContent);
        View wizard = getLayoutInflater().inflate(R.layout.content_main, null);
        dynamicContent.addView(wizard);*/
        final String mPath = "Geo.txt";
        QuoteBank mQuoteBank = new QuoteBank(this);
        List<String> mLines = mQuoteBank.readLine(mPath);
        String[] Geo= new String[3];
        int i=0;
        for (String string : mLines) {
            Log.d(TAG + "seemasan", string);
            Geo[i]=string.trim();
            i++;

        }
        lat=Geo[0];
        lon=Geo[1];
        dist=Geo[2];
        Button search_btn=(Button)findViewById(R.id.search);
        Button clear_btn=(Button)findViewById(R.id.clear);

        final Context context=this;
        Log.d(TAG, "button() called with: params = ["+context+"and"  );
        clear_btn.setOnClickListener(new View.OnClickListener() {
                                          URL url = null;
                                          URI uri = null;


                                          String search;
                                          List<String> responseList = new ArrayList<String>();

                                          @Override
                                          public void onClick(View v) {
                                        edit.setText("");
                                          }
                                      });
        search_btn.setOnClickListener(new View.OnClickListener()
        {
            URL url=null;
            URI uri=null;


            String search;
            List<String> responseList=new ArrayList<String>();

            @Override
            public void onClick(View v) {

                 edit = (EditText)findViewById(R.id.searchText);
                if(edit==null||edit.getText()==null){
                    Toast.makeText(context, "Please Enter a keyword!" , Toast.LENGTH_LONG).show();
                    return;
                }
                else if(edit.getText().toString().equals("")){
                    Toast.makeText(context, "Please Enter a keyword!" , Toast.LENGTH_LONG).show();
                    return;
                }

               search=edit.getText().toString();
                Log.d(TAG, "Inge bhai = ["+"http://sample-env.7aw77efvjh.us-west-2.elasticbeanstalk.com/?symbol="+search+"&selType=Users" );

        new AsyncTask<Void,Void,List<String>>(){
            Response response1;
            @Override
            protected List<String> doInBackground(Void... params) {
                OkHttpClient client = new OkHttpClient();
                String result=null;
                Request request = new Request.Builder()
                        .url("http://sample-env.7aw77efvjh.us-west-2.elasticbeanstalk.com/?symbol="+search+"&selType=Users")
                        .build();

                Request request1 = new Request.Builder()
                        .url("http://sample-env.7aw77efvjh.us-west-2.elasticbeanstalk.com/?symbol="+search+"&selType=Pages")
                        .build();

                Request request2 = new Request.Builder()
                        .url("http://sample-env.7aw77efvjh.us-west-2.elasticbeanstalk.com/?symbol="+search+"&selType=Events")
                        .build();

                Request request3 = new Request.Builder()
                        .url("http://sample-env.7aw77efvjh.us-west-2.elasticbeanstalk.com/?symbol="+search+"&selType=Places&lat="+lat+"&long="+lon+"&accuracy="+dist)
                        .build();

                Request request4 = new Request.Builder()
                        .url("http://sample-env.7aw77efvjh.us-west-2.elasticbeanstalk.com/?symbol="+search+"&selType=Groups")
                        .build();

                try {
                      response1 = client.newCall(request).execute();
                    result=response1.body().string();
                    responseList.add(result);
                    response1 = client.newCall(request1).execute();
                    result=response1.body().string();
                    responseList.add(result);
                    response1 = client.newCall(request2).execute();
                    result=response1.body().string();
                    responseList.add(result);
                    response1 = client.newCall(request3).execute();
                    result=response1.body().string();
                    responseList.add(result);
                    response1 = client.newCall(request4).execute();
                    result=response1.body().string();
                    responseList.add(result);
                   // Log.d(TAG, "doInBackground() called with: params = [" + response1.body().string() + "]");
                  //  gson=new Gson();
                  //  responseObj=gson.fromJson(response1.body().string(), com.example.a12345.fbsearch.Response.class);

                } catch (IOException e) {
                    e.printStackTrace();
                }


                return responseList;

            }

            protected void onPostExecute(List<String> inge) {
                //showDialog("Downloaded " + result + " bytes");
                gson=new Gson();
             //   Log.d(TAG, "doInForeground() called with: params = [" + response1 + "]");

                responseObj=gson.fromJson(inge.get(0), com.example.a12345.fbsearch.Response.class);
                responseObjPages=gson.fromJson(inge.get(1), com.example.a12345.fbsearch.Response.class);
                responseObjEvents=gson.fromJson(inge.get(2), com.example.a12345.fbsearch.Response.class);
                responseObjPlaces=gson.fromJson(inge.get(3), com.example.a12345.fbsearch.Response.class);
                responseObjGroups=gson.fromJson(inge.get(4), com.example.a12345.fbsearch.Response.class);

                Log.d(TAG, "usershuhihiuuhiuihuihuiuiuiiuhioioihi = [" + responseObj.getName() + "]");

                Intent activity = new Intent(context, Favorites.class);
                activity.putExtra("Pages",new Gson().toJson(responseObjPages));
                activity.putExtra("User", new Gson().toJson(responseObj));
                activity.putExtra("Events", new Gson().toJson(responseObjEvents));
                activity.putExtra("Groups", new Gson().toJson(responseObjGroups));
                activity.putExtra("Places", new Gson().toJson(responseObjPlaces));
                Log.d(TAG, "usershuhihiuuhiuihuihuiuiuiiuhioioihi = [" + responseObj.getName() + "]");
                responseList.clear();

                startActivity(activity);

            }


        }.execute();


     /*   new AsyncTask<Void,Void,String>(){
            Response response1;

            @Override
            protected String doInBackground(Void... params) {
                OkHttpClient client = new OkHttpClient();
                String result=null;
                Request request = new Request.Builder()
                        .url("http://sample-env.7aw77efvjh.us-west-2.elasticbeanstalk.com/?symbol="+search+"&selType=Pages")
                        .build();

                try {
                    response1 = client.newCall(request).execute();
                    result=response1.body().string();
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
                gson=new Gson();
                //   Log.d(TAG, "doInForeground() called with: params = [" + response1 + "]");

                responseObjPages=gson.fromJson(response1, com.example.a12345.fbsearch.Response.class);


            }


        }.execute();




        new AsyncTask<Void,Void,String>(){
            Response response1;
            @Override
            protected String doInBackground(Void... params) {
                OkHttpClient client = new OkHttpClient();
                String result=null;
                Request request = new Request.Builder()
                        .url("http://sample-env.7aw77efvjh.us-west-2.elasticbeanstalk.com/?symbol="+search+"&selType=Events")
                        .build();

                try {
                    response1 = client.newCall(request).execute();
                    result=response1.body().string();
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
                gson=new Gson();
                //   Log.d(TAG, "doInForeground() called with: params = [" + response1 + "]");

                responseObjEvents=gson.fromJson(response1, com.example.a12345.fbsearch.Response.class);


            }


        }.execute();

        new AsyncTask<Void,Void,String>(){
            Response response1;
            @Override
            protected String doInBackground(Void... params) {
                OkHttpClient client = new OkHttpClient();
                String result=null;
                Request request = new Request.Builder()
                        .url("http://sample-env.7aw77efvjh.us-west-2.elasticbeanstalk.com/?symbol="+search+"&selType=Groups")
                        .build();

                try {
                    response1 = client.newCall(request).execute();
                    result=response1.body().string();
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
                gson=new Gson();
                //   Log.d(TAG, "doInForeground() called with: params = [" + response1 + "]");

                responseObjGroups=gson.fromJson(response1, com.example.a12345.fbsearch.Response.class);


            }


        }.execute();


        new AsyncTask<Void,Void,String>(){
            Response response1;
            @Override
            protected String doInBackground(Void... params) {
                OkHttpClient client = new OkHttpClient();
                String result=null;
                Request request = new Request.Builder()
                        .url("http://sample-env.7aw77efvjh.us-west-2.elasticbeanstalk.com/?symbol="+search+"&selType=Places&lat=34.021167&long=-118.289028&accuracy=1000")
                        .build();

                try {
                    response1 = client.newCall(request).execute();
                    result=response1.body().string();
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
                gson=new Gson();
                //   Log.d(TAG, "doInForeground() called with: params = [" + response1 + "]");

                responseObjPlaces=gson.fromJson(response1, com.example.a12345.fbsearch.Response.class);


            }


        }.execute();*/

             /*    Log.d(TAG, "usershuhihiuuhiuihuihuiuiuiiuhioioihi = [" + responseObj.getName() + "]");

                Intent activity = new Intent(context, Favorites.class);
                activity.putExtra("Pages",new Gson().toJson(responseObjPages));
                activity.putExtra("User", new Gson().toJson(responseObj));
                activity.putExtra("Events", new Gson().toJson(responseObjEvents));
                activity.putExtra("Groups", new Gson().toJson(responseObjGroups));
                activity.putExtra("Places", new Gson().toJson(responseObjPlaces));
                Log.d(TAG, "usershuhihiuuhiuihuihuiuiuiiuhioioihi = [" + responseObj.getName() + "]");

                startActivity(activity);*/
            // Inge();

            } public void Inge(){

            Log.d(TAG, "usershuhihiuuhiuihuihuiuiuiiuhioioihi = [" + responseObj.getName() + "]");

                Intent activity = new Intent(context, Favorites.class);
                activity.putExtra("Pages",new Gson().toJson(responseObjPages));
                activity.putExtra("User", new Gson().toJson(responseObj));
                activity.putExtra("Events", new Gson().toJson(responseObjEvents));
                activity.putExtra("Groups", new Gson().toJson(responseObjGroups));
                activity.putExtra("Places", new Gson().toJson(responseObjPlaces));
                Log.d(TAG, "usershuhihiuuhiuihuihuiuiuiiuhioioihi = [" + responseObj.getName() + "]");

                startActivity(activity);


        }});


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




        @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
            Fragment newFragment;
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (id == R.id.Favorites) {
            // Handle the camera action

            //startActivity(new Intent(this,Favorites.class));
            Intent activity = new Intent(this, favorite.class);
          /*  activity.putExtra("Pages",new Gson().toJson(responseObjPages));
            activity.putExtra("User", new Gson().toJson(responseObj));
            activity.putExtra("Events", new Gson().toJson(responseObjEvents));
            activity.putExtra("Groups", new Gson().toJson(responseObjGroups));
            activity.putExtra("Places", new Gson().toJson(responseObjPlaces));
*/
           startActivity(activity);
               /* newFragment = new FavFrag();
                transaction.replace(R.id.content_frame, newFragment,"frag2");
                transaction.addToBackStack(null);
                transaction.commit();*/
            return true;

        }
        if(id==R.id.About){
            Intent activity = new Intent(this, About_Me.class);
            startActivity(activity);
            return true;

        }


      /*  if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
