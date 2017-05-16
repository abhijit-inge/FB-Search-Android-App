package com.example.a12345.fbsearch;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.a12345.fbsearch.R;
import com.squareup.picasso.Picasso;

import java.net.URI;
import java.net.URL;
import java.util.List;

/**
 * Created by a12345 on 4/20/17.
 */

public class favadaptor extends BaseAdapter {
    private Context mContext;

    public favadaptor(Context mContext, List<String> name, List<String> profile_pic, List<String> id,String typo) {
        this.mContext = mContext;
        this.name = name;
        this.profile_pic = profile_pic;
        this.id = id;
        this.typo=typo;
    }
    private static final String TAG = "CustomAdapter";

    private LayoutInflater inflater;
    private List<String> name;
    private List<String> profile_pic;
    private List<String> id;
    private String typo;


    @Override
    public int getCount() {
        return profile_pic.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    public String getItemPic(int position) {
        return profile_pic.get(position);
    }
    public String getItemname(int position) {
        return name.get(position);
    }
    public String getItemid(int position) {
        return id.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView=inflater.inflate(R.layout.each_list_item,parent,false);
        ImageView thumbnail= (ImageView)rowView.findViewById(R.id.thumbnail);
        TextView name=(TextView)rowView.findViewById(R.id.name);
        name.setText(getItemname(position));
        String imageUrl=getItemPic(position);
        final ImageButton details=(ImageButton)rowView.findViewById( R.id.imageButton2);
        final ImageButton Favorites=(ImageButton)rowView.findViewById(R.id.imageButton);

        Favorites.setImageResource(R.mipmap.ic_launcher);
        Favorites.setBackgroundColor(0);
        details.setOnClickListener(new View.OnClickListener()
        {
            URL url=null;
            URI uri=null;




            @Override
            public void onClick(View v) {

                Log.d(TAG, "button clicked: params = [" + "context is null" + "]");
                Intent activity = new Intent(v.getContext(), Details.class);
                activity.putExtra("name",getItemname(position));
                activity.putExtra("id",getItemid(position));
                activity.putExtra("profile_pic",getItemPic(position));
                activity.putExtra("typo",typo);
                activity.putExtra("isFavorite",true);
                v.getContext().startActivity(activity);


            }
        });

        Picasso.with(mContext).load(imageUrl).into(thumbnail);

        return rowView;
    }
}
