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
    @SerializedName("artwork_url")
    private String mArtworkURL;

    public String getmArtworkURL()
    {
        return mArtworkURL;
    }

    public String getAvatarURL()
    {
        if (mArtworkURL != null)
        {
            return mArtworkURL.replace("large", "tiny");
        }
        return null;
    }

    public int getID()
    {
        return mID;
    }

    public String getTitle()
    {
        return mTitle;
    }

    public String getStreamURL()
    {
        return mStreamURL;
    }
}
