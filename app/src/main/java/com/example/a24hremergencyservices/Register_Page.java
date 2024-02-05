package com.example.a24hremergencyservices;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register_Page extends AppCompatActivity {

    EditText EditTextUserName,EditTextTel,EditTextEmail,EditTextPassword,EditTextConfirmPassword;

    private DBHelper dbHelper;
    Button ButtonRegister;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        dbHelper= new DBHelper(this);
        dbHelper.OpenDB();

        EditTextUserName = findViewById(R.id.appCompatEditText);
        EditTextTel = findViewById(R.id.appCompatEditText1);
        EditTextEmail = findViewById(R.id.appCompatEditText2);
        EditTextPassword = findViewById(R.id.appCompatEditText3);
        EditTextConfirmPassword = findViewById(R.id.appCompatEditText4);
        ButtonRegister = findViewById(R.id.appCompatButton);

        ButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                validateEmailAddress(EditTextEmail);


                if (EditTextUserName.getText().toString().isEmpty() || EditTextTel.getText().toString().isEmpty() || EditTextEmail.getText().toString().isEmpty() || EditTextPassword.getText().toString().isEmpty() || EditTextConfirmPassword.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Fields Can't be Blank", Toast.LENGTH_LONG).show();
                }
                else if (!EditTextPassword.getText().toString().equals(EditTextConfirmPassword.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Password and Confirm password should match Login",Toast.LENGTH_LONG).show();
                }
                else{
                    Cleaner cleaner = new Cleaner(EditTextUserName.getText().toString(),EditTextTel.getText().toString(), EditTextEmail.getText().toString(),EditTextPassword.getText().toString());
                    if (dbHelper.RegisterCleaner(cleaner)){
                        Toast.makeText(getApplicationContext(),"Registration Successfully Completed",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Register_Page.this,MainActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "User Registration Failed !", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

//    private boolean validateEmailAddress(EditText editTextEmail){
//        String emailInput = editTextEmail.getText().toString().trim();
//
//        if (!emailInput.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
//            Toast.makeText(this, "Email Validated Successfully!", Toast.LENGTH_SHORT).show();
//            return true;
//        }else {
//            Toast.makeText(this, "Invalid Email Address", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//    }

    public void Login(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}