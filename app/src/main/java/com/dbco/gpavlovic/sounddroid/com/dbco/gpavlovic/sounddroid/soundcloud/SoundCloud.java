package com.dbco.gpavlovic.sounddroid.com.dbco.gpavlovic.sounddroid.soundcloud;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class SoundCloud
{
    private static final String API_URL = "http://api.soundcloud.com";
    private static final Retrofit RETROFIT = new Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static final SoundCloudService SOUNDCLOUD_SERVICE = RETROFIT.create(SoundCloudService.class);

    public static SoundCloudService getServiceInstance()
    {
        return SOUNDCLOUD_SERVICE;
    }
}
