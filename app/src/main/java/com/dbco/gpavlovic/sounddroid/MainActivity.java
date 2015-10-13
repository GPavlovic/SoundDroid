package com.dbco.gpavlovic.sounddroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.dbco.gpavlovic.sounddroid.com.dbco.gpavlovic.sounddroid.soundcloud.SoundCloud;
import com.dbco.gpavlovic.sounddroid.com.dbco.gpavlovic.sounddroid.soundcloud.SoundCloudService;
import com.dbco.gpavlovic.sounddroid.com.dbco.gpavlovic.sounddroid.soundcloud.Track;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity
{

    private static final String TAG = "MAINACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SoundCloudService soundCloudService = SoundCloud.getServiceInstance();

        Call<List<Track>> getTracksWithQuery = soundCloudService.searchSongs("Back to back");
        getTracksWithQuery.enqueue(new Callback<List<Track>>()
        {
            @Override
            public void onResponse(Response<List<Track>> response, Retrofit retrofit)
            {
                Log.d(TAG, "First result title: " + response.body().get(0).getTitle());
            }

            @Override
            public void onFailure(Throwable t)
            {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
