package com.mycustody.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mycustody.R;
import com.mycustody.Util.SessionManagementUtil;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

public class ScanQRCodeTripActivity extends AppCompatActivity implements ZBarScannerView.ResultHandler{

    private ZBarScannerView mScannerView;

    static final int REQUEST_ACCESS_LOCATION = 101;
    private static final int MY_CAMERA_REQUEST_CODE = 100;
    private static final int MY_READ_EXTERNAL_STORAGE=102;
    private static final int MY_WRITE_EXTERNAL_STORAGE=103;
    SessionManagementUtil session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(getString(R.string.stringScanQRCodeTripSheet));
        checkPermission();
        session = new SessionManagementUtil(getApplicationContext());

        if (session.isLoggedIn()){
            startActivity(new Intent(ScanQRCodeTripActivity.this, HomeActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }

        mScannerView = new ZBarScannerView(this);    // Programmatically initialize the scanner view

        ViewGroup contentFrame = (ViewGroup) findViewById(R.id.contentPanel);
        contentFrame.addView(mScannerView);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == REQUEST_ACCESS_LOCATION){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //getLocation();
            } else {
                Toast.makeText(this, "Need your location!", Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == MY_CAMERA_REQUEST_CODE){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                //dispatchTakePictureIntent();

            } else {

                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }

        if(requestCode == MY_READ_EXTERNAL_STORAGE){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //getLocation();
            } else {
                Toast.makeText(this, "Need your location!", Toast.LENGTH_SHORT).show();
            }
        }
        if(requestCode == MY_WRITE_EXTERNAL_STORAGE){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //getLocation();
            } else {
                Toast.makeText(this, "Need your location!", Toast.LENGTH_SHORT).show();
            }
        }

    }


    public void checkPermission()
    {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
        }

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_READ_EXTERNAL_STORAGE);
        }

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_WRITE_EXTERNAL_STORAGE);
        }


    }

    @Override
    public void onResume() {
        super.onResume();
//        mScannerView = new ZBarScannerView(this);    // Programmatically initialize the scanner view
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume

    }

    @Override
    public void onPause() {
        super.onPause();

//        mScannerView = new ZBarScannerView(this);    // Programmatically initialize the scanner view
        mScannerView.stopCamera();           // Stop camera on pause

    }

    @Override
    public void handleResult(Result result) {
        // Do something with the result here
        Log.v("hasilQR", result.getContents()); // Prints scan results
        Log.v("hasilQR1", result.getBarcodeFormat().getName()); // Prints the scan format (qrcode, pdf417 etc.)

        // If you would like to resume scanning, call this method below:
        mScannerView.resumeCameraPreview(this);
        session.createLoginSession(result.getContents());
        Intent intent=new Intent(ScanQRCodeTripActivity.this, HomeActivity.class);

        intent.putExtra("qrCodeResult", result.getContents());
        intent.putExtra("qrCodeType", result.getBarcodeFormat().getName());
        startActivity(intent);
    }
}
