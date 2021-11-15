package com.example.navigationdrawer.ui.registrer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.navigationdrawer.R
import com.example.navigationdrawer.data.model.User
import com.example.navigationdrawer.databinding.ActivityRegisterBinding
import com.example.navigationdrawer.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {


    private lateinit var binding: ActivityRegisterBinding
    private val db = Firebase.firestore
    lateinit var mAuth: FirebaseAuth

    lateinit var name: String
    lateinit var surname: String
    lateinit var email: String
    lateinit var password: String
    lateinit var dateOfBirth: String
    lateinit var weight: String
    lateinit var height: String
    lateinit var gender: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mAuth = FirebaseAuth.getInstance()
        binding.btnFinishLogUp.setOnClickListener { eventos() }
        binding.tvBtnGoToLogIn.setOnClickListener {
            var login = Intent(this,LoginActivity::class.java)
            startActivity(login)
        }
    }

    private fun eventos() {
        name = binding.editTextName.text.toString()
        surname = binding.editTextSurname.text.toString()
        email = binding.editTextEmail.text.toString()
        password = binding.editTextPassword.text.toString()
        dateOfBirth = binding.inputTextDateOfBirth.text.toString()
        weight = binding.inputTextWeight.text.toString()
        height = binding.inputTextHeight.text.toString()
        gender = binding.inputTextGender.text.toString()


        if (name.isNullOrEmpty()) {
            binding.editTextName.error = "El Nombre es obligatorio"
            Toast.makeText(this, "Por favor completa el campo Nombre", Toast.LENGTH_SHORT).show()
        } else if (surname.isNullOrEmpty()) {
            binding.editTextSurname.error = "El Apellido es obligatorio"
            Toast.makeText(this, "Por favor completa el campo Apellido", Toast.LENGTH_SHORT).show()
        } else if (email.isNullOrEmpty()) {
            binding.editTextEmail.error = "El Email es obligatorio"
            Toast.makeText(this, "Por favor completa el campo Email", Toast.LENGTH_SHORT).show()
        } else if (password.isNullOrEmpty()) {
            binding.editTextPassword.error = "La contraseña es obligatorio"
            Toast.makeText(this, "Por favor completa el campo Contrasena", Toast.LENGTH_SHORT)
                .show()
        } else if (dateOfBirth.isNullOrEmpty()) {
            binding.inputTextDateOfBirth.error = "La fecha de Nacimiento es obligatorio"
            Toast.makeText(
                this,
                "Por favor completa el campo Fecha de Nacimiento",
                Toast.LENGTH_SHORT
            ).show()
        } else if (weight.isNullOrEmpty()) {
            binding.inputTextHeight.error = "El peso es obligatorio"
            Toast.makeText(this, "Por favor completa el campo Peso", Toast.LENGTH_SHORT).show()
        } else if (height.isNullOrEmpty()) {
            binding.inputTextHeight.error = "La Altura es obligatorio"
            Toast.makeText(this, "Por favor completa el campo Altura", Toast.LENGTH_SHORT).show()
        } else if (gender.isNullOrEmpty()) {
            binding.inputTextGender.error = "El peso es obligatorio"
            Toast.makeText(this, "Por favor completa el campo Genero", Toast.LENGTH_SHORT).show()
        } else {
            createUser()
        }
    }

    private fun createUser() {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                val user = mAuth.currentUser
                updateUI(user)
            } else {
                Toast.makeText(this, "Error al registrar el usuario", Toast.LENGTH_SHORT).show()
                updateUI(null)
            }
        }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            /*Almacenar en Firestore*/
            val nuevoUsuario = User(name, surname, dateOfBirth, weight, height, gender)

            /*El uid nos devuelve la id del identificador de la autenticacion del firebase */
            db.collection("users").document(user.uid).set(nuevoUsuario).addOnSuccessListener {
                finish() /*Destruir de la pila de navegacion*/
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }


        } else {
            Toast.makeText(this, "No se ha podido registrar correctamente", Toast.LENGTH_SHORT)
                .show()
        }
    }
}
