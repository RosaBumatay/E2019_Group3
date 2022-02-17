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
import android.widget.Toast;

import java.util.HashMap;

public class ForgotPassword extends AppCompatActivity {


    Button forgot,forgot_back;
    EditText email, password;
    String PasswordHolder, EmailHolder;
    String finalResult;
    String HttpURL = "https://e2019cc107grp3.000webhostapp.com/forgot.php";
    Boolean CheckEditText;
    ProgressDialog progressDialog;
    HashMap<String, String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    //String Password, Email;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);



        forgot = (Button) findViewById(R.id.Forgot_Submit);
        email = (EditText) findViewById(R.id.Change_Email);
        password = (EditText) findViewById(R.id.Change_Password);
        forgot_back = (Button) findViewById(R.id.Forgot_back);



        forgot_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ForgotPassword.this, LoginForm.class);
                startActivity(intent);


            }
        });


        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Password = password.getText().toString();
                //Email = email.getText().toString();
                // BackGround b = new BackGround();
                //b.execute(Email,Password);


                CheckEditTextIsEmptyOrNot();

                if (CheckEditText) {

                    UserLoginFunction(EmailHolder, PasswordHolder);

                } else {

                    Toast.makeText(ForgotPassword.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();

                }

            }
        });


    }

    public void CheckEditTextIsEmptyOrNot() {

        EmailHolder = email.getText().toString();
        PasswordHolder = password.getText().toString();

        if (TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder)) {
            CheckEditText = false;
        } else {

            CheckEditText = true;
        }
    }


    public void UserLoginFunction(final String email, final String password) {

        class UserLoginClass extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(ForgotPassword.this, "Loading Data", null, true, true);
            }


            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();
                Boolean s = httpResponseMsg.equalsIgnoreCase("Data Matched");
                if (s == true ) {
                    Toast.makeText(ForgotPassword.this, "Data successfull", Toast.LENGTH_SHORT).show();

                    finish();



                } else {

                    Toast.makeText(ForgotPassword.this, httpResponseMsg, Toast.LENGTH_LONG).show();
                }

            }


            @Override
            protected String doInBackground(String... params) {

                hashMap.put("email", params[0]);

                hashMap.put("password", params[1]);

                finalResult = httpParse.postRequest(hashMap, HttpURL);

                return finalResult;
            }
        }


        UserLoginClass userLoginClass = new UserLoginClass();

        userLoginClass.execute(email, password);
    }

}


//class BackGround extends AsyncTask<String, String, String> {


//@Override
//protected String doInBackground(String... params) {

//String email = params[0];
// String password = params[1];
// String data = "";
//int tmp;

//try {
//URL url = new URL("https://e2019cc107grp3.000webhostapp.com/forgot.php");
//String urlparams = "email=" + email + "&password=" + password;

//HttpURLConnection hurl = (HttpURLConnection) url.openConnection();
//hurl.setDoOutput(true);
//  OutputStream os = hurl.getOutputStream();
//  os.write(urlparams.getBytes());
// os.flush();
//os.close();

// InputStream is = hurl.getInputStream();
//   while ((tmp = is.read()) != 1) {

//  data += (char) tmp;

//   }
//  is.close();
//  hurl.disconnect();
//  return data;


//     } catch (MalformedURLException e) {
//       e.printStackTrace();
//    return e.getMessage();

//  } catch (IOException e) {
//       e.printStackTrace();
//  return e.getMessage();


// }


//}

//  @Override
//  protected void onPostExecute(String s) {

//  if (s.equals("")) {

//    s = "Data not updated";


// }
// Toast.makeText(ForgotPassword.this, "Password Updated Successfully !!..", Toast.LENGTH_SHORT).show();

// }