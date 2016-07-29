
package com.malenalbc.superherotest.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Image implements Parcelable{

    @SerializedName("path")
    @Expose
    public String path;
    @SerializedName("extension")
    @Expose
    public String extension;

    public Image() {
    }

    public String getUrl () {
        return path + "." + extension;
    }


    @Override
    public int describeContents () {
        return 0;
    }

    @Override
    public void writeToParcel (Parcel dest, int flags) {
        dest.writeString(this.path);
        dest.writeString(this.extension);
    }

    protected Image (Parcel in) {
        this.path = in.readString();
        this.extension = in.readString();
    }

    public static final Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image createFromParcel (Parcel source) {
            return new Image(source);
        }

        @Override
        public Image[] newArray (int size) {
            return new Image[size];
        }
    };
}
