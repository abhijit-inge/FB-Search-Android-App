package com.example.a12345.fbsearch;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;

/**
 * Created by a12345 on 4/27/17.
 */

public class MyLayoutInflator {

    public void inflate(Activity activity, int LayoutResource, android.support.v7.app.ActionBar getSupportActionBar, Intent getIntent){
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) activity.findViewById(R.id.coordin);
        android.view.LayoutInflater inflater = (android.view.LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(LayoutResource, null, false);

        //change i so that it suits the child number in you coordinator layout
        int i = 1;
        coordinatorLayout.removeViewAt(i);
        coordinatorLayout.addView(contentView, i);
       // getSupportActionBar.setTitle(actionBarTitle);
    }

    public void inflate(Activity activity, int LayoutResource, android.support.v7.app.ActionBar getActionBar, String actionBarTitle){
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) activity.findViewById(R.id.coordin);
        android.view.LayoutInflater inflater = (android.view.LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(LayoutResource, null, false);

        //change i so that it suits the child number in you coordinator layout
        int i = 1;
        coordinatorLayout.removeViewAt(i);
        coordinatorLayout.addView(contentView, i);
        getActionBar.setTitle(actionBarTitle);
    }

}