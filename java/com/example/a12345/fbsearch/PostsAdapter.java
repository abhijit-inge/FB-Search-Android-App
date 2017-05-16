package com.example.a12345.fbsearch;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.a12345.fbsearch.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by a12345 on 4/20/17.
 */

public class PostsAdapter extends BaseAdapter {
    private Context mContext;

    public PostsAdapter(Context mContext, List<String> messageTime, List<String> posts, String profile_pic,String uname) {
        this.mContext = mContext;
        this.posts = posts;
        this.profile_pic = profile_pic;
        this.messageTime = messageTime;
        this.uname=uname;
    }

    private LayoutInflater inflater;
    private List<String> posts;
    private List<String> messageTime;
    private String profile_pic;
    private String uname;

    @Override
    public int getCount() {
        return posts.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    public String getItemPost(int position) {
        return posts.get(position);
    }
    public String getItemmessageTime(int position) {
        return messageTime.get(position);
    }

    public String getProfile_pic(){
        return profile_pic;
    }
    public  String getUname(){
        return  uname;
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView=inflater.inflate(R.layout.posts,parent,false);
        ImageView thumbnail= (ImageView)rowView.findViewById(R.id.thumbnail);
        TextView name=(TextView)rowView.findViewById(R.id.Name);
        TextView time=(TextView)rowView.findViewById(R.id.Time);
        TextView Status=(TextView)rowView.findViewById(R.id.Status);


        name.setText(getUname());
        time.setText(getItemmessageTime(position));
        Status.setText(getItemPost(position));

        String imageUrl=getProfile_pic();
        Picasso.with(mContext).load(imageUrl).into(thumbnail);

        return rowView;
    }
}
