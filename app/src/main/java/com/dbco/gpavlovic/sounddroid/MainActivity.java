package com.dbco.gpavlovic.sounddroid;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.dbco.gpavlovic.sounddroid.com.dbco.gpavlovic.sounddroid.soundcloud.SoundCloud;
import com.dbco.gpavlovic.sounddroid.com.dbco.gpavlovic.sounddroid.soundcloud.SoundCloudService;
import com.dbco.gpavlovic.sounddroid.com.dbco.gpavlovic.sounddroid.soundcloud.Track;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity
{

    private static final String TAG = "MAINACTIVITY";
    private TracksAdapter mTracksAdapter;
    private List<Track> mTrackList;
    private TextView mSelectedTitle;
    private ImageView mSelectedThumbnail;
    private MediaPlayer mMediaPlayer;
    private ImageView mPlayPauseToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Media player
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener()
        {
            @Override
            public void onPrepared(MediaPlayer mp)
            {
                togglePlayerState();
            }
        });

        // Player toolbar
        Toolbar playerToolbar = (Toolbar) findViewById(R.id.player_toolbar);
        mSelectedTitle = (TextView) findViewById(R.id.selected_title);
        mSelectedThumbnail = (ImageView) findViewById(R.id.selected_thumbnail);

        mPlayPauseToggle = (ImageView) findViewById(R.id.player_play_pause);
        mPlayPauseToggle.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                togglePlayerState();
            }
        });

        // Setup the view
        RecyclerView songsList = (RecyclerView) findViewById(R.id.songs_list);

        songsList.setLayoutManager(new LinearLayoutManager(this));
        mTrackList = new ArrayList<Track>();
        mTracksAdapter = new TracksAdapter(this, mTrackList);
        mTracksAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Track selectedTrack = mTrackList.get(position);
                mSelectedTitle.setText(selectedTrack.getTitle()); // Set title
                Picasso.with(MainActivity.this).load(selectedTrack.getAvatarURL()).into(mSelectedThumbnail); // Set image

                try
                {
                    mMediaPlayer.setDataSource(selectedTrack.getStreamURL() + "?client_id=" + SoundCloudService.CLIENT_ID);
                    mMediaPlayer.prepareAsync();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        });
        songsList.setAdapter(mTracksAdapter);

        // Make HTTP request for tracks
        SoundCloudService soundCloudService = SoundCloud.getServiceInstance();

        Call<List<Track>> getTracksWithQuery = soundCloudService.searchSongs("Hotline bling");
        getTracksWithQuery.enqueue(new Callback<List<Track>>()
        {
            @Override
            public void onResponse(Response<List<Track>> response, Retrofit retrofit)
            {
                mTrackList.clear();
                mTrackList.addAll(response.body());
                mTracksAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Throwable t)
            {

            }
        });
    }

    private void togglePlayerState()
    {
        if (mMediaPlayer.isPlaying())
        {
            mMediaPlayer.pause();
            mPlayPauseToggle.setImageResource(R.drawable.ic_play);
        }
        else
        {
            mMediaPlayer.start();
            mPlayPauseToggle.setImageResource(R.drawable.ic_pause);
        }
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
