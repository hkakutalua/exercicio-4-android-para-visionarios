package com.buka.exercicio4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.buka.exercicio4.models.Track;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

public class TracksAdapter extends RecyclerView.Adapter<TracksAdapter.TrackViewHolder> {
    private List<Track> deezerChart = new ArrayList<>();

    @NonNull
    @Override
    public TrackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View inflatedView = inflater.inflate(R.layout.item_track, parent, false);
        return new TrackViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(@NonNull TrackViewHolder holder, int position) {
        Track track = deezerChart.get(position);

        Picasso.get().load(track.getAlbumCover())
                .fit()
                .centerCrop()
                .into(holder.albumCoverImageView);

        holder.artistTextView.setText(track.getArtist().getName());
        holder.titleTextView.setText(track.getTitle());
    }

    @Override
    public int getItemCount() {
        return deezerChart.size();
    }

    public void setTracks(List<Track> chart) {
        if (chart != null) {
            deezerChart = chart;
            notifyDataSetChanged();
        }
    }

    class TrackViewHolder extends RecyclerView.ViewHolder {
        private final AppCompatImageView albumCoverImageView;
        private final TextView titleTextView;
        private final TextView artistTextView;

        public TrackViewHolder(@NonNull View itemView) {
            super(itemView);

            albumCoverImageView = itemView.findViewById(R.id.imageview_album_cover);
            titleTextView = itemView.findViewById(R.id.textview_title);
            artistTextView = itemView.findViewById(R.id.textview_artist);
        }
    }
}
