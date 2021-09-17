package com.example.navigationdrawer.ui.registrer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.navigationdrawer.R
import com.example.navigationdrawer.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import org.koin.android.viewmodel.ext.android.viewModel


class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null

    private val binding get() = _binding!!

    private val mainViewModel: RegisterViewModel by viewModel()

    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        _binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)
        //auth = Firebase.auth
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //setDate()
        //navToOtherFragment()
        binding.btnNextStepSignUp.setOnClickListener { navigateToStepTwo() }
    }

    private fun navigateToLogin() {
        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
    }


    private fun navigateToStepTwo() {
        findNavController().navigate(R.id.action_registerFragment_to_registerStepTwoFragment)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}