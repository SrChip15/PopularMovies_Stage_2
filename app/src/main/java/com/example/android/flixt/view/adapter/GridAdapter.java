package com.example.android.flixt.view.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.flixt.R;
import com.example.android.flixt.service.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class GridAdapter extends RecyclerView.Adapter<GridViewHolder> {

	private static final String POSTER_IMAGE_BASE_URL = "http://image.tmdb.org/t/p/";
	private static final String POSTER_IMAGE_SIZE = "w780";
	private final ListItemClickListener mItemClickListener;
	private List<Movie> mListOfMovies = new ArrayList<>();
	private Context mContext;

	/**
	 * Create adapter object
	 */
	public GridAdapter(Context context, ListItemClickListener itemClickListener) {
		// Initialize data set
		mContext = context;
		mItemClickListener = itemClickListener;
	}

	@Override
	public GridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		// Get a new layout inflater to create item view in parent
		LayoutInflater inflater = LayoutInflater.from(mContext);

		// Create a new item view with the above inflater
		View itemView = inflater.inflate(R.layout.grid_list_item, parent, false);

		// Return the newly created view
		return new GridViewHolder(itemView, mListOfMovies, mItemClickListener);
	}

	@Override
	public void onBindViewHolder(GridViewHolder holder, int position) {
		if (mListOfMovies != null && !(mListOfMovies.isEmpty())) {
			Movie currentMovie = mListOfMovies.get(position);
			String posterPath = currentMovie.getPosterPath();
			if (posterPath != null && !(TextUtils.isEmpty(posterPath))) {
				String imageUrl = POSTER_IMAGE_BASE_URL + POSTER_IMAGE_SIZE + posterPath;
				Picasso.with(mContext)
						.load(imageUrl)
						.into(holder.posterImageView);
			}
		}
	}

	@Override
	public int getItemCount() {
		return mListOfMovies != null ? mListOfMovies.size() : 0;
	}

	public synchronized void addMovies(@Nullable List<Movie> listOfMovies) {
		if (listOfMovies != null) {
			mListOfMovies.addAll(listOfMovies);
			notifyDataSetChanged();
		} else {
			mListOfMovies = null;
		}
	}

	public interface ListItemClickListener {
		void onPosterClick(Movie movie);
	}
}
