package com.memor.thinkers.jilani.hotelapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity
{
    EditText email,loginpass;
    Button login;
    RequestQueue requestQueue;
    String loginUrl="https://hamarahotel.000webhostapp.com/registration/hotellogin.php";
    String emailstr,passstr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email=findViewById(R.id.email);
        loginpass=findViewById(R.id.loginpass);
        requestQueue= Volley.newRequestQueue(this);

        login=findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
    }

    public void loginUser() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, loginUrl, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
//                Log.d("Success","S:");
                emailstr=email.getText().toString();
                passstr=loginpass.getText().toString();



                try {
                     JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    String msg = jsonObject.getString("success");
                    Toast.makeText(Login.this, "jilani", Toast.LENGTH_SHORT).show();
//                    Log.d("Success","S:"+ msg);

                    if(msg.equals("1")) {
                        Toast.makeText(Login.this, "Login Successfully.", Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(Login.this,Homepage.class);
                        startActivity(i);
                        finish();
                    }
                    else if(msg.equals("0")){
                        Toast.makeText(Login.this, "Login Failed!!", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Login.this, "Oops, Something went wrong.", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", emailstr);
                params.put("password", passstr);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
