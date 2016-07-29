
package com.malenalbc.superherotest.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResultContainer<D extends Result> {

    @SerializedName("offset")
    @Expose
    public int offset;
    @SerializedName("limit")
    @Expose
    public int limit;
    @SerializedName("total")
    @Expose
    public int total;
    @SerializedName("count")
    @Expose
    public int count;
    @SerializedName("results")
    @Expose
    public List<D> results = new ArrayList<>();

    public List<D> getResults () {
        return results;
    }
}
