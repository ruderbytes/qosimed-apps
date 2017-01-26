package com.pondokprogrammer.qosim.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by muhammad on 04/05/16.
 */
public class QosimapModel implements Parcelable {
    public String name, address, latitude, longitude, photo;

    protected QosimapModel(Parcel in) {
        name = in.readString();
        address = in.readString();
        latitude = in.readString();
        longitude = in.readString();
        photo = in.readString();
    }

    public static final Creator<QosimapModel> CREATOR = new Creator<QosimapModel>() {
        @Override
        public QosimapModel createFromParcel(Parcel in) {
            return new QosimapModel(in);
        }

        @Override
        public QosimapModel[] newArray(int size) {
            return new QosimapModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(latitude);
        dest.writeString(longitude);
        dest.writeString(photo);
    }
}
