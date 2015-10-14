package com.dbco.gpavlovic.sounddroid;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.dbco.gpavlovic.sounddroid.com.dbco.gpavlovic.sounddroid.soundcloud.Track;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TracksAdapter extends RecyclerView.Adapter<TracksAdapter.ViewHolder>
{
    private List<Track> mTrackList;
    private Context mContext;
    private AdapterView.OnItemClickListener mOnItemClickListener;

    TracksAdapter(Context context, List<Track> trackList)
    {
        mContext = context;
        mTrackList = trackList;
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener mOnItemClickListener)
    {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.track_row, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public int getItemCount()
    {
        return mTrackList.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        Track currTrack = mTrackList.get(position);
        holder.titleTV.setText(currTrack.getTitle());
        Picasso.with(mContext).load(currTrack.getAvatarURL()).into(holder.trackThumbnail);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private final TextView titleTV;
        private final ImageView trackThumbnail;

        ViewHolder(View v)
        {
            super(v);

            titleTV = (TextView) v.findViewById(R.id.track_title);
            trackThumbnail = (ImageView) v.findViewById(R.id.track_thumbnail);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v)
        {
            if (mOnItemClickListener != null)
            {
                mOnItemClickListener.onItemClick(null, v, getAdapterPosition(), 0);
            }
        }
    }
}
