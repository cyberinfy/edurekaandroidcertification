package com.tcs.projecttwomoviesapp.adapters;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.tcs.projecttwomoviesapp.R;
import com.tcs.projecttwomoviesapp.datamodel.Dashboard;

import java.util.List;
import java.util.Locale;

public class DashboardViewAdapter extends RecyclerView.Adapter<DashboardViewAdapter.DashboardViewHolder>{
    List<Dashboard.Result> movies;
    Context context;
    static final int pic_width=160, pic_height=200;
    public DashboardViewAdapter(List<Dashboard.Result> movies, Context context) {
        this.movies = movies;
        this.context = context;
    }

    @NonNull
    @Override
    public DashboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.dashboard_item, parent, false);
        return new DashboardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardViewHolder holder, int position) {
        holder.setItemPosition(position);
        Dashboard.Result movie = movies.get(position);
        if(movie.isAdult() == false)
            holder.textViewAdult.setText("");
        holder.textViewMovieTitle.setText(movie.getTitle());
        holder.textViewLanguage.setText(new Locale(movie.getOriginal_language()).getDisplayLanguage());
        holder.textViewReleaseDate.setText(movie.getRelease_date());
        holder.textViewRatingPercentage.setText(movie.getVote_average()+" %,");
        holder.textViewVoterCount.setText(movie.getVote_count()+" votes");
        Picasso.get().load(movie.getPoster_path()).resize(pic_width,pic_height).into(holder.imageViewPoster);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class DashboardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textViewMovieTitle;
        TextView textViewLanguage;
        TextView textViewReleaseDate;
        TextView textViewRatingPercentage;
        TextView textViewVoterCount;
        TextView textViewAdult;
        ImageView imageViewPoster;
        int itemPosition;

        public void setItemPosition(int position) {
            this.itemPosition = position;
        }

        public DashboardViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewMovieTitle = (TextView) itemView.findViewById(R.id.textViewMovieTitle);
            textViewLanguage = (TextView) itemView.findViewById(R.id.textViewLanguage);
            textViewReleaseDate = (TextView) itemView.findViewById(R.id.textViewReleaseDate);
            textViewRatingPercentage = (TextView) itemView.findViewById(R.id.textViewRatingPercentage);
            textViewVoterCount = (TextView) itemView.findViewById(R.id.textViewVoterCount);
            textViewAdult = (TextView) itemView.findViewById(R.id.textViewAdult);
            imageViewPoster = (ImageView) itemView.findViewById(R.id.imageViewPoster);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            long movieID = movies.get(itemPosition).getId();
            Bundle bundle = new Bundle();
            bundle.putLong("movieID",movieID);
            Log.d("tag",movieID+"");
            Navigation.findNavController(v).navigate(R.id.action_dashboardFragment_to_movieFragment, bundle);
        }
    }
}
