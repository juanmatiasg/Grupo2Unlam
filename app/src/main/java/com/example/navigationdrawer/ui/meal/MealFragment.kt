package com.example.navigationdrawer.ui.meal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.navigationdrawer.data.DataSource
import com.example.navigationdrawer.data.model.Meals
import com.example.navigationdrawer.databinding.FragmentHomeBinding
import com.example.navigationdrawer.databinding.FragmentMealBinding
import com.example.navigationdrawer.domain.RepoImp
import com.example.navigationdrawer.ui.adapter.AdapterMeals
import com.example.navigationdrawer.ui.home.HomeViewModel
import com.example.navigationdrawer.vo.Status

class MealFragment: Fragment() {

    private var _binding: FragmentMealBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val mealViewModel by viewModels<MealViewModel> {VMFactory(RepoImp(DataSource())) }
    private lateinit var adapterMeals: AdapterMeals

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMealBinding.inflate(layoutInflater,container,false)

        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
        setupObserver()
    }

    private fun setupRecycler() {
        binding.recyclerViewMeal.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireContext(),2)
            adapterMeals = AdapterMeals(arrayListOf())
            binding.recyclerViewMeal.adapter = adapterMeals
        }
    }

    private fun setupObserver(){
        mealViewModel.setComida("chicken")
        mealViewModel.fetchMeals.observe(viewLifecycleOwner, Observer {
            it?.let{result ->
                when(result.status){
                    Status.LOADING ->{
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    Status.SUCCESS ->{
                        binding.progressBar.visibility = View.INVISIBLE
                        binding.recyclerViewMeal.visibility = View.VISIBLE
                        result.data?.let { listMeals -> retrieveList(listMeals.meals)}
                    }
                    Status.ERROR ->{}
                }

            }
        })

    }

    private fun retrieveList(list:ArrayList<Meals>) {
        adapterMeals.apply {
            getAddListMeals(list)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}