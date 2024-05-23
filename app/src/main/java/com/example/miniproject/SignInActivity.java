package com.example.miniproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText txtUserName;

    private EditText txtPassWord;

    private Button btnSignIn;


    private final String REQUIRE = "Require";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        txtUserName =(EditText) findViewById(R.id.editUserName);
        txtPassWord =(EditText) findViewById(R.id.editPassword);
        btnSignIn =(Button) findViewById(R.id.buttonSignIn);

        btnSignIn.setOnClickListener(this);

    }
    private boolean checkInput(){
        if (TextUtils.isEmpty(txtUserName.getText().toString())){
            txtUserName.setError(REQUIRE);
            return false;
        }

        if (TextUtils.isEmpty(txtPassWord.getText().toString())){
            txtPassWord.setError(REQUIRE);
            return false;
        }
        return true;
    }
    private void signIn() {
        if (!checkInput()) {
            return;
        }

        if ((TextUtils.equals(txtUserName.getText().toString(), "admin"))
                && (TextUtils.equals(txtPassWord.getText().toString(), "12345"))) {
            Intent intent = new Intent(this, MenuActivity.class);
            intent.putExtra("username", txtUserName.getText().toString());
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Wrong UserName or Password", Toast.LENGTH_SHORT).show();
            Reset();
        }
    }

    private void Reset(){
        txtUserName.setText("");
        txtPassWord.setText("");
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonSignIn) {
            signIn();
        }
    }
}
