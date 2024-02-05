package com.example.a24hremergencyservices;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText EditTextUserName,EditTextPassword;
    Button ButtonLogin;
//    CheckBox remember;
    String string;

    private DBHelper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);
        dbHelper.OpenDB();

        EditTextUserName = findViewById(R.id.appCompatEditText);
        EditTextPassword = findViewById(R.id.appCompatEditText1);
        ButtonLogin = findViewById(R.id.appCompatButton);
//        remember = findViewById(R.id.checkBox);
//        SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
//        String checkbox = preferences.getString("remember", "");
//        if (checkbox.equals("true")){
//            Intent ii = new Intent(MainActivity.this, emergencyContact.class);
//            startActivity(ii);
//
//        } else if (checkbox.equals("false")) {
//            Toast.makeText(this, "Please Sign In", Toast.LENGTH_SHORT).show();
//
//        }

        ButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,emergencyContact.class);
                startActivity(i);

                string = EditTextUserName.getText().toString();

                ArrayList<Cleaner> userDetails = dbHelper.LoginCleaner(EditTextUserName.getText().toString(), EditTextPassword.getText().toString());

                if (userDetails.size() != 0){
                    Cleaner cleaner = userDetails.get(0);
                    Toast.makeText(getApplicationContext(), "Valid User", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, emergencyContact.class);
                    intent.putExtra("username",string);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Invalid User",Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
//        remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (buttonView.isChecked()){
//                    SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
//                    SharedPreferences.Editor editor = preferences.edit();
//                    editor.putString("remember","true");
//                    editor.apply();
//                    Toast.makeText(MainActivity.this, "Checked", Toast.LENGTH_SHORT).show();
//
//                } else if (!buttonView.isChecked()) {
//
//                    SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
//                    SharedPreferences.Editor editor = preferences.edit();
//                    editor.putString("remember","false");
//                    editor.apply();
//                    Toast.makeText(MainActivity.this, "Unchecked", Toast.LENGTH_SHORT).show();
//
//
//                }
//            }
//        });
    }

    public void Register(View view){
        Intent intent = new Intent(this,Register_Page.class);
        startActivity(intent);
        finish();
    }
}