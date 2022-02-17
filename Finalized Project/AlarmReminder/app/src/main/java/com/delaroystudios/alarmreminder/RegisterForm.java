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

public class RegisterForm extends AppCompatActivity {

    private TextView log_in;
    Button register;
    EditText Full_Name, Email, Password;
    String Full_Name_Holder, EmailHolder, PasswordHolder;
    String finalResult;
    String HttpURL = "https://e2019cc107grp3.000webhostapp.com/UserRegistration.php";
    Boolean CheckEditText;
    ProgressDialog progressDialog;
    HashMap<String, String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_form);

        log_in = (TextView) findViewById(R.id.Log_in);
        Full_Name = (EditText) findViewById(R.id.full_name);
        Email = (EditText) findViewById(R.id.email);
        Password = (EditText) findViewById(R.id.password);

        register = (Button) findViewById(R.id.submit);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // Checking whether EditText is Empty or Not
                CheckEditTextIsEmptyOrNot();

                if (CheckEditText) {

                    // If EditText is not empty and CheckEditText = True then this block will execute.

                    UserRegisterFunction(Full_Name_Holder, EmailHolder, PasswordHolder);

                } else {

                    // If EditText is empty then this block will execute .
                    Toast.makeText(RegisterForm.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();

                }


            }


        });


        log_in.setOnClickListener(new View.OnClickListener() {
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

    public void CheckEditTextIsEmptyOrNot() {

        Full_Name_Holder = Full_Name.getText().toString();
        EmailHolder = Email.getText().toString();
        PasswordHolder = Password.getText().toString();


        if (TextUtils.isEmpty(Full_Name_Holder) || TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder)) {

            CheckEditText = false;

        } else {

            CheckEditText = true;
        }

    }


    public void UserRegisterFunction(final String F_Name,final String email, final String password) {

        class UserRegisterFunctionClass extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(RegisterForm.this, "Loading Data", null, true, true);
            }
            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();

                Toast.makeText(RegisterForm.this,httpResponseMsg.toString(), Toast.LENGTH_LONG).show();

            }


            @Override
            protected String doInBackground(String... params) {

                hashMap.put("full_name",params[0]);

                hashMap.put("email",params[1]);

                hashMap.put("password",params[2]);

                finalResult = httpParse.postRequest(hashMap, HttpURL);

                return finalResult;
            }

        }

        UserRegisterFunctionClass userRegisterFunctionClass = new UserRegisterFunctionClass();

        userRegisterFunctionClass.execute(F_Name,email,password);


    }



}