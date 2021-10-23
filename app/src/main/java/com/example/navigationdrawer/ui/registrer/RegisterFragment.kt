package com.example.navigationdrawer.ui.registrer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.navigationdrawer.R
import com.example.navigationdrawer.data.entities.PlannerEntity
import com.example.navigationdrawer.data.model.Meals
import com.example.navigationdrawer.databinding.FragmentHomeBinding
import com.example.navigationdrawer.databinding.FragmentRegisterBinding
import com.example.navigationdrawer.ui.adapter.AdapterHome
import com.example.navigationdrawer.ui.home.HomeViewModel
import com.example.navigationdrawer.vo.Status
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.DateFormat
import java.util.*

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null

    private val binding get() = _binding!!

    private val mainViewModel : RegisterViewModel by viewModel()

    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        _binding = FragmentRegisterBinding.inflate(layoutInflater,container,false)

        val view = binding.root

        //auth = Firebase.auth
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //setDate()
        //navToOtherFragment()
    }


    private fun navToOtherFragment(){
        /*binding.btnAddMealMain.setOnClickListener {
            findNavController().navigate(R.id.registerFragment)
        }*/
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}