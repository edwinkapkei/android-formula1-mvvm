package com.edwinkapkei.formula1.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.edwinkapkei.formula1.R
import com.edwinkapkei.formula1.databinding.ActivityMainBinding
import com.edwinkapkei.formula1.views.viewmodel.CurrentScheduleViewModel
import com.edwinkapkei.formula1.views.viewmodel.CurrentScheduleViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigation.setupWithNavController(navController)

        val appBarConfiguration =
            AppBarConfiguration(setOf(R.id.scheduleFragment, R.id.driversFragment, R.id.standingsFragment))
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)

        binding.toolbar.title = getString(R.string.app_name)
    }
}