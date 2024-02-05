package com.example.a24hremergencyservices;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class emergencyContact extends AppCompatActivity {

    TextView textView;
    Button b12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_contact);

        b12=findViewById(R.id.button12);
        b12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(emergencyContact.this,Home_Page.class);
                startActivity(intent);
            }
        });

        textView=findViewById(R.id.appCompatEditText1);
        String string = getIntent().getExtras().getString("username");
        textView.setText(string);
    }
}