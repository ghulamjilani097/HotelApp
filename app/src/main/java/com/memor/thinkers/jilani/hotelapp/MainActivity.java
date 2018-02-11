package com.memor.thinkers.jilani.hotelapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button signup,login;
    EditText name,password,email;
    String user1,pass1,email1;
    String urlRegister="";
    String urlLogin="";
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signup=(Button)findViewById(R.id.signup);
        login=(Button)findViewById(R.id.login);
        name=(EditText)findViewById(R.id.name);
        password=(EditText)findViewById(R.id.pass);
        email=(EditText)findViewById(R.id.email);

        requestQueue= Volley.newRequestQueue(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
    }

    public void registerUser()
    {
        user1=name.getText().toString();
        pass1=password.getText().toString();
        email1=email.getText().toString();

        StringRequest stringRequest=new StringRequest(Request.Method.POST, urlRegister, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            @Override
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", user1);
                params.put("email", email1);
                params.put("password", pass1);
                return params;
            }
        };
    }

    public  void loginUser()
    {

    }
}
