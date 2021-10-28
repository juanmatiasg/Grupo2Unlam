package com.example.navigationdrawer.ui.registrer

import android.app.Application
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.annotation.RequiresApi
import androidx.core.view.get
import androidx.navigation.fragment.findNavController
import com.example.navigationdrawer.R
import com.example.navigationdrawer.data.model.User
import com.example.navigationdrawer.databinding.FragmentRegisterBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.android.bind
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.dsl.koinApplication


class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null

    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    private var name: String = ""
    private var surname: String = ""
    private var email: String = ""
    private var password: String = ""
    private var confirmPassword: String = ""
    private var dateOfBirth: String = ""
    private var weight: String = ""
    private var height: String = ""
    private var gender: String = ""


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        _binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()

        binding.btnFinishLogUp.setOnClickListener { register() }

    }

       private fun register() {
        email = binding.editTextEmail.text.toString()
        password = binding.editTextPassword.toString()

        if (!email.isNullOrEmpty() && !password.isNullOrEmpty()) {
            registerUser()
        } else {
            Toast.makeText(
                requireContext(),
                "Por favor completa los datos correspondientes",
                Toast.LENGTH_SHORT
            ).show()
        }
    }


    private fun registerUser() {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(
            OnCompleteListener {
                if (it.isSuccessful) {
                    //findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                } else {
                    Toast.makeText(
                        requireContext(),
                        "No pudo haber registrado el usuario",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}