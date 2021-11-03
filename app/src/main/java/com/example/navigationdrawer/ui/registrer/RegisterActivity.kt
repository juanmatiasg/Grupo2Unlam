package com.example.navigationdrawer.ui.registrer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.navigationdrawer.R
import com.example.navigationdrawer.databinding.ActivityRegisterBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding:ActivityRegisterBinding
    private val db = Firebase.firestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnFinishLogUp.setOnClickListener {  saveIntoDatabaseFirebase()  }
    }

    private fun saveIntoDatabaseFirebase() {
        val name = binding.editTextName.text.toString()
        val surname = binding.editTextSurname.text.toString()
        val dateOfBirth = binding.inputTextDateOfBirth.text.toString()
        val weight = binding.inputTextWeight.text.toString()
        val height = binding.inputTextHeight.text.toString()
        val gender = binding.inputTextGender.text.toString()

        val user = hashMapOf(
            "name" to name,
            "surname" to surname,
            "dateOfBirth" to dateOfBirth,
            "weight" to weight,
            "height" to height,
            "gender" to gender
        )

        db.collection("users").document(binding.editTextEmail.text.toString())
            .set(user)
            .addOnSuccessListener { Log.d("SUCCESS", "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w("FAILURE", "Error writing document", e) }
    }


}