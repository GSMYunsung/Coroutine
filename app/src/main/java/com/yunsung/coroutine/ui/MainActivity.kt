package com.yunsung.coroutine.ui

import android.util.Log
import android.view.MenuItem
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.yunsung.coroutine.ui.news.NaverSearchAdapter
import com.yunsung.coroutine.ui.news.NaverSearchViewModel
import com.yunsung.coroutine.R
import com.yunsung.coroutine.base.BaseActivity
import com.yunsung.coroutine.databinding.ActivityMainBinding
import com.yunsung.coroutine.util.NetWorkResult
import com.yunsung.coroutine.util.extension.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private lateinit var navController: NavController

    override fun init() {

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        navController = navHostFragment.navController

        setUpBottomNav()

    }

    private fun setUpBottomNav() {
        supportActionBar?.hide()
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.newsFragment,
                R.id.favoritesFragment,
            )
        )
        binding.mainBottomNavigation.setupWithNavController(navController)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onNavigateUp(): Boolean = navController.navigateUp() || super.onNavigateUp()

    override fun onBackPressed() {
        if (!navController.navigateUp())
            super.onBackPressed()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            navController.navigateUp()
        return true
    }

}