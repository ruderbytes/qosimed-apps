package com.pondokprogrammer.qosim;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText editText_username,editText_password;
    TextView button_login,forgot_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editText_username = (EditText) findViewById(R.id.editText_username);
        editText_password = (EditText) findViewById(R.id.editText_password);
        button_login = (Button)findViewById(R.id.button_login);
        forgot_password = (TextView)findViewById(R.id.forgot_password);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/century_gothic.ttf");
        button_login.setTypeface(custom_font);
        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Forgot Password", Toast.LENGTH_SHORT).show();
            }
        });
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText_username.getText().toString().equals("demo")){
                    if(editText_password.getText().toString().equals("demo")){
                        Intent ins=new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(ins);
                        finish();
                    }else {
                        editText_password.setError("Password Salah");
                    }
                }else {
                    editText_username.setError("Username Tidak Terdaftar");
                }
            }
        });
    }
}
