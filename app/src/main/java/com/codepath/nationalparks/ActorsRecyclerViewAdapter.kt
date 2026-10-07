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
class ActorsRecyclerViewAdapter(
    private val actors: List<Actor>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<ActorsRecyclerViewAdapter.ActorViewHolder>() {

    // Inflate the item layout from XML
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_actor, parent, false)
        return ActorViewHolder(view)
    }

    // ViewHolder class holds references to all UI elements inside the list item layout
    inner class ActorViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mItem: Actor? = null

        // TODO: Step 4a - Add references for remaining views from XML
        val mActorName: TextView = mView.findViewById(id.actor_name) as TextView

        val mActorImage: ImageView = mView.findViewById(R.id.actor_image)

        override fun toString(): String {
            return mActorName.toString()
        }
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        val actor = actors[position]

        // TODO: Step 4b - Bind the park data to the views
        holder.mItem = actor
        holder.mActorName.text = actor.name



        // TODO: Step 4c - Use Glide to load the first image
        val imageUrl = "https://image.tmdb.org/t/p/w500${actor.profilePath}"
        Glide.with(holder.mView)
            .load(imageUrl)
            .centerInside()
            .into(holder.mActorImage)


        // Sets up click listener for this park item
        holder.mView.setOnClickListener {
            holder.mItem?.let { actor ->
                mListener?.onItemClick(actor)
            }
        }
    }

    // Tells the RecyclerView how many items to display
    override fun getItemCount(): Int {
        return actors.size
    }
}
