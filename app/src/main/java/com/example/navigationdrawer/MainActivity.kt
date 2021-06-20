package com.example.navigationdrawer

import android.R
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.navigationdrawer.databinding.FragmentHomeBinding
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {
    private var _binding: MainActivity? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_mealFragment), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
    private var usernameEditText: EditText? = null
    private var passwordEditText: EditText? = null
    private var loginButton: Button? = null
    private var loginViaGoogle: Button? = null


    fun Login(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding.loginButton
        fin
        val username: EditText =findViewById(R.id.act)
        passwordEditText = findViewById(R.id.activity_main_passwordEditText)
        loginButton= findViewById(R.id.activity_main_loginButton)
        loginViaGoogle = findViewById(R.id.activity_main_loginGoogleS)
        Button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (usernameEditText.getText().length > 0 && passwordEditText.getText().length > 0) {
                    val toastMessage = "Username: " + usernameEditText.getText()
                        .toString() + ", Password: " + passwordEditText.getText().toString()
                    Toast.makeText(applicationContext, toastMessage, Toast.LENGTH_SHORT).show()
                } else {
                    val toastMessage = "Username or Password are not populated"
                    Toast.makeText(applicationContext, toastMessage, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}