package com.example.a24hremergencyservices;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class EmergencyService extends AppCompatActivity {


    private static final int REQUEST_CALL = 1;
    private TextView callText, callText1, callText2, callText3, callText4, callText5;
    private Button callBtn, callBtn1, callBtn2, callBtn3, callBtn4, callBtn5;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emergencyservice);

        callText = findViewById(R.id.callTxt);
        callText1 = findViewById(R.id.callTxt1);
        callText2 = findViewById(R.id.callTxt2);
        callText3 = findViewById(R.id.calleTxt);
        callText4 =findViewById(R.id.callTxt3);
        callText5 = findViewById(R.id.callTxt4);

        callBtn = findViewById(R.id.callButton);
        callBtn1 = findViewById(R.id.callButton1);
        callBtn2 = findViewById(R.id.callButton2);
        callBtn3 = findViewById(R.id.callButton3);
        callBtn4 = findViewById(R.id.callButton4);
        callBtn5 = findViewById(R.id.callButton5);

        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CallButton();
            }
        });

        callBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CallButton1();
            }
        });

        callBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CallButton2();
            }
        });

        callBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CallButton3();
            }
        });

        callBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CallButton4();
            }
        });
        callBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CallButton5();
            }
        });
    }

    private void CallButton() {
        String number = callText.getText().toString();
        if (number.trim().length() > 2) {
            if (ContextCompat.checkSelfPermission(EmergencyService.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(EmergencyService.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        }
    }

    private void CallButton1() {
        String number = callText1.getText().toString();
        if (number.trim().length() > 2) {
            if (ContextCompat.checkSelfPermission(EmergencyService.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(EmergencyService.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        }
    }

    private void CallButton2() {
        String number = callText2.getText().toString();
        if (number.trim().length() > 2) {
            if (ContextCompat.checkSelfPermission(EmergencyService.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(EmergencyService.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        }
    }

    private void CallButton3() {
        String number = callText3.getText().toString();
        if (number.trim().length() > 2) {
            if (ContextCompat.checkSelfPermission(EmergencyService.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(EmergencyService.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        }
    }

    private void CallButton4() {
        String number = callText4.getText().toString();
        if (number.trim().length() > 2) {
            if (ContextCompat.checkSelfPermission(EmergencyService.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(EmergencyService.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        }
    }

    private void CallButton5() {
        String number = callText5.getText().toString();
        if (number.trim().length() > 2) {
            if (ContextCompat.checkSelfPermission(EmergencyService.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(EmergencyService.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                CallButton();
                CallButton1();
                CallButton2();
                CallButton3();
                CallButton4();
                CallButton5();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }
}