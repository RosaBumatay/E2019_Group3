package com.example.medreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout button2;
    private RelativeLayout button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (RelativeLayout) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegisterForm();
            }
        });

        button2 = (RelativeLayout) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginForm();
            }
        });

    }
    public void openLoginForm() {
        Intent intent = new Intent(this, LoginForm.class);
        startActivity(intent);
    }
    public void openRegisterForm() {
        Intent intent = new Intent(this, RegisterForm.class);
        startActivity(intent);
    }
}