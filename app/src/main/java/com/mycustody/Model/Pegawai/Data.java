
package com.mycustody.Model.Pegawai;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Serializable, Parcelable
{

    @SerializedName("pegawai")
    @Expose
    private List<Pegawai_> pegawai = null;
    public final static Creator<Data> CREATOR = new Creator<Data>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        public Data[] newArray(int size) {
            return (new Data[size]);
        }

    }
    ;
    private final static long serialVersionUID = 19597262308235883L;

    protected Data(Parcel in) {
        in.readList(this.pegawai, (com.mycustody.Model.Pegawai.Pegawai_.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Data() {
    }

    /**
     * 
     * @param pegawai
     */
    public Data(List<Pegawai_> pegawai) {
        super();
        this.pegawai = pegawai;
    }

    public List<Pegawai_> getPegawai() {
        return pegawai;
    }

    public void setPegawai(List<Pegawai_> pegawai) {
        this.pegawai = pegawai;
    }

    public Data withPegawai(List<Pegawai_> pegawai) {
        this.pegawai = pegawai;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(pegawai);
    }

    public int describeContents() {
        return  0;
    }

}
