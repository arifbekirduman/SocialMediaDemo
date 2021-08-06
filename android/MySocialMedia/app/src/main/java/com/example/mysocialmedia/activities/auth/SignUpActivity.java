package com.example.mysocialmedia.activities.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mysocialmedia.api.ApiUtils;
import com.example.mysocialmedia.Model.User.User;
import com.example.mysocialmedia.Model.User.UserRegister;
import com.example.mysocialmedia.R;
import com.example.mysocialmedia.api.APIService;
import com.example.mysocialmedia.utils.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignUpActivity extends AppCompatActivity {
    EditText etUserNameAndSurname, etUserNick, etUserEmail, etUserPassword, etUserPasswordConfirm;
    TextView tvSignInBack;
    private APIService apiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        init();
        apiService = ApiUtils.getAPIService();
        tvSignInBack.setOnClickListener(v -> {
            onBackPressed();
        });

    }

    private void init() {
        etUserNameAndSurname = findViewById(R.id.inputUserNameAndSurname);
        etUserNick = findViewById(R.id.inputUserNick);
        etUserEmail = findViewById(R.id.inputUserEmail);
        etUserPassword = findViewById(R.id.inputUserPassword);
        etUserPasswordConfirm = findViewById(R.id.inputUserConfirmPassword);
        tvSignInBack = findViewById(R.id.textSignIn);
    }


    public void btnSignUpOnClick(View view) {

        if (etUserEmail.getText().toString().isEmpty() || etUserPassword.getText().toString().isEmpty() || etUserNameAndSurname.getText().toString().isEmpty()) {
            Utils.makeToast("Boş alanları doldurunuz!",getApplicationContext());
        } else if (etUserPassword.getText().toString().length() < 6) {
            Utils.makeToast("Şifreniz 6 karakterden az olamaz!",getApplicationContext());
        }
        else if (!isEmailValid(etUserEmail.getText().toString().trim())){
            Utils.makeToast("Emailinizi kontrol ediniz!",getApplicationContext());
        }
        else if(!etUserPassword.getText().toString().equals(etUserPasswordConfirm.getText().toString())){
            Utils.makeToast("Şifreler aynı değil!",getApplicationContext());
        }
        else {
            UserRegister userRegister = new UserRegister();
            String[] userNameAndSurname = splitUserNameAndSurname(etUserNameAndSurname.getText().toString());

            userRegister.setFirstName(userNameAndSurname[0]);
            userRegister.setLastName(userNameAndSurname[1]);
            userRegister.setEmail(etUserEmail.getText().toString().trim());
            userRegister.setUserNick(etUserNick.getText().toString().trim());
            userRegister.setPassword(etUserPassword.getText().toString());

            userRegister(userRegister);

        }

    }

    private String[] splitUserNameAndSurname(String userName)   {
        String[] userNameAndSurname = new String[2];

        String[] userNameArr = userName.split(" ");

        for (int i = 0; i < userNameArr.length; i++) {
            if (i != userNameArr.length-1){
                userNameAndSurname[0] = userNameArr[i] + " ";
            }
            else userNameAndSurname[1] = userNameArr[i];
        }
        return userNameAndSurname;
    }


    private void userRegister(UserRegister userRegister) {
        apiService.userRegister(userRegister).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful() && response.body() != null){
                    Utils.makeToast("Kayıt Oluşturuldu.",getApplicationContext());
                    moveToSignInActivity(userRegister.getEmail());
                }else {
                    Utils.makeToast("Bir hata ile karşılaşıldı!",getApplicationContext());
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println("User register Error ->" + t.getMessage() );
                Utils.makeToast("Bir hata ile karşılaşıldı!",getApplicationContext());
            }
        });
    }

    private void moveToSignInActivity(String email) {
        User user = new User();
        user.setEmail(email);
        Intent intent = SignInActivity.newIntent(SignUpActivity.this,user);
        startActivity(intent);
    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private void makeToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }

}