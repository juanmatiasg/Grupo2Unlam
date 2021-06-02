package com.example.navigationdrawer.ui.meal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.navigationdrawer.databinding.FragmentHomeBinding
import com.example.navigationdrawer.databinding.FragmentMealBinding
import com.example.navigationdrawer.ui.home.HomeViewModel

class MealFragment: Fragment() {

    private var _binding: FragmentMealBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var mealViewModel: MealViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mealViewModel =
            ViewModelProvider(this).get(MealViewModel::class.java)
        _binding = FragmentMealBinding.inflate(layoutInflater,container,false)

        mealViewModel.text.observe(viewLifecycleOwner, Observer {
            binding.textMeal.text = it
        })
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}