package com.example.a12345.fbsearch;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.audiofx.BassBoost;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.NavUtils;
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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.share.ShareApi;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.google.gson.Gson;
import com.squareup.okhttp.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class Details extends AppCompatActivity{
    private static final String TAG = "Details";
    private CallbackManager callbackManager;
    private LoginManager manager;

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    ArrayList<ToPass> userlist=null;
    private int[] tabIcons = {
            R.drawable.ic_albums,
            R.drawable.ic_posts,
            R.drawable.ic_events
    };
    ShareDialog shareDialog;
    com.example.a12345.fbsearch.AlbumsResponse responseObj;
    ListView listView;
    String id, name, profile_pic,typo;
    Boolean isFavorite=false;
    Context context=this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
     /*  String jsonMyObject = null;
       String jsonMyObjectPages=null;
       String jsonMyObjectEvents=null;
       String jsonMyObjectGroups=null;

       Bundle extras = getIntent().getExtras();
       if (extras != null) {
           jsonMyObject = extras.getString("User");
           jsonMyObjectPages=extras.getString("Pages");
           jsonMyObjectEvents=extras.getString("Events");
           jsonMyObjectGroups=extras.getString("Groups");

       }
       Response myObject = new Gson().fromJson(jsonMyObject, Response.class);

*/
      //  List<String> permissionNeeds= Arrays.asList("publish_actions");

        setContentView(R.layout.activity_details2);
        setTitle("Details");
        Bundle extras=getIntent().getExtras();
        if(extras!=null){
           id=extras.getString("id");
           name=extras.getString("name");
           profile_pic=extras.getString("profile_pic");
            typo=extras.getString("typo");
            isFavorite=extras.getBoolean("isFavorite");

        }
     /*   callbackManager = CallbackManager.Factory.create();
        manager=LoginManager.getInstance();
        manager.logInWithPublishPermissions(this,permissionNeeds);
        manager.registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        publishImage();
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });


*/
        FacebookSdk.sdkInitialize(getApplicationContext());

        manager.getInstance().logOut();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        final Context context=this;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Log.d(TAG, "dadddddyyyyy"+"http://cs-server.usc.edu:23998/fb-working2.php?&typo="+typo+"&selType=details&identity="+id);


        //final AlbumsResponse[] responseObj = {null};
        new AsyncTask<Void,Void,String>(){
            com.squareup.okhttp.Response response1;

            @Override
            protected String doInBackground(Void... params) {
                OkHttpClient client = new OkHttpClient();
                String result=null;
                Request request = new Request.Builder()
                        .url("http://sample-env.7aw77efvjh.us-west-2.elasticbeanstalk.com/?&typo="+typo+"&selType=details&identity="+id)
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



            protected void onPostExecute(String inge) {
                //showDialog("Downloaded " + result + " bytes");
                Gson gson=new Gson();
                //   Log.d(TAG, "doInForeground() called with: params = [" + response1 + "]");

                 responseObj =gson.fromJson(inge, com.example.a12345.fbsearch.AlbumsResponse.class);

                //twoF.setArguments( getIntent().getExtras() );

                // Intent activity = new Intent(context, Details.class);
                //activity.putExtra("Details",new Gson().toJson(responseObj));

                Log.d(TAG, "usershuhihiuuhiuihuihuiuiuiiuhioioihi = [" +   "]");

                viewPager = (ViewPager) findViewById(R.id.viewpager);
                setupViewPager(viewPager);

                tabLayout = (TabLayout) findViewById(R.id.tabs);
                tabLayout.setupWithViewPager(viewPager);
                //  getLayoutInflater().inflate(R.layout.activity_main,frameLayout);
                setupTabIcons();

                // startActivity(activity);
            }


        }.execute();

        // setSupportActionBar(toolbar);
        Gson gson=new Gson();
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       /* String temp="{\"empty\":1,\"posts\":[\"FINAL: USC 4, UCLA 1.\\n\\nTop-ranked USC Beach Volleyball beats No. 2 UCLA to finish the regular season undefeated and extend its winning streak to 60 matches!\\n\\n#FightOn #BeatTheBruins\",\"Welcome to the #TrojanFamily, Charles O'Bannon Jr.! The Las Vegas, NV product is USC's first McDonald's All-American since 2008. #ItTakesATeam\",\"Play ball!\\n\\nUSC Football head coach Clay Helton threw the ceremonial first pitch at tonight's Los Angeles Dodgers game!\\n\\n#LetsGoDodgers\",\"USC Trojans shared The Players' Tribune's post.\",\"USC sophomore Chimezie Metu announced that he will RETURN for his junior season! #ItTakesATeam\"],\"pics_url\":[\"https:\\/\\/scontent.xx.fbcdn.net\\/v\\/t1.0-9\\/s720x720\\/18034282_1496614910370709_2478753578815586968_n.jpg?oh=c8c853a4e70b77bd4e862b1814146681&oe=597C3F79\",\"https:\\/\\/scontent.xx.fbcdn.net\\/v\\/t1.0-9\\/s720x720\\/18034282_1496614910370709_2478753578815586968_n.jpg?oh=c8c853a4e70b77bd4e862b1814146681&oe=597C3F79\",\"https:\\/\\/scontent.xx.fbcdn.net\\/v\\/t1.0-9\\/s720x720\\/18032995_1492813534084180_1201927874398016015_n.jpg?oh=636e17c059a2478903cdc3806805d559&oe=5985C3CC\",\"https:\\/\\/scontent.xx.fbcdn.net\\/v\\/t1.0-0\\/p480x480\\/17800091_1479565238742343_1201289175410253197_n.jpg?oh=08a0974ff162ada00007fc31772eae0c&oe=5991B496\",\"https:\\/\\/scontent.xx.fbcdn.net\\/v\\/t1.0-0\\/p480x480\\/17800091_1479565238742343_1201289175410253197_n.jpg?oh=08a0974ff162ada00007fc31772eae0c&oe=5991B496\",\"https:\\/\\/scontent.xx.fbcdn.net\\/v\\/t1.0-9\\/s720x720\\/17799210_1479565222075678_7578066307759069243_n.jpg?oh=e541500c499801fb7253f837ed80e7bf&oe=59805211\",\"https:\\/\\/scontent.xx.fbcdn.net\\/v\\/t1.0-9\\/s720x720\\/15826283_1383564168342451_8092976519396408045_n.jpg?oh=46d745ab0d892ac4a2fcc0024a1f62f3&oe=597A4C41\",\"https:\\/\\/scontent.xx.fbcdn.net\\/v\\/t1.0-9\\/s720x720\\/15826283_1383564168342451_8092976519396408045_n.jpg?oh=46d745ab0d892ac4a2fcc0024a1f62f3&oe=597A4C41\",\"https:\\/\\/scontent.xx.fbcdn.net\\/v\\/t1.0-9\\/s720x720\\/15621699_1370158329683035_7007037210676132495_n.jpg?oh=154c55124cb886f061f83b19dcce0edf&oe=594C83CC\",\"https:\\/\\/scontent.xx.fbcdn.net\\/v\\/t1.0-9\\/14317609_1266608076704728_8902691418088589527_n.jpg?oh=9b540383ac5b9ce5b3ac043b28be7e1c&oe=59751272\",\"https:\\/\\/scontent.xx.fbcdn.net\\/v\\/t1.0-9\\/14317609_1266608076704728_8902691418088589527_n.jpg?oh=9b540383ac5b9ce5b3ac043b28be7e1c&oe=59751272\",\"https:\\/\\/scontent.xx.fbcdn.net\\/v\\/t1.0-9\\/11703350_1014135831951955_3282389641637245168_n.jpg?oh=03cbb5d1b262f9dd9c8be43947b93cbc&oe=59906FD2\",\"https:\\/\\/scontent.xx.fbcdn.net\\/v\\/t31.0-8\\/q83\\/s720x720\\/13723920_1231934766838726_8349870493122541488_o.jpg?oh=e929da19cbe4cd037cb4a981f3c012e8&oe=598FA2AA\",\"https:\\/\\/scontent.xx.fbcdn.net\\/v\\/t31.0-8\\/q83\\/s720x720\\/13723920_1231934766838726_8349870493122541488_o.jpg?oh=e929da19cbe4cd037cb4a981f3c012e8&oe=598FA2AA\",\"https:\\/\\/scontent.xx.fbcdn.net\\/v\\/t31.0-8\\/s720x720\\/13680064_1231934770172059_6323709052222106036_o.jpg?oh=f5898ae672627f3990faa3dd027bab5b&oe=5987DBC1\"],\"no_pics\":[2,2,2,2,2],\"details_posts\":1,\"albums\":[\"Timeline Photos\",\"Mobile Uploads\",\"Cover Photos\",\"Profile Pictures\",\"#USC2RIO\"],\"profile_pic\":\"https:\\/\\/scontent.xx.fbcdn.net\\/v\\/t1.0-1\\/14317609_1266608076704728_8902691418088589527_n.jpg?oh=9bea7c18c9337f032a7e239ce5a431a4&oe=5994A4F8\",\"uname\":\"USC Trojans\",\"messagetime\":[\"2017-04-23T01:08:11+0000\",\"2017-04-20T23:40:12+0000\",\"2017-04-20T02:44:16+0000\",\"2017-04-19T16:24:08+0000\",\"2017-04-18T16:20:51+0000\"]}";
        responseObj =gson.fromJson(temp, com.example.a12345.fbsearch.AlbumsResponse.class);

        Log.d(TAG, "here = [" + responseObj.getProfile_pic() + "]");


        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        //  getLayoutInflater().inflate(R.layout.activity_main,frameLayout);
        setupTabIcons();
*/
    }
    public void publishImage(){

        Bitmap image = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        SharePhoto photo = new SharePhoto.Builder()
                .setBitmap(image)
                .build();

        SharePhotoContent content = new SharePhotoContent.Builder()
                .addPhoto(photo)
                .build();

        ShareApi.share(content, null);

    }
    @Override
    protected void onActivityResult(int requestCode, int responseCode, Intent data)
    {
        super.onActivityResult(requestCode, responseCode, data);
        callbackManager.onActivityResult(requestCode, responseCode, data);
    }



    private void setupViewPager(ViewPager viewPager) {



        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        Fragment oneF=new AlbumsFragment();
        Bundle bundle= new Bundle();
        bundle.putString("Details",new Gson().toJson(responseObj));
        oneF.setArguments( bundle );
        adapter.addFragment(oneF, "ONE");
        Fragment twoF=new PostsFragment();
        twoF.setArguments( bundle);
        adapter.addFragment(twoF, "TWO");


        //adapter.addFragment(new FiveFragment(), "Five");

        viewPager.setAdapter(adapter);
    }
    private void setupTabIcons() {


        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabOne.setText("Albums");
        tabOne.setTextColor(Color.BLACK);

        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_albums, 0, 0);
        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabTwo.setText("Posts");
        tabTwo.setTextColor(Color.BLACK);
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_posts, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabTwo);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.details_menu, menu);
        MenuItem item=menu.findItem(R.id.Fav);
        if(isFavorite){
            item.setTitle("Remove from Favorites");

        }
        else
        {
            item.setTitle("Add to Favorites");
        }
        return super.onCreateOptionsMenu(menu);
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items

        switch (item.getItemId()) {
            case R.id.Fav: {
                ToPass obj= new ToPass();
                obj.setId(id);
                obj.setName(name);
                obj.setProfile_pic(profile_pic);
                String output=null;
                ArrayList<ToPass> temp=new ArrayList<ToPass>();
                SharedPreferences sharedPreferences=this.getSharedPreferences("com.example.a12345.fbsearch", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                if(userlist==null){
                    userlist=new ArrayList<ToPass>();
                }
               // sharedPreferences.edit().clear().commit();
               // Gson gson = new Gson();
                //String jsonText = sharedPreferences.getString("user", null);
                //ArrayList<ToPass> text = (ArrayList<ToPass>)gson.fromJson(jsonText, ArrayList.class);
               /* if(text==null){
                    output=gson.toJson(obj);

                }
               else if(text.isEmpty()){
                     output=gson.toJson(obj);

                }
                else{
                text.add(obj);
                 output=gson.toJson(text);}*/
                try {
                    temp = (ArrayList<ToPass>) ObjectSerializer.deserialize(sharedPreferences.getString(typo, ObjectSerializer.serialize(new ArrayList<ToPass>())));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if(temp!=null&&isFavorite){
                    for(int i=0;i<temp.size();i++) {
                        ToPass tempted = temp.get(i);
                        if(tempted.getId().equals(id)){
                            temp.remove(i);
                            editor.putString(typo, ObjectSerializer.serialize(temp)).apply();

                            Toast.makeText(this, "Removed from Favorites" , Toast.LENGTH_LONG).show();

                        }

                    }
                }
                else {
                    if (temp == null) {
                        temp = userlist;
                        temp.add(obj);

                    } else {
                        temp.add(obj);
                    }

                    try {
                        editor.putString(typo, ObjectSerializer.serialize(temp)).apply();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Log.d(TAG, "Ekta = " + ObjectSerializer.serialize(temp) + "temp" + temp.get(0).getName() + "]");

                    Log.d(TAG, "Ekta = " + (ArrayList<ToPass>) ObjectSerializer.deserialize(sharedPreferences.getString(typo, ObjectSerializer.serialize(new ArrayList<ToPass>()))) + "]");

                    Toast.makeText(this, "Added to Favorites", Toast.LENGTH_LONG).show();
                }
                //sharedPreferences.edit().putString("user", "specify name here").commit();

                return true;
            }
            case R.id.Share: {
                //FacebookSdk.sdkInitialize(getApplicationContext());
                List<String> permissionNeeds= Arrays.asList("publish_actions");
                callbackManager = CallbackManager.Factory.create();
                shareDialog = new ShareDialog(this);
                manager=LoginManager.getInstance();

                manager.logInWithPublishPermissions(this,permissionNeeds);
                manager.registerCallback(callbackManager,
                        new FacebookCallback<LoginResult>() {
                            @Override
                            public void onSuccess(LoginResult loginResult) {
                                // App code
                                shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
                                    @Override
                                    public void onSuccess(Sharer.Result result) {
                                        Toast.makeText(context, "Posted to Facebook", Toast.LENGTH_LONG).show();
                                    }

                                    @Override
                                    public void onCancel() {
                                        Toast.makeText(context, "Canceled Posting to Facebook", Toast.LENGTH_LONG).show();

                                    }

                                    @Override
                                    public void onError(FacebookException e) {
                                    }

                                });

                                if (ShareDialog.canShow(ShareLinkContent.class)) {
                                    ShareLinkContent linkContent = new ShareLinkContent.Builder()
                                            .setImageUrl(Uri.parse(profile_pic)).setContentTitle(name).setContentDescription("FB Search from USC CSCI 571")
                                            .build();
                                    shareDialog.show(linkContent);

                                }
                                //Toast.makeText(context, "Posted to Facebook", Toast.LENGTH_LONG).show();

                            }

                            @Override
                            public void onCancel() {
                                // App code
                                Toast.makeText(context, "Canceled Posting to Facebook", Toast.LENGTH_LONG).show();

                            }

                            @Override
                            public void onError(FacebookException exception) {
                                // App code
                            }
                        });
                return true;
            }
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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

