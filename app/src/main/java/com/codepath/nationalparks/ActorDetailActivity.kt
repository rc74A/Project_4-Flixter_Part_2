package com.codepath.movies

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

private const val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"

class ActorDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_actor_detail)

        val actorImage =
            findViewById<ImageView>(R.id.actor_detail_image)

        val actorName =
            findViewById<TextView>(R.id.actor_detail_name)

        val actorBio =
            findViewById<TextView>(R.id.actor_detail_bio)

        // Get the actor ID that was passed through the Intent
        val actorId =
            intent.getIntExtra("actor_id", -1)

        if (actorId == -1) {
            Log.e("ActorDetailActivity", "No actor ID was provided")
            return
        }

        val client = AsyncHttpClient()

        val params = RequestParams()
        params["api_key"] = API_KEY

        client[
            "https://api.themoviedb.org/3/person/$actorId",
            params,
            object : JsonHttpResponseHandler() {

                override fun onSuccess(
                    statusCode: Int,
                    headers: Headers,
                    json: JsonHttpResponseHandler.JSON
                ) {

                    val actor = json.jsonObject

                    val name =
                        actor.getString("name")

                    val biography =
                        actor.optString("biography")

                    val profilePath =
                        actor.optString("profile_path")

                    actorName.text = name
                    actorBio.text = biography

                    if (profilePath.isNotEmpty()) {

                        val imageUrl =
                            "https://image.tmdb.org/t/p/w500$profilePath"

                        Glide.with(this@ActorDetailActivity)
                            .load(imageUrl)
                            .centerInside()
                            .into(actorImage)
                    }

                    Log.d(
                        "ActorDetailActivity",
                        "Actor details loaded successfully"
                    )
                }

                override fun onFailure(
                    statusCode: Int,
                    headers: Headers?,
                    errorResponse: String,
                    t: Throwable?
                ) {

                    Log.e(
                        "ActorDetailActivity",
                        "Request failed: $errorResponse",
                        t
                    )
                }
            }
        ]
    }
}