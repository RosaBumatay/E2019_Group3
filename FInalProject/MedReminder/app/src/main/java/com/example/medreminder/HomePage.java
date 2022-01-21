package com.example.medreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HomePage extends AppCompatActivity {


    Button LogOut;
    TextView EmailShow;
    String EmailHolder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_home_page);
        LogOut = (Button)findViewById(R.id.button);
        EmailShow = (TextView)findViewById(R.id.EmailShow);


        Intent intent = getIntent();
        EmailHolder = intent.getStringExtra(LoginForm.UserEmail);
        EmailShow.setText(EmailHolder);




        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

                Intent intent = new Intent(HomePage.this, LoginForm.class);

                startActivity(intent);

                Toast.makeText(HomePage.this, "Log Out Successfully", Toast.LENGTH_LONG).show();




            }
        });

    }
}