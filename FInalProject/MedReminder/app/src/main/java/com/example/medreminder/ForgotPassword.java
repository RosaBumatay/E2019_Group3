package com.example.medreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ForgotPassword extends AppCompatActivity {

EditText password,email;
Button forgot_password;
String Password,Email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

password = (EditText) findViewById(R.id.Change_Password);
email = (EditText)  findViewById(R.id.Change_Email);

forgot_password = (Button) findViewById(R.id.Forgot_Submit);


forgot_password.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

Password = password.getText().toString();
Email = email.getText().toString();
BackGround b = new BackGround();
b.execute(Password,Email);

    }
});

    }

    class BackGround extends AsyncTask<String,String,String>{


        @Override
        protected String doInBackground(String...params) {

    String password = params[0];
    String email = params[1];
    String data="";
    int tmp;

    try {
       URL url = new URL("https://thesisgaming.000webhostapp.com/forgot.php");
       String urlparams="password="+password+"&email="+email;

        HttpURLConnection hurl =(HttpURLConnection) url.openConnection();
        hurl.setDoOutput(true);
        OutputStream os = hurl.getOutputStream();
        os.write(urlparams.getBytes());
        os.flush();
        os.close();

        InputStream is = hurl.getInputStream();
        while((tmp=is.read())!=1){

         data +=(char)tmp;

        }
is.close();
hurl.disconnect();
return data;


    }catch (MalformedURLException e){
e.printStackTrace();
return e.getMessage();

    }catch(IOException e){

        e.printStackTrace();
        return e.getMessage();


    }


        }
        @Override
        protected void onPostExecute(String s){

if (s.equals("")){

    s = "Data not updated";


}
            Toast.makeText(ForgotPassword.this, "Password Updated Successfully !!..", Toast.LENGTH_SHORT).show();

        }


    }



}