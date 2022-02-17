package com.delaroystudios.alarmreminder;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class LoginForm extends AppCompatActivity {

    private TextView Notregister;
    TextView forgot;
    Button Log;
    EditText Email, Password;
    String PasswordHolder, EmailHolder;
    String finalResult ;
    String HttpURL = "https://e2019cc107grp3.000webhostapp.com/UserLogin.php";
    Boolean CheckEditText ;
    ProgressDialog progressDialog;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    public static final String UserEmail = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);

        forgot =(TextView) findViewById(R.id.btnforgot);
        Notregister = (TextView) findViewById(R.id.sign_up);
        Log = (Button) findViewById(R.id.Login);
        Email = (EditText)findViewById(R.id.editTextEmail);
        Password = (EditText)findViewById(R.id.editTextPassword);


        //Login button going to homepage
        Log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CheckEditTextIsEmptyOrNot();

                if(CheckEditText){

                    UserLoginFunction(EmailHolder, PasswordHolder);

                }
                else {

                    Toast.makeText(LoginForm.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();

                }

            }
        });



//if don't have an account
        Notregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openRegisterForm(); }

        });


//forgot password page
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),ForgotPassword.class);
                startActivity(intent);


            }
        });




    }

    public void openRegisterForm(){
        Intent intent = new Intent(this, RegisterForm.class);
        startActivity(intent);


    }


    public void CheckEditTextIsEmptyOrNot(){

        EmailHolder = Email.getText().toString();
        PasswordHolder = Password.getText().toString();

        if(TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder))
        {
            CheckEditText = false;
        }
        else {

            CheckEditText = true ;
        }
    }

    public void UserLoginFunction(final String email, final String password) {

        class UserLoginClass extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(LoginForm.this, "Loading Data", null, true, true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();

                if (httpResponseMsg.equalsIgnoreCase("Data Matched")) {

                    finish();

                    Intent intent = new Intent(LoginForm.this, MainActivity.class);

                    intent.putExtra(UserEmail, email);
                    startActivity(intent);

                } else {

                    Toast.makeText(LoginForm.this, httpResponseMsg, Toast.LENGTH_LONG).show();
                }

            }


            @Override
            protected String doInBackground(String... params) {

                hashMap.put("email",params[0]);

                hashMap.put("password",params[1]);

                finalResult = httpParse.postRequest(hashMap, HttpURL);

                return finalResult;
            }
        }

        UserLoginClass userLoginClass = new UserLoginClass();

        userLoginClass.execute(email,password);


    }


}