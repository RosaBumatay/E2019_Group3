package com.example.medreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RegisterForm extends AppCompatActivity {

    private TextView textView14;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_form);

        textView14 = (TextView) findViewById(R.id.textView14);
        textView14.setOnClickListener(new View.OnClickListener() {
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
}