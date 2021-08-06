package com.example.mysocialmedia.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.mysocialmedia.fragment.PostFragment;
import com.example.mysocialmedia.Model.User.User;
import com.example.mysocialmedia.R;
import com.example.mysocialmedia.activities.HomeActivity;
import com.example.mysocialmedia.activities.ProfileActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class BottomNavigationViewHelper {

    public static void setupBottomNavigationView(final Context context, BottomNavigationView bottomNavigationView, User user, int ACTIVITY_NO){
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigationHome:
                        Intent intentHome = HomeActivity.newIntent((Activity) context,user).setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        context.startActivity(intentHome);
                        return true;

                    case R.id.navigationPost:
                        DialogFragment postFragment = new PostFragment(context,user);

                        if (ACTIVITY_NO == 0){
                            postFragment.show(((HomeActivity) context).getSupportFragmentManager(),"fragment");
                        }else{
                            postFragment.show(((ProfileActivity) context).getSupportFragmentManager(),"fragment");
                        }
                        return true;
                    case R.id.navigationProfile:
                        Intent intentPost = ProfileActivity.newIntent((Activity) context,user).setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        context.startActivity(intentPost);
                        return true;
                    default:
                        return false;
                }
            }
        });

    }
}
