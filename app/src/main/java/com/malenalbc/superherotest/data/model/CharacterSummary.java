
package com.malenalbc.superherotest.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CharacterSummary {

    @SerializedName("resourceURI")
    @Expose
    public String resourceURI;
    @SerializedName("name")
    @Expose
    public String name;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CharacterSummary () {
    }

    /**
     * 
     * @param resourceURI
     * @param name
     */
    public CharacterSummary (String resourceURI, String name) {
        this.resourceURI = resourceURI;
        this.name = name;
    }

}
