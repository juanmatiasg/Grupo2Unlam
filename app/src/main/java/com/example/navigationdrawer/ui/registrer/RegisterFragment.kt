package com.example.navigationdrawer.ui.registrer

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.core.view.get
import androidx.navigation.fragment.findNavController
import com.example.navigationdrawer.R
import com.example.navigationdrawer.data.model.User
import com.example.navigationdrawer.databinding.FragmentRegisterBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.android.viewmodel.ext.android.viewModel


class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null

    private val binding get() = _binding!!

    private val mainViewModel: RegisterViewModel by viewModel()

    private lateinit var auth: FirebaseAuth

    private var name: String = ""
    private var surname: String = ""
    private var email: String= ""
    private var password: String= ""
    private var confirmPassword: String= ""
    private var dateOfBirth: String= ""
    private var weight: String= ""
    private var height: String= ""
    private var gender: String= ""


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        _binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)
        auth = FirebaseAuth.getInstance()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnFinishLogUp.setOnClickListener { /*navigateToStepTwo()*/ checkData() }
        binding.tvBtnGoToLogIn.setOnClickListener { navigateToLogin() }
    }

    private fun navigateToLogin() {
        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
    }


    private fun navigateToStepTwo() {
        findNavController().navigate(R.id.action_registerFragment_to_registerStepTwoFragment)
    }

    private fun checkData(){
        name= binding.editTextName.text.toString()
        surname=binding.editTextSurname.text.toString()
        email=binding.editTextEmail.text.toString()
        password=binding.editTextPassword.text.toString()
        confirmPassword=binding.editTextConfirmPassword.text.toString()
        dateOfBirth= binding.inputTextDateOfBirth.text.toString()
        weight= binding.inputTextWeight.text.toString()
        height= binding.inputTextHeight.text.toString()
        gender= binding.inputTextGender.text.toString()

        if(name.isNotEmpty() && surname.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()){
            if (binding.editTextPassword.text.toString().length >=6) {
                Toast.makeText(requireContext(),"your email: ${(email)}",Toast.LENGTH_SHORT).show()
                registerUser()
            }else{
                Toast.makeText(requireContext(),"La contraseña debe tener al menos 6 caracteres",Toast.LENGTH_LONG).show()
            }
        }else{
            Toast.makeText(requireContext(),"Compruebe los datos",Toast.LENGTH_SHORT).show()
        }
    }

    private fun registerUser(){
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if(it.isSuccessful){
                navigatoToConfirmEmail()
            }else{
                Toast.makeText(requireContext(),"error registerUser",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun navigatoToConfirmEmail(){
        findNavController().navigate(R.id.action_registerFragment_to_confirmEmailFragment)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}