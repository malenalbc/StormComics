
package com.malenalbc.superherotest.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Comic extends Result implements Parcelable {

    @SerializedName("digitalId")
    @Expose
    public int digitalId;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("issueNumber")
    @Expose
    public int issueNumber;
    @SerializedName("variantDescription")
    @Expose
    public String variantDescription;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("modified")
    @Expose
    public String modified;
    @SerializedName("isbn")
    @Expose
    public String isbn;
    @SerializedName("upc")
    @Expose
    public String upc;
    @SerializedName("diamondCode")
    @Expose
    public String diamondCode;
    @SerializedName("ean")
    @Expose
    public String ean;
    @SerializedName("issn")
    @Expose
    public String issn;
    @SerializedName("format")
    @Expose
    public String format;
    @SerializedName("pageCount")
    @Expose
    public int pageCount;
    @SerializedName("textObjects")
    @Expose
    public List<TextObject> textObjects = new ArrayList<TextObject>();
    @SerializedName("resourceURI")
    @Expose
    public String resourceURI;
    @SerializedName("urls")
    @Expose
    public List<Url> urls = new ArrayList<>();
    @SerializedName("dates")
    @Expose
    public List<Date> dates = new ArrayList<>();
    @SerializedName("prices")
    @Expose
    public List<Price> prices = new ArrayList<>();
    @SerializedName("thumbnail")
    @Expose
    public Image thumbnail;
    @SerializedName("images")
    @Expose
    public List<Image> images = new ArrayList<>();
    @SerializedName("creators")
    @Expose
    public Creators creators;


    @Override
    public int describeContents () {
        return 0;
    }

    @Override
    public void writeToParcel (Parcel dest, int flags) {
        dest.writeInt(this.digitalId);
        dest.writeString(this.title);
        dest.writeInt(this.issueNumber);
        dest.writeString(this.variantDescription);
        dest.writeString(this.description);
        dest.writeString(this.modified);
        dest.writeString(this.isbn);
        dest.writeString(this.upc);
        dest.writeString(this.diamondCode);
        dest.writeString(this.ean);
        dest.writeString(this.issn);
        dest.writeString(this.format);
        dest.writeInt(this.pageCount);
        dest.writeList(this.textObjects);
        dest.writeString(this.resourceURI);
        dest.writeTypedList(this.urls);
        dest.writeTypedList(this.dates);
        dest.writeList(this.prices);
        dest.writeParcelable(this.thumbnail, flags);
        dest.writeTypedList(this.images);
        dest.writeParcelable(this.creators, flags);
    }

    public Comic () {
    }

    protected Comic (Parcel in) {
        this.digitalId = in.readInt();
        this.title = in.readString();
        this.issueNumber = in.readInt();
        this.variantDescription = in.readString();
        this.description = in.readString();
        this.modified = in.readString();
        this.isbn = in.readString();
        this.upc = in.readString();
        this.diamondCode = in.readString();
        this.ean = in.readString();
        this.issn = in.readString();
        this.format = in.readString();
        this.pageCount = in.readInt();
        this.textObjects = new ArrayList<>();
        in.readList(this.textObjects, TextObject.class.getClassLoader());
        this.resourceURI = in.readString();
        this.urls = in.createTypedArrayList(Url.CREATOR);
        this.dates = in.createTypedArrayList(Date.CREATOR);
        this.prices = new ArrayList<>();
        in.readList(this.prices, Price.class.getClassLoader());
        this.thumbnail = in.readParcelable(Image.class.getClassLoader());
        this.images = in.createTypedArrayList(Image.CREATOR);
        this.creators = in.readParcelable(Creators.class.getClassLoader());
    }

    public static final Creator<Comic> CREATOR = new Creator<Comic>() {
        @Override
        public Comic createFromParcel (Parcel source) {
            return new Comic(source);
        }

        @Override
        public Comic[] newArray (int size) {
            return new Comic[size];
        }
    };
}
