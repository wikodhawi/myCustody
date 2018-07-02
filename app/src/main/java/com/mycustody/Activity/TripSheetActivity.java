package com.mycustody.Activity;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.mycustody.R;

import java.io.File;
import java.io.FileOutputStream;

public class TripSheetActivity extends AppCompatActivity {

    TextView txtQRCodeResult, txtQRCodeType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_sheet);

        File path = getFilesDir();
        File file = new File(path, "my-file-name.txt");

        Log.d("path", path+"");
        try{
            FileOutputStream stream = new FileOutputStream(file);
            try {
                stream.write("text-to-write".getBytes());
                Log.d("path2", path+"");
            } finally {
                stream.close();
            }
        }
        catch (Exception e)
        {

        }


        txtQRCodeResult=(TextView)findViewById(R.id.txtQRCodeResult);
        txtQRCodeType=(TextView)findViewById(R.id.txtQRCodeType);


        txtQRCodeResult.setText(""+getIntent().getStringExtra("qrCodeResult"));
        txtQRCodeType.setText(""+getIntent().getStringExtra("qrCodeType"));
    }
}
