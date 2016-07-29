
package com.malenalbc.superherotest.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultWrapper<D extends Result> {

    @SerializedName("code")
    @Expose
    public int code;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("copyright")
    @Expose
    public String copyright;
    @SerializedName("attributionText")
    @Expose
    public String attributionText;
    @SerializedName("attributionHTML")
    @Expose
    public String attributionHTML;
    @SerializedName("etag")
    @Expose
    public String etag;
    @SerializedName("data")
    @Expose
    public ResultContainer<D> data;


    public ResultContainer<D> getData () {
        return data;
    }
}
