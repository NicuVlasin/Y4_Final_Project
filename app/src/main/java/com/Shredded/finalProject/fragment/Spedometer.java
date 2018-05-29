package com.Shredded.finalProject.fragment;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.Shredded.finalProject.R;

public class Spedometer extends AppCompatActivity {

    static boolean status;
    LocationManager locationManager;
    static TextView distance, time;

    Button btnStart, btnPause, btnStop;
    static long startTime, endTime;
    static ProgressDialog progressDialog;
    static int p = 0;

    LocationService myService;

    ServiceConnection sc = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LocationService.LocalBinder binder = (LocationService.LocalBinder) service;
            myService = binder.getService();
            status = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            status = false;
        }
    };

    @Override
    public void onDestroy() {
        if (status == true)
            unbindService();
        super.onDestroy();
    }

    private void unbindService() {
        if (status == false)
            return;
        Intent i = new Intent(getApplicationContext(), LocationService.class);
        unbindService(sc);
        status = false;

    }

    public void onBackPressed() {
        if (status == true) {
            super.onBackPressed();
        } else
            moveTaskToBack(true);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1000: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    Toast.makeText(this, "GRANTED", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, "DENIED", Toast.LENGTH_SHORT).show();
            }
            return;
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spedometer);


        //Request permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
            }, 1000);
        }

        //Init variable
        distance = findViewById(R.id.distance);
        time = findViewById(R.id.time);

        btnPause = findViewById(R.id.btnPause);
        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkGPS();
                locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
                if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
                    return;
                if (status == false) {
                    bindService();
                }
                    progressDialog = new ProgressDialog(Spedometer.this);
                    progressDialog.setIndeterminate(true);
                    progressDialog.setCancelable(false);
                    progressDialog.setMessage("Getting location...");
                    progressDialog.show();

                    btnStart.setVisibility(View.GONE);
                    btnPause.setVisibility(View.VISIBLE);
                    btnPause.setText("Pause");
                    btnStop.setVisibility(View.VISIBLE);
                }
            });

        btnPause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (btnPause.getText().toString().equalsIgnoreCase("pause")) {
                        btnPause.setText("Resume");
                        p = 1;
                    } else if (btnPause.getText().toString().equalsIgnoreCase("resume")) {
                        checkGPS();
                        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
                    if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
                        return;
                    btnPause.setText("Pause");
                    p = 0;
                }
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (status == true)
                    unbindService();
                btnStart.setVisibility(View.VISIBLE);
                btnPause.setText("Pause");
                btnPause.setVisibility(View.GONE);
                btnStop.setVisibility(View.GONE);
            }
        });

    }

    private void checkGPS() {
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
            showGPSDisableAlert();
    }

    private void showGPSDisableAlert() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Enable GPS to use application")
                .setCancelable(false)
                .setPositiveButton("Enable GPS", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(intent);
                    }
                });
        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void bindService() {
        if (status == true) {
            return;
        }
        Intent intent = new Intent(getApplicationContext(), LocationService.class);
        bindService(intent, sc, BIND_ABOVE_CLIENT);
        status = true;
        startTime = System.currentTimeMillis();

    }


}
