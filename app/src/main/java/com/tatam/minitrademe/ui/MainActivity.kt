package com.tatam.minitrademe.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.tatam.minitrademe.R
import com.tatam.minitrademe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = this.findNavController(R.id.nav_fragment)
        navController.addOnDestinationChangedListener(object : NavController.OnDestinationChangedListener{
            override fun onDestinationChanged(
                controller: NavController,
                destination: NavDestination,
                arguments: Bundle?
            ) {
                title = when (destination.id) {
                    R.id.latestListingsFragment -> resources.getString(R.string.bottom_menu_title_latest_listings)
                    R.id.watchlistFragment -> resources.getString(R.string.bottom_menu_title_watchlist)
                    R.id.myTradeMeFragment -> resources.getString(R.string.bottom_menu_title_my_trade_me)
                    else -> resources.getString(R.string.app_name)
                }
            }

        })
        binding.bottomNavigationView.setupWithNavController(navController)
    }
}