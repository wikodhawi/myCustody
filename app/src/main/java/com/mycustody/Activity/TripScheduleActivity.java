package com.mycustody.Activity;

import android.content.ClipData;
import android.content.DialogInterface;
import android.support.annotation.DrawableRes;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import com.mycustody.Adapter.TripScheduleAdapter;
import com.mycustody.Model.TripSchedule.TripSchedule;
import com.mycustody.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TripScheduleActivity extends AppCompatActivity {

    private View parentView;

    ExpandableListView elvTripSchedule;
    List<TripSchedule> listDataHeader;
    HashMap<TripSchedule,List<String>> listHash;
    TripScheduleAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_schedule);

        parentView=(View)findViewById(android.R.id.content);

        elvTripSchedule=(ExpandableListView)findViewById(R.id.elvTripSchedule);
        elvTripSchedule.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                TripSchedule item = (TripSchedule) listAdapter.getGroup(i);
                if(listDataHeader.get(i).getProgressTrip().matches("checkIn"))
                {
                    showAlertDialog("Check-In", "08.00", listDataHeader.get(i).getJobTrip());
                    listDataHeader.get(i).setProgressTrip("checkOut");
                    initListTripScheduleAdapter();
                }
                else if(listDataHeader.get(i).getProgressTrip().matches("checkOut"))
                {
                    showAlertDialog("Check-Out", "08.00", listDataHeader.get(i).getJobTrip());
                    listDataHeader.get(i).setProgressTrip("history");
                    initListTripScheduleAdapter();
                }

                if(item.getProgressTrip().matches("history"))
                    return false;
                else return true;


            }
        });
        initData();
        initListTripScheduleAdapter();

    }

    private void initListTripScheduleAdapter()
    {
        listAdapter = new TripScheduleAdapter(this,listDataHeader,listHash);
        elvTripSchedule.setAdapter(listAdapter);
    }

    private void initData() {
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();

        //TripSchedule(String idTrip, String jobTrip, String timeTrip, String progressTrip, String logoTrip)
        TripSchedule tripSchedule=new TripSchedule("0","Nawakara HQ - DPS","08.00", "history","nawakara");

        listDataHeader.add(tripSchedule);

        tripSchedule=new TripSchedule("1","Fastrata Buana","08.45", "checkIn","pu");
        listDataHeader.add(tripSchedule);
//        listDataHeader.add("Android");
//        listDataHeader.add("Xamarin");
//        listDataHeader.add("UWP");

        List<String> edmtDev = new ArrayList<>();
        edmtDev.add("Check-In\t: 08.00");
        edmtDev.add("Check-Out\t: 08.30");

        List<String> androidStudio = new ArrayList<>();
        androidStudio.add("Check-In\t: 09.00");
        androidStudio.add("Check-Out\t: 09.30");
//        androidStudio.add("Chat Application");
//        androidStudio.add("Firebase ");
//
//        List<String> xamarin = new ArrayList<>();
//        xamarin.add("Xamarin Expandable ListView");
//        xamarin.add("Xamarin Google Map");
//        xamarin.add("Xamarin Chat Application");
//        xamarin.add("Xamarin Firebase ");
//
//        List<String> uwp = new ArrayList<>();
//        uwp.add("UWP Expandable ListView");
//        uwp.add("UWP Google Map");
//        uwp.add("UWP Chat Application");
//        uwp.add("UWP Firebase ");

        listHash.put(listDataHeader.get(0),edmtDev);
        listHash.put(listDataHeader.get(1),androidStudio);
//        listHash.put(listDataHeader.get(2),xamarin);
//        listHash.put(listDataHeader.get(3),uwp);
    }

    private void showAlertDialog(final String title, String time, String trip) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage("Anda telah melakukan "+ title.toLowerCase() +" di "+ trip +" pada "+time);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Snackbar.make(parentView, title, Snackbar.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }


}
