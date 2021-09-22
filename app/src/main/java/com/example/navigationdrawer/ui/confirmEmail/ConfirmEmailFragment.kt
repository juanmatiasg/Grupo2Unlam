package com.example.navigationdrawer.ui.confirmEmail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.navigationdrawer.R
import com.example.navigationdrawer.databinding.FragmentConfirmEmailBinding
import org.koin.android.viewmodel.ext.android.viewModel

class ConfirmEmailFragment: Fragment() {
    private var _binding: FragmentConfirmEmailBinding? = null

    private val binding get() = _binding!!

    private val mainViewModel: ConfirmEmailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentConfirmEmailBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnToLogin.setOnClickListener { navigateToLogin() }
    }

    private fun navigateToLogin() {
        findNavController().navigate(R.id.action_confirmEmailFragment_to_loginFragment)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}