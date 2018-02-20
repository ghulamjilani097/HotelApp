package com.memor.thinkers.jilani.hotelapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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
    EditText btn_email, btn_password;
    RequestQueue rq;
    Button loginbtn;
    String loginUrl="https://hamarahotel.000webhostapp.com/registration/hotellogin.php";
    ProgressDialog progressDialog;
    String name1,email1,password1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        rq = Volley.newRequestQueue(Login.this);
        loginbtn = (Button) findViewById(R.id.login);
        btn_email = (EditText) findViewById(R.id.loginemail);
        btn_password = (EditText) findViewById(R.id.loginpass);
        progressDialog = new ProgressDialog(Login.this);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
    }

    public void loginUser()
    {
        email1 = btn_email.getText().toString();
        password1 = btn_password.getText().toString();


        if (TextUtils.isEmpty(email1)) {
            btn_email.setError("Enter Your Email");
            btn_email.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password1)) {
            btn_password.setError("Enter a password");
            btn_password.requestFocus();
            return;
        }
        progressDialog.setMessage("Please Wait.....");
        progressDialog.setIndeterminate(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, loginUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("msg", "Register Response: " + response.toString());
                        progressDialog.dismiss();
                        try {
                            JSONObject jObj = new JSONObject(response);
                            String msg = jObj.getString("success");

                            if (msg.equals("1"))
                            {
                                Toast.makeText(Login.this, "Response: "+response, Toast.LENGTH_SHORT).show();
                                Toast.makeText(Login.this, "Logged In Successfully!!", Toast.LENGTH_SHORT).show();
                                Intent myIntent = new Intent(Login.this, Homepage.class);
                                startActivity(myIntent);
                                finish();
                            }
                            else
                            {
                                String errorMsg = jObj.getString("message");
                                Toast.makeText(Login.this, errorMsg, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hide();
                Log.e("msg", "Login Error: " + error.getMessage());
                Toast.makeText(Login.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email1);
                params.put("password", password1);
                return params;
            }
        };
        rq.add(stringRequest);
    }
}
