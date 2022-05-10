package com.tatam.minitrademe.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.tatam.minitrademe.R
import com.tatam.minitrademe.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_frag) as NavHostFragment
        val navController = navHostFragment.navController
        //Switching titles for bottom nav bar
        navController.addOnDestinationChangedListener { _, destination, _ ->
            title = when (destination.id) {
                R.id.latestListingsFragment -> resources.getString(R.string.bottom_menu_title_latest_listings)
                R.id.watchlistFragment -> resources.getString(R.string.bottom_menu_title_watchlist)
                R.id.myTradeMeFragment -> resources.getString(R.string.bottom_menu_title_my_trade_me)
                else -> resources.getString(R.string.app_name)
            }
        }
        binding.bottomNavigationView.setupWithNavController(navController)
    }
}