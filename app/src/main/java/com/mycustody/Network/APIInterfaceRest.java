package com.mycustody.Network;

import com.mycustody.Model.Pegawai.Pegawai;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by user on 5/11/2018.
 */

public interface APIInterfaceRest {

    @GET("api/pegawai/all")
    Call<Pegawai> getListPegawai();
}
