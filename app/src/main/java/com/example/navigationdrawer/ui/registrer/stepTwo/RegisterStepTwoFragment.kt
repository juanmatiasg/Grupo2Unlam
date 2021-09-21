package com.example.navigationdrawer.ui.registrer.stepTwo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.navigationdrawer.R
import com.example.navigationdrawer.databinding.FragmentLoginBinding
import com.example.navigationdrawer.databinding.FragmentLogupStepTwoBinding
import com.example.navigationdrawer.databinding.FragmentRegisterBinding
import com.example.navigationdrawer.ui.registrer.RegisterFragment
import com.example.navigationdrawer.ui.registrer.RegisterViewModel
import com.google.firebase.auth.FirebaseAuth
import org.koin.android.viewmodel.ext.android.viewModel
import java.sql.Date
import java.util.*

class RegisterStepTwoFragment: Fragment() {
    private var _binding: FragmentLogupStepTwoBinding? = null

    private val binding get() = _binding!!

    private val mainViewModel: RegisterStepTwoViewModel by viewModel()

    var registerFragment = RegisterFragment()

    private var dateOfBirth: String= ""
    private var weight: String =""
    private var height: String=""
    private var gender: String= ""

    lateinit var safeEmail: String
    lateinit var safePassword: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLogupStepTwoBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnFinishLogUp.setOnClickListener { /*navigateToConfirmEmail()*/ checkData() }
    }

    private fun checkData() {
        dateOfBirth=binding.inputTextDateOfBirth.text.toString()
        weight=binding.inputTextWeight.text.toString()
        height=binding.inputTextHeight.text.toString()
        gender=binding.inputTextGender.text.toString()

        //safeEmail= registerFragment.email
        //safePassword= registerFragment.password

        if (dateOfBirth.isNotEmpty() && weight.isNotEmpty() && height.isNotEmpty() && gender.isNotEmpty() && safeEmail.isNotEmpty() && safePassword.isNotEmpty()){
            registerUser()
        }else{
            Toast.makeText(requireContext(),"Error to check data",Toast.LENGTH_SHORT).show()
        }

    }
    private fun registerUser() {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(safeEmail,safePassword).addOnCompleteListener {
            if(it.isSuccessful){
                navigateToConfirmEmail()
            }else{
                Toast.makeText(requireContext(),"error registerUser",Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun navigateToConfirmEmail() {
        findNavController().navigate(R.id.action_registerStepTwoFragment_to_confirmEmailFragment)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}