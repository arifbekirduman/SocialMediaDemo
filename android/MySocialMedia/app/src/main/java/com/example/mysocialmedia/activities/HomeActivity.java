package com.example.mysocialmedia.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mysocialmedia.Model.Post.PostResponse;
import com.example.mysocialmedia.adapter.PostAdapter;
import com.example.mysocialmedia.api.ApiUtils;
import com.example.mysocialmedia.fragment.CommentFragment;
import com.example.mysocialmedia.utils.BottomNavigationViewHelper;
import com.example.mysocialmedia.Model.User.User;
import com.example.mysocialmedia.listener.MessageListener;
import com.example.mysocialmedia.R;
import com.example.mysocialmedia.api.APIService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements MessageListener {
    private static final String KEY_USER = "KEY_USER";
    private User user;
    BottomNavigationView bottomNavigationView;
    RecyclerView recyclerView;
    private APIService apiService;
    private int ACTIVITY_NO = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        apiService = ApiUtils.getAPIService();

        init();

        if (getIntent().hasExtra(KEY_USER)){
            user = (User) getIntent().getSerializableExtra(KEY_USER);
        }

        setupNavigationView();
        getPostApiService();


    }

    private void getPostApiService() {
        apiService.getAllPost().enqueue(new Callback<List<PostResponse>>() {
            @Override
            public void onResponse(Call<List<PostResponse>> call, Response<List<PostResponse>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        setupRecyclerView(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<PostResponse>> call, Throwable t) {
                System.out.println("getPostApiService Error : " + t.getMessage());
            }
        });
    }

    private void setupRecyclerView(List<PostResponse> postResponses) {

        PostAdapter postAdapter = new PostAdapter(this, postResponses,user);
        recyclerView.setAdapter(postAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void init() {
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        recyclerView = findViewById(R.id.recyclerViewHomeActivity);
    }

    private void setupNavigationView() {
        BottomNavigationViewHelper.setupBottomNavigationView(this, bottomNavigationView,user, ACTIVITY_NO);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NO);
        menuItem.setChecked(true);

    }

    public static Intent newIntent(Activity callerActivity, User user){
        Intent intent=new Intent(callerActivity, HomeActivity.class).setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.putExtra(KEY_USER, user);
        return intent;
    }


    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickShowComments(UUID postId) {
        DialogFragment dialogFragment = new CommentFragment(getApplicationContext(),postId,user);
        dialogFragment.show(getSupportFragmentManager(),"CommentsDialogFragment");
    }
}