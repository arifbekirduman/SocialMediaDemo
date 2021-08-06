package com.example.mysocialmedia.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.mysocialmedia.Model.User.User;
import com.example.mysocialmedia.Model.User.UserResponse;
import com.example.mysocialmedia.api.APIService;
import com.example.mysocialmedia.api.ApiUtils;
import com.example.mysocialmedia.utils.BottomNavigationViewHelper;
import com.example.mysocialmedia.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    private static final String KEY_USER = "KEY_USER";
    private User user;
    TextView tvUserName, tvUserEmail, tvUserNick;
    BottomNavigationView bottomNavigationView;
    private int ACTIVITY_NO = 2;
    APIService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        init();
        if (getIntent().hasExtra(KEY_USER)){
            user = (User) getIntent().getSerializableExtra(KEY_USER);
        }
        setupNavigationView();
        getUserInfo();


    }

    private void getUserInfo() {
        apiService.getUser(1L).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful() && response.body() != null){
                    setUserInfo(response.body());
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });
    }

    private void setUserInfo(UserResponse user) {
        tvUserEmail.setText(user.getEmail());
        tvUserNick.setText(user.getUserName());
        tvUserName.setText(user.getFirstName()+ " " + user.getLastName());
    }

    private void init(){
        bottomNavigationView = findViewById(R.id.bottomNavigationViewProfileActivity);
        tvUserEmail = findViewById(R.id.tvUserEmailProfleActivity);
        tvUserName = findViewById(R.id.tvUserNameProfileActivity);
        tvUserNick = findViewById(R.id.tvUserNickProfileActivity);
        apiService = ApiUtils.getAPIService();
    }

    private void setupNavigationView() {
        BottomNavigationViewHelper.setupBottomNavigationView(this, bottomNavigationView,user, ACTIVITY_NO);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NO);
        menuItem.setChecked(true);

    }
    public static Intent newIntent(Activity callerActivity, User user){
        Intent intent=new Intent(callerActivity, ProfileActivity.class);
        intent.putExtra(KEY_USER, user);
        return intent;
    }
}