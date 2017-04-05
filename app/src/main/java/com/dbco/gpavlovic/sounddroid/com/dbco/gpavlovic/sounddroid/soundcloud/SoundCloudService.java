package com.dbco.gpavlovic.sounddroid.com.dbco.gpavlovic.sounddroid.soundcloud;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

public interface SoundCloudService
{
    static final String CLIENT_ID = "API_KEY";

    @GET("/tracks?client_id=" + CLIENT_ID)
    Call<List<Track>> searchSongs(@Query("q") String query);

    @GET("/tracks?client_id=" + CLIENT_ID)
    Call<List<Track>> recentUploadedSongs(@Query("created_at[from]") String date);
}
