package com.example.navigationdrawer

import android.Manifest
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.onNavDestinationSelected
import com.example.navigationdrawer.databinding.ActivityMainBinding


import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private val REQUIRED_PERMISSION = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
    private lateinit var registerPermissionLaunchar: ActivityResultLauncher<Array<String>>

    private lateinit var mAuth: FirebaseAuth

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAuth = FirebaseAuth.getInstance()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView = binding.buttonNavigation

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView3) as NavHostFragment
        navController = navHostFragment.navController


        navView.menu.findItem(R.id.logOut).setOnMenuItemClickListener { menuItem ->
            val alertDialog = AlertDialog.Builder(this@MainActivity)
            alertDialog.setMessage("Deséas salir de la Aplicación? ")
            alertDialog.setCancelable(false)
            alertDialog.setPositiveButton("Ok", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, p1: Int) {
                     mAuth.signOut()
                     finish()
                }
            })
            alertDialog.setNegativeButton("Cancel", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, p1: Int) {
                    dialog!!.cancel()
                }
            })
            val titulo = alertDialog.create()
            titulo.setTitle("Salida")
            titulo.show()
            true
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

    private fun launchLocationClicked() {
        if (arePermissionGranted()) {
            navController.navigate(R.id.nav_slideshow)
        } else {
            askPermission() // como pido permiso si no fueron otorgados
        }
    }

    private fun askPermission() {
        registerPermissionLaunchar.launch(REQUIRED_PERMISSION)
    }

    private fun arePermissionGranted(): Boolean = REQUIRED_PERMISSION.all {
        ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
    }


}