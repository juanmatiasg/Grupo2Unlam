package com.example.navigationdrawer.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.fragment.findNavController
import com.example.navigationdrawer.MainActivity
import com.example.navigationdrawer.R
import com.example.navigationdrawer.databinding.ActivityLoginBinding
import com.example.navigationdrawer.databinding.FragmentLoginBinding
import com.example.navigationdrawer.ui.registrer.RegisterActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    lateinit var email: String
    lateinit var password: String

    lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAuth = FirebaseAuth.getInstance()
        binding.buttonIniciarSesion.setOnClickListener { signInUser() }
        binding.registrarAquiButton.setOnClickListener { registrarUsuario() }
    }


    private fun signInUser() {
        email = binding.editTextEmailLogin.text.toString()
        password = binding.editTextPasswordLogin.text.toString()

        if (email.isNullOrEmpty()) {
            binding.editTextEmailLogin.error = "El email es obligatorio"
        } else if (password.isNullOrEmpty()) {
            binding.editTextPasswordLogin.error = "La contrasena es obligatorio"
        } else {
            loginUser()
        }

    }

    private fun loginUser() {

        binding.buttonIniciarSesion.visibility = View.INVISIBLE
        binding.progressBar.visibility = View.VISIBLE

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                val user = mAuth.currentUser
                updateUI(user)

                binding.buttonIniciarSesion.visibility = View.VISIBLE
                binding.progressBar.visibility = View.INVISIBLE


            } else {
                binding.progressBar.visibility = View.INVISIBLE
                binding.buttonIniciarSesion.visibility = View.VISIBLE
                Toast.makeText(this, "No se pudo iniciar sesion", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun updateUI(user: FirebaseUser?) {
        if(user != null){
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }

    /*Este metodo comprobamos si el usuario  previamente ya ha iniciado la sesion*/
    override fun onStart() {
        super.onStart()
        val currentUser = mAuth.currentUser
        updateUI(currentUser)
    }


    private fun registrarUsuario() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }


}