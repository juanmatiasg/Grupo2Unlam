package com.example.navigationdrawer.ui.registrer.stepTwo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.navigationdrawer.R
import com.example.navigationdrawer.databinding.FragmentLoginBinding
import com.example.navigationdrawer.databinding.FragmentLogupStepTwoBinding
import com.example.navigationdrawer.databinding.FragmentRegisterBinding
import com.example.navigationdrawer.ui.registrer.RegisterViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class RegisterStepTwoFragment: Fragment() {
    private var _binding: FragmentLogupStepTwoBinding? = null

    private val binding get() = _binding!!

    private val mainViewModel: RegisterStepTwoViewModel by viewModel()

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
        binding.btnFinishLogUp.setOnClickListener { navigateToConfirmEmail() }
    }

    private fun navigateToConfirmEmail() {
        findNavController().navigate(R.id.action_registerStepTwoFragment_to_confirmEmailFragment)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}