package com.tatam.minitrademe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

/*
* 1. Nav component with all the required fragments
* 2. List of sample items and
* 3. Dagger Hilt + Retrofit
* 4. Implement api call with use case, repo, viewmodel
* 5. Unit tests, integration test, UI tests
* 6. Put config keys, URLS into BuildConfig
*
* */