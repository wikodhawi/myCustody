package com.mycustody.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mycustody.Model.Pegawai.Pegawai_;
import com.mycustody.R;
import com.mycustody.Util.ImageUtil;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class TeamAdapter extends ArrayAdapter<Pegawai_> {

    Context context;
    List<Pegawai_> lstorder = new ArrayList<Pegawai_>();

    public TeamAdapter(@NonNull Context context, int resource, @NonNull List<Pegawai_> objects) {
        super(context, resource, objects);

        this.context = context;
        lstorder = objects;
    }




    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;

        listItem = LayoutInflater.from(context).inflate(R.layout.list_team,parent,false);
        Pegawai_ pegawai=lstorder.get(position);

        TextView txtTeamName = (TextView) listItem.findViewById(R.id.txtTeamName);
        txtTeamName.setText(pegawai.getNama());

        TextView txtTeamEmployeeId = (TextView) listItem.findViewById(R.id.txtTeamEmployeeId);
        txtTeamEmployeeId.setText(pegawai.getNoPegawai());

        TextView txtTeamLocation = (TextView) listItem.findViewById(R.id.txtTeamLocation);
        txtTeamLocation.setText(pegawai.getLokasi());

        CircleImageView imgTeamPhoto = (CircleImageView) listItem.findViewById(R.id.imgTeamPhoto);
/////      ImageUtil.displayImage(image,tblBiodatum.getFoto(),);
        ImageUtil.displayCircleImage(imgTeamPhoto,pegawai.getFoto(),null);

        return listItem;
    }
}

