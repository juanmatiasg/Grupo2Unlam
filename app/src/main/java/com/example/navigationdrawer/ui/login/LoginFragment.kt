package com.example.navigationdrawer.ui.login
/*
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.navigationdrawer.R
import com.example.navigationdrawer.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private var email: String = ""
    private var password: String = ""
    lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAuth = FirebaseAuth.getInstance()
        binding.buttonIniciarSesion.setOnClickListener { login() }
        binding.registrarAquiButton.setOnClickListener { registrarUsuario() }
    }

    private fun registrarUsuario() {
        //findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }

    private fun login() {
        email = binding.editTextEmailLogin.text.toString()
        password = binding.editTextPasswordLogin.text.toString()


        if (email.isNotEmpty() && password.isNotEmpty()) {
            loginUser()
        } else {
            Toast.makeText(
                requireContext(),
                "Por favor ingrese los campos completos",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    private fun loginUser() {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                //findNavController().navigate(R.id.action_loginFragment_to_nav_home)
            } else {
                Toast.makeText(requireContext(), "No se pudo iniciar sesion", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}

*/