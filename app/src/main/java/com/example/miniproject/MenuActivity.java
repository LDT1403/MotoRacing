package com.example.miniproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnStart;
    private Button btnTutotial;
    private Button btnExit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        btnStart =(Button) findViewById(R.id.buttonStart);
        btnTutotial=(Button) findViewById(R.id.buttonTutorial);
        btnExit=(Button) findViewById(R.id.buttonExit);

        btnStart.setOnClickListener(this);
        btnTutotial.setOnClickListener(this);
        btnExit.setOnClickListener(this);

    }
    private void Start() {
        Intent intent = new Intent(this, MainActivity.class);
        String username = getIntent().getStringExtra("username");
        intent.putExtra("username", username);
        startActivity(intent);
        finish();
    }
    private void Tutorial() {
        Intent intent = new Intent(this, TutorialActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonStart) {
            Start();
        }
        if (v.getId() == R.id.buttonTutorial) {
            Tutorial();
        }
        if (v.getId() == R.id.buttonExit) {

        }
    }
}
