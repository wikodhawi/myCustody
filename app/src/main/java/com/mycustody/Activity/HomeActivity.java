package com.mycustody.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mycustody.R;
import com.mycustody.Util.SessionManagementUtil;

public class HomeActivity extends AppCompatActivity {

    SessionManagementUtil session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        session=new SessionManagementUtil(getApplicationContext());
    }

    public void showTeamProfile(View view){
        Intent overtime = new Intent(this, TeamProfileActivity.class);
        startActivity(overtime);

    }

    public void showTripSchedule(View view){
        Intent overtime = new Intent(this, TripScheduleActivity.class);
        startActivity(overtime);

    }

    public void showAboutUs(View view){
        Intent overtime = new Intent(this, AboutUsActivity.class);
        startActivity(overtime);
    }

    public void signOut(View view){
        AlertDialog.Builder alert = new AlertDialog.Builder(HomeActivity.this);
        alert.setTitle("Confirmation");
        alert.setTitle("Are You Sure to Sign Out?");
        alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                session.logoutUser();
                finish();
            }
        });

        alert.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alert.show();


    }
}
