package com.memor.thinkers.jilani.hotelapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button b1;
    EditText user,pass;
    String user1,pass1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1=(Button)findViewById(R.id.signup);
        user=(EditText)findViewById(R.id.username);
        pass=(EditText)findViewById(R.id.pass);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user1=user.getText().toString();
                pass1=pass.getText().toString();

                Intent i=new Intent(MainActivity.this,Homepage.class);
                i.putExtra("username",user1);
                startActivity(i);
                finish();

                Toast.makeText(MainActivity.this, ""+user1+"-"+pass1, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
