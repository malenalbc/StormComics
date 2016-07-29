
package com.malenalbc.superherotest.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreatorSummary implements Parcelable {

    @SerializedName("resourceURI")
    @Expose
    public String resourceURI;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("role")
    @Expose
    public String role;

    @Override
    public int describeContents () {
        return 0;
    }

    @Override
    public void writeToParcel (Parcel dest, int flags) {
        dest.writeString(this.resourceURI);
        dest.writeString(this.name);
        dest.writeString(this.role);
    }

    public CreatorSummary () {
    }

    private CreatorSummary (Parcel in) {
        this.resourceURI = in.readString();
        this.name = in.readString();
        this.role = in.readString();
    }

    public static final Creator<CreatorSummary> CREATOR = new Creator<CreatorSummary>() {
        @Override
        public CreatorSummary createFromParcel (Parcel source) {
            return new CreatorSummary(source);
        }

        @Override
        public CreatorSummary[] newArray (int size) {
            return new CreatorSummary[size];
        }
    };
}
