package com.example.navigationdrawer.ui.planner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.navigationdrawer.R
import com.example.navigationdrawer.data.model.Meals
import com.example.navigationdrawer.databinding.FragmentPlannerBinding
import com.example.navigationdrawer.ui.adapter.AdapterHome
import com.example.navigationdrawer.vo.Status
import androidx.lifecycle.Observer
import com.example.navigationdrawer.data.entities.BreakfastEntity
import com.example.navigationdrawer.ui.adapter.AdapterBreakfast
import org.koin.android.viewmodel.ext.android.viewModel

class PlannerFragment : Fragment() {
    private var _binding: FragmentPlannerBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel: PlannerViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPlannerBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navToMeals()
        setUpRecyclerDesayuno()
        setUpRecyclerAlmuerzo()
        setUpRecyclerMerienda()
        setUpRecyclerCena()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun navToMeals(){
        binding.btnAddMealMain.setOnClickListener {
            it.findNavController().navigate(R.id.action_plannerFragment_to_nav_mealFragment)
        }
    }

    private fun setUpRecyclerDesayuno(){
        binding.rvDesayuno.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)

        }
    }
    private fun setUpRecyclerAlmuerzo(){
        binding.rvAlmuerzo.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        }
    }

    private fun setUpRecyclerMerienda() {
        binding.rvMerienda.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        }
    }

    private fun setUpRecyclerCena() {
        binding.rvCena.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        }
    }

}