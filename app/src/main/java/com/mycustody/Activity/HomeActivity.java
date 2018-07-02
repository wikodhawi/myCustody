package com.mycustody.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mycustody.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void showTeamProfile(View view){
        Intent overtime = new Intent(this, TeamProfileActivity.class);
        startActivity(overtime);

    }

    public void showTripSchedule(View view){
        Intent overtime = new Intent(this, TripScheduleActivity.class);
        startActivity(overtime);

    }
}
