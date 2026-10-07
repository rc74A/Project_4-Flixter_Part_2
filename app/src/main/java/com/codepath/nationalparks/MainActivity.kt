package com.codepath.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codepath.movies.ActorsFragment


/**
 * The MainActivity for the NationalParks app.
 * Launches a [NationalParksFragment].
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val supportFragmentManager = supportFragmentManager
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.content, ActorsFragment(), null).commit()

    }
}