package com.example.navigationdrawer

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHost
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.navigationdrawer.databinding.ActivityMainBinding
import com.example.navigationdrawer.ui.home.HomeFragment


import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private val REQUIRED_PERMISSION = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
    private lateinit var registerPermissionLaunchar: ActivityResultLauncher<Array<String>>

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView = binding.buttonNavigation

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView3) as NavHostFragment
        navController = navHostFragment.navController

        createPermissionLauncher()

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.nav_slideshow) {
                launchLocationClicked()
            }

        }


        navView.setupWithNavController(navController)

    }

    private fun createPermissionLauncher() {
        registerPermissionLaunchar =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permission ->
                if (permission[Manifest.permission.ACCESS_FINE_LOCATION] == true) {
                    launchLocationClicked()
                } else {
                    Toast.makeText(
                        this,
                        "Se necesita los permisos para lanzar la ubicacion",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun launchLocationClicked(){
        if(arePermissionGranted()){
           navController.navigate(R.id.nav_slideshow)
        }
        else{
            askPermission() // como pido permiso si no fueron otorgados
        }
    }

    private fun askPermission() {
        registerPermissionLaunchar.launch(REQUIRED_PERMISSION)
    }

    private fun arePermissionGranted(): Boolean = REQUIRED_PERMISSION.all{
        ContextCompat.checkSelfPermission(this,it) == PackageManager.PERMISSION_GRANTED
    }


}