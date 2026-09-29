package com.codepath.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codepath.movies.R.id

/**
 * [RecyclerView.Adapter] that can display a [NationalPark] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 */
class MoviesRecyclerViewAdapter(
    private val movies: List<Movie>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<MoviesRecyclerViewAdapter.MovieViewHolder>() {

    // Inflate the item layout from XML
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_movie, parent, false)
        return MovieViewHolder(view)
    }

    // ViewHolder class holds references to all UI elements inside the list item layout
    inner class MovieViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mItem: Movie? = null

        // TODO: Step 4a - Add references for remaining views from XML
        val mMovieName: TextView = mView.findViewById(id.movie_title) as TextView

        val mMovieDescription: TextView = mView.findViewById(id.movie_description) as TextView
        val mMovieImage: ImageView = mView.findViewById(R.id.movie_image)

        override fun toString(): String {
            return mMovieName.toString() + " '" + mMovieDescription.text + "'"
        }
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]

        // TODO: Step 4b - Bind the park data to the views
        holder.mItem = movie
        holder.mMovieName.text = movie.title

        holder.mMovieDescription.text = movie.description

        // TODO: Step 4c - Use Glide to load the first image
        val imageUrl = "https://image.tmdb.org/t/p/w500${movie.posterPath}"
        Glide.with(holder.mView)
            .load(imageUrl)
            .centerInside()
            .into(holder.mMovieImage)


        // Sets up click listener for this park item
        holder.mView.setOnClickListener {
            holder.mItem?.let { movie ->
                mListener?.onItemClick(movie)
            }
        }
    }

    // Tells the RecyclerView how many items to display
    override fun getItemCount(): Int {
        return movies.size
    }
}
