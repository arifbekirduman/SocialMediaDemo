package com.example.mysocialmedia.activities.auth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mysocialmedia.api.ApiUtils;
import com.example.mysocialmedia.Model.User.User;
import com.example.mysocialmedia.Model.User.UserLogin;
import com.example.mysocialmedia.R;
import com.example.mysocialmedia.activities.HomeActivity;
import com.example.mysocialmedia.api.APIService;
import com.example.mysocialmedia.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignInActivity extends AppCompatActivity {
    private static final String KEY_USER = "KEY_USER";
    private User user;
    EditText etUserEmailorNick, etUserPassword;
    Button btnLogIn;
    private APIService apiService;
    private String userEmailorNick, userPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        if (getIntent().hasExtra(KEY_USER)){
            user = (User) getIntent().getSerializableExtra(KEY_USER);
        }

        init();


        apiService = ApiUtils.getAPIService();

    }

    private void init() {
        etUserEmailorNick = findViewById(R.id.inputEmail);
        etUserPassword = findViewById(R.id.inputPassword);
        btnLogIn = findViewById(R.id.btnNextSignInActivity);
        if (user != null){
            etUserEmailorNick.setText(user.getEmail());
        }

    }

    public void userRegisterOnClick(View view) {
        startActivity(new Intent(this, SignUpActivity.class));
    }

    public static Intent newIntent(Activity callerActivity, User user){
        Intent intent=new Intent(callerActivity, SignInActivity.class);
        intent.putExtra(KEY_USER, user);
        return intent;
    }


    public void signInUserOnClick(View view) {
        if (etUserEmailorNick.getText().toString().isEmpty() || etUserPassword.getText().toString().isEmpty()){
            Utils.makeToast("Boş alanları doldurunuz!",getApplicationContext());
        }else {
            userEmailorNick = etUserEmailorNick.getText().toString().trim();
            userPassword = etUserPassword.getText().toString().trim();
            UserLogin user = new UserLogin();
            user.setEmail(userEmailorNick);
            user.setPassword(userPassword);

            apiService.userLogin(user).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful()){
                        if (response.body() != null){
                            signInUser(response.body());
                        }
                        else {
                            System.out.println("User login if durumunda");
                            Utils.makeToast("Kullanıcı adı ya da şifre hatalı!",getApplicationContext());
                        }
                    }
                    else {
                        System.out.println("User login else durumunda");
                        Utils.makeToast("Kullanıcı adı ya da şifre hatalı!",getApplicationContext());
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Utils.makeToast("Kullanıcı adı ya da şifre hatalı!",getApplicationContext());
                    System.out.println("User login error ->" + t.getMessage());
                }
            });

        }

    }

    private void signInUser(User user) {
        if (user != null){
            this.user = user;
            Intent intent = HomeActivity.newIntent(SignInActivity.this,user);
            startActivity(intent);
        }else {
            Utils.makeToast("Kullanıcı adı ya da şifre hatalı!",getApplicationContext());
        }

    }

}