package com.example.getimei;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView imeiTV;
    private TelephonyManager telephonyManager;
    public static final int REQUEST_CODE = 1;
    private String imei;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imeiTV = findViewById(R.id.imeiTV);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.READ_PHONE_STATE}, REQUEST_CODE);
        }else {
            telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
            if (telephonyManager == null){
                Toast.makeText(this, "Manager not support!", Toast.LENGTH_SHORT).show();
            }else {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    imei = telephonyManager.getImei();
                }else {
                    imei = telephonyManager.getDeviceId();
                }
            }
        }

        imeiTV.setText(imei);
    }
}
