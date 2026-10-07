package com.codepath.movies

import com.google.gson.annotations.SerializedName

/**
 * The Model for storing a single park from the National Parks API.
 *
 * SerializedName tags MUST match the JSON response for the
 * object to correctly parse with the gson library.
 */
class Actor {
    @JvmField
    @SerializedName("id")
    var id: Int = 0

    @JvmField
    @SerializedName("name")
    var name: String? = null



    @JvmField
    @SerializedName("profile_path")
    var profilePath: String? = null
}
