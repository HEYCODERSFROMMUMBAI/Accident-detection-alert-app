package com.example.a24hremergencyservices;


import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.telephony.SmsManager;


import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import org.w3c.dom.Text;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DrivingMode extends AppCompatActivity implements SensorEventListener{



    private static final int REQUEST_CALL = 1;

    TextView callText8;
    private TextView callText7;

    AlertDialog.Builder askDialog;



    CountDownTimer timer;
    AlertDialog dialog;
    String string;


    Boolean isSense = false;
    private FusedLocationProviderClient client;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drivingmode);
        requestPermission();
        client = LocationServices.getFusedLocationProviderClient(this);



        callText7 = findViewById(R.id.callTxt7);
        callText8 = findViewById(R.id.callTxt8);

//        string=getIntent().getExtras().getString("value");
//        callText8.setText(sted);


       askDialog = new AlertDialog.Builder(DrivingMode.this);
       askDialog.setTitle("ALERT!");
       askDialog.setIcon(R.drawable.baseline_add_alert_24);
       askDialog.setMessage("Are you FINE");
       askDialog.setPositiveButton("NO", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {
               String number = callText8.getText().toString();

               if (number.trim().length() > 2) {
                   if (ContextCompat.checkSelfPermission(DrivingMode.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                       ActivityCompat.requestPermissions(DrivingMode.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
                   } else {
                       String dial = "tel:" + number;
                       startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
                   }
               }
           }
       });
       askDialog.setNegativeButton("YES", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {
               Toast.makeText(DrivingMode.this, "Call action", Toast.LENGTH_SHORT).show();
           }
       });
//        askDialog.show();

//       custom alert for yes no

         dialog = new AlertDialog.Builder(this)
                .setTitle("ALERT")
                 .setIcon(R.drawable.baseline_notifications_24)
                .setMessage("Is it an Accident")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO: Add positive button action code here
                        MakeCall();
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            private static final int AUTO_DISMISS_MILLIS = 30000;
            @Override
            public void onShow(final DialogInterface dialog) {
                final Button defaultButton = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                final CharSequence negativeButtonText = defaultButton.getText();
                new CountDownTimer(AUTO_DISMISS_MILLIS, 100) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        defaultButton.setText(String.format(
                                Locale.getDefault(), "%s (%d)",
                                negativeButtonText,
                                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) + 1 //add one so it never displays zero
                        ));
                    }
                    @Override
                    public void onFinish() {
                        if (((AlertDialog) dialog).isShowing()) {
                            dialog.dismiss();
                            isSense = false;
                            String msg = "I Need Your Help ! \n Please take me from :\n https://maps.google.com/?q=19.2091236,73.0990806";
                            SmsManager sms = SmsManager.getDefault();
                            sms.sendTextMessage("+919869669668", null, msg, null, null);

                            Toast.makeText(DrivingMode.this, "Message Sent successfully!",
                                    Toast.LENGTH_LONG).show();
                    }
                    }
                }.start();
            }
        });

//        sensor code
        SensorManager sensorManager =(SensorManager) getSystemService(SENSOR_SERVICE);

        if(sensorManager!=null){

            Sensor acceleroSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

            if (acceleroSensor!=null){
                sensorManager.registerListener(this, acceleroSensor, SensorManager.SENSOR_DELAY_NORMAL);
            }

        }else {
            Toast.makeText(this, "Accelerometer Sensor Service Not Detected", Toast.LENGTH_SHORT).show();
        }
//BackButton
        ImageView leftIcon = findViewById(R.id.left_icon);
        TextView title = findViewById(R.id.toolbar_title);

        leftIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in2=new Intent(DrivingMode.this,Home_Page.class);
                startActivity(in2);
            }
        });

        title.setText("Detection Mode");

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            if(event.values[0] > 10 && event.values[1] > 10 && event.values[2] > 10 && isSense == false){

                isSense = true;
                dialog.show();

//                df.show(getSupportFragmentManager(), "dialogfragment");

            }
            ((TextView)findViewById(R.id.acc_values)).setText("X: "+event.values[0] +", Y: "+event.values[1] + ", Z: "+event.values[2]);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        Log.d("sensor ", String.valueOf(sensor));
        Log.d("Accuracy " , String.valueOf(accuracy));
    }
//call function for alert box

   public void MakeCall(){


       String number = callText7.getText().toString();

        if (number.trim().length() > 2) {
            if (ContextCompat.checkSelfPermission(DrivingMode.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(DrivingMode.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        }
    }

//    request permission
private void requestPermission(){
    ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, 1);
}
}