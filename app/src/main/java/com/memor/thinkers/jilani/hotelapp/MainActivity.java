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

public class MainActivity extends AppCompatActivity {

    Button signup,login;
    EditText name,password,email;
    String user1,pass1,email1;
    String urlRegister="https://hamarahotel.000webhostapp.com/registration/hotelregistration.php";
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
//                registerUser();
//                Toast.makeText(MainActivity.this, "jilani", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(MainActivity.this,Homepage.class);
                startActivity(i);
                Log.d("dorami123", "onClick: "+"abc");
                finish();
            }
        });

        /*login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,Homepage.class);
                startActivity(i);
                finish();
                //loginUser();
            }
        });*/
    }

    public void registerUser()
    {
        user1=name.getText().toString();
        pass1=password.getText().toString();
        email1=email.getText().toString();

        StringRequest stringRequest=new StringRequest(Request.Method.POST, urlRegister, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray=new JSONArray(response);
                    JSONObject jsonObject=jsonArray.getJSONObject(0);
                    String abc=jsonObject.getString("msg");

                    Toast.makeText(MainActivity.this, ""+abc, Toast.LENGTH_SHORT).show();
                    Log.d("dorami123", "onResponse: "+abc);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            @Override
            public Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<String, String>();
                //Toast.makeText(MainActivity.this, "Jilani", Toast.LENGTH_SHORT).show();
                params.put("name", user1);
                params.put("email", email1);
                params.put("password", pass1);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

//    public  void loginUser()
//    {
//
//    }*/
}
