package com.mycustody.Activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.mycustody.Adapter.TeamAdapter;
import com.mycustody.Model.Pegawai.Pegawai;
import com.mycustody.Model.Pegawai.Pegawai_;
import com.mycustody.Network.APIClient;
import com.mycustody.Network.APIInterfaceRest;
import com.mycustody.R;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamProfileActivity extends AppCompatActivity {

    APIInterfaceRest apiInterfaceRest;
    ProgressDialog progressDialog;
    List<Pegawai_> listData;
    ListView lstTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_custody);
        lstTeam=(ListView)findViewById(R.id.lstTeam);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        com.nostra13.universalimageloader.core.ImageLoader.getInstance().init(config);
        getPegawai();
    }

    public void getPegawai() {

        apiInterfaceRest = APIClient.getClient().create(APIInterfaceRest.class);
        if(progressDialog==null)
        {
            progressDialog = new ProgressDialog(TeamProfileActivity.this);
        }
        progressDialog.setTitle("Loading");
        progressDialog.show();
        Call<Pegawai> pegawaiCall = apiInterfaceRest.getListPegawai();
        pegawaiCall.enqueue(new Callback<Pegawai>() {
            @Override
            public void onResponse(Call<Pegawai> call, Response<Pegawai> response) {
                Pegawai userList = response.body();
                if (userList != null) {
                    if (userList.getData().getPegawai().size() > 0) {
                        listData=userList.getData().getPegawai();
                        TeamAdapter adapter= new TeamAdapter(TeamProfileActivity.this, 0, listData);
                        lstTeam.setAdapter(adapter);

                    }


                }
                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<Pegawai> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Maaf koneksi bermasalah", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });

    }
}
