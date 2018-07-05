package com.mycustody.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mycustody.Model.TripSchedule.TripSchedule;
import com.mycustody.R;

import java.util.HashMap;
import java.util.List;

public class TripScheduleAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<TripSchedule> listDataHeader;
    private HashMap<TripSchedule, List<String>> listHashMap;

    public TripScheduleAdapter(Context context, List<TripSchedule> listDataHeader, HashMap<TripSchedule, List<String>> listHashMap) {
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listHashMap = listHashMap;
    }

    @Override
    public int getGroupCount() {
        return listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return listHashMap.get(listDataHeader.get(i)).size();
    }

    @Override
    public TripSchedule getGroup(int i) {
        return listDataHeader.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return listHashMap.get(listDataHeader.get(i)).get(i1); // i = Group Item , i1 = ChildItem
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        TripSchedule headerTitle = (TripSchedule) getGroup(i);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_trip_schedule, null);
        }
        TextView lblPTTripSchedule = (TextView) view.findViewById(R.id.lblPTTripSchedule);
        ImageView imgProgressTripSchedule=(ImageView)view.findViewById(R.id.imgProgressTripSchedule);
        ImageView imgLogoTripSchedule=(ImageView)view.findViewById(R.id.imgLogoTripSchedule);

        lblPTTripSchedule.setTypeface(null, Typeface.BOLD);
        lblPTTripSchedule.setText(headerTitle.getJobTrip());
        if(headerTitle.getProgressTrip().matches("checkIn"))
        {
            imgProgressTripSchedule.setImageDrawable(context.getDrawable(R.mipmap.ic_check_in));
        }
        else if(headerTitle.getProgressTrip().matches("checkOut"))
        {
            imgProgressTripSchedule.setImageDrawable(context.getDrawable(R.mipmap.ic_check_out));
        }
        else if(headerTitle.getProgressTrip().matches("history"))
        {
            imgProgressTripSchedule.setImageDrawable(context.getDrawable(R.mipmap.ic_history));
        }

        if(headerTitle.getLogoTrip().matches("nawakara"))
        {
            imgLogoTripSchedule.setImageDrawable(context.getDrawable(R.mipmap.ic_nawakara_bw));
        }
        else if(headerTitle.getLogoTrip().matches("pu"))
        {
            imgLogoTripSchedule.setImageDrawable(context.getDrawable(R.mipmap.ic_pu));
        }
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        final String childText = (String) getChild(i, i1);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_trip_schedule_check_in_out, null);
        }

        TextView txtListChild = (TextView) view.findViewById(R.id.lblListItem);
        txtListChild.setText(childText);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}