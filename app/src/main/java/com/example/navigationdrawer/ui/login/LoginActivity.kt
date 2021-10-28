package com.example.navigationdrawer.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.fragment.findNavController
import com.example.navigationdrawer.MainActivity
import com.example.navigationdrawer.R
import com.example.navigationdrawer.databinding.ActivityLoginBinding
import com.example.navigationdrawer.databinding.FragmentLoginBinding
import com.example.navigationdrawer.ui.registrer.RegisterActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit  var binding: ActivityLoginBinding

    private var email: String = ""
    private var password: String = ""
    lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonIniciarSesion.setOnClickListener { login() }
        binding.registrarAquiButton.setOnClickListener { registrarUsuario() }
    }

    private fun registrarUsuario() {
        val intent = Intent(this,RegisterActivity::class.java)
        startActivity(intent)
    }

    private fun login() {
        email = binding.editTextEmailLogin.text.toString()
        password = binding.editTextPasswordLogin.text.toString()


        if (email.isNotEmpty() && password.isNotEmpty()) {
            loginUser()
        } else {
            Toast.makeText(
                this,
                "Por favor ingrese los campos completos",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    private fun loginUser() {
        val intent = Intent(this,MainActivity::class.java)

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                startActivity(intent)
            } else {
                Toast.makeText(this, "No se pudo iniciar sesion", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

}