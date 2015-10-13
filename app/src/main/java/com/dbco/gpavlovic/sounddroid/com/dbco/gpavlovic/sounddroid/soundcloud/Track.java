package com.dbco.gpavlovic.sounddroid.com.dbco.gpavlovic.sounddroid.soundcloud;

import com.google.gson.annotations.SerializedName;

public class Track
{
    @SerializedName("id")
    private int mID;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("stream_url")
    private String mStreamURL;

    public int getID()
    {
        return mID;
    }

    public void setID(int mID)
    {
        this.mID = mID;
    }

    public String getTitle()
    {
        return mTitle;
    }

    public void setTitle(String mTitle)
    {
        this.mTitle = mTitle;
    }

    public String getStreamURL()
    {
        return mStreamURL;
    }

    public void setStreamURL(String mStreamURL)
    {
        this.mStreamURL = mStreamURL;
    }

}
