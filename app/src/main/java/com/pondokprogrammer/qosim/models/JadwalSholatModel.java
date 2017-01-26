package com.pondokprogrammer.qosim.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by muhammad on 23/04/16.
 */
public class JadwalSholatModel implements Parcelable {


    public String title;
    public String city;
    public String state;
    public String country;

    public String date_for;
    public String fajr;
    public String shurooq;
    public String dhuhr;
    public String asr;
    public String maghrib;
    public String isha;

    protected JadwalSholatModel(Parcel in) {
        title = in.readString();
        city = in.readString();
        state = in.readString();
        country = in.readString();

        date_for = in.readString();
        fajr = in.readString();
        shurooq = in.readString();
        dhuhr = in.readString();
        asr = in.readString();
        maghrib = in.readString();
        isha = in.readString();
    }

    public static final Creator<JadwalSholatModel> CREATOR = new Creator<JadwalSholatModel>() {
        @Override
        public JadwalSholatModel createFromParcel(Parcel in) {
            return new JadwalSholatModel(in);
        }

        @Override
        public JadwalSholatModel[] newArray(int size) {
            return new JadwalSholatModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(city);
        dest.writeString(state);
        dest.writeString(country);

        dest.writeString(date_for);
        dest.writeString(fajr);
        dest.writeString(shurooq);
        dest.writeString(dhuhr);
        dest.writeString(asr);
        dest.writeString(maghrib);
        dest.writeString(isha);
    }
}
