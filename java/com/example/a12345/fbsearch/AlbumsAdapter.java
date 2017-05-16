package com.example.a12345.fbsearch;

/**
 * Created by a12345 on 4/23/17.
 */

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;



public class AlbumsAdapter extends BaseExpandableListAdapter {
    private static final String TAG = "AlbumsAdapter";

    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<String>> _listDataChild;
    private List<String> posts;
    private List<String> pics_url;
    private List<Integer> no_pics;
    private List<String> messagetime;
    private List<String> albums;
    int count=0;
    public AlbumsAdapter(Context context, List<String> listDataHeader,
                                 List<String>posts,List<String> pics_url,List<Integer>no_pics,List<String> messagetime,List<String> albums) {
        this._context = context;
        this._listDataHeader = albums;
        this.posts = posts;
        this.pics_url = pics_url;
        this.no_pics = no_pics;
        this.messagetime = messagetime;
        this.albums=albums;

    }

    @Override
    public Object getChild(int count, int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(groupPosition);
    }

    public Object getObj(int count, int groupPosition) {
        return pics_url.get(count);
    }
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        int temp=0;
        String album_name1="";
        String album_name2="";
        int no_pics=getItemno_pics(groupPosition);
        for(int i=0;i<groupPosition;i++){
            temp+=getItemno_pics(i);
        }
        temp=temp+1;
        if(no_pics==2) {
              album_name1 = (String) getObj(temp, childPosition);
             album_name2 = (String) getObj(temp+1, childPosition);

        }
        else
        {
            if(no_pics==1) {
                 album_name1 = (String) getChild(temp, childPosition);

            }
        }
           count+=no_pics;
        Log.d(TAG, "group postition is "+ groupPosition+"temp is  = [" + temp + "no_pics is"+no_pics);


        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
        }

        ImageView thumbnail1 = (ImageView) convertView
                .findViewById(R.id.lblListItem);
        ImageView thumbnail2= (ImageView) convertView
                .findViewById(R.id.lblListItem2);
        if(!album_name1.equals("")) {
            Picasso.with(this._context).load(album_name1).into(thumbnail1);
        }
       if(!album_name2.equals("")) {
            Picasso.with(this._context).load(album_name2).into(thumbnail2);
        }

     //   txtListChild.setText(childText);
        return convertView;
    }
    public String getItemposts(int position) {
        return posts.get(position);
    }
    public String getItempics_url(int position) {
        return pics_url.get(position);
    }
    public Integer getItemno_pics(int position) {
        return no_pics.get(position);
    }
    public String getItemalbums(int position) {
        return albums.get(position);
    }
    public String getItemmessagetime(int position) {
        return messagetime.get(position);
    }


    @Override
    public int getChildrenCount(int groupPosition) {
        if(no_pics.get(groupPosition)==0)
            return 0;
        else
            if(no_pics.get(groupPosition)==1)
                return 1;
        else
            return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);


        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}