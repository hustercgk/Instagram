package com.example.huster.instagram.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.huster.instagram.R;

public class Login_Activity extends Activity {
    String url = "http://219.223.196.213:8080/pkuServlet/pkuServlet";
    EditText etUsername, etPassword;
    ImageView imgLogo;
    Button btLogin, btCreateAccount;
    int screenWidth, screenHeight;
    String username = "", password = "";
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        screenHeight = getResources().getDisplayMetrics().heightPixels;
        CheckCache();
        InitWidgets();
    }
    void CheckCache(){
        SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        username = sharedPreferences.getString("username", "");
        password = sharedPreferences.getString("password", "");
        if(!username.equals("") && !password.equals("")){
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
    void InitWidgets(){
        etUsername = (EditText)findViewById(R.id.username);
        etPassword = (EditText)findViewById(R.id.password);
        imgLogo = (ImageView)findViewById(R.id.logo);
        btLogin = (Button)findViewById(R.id.button_login);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = etUsername.getText().toString(), s2 = etPassword.getText().toString();
                if(!s1.equals("") && !s2.equals("")){
                    if(s1.equals(username) && s2.equals(password)){
                        Intent intent = new Intent(getBaseContext(), MainActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(getBaseContext(), "用户名或密码错误", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getBaseContext(), "用户名和密码不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btCreateAccount = (Button)findViewById(R.id.button_create);
        btCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username", "huster");
                editor.putString("password", "kang");
                editor.commit();
                Toast.makeText(getBaseContext(), "Create Successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        etUsername.setWidth((int)(screenWidth*0.75));
        etPassword.setWidth((int) (screenWidth * 0.75));
        ViewGroup.LayoutParams layoutParams = imgLogo.getLayoutParams();
        layoutParams.width = screenWidth/2;
        imgLogo.setLayoutParams(layoutParams);
        btLogin.setWidth((int) (screenWidth * 0.75));
        btCreateAccount.setWidth((int) (screenWidth * 0.75));
    }
}
