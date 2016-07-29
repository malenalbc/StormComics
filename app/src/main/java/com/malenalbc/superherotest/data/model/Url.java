
package com.malenalbc.superherotest.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Url implements Parcelable{

    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("url")
    @Expose
    public String url;

    @Override
    public int describeContents () {
        return 0;
    }

    @Override
    public void writeToParcel (Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeString(this.url);
    }

    public Url () {
    }

    protected Url (Parcel in) {
        this.type = in.readString();
        this.url = in.readString();
    }

    public static final Creator<Url> CREATOR = new Creator<Url>() {
        @Override
        public Url createFromParcel (Parcel source) {
            return new Url(source);
        }

        @Override
        public Url[] newArray (int size) {
            return new Url[size];
        }
    };
}
