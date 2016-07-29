
package com.malenalbc.superherotest.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Creators implements Parcelable {

    @SerializedName("available")
    @Expose
    public int available;
    @SerializedName("collectionURI")
    @Expose
    public String collectionURI;
    @SerializedName("items")
    @Expose
    public List<CreatorSummary> creatorSummaries = new ArrayList<>();
    @SerializedName("returned")
    @Expose
    public int returned;

    @Override
    public int describeContents () {
        return 0;
    }

    @Override
    public void writeToParcel (Parcel dest, int flags) {
        dest.writeInt(this.available);
        dest.writeString(this.collectionURI);
        dest.writeList(this.creatorSummaries);
        dest.writeInt(this.returned);
    }

    private Creators (Parcel in) {
        this.available = in.readInt();
        this.collectionURI = in.readString();
        this.creatorSummaries = new ArrayList<>();
        in.readList(this.creatorSummaries, CreatorSummary.class.getClassLoader());
        this.returned = in.readInt();
    }

    public static final Creator<Creators> CREATOR = new Creator<Creators>() {
        @Override
        public Creators createFromParcel (Parcel source) {
            return new Creators(source);
        }

        @Override
        public Creators[] newArray (int size) {
            return new Creators[size];
        }
    };
}
