package com.example.navigationdrawer.ui.meal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.navigationdrawer.data.DataSource
import com.example.navigationdrawer.data.model.Meals
import com.example.navigationdrawer.databinding.FragmentMealBinding
import com.example.navigationdrawer.domain.RepoImp
import com.example.navigationdrawer.ui.adapter.AdapterMeals
import com.example.navigationdrawer.ui.factory.VMFactory
import com.example.navigationdrawer.vo.Status
import kotlinx.android.synthetic.main.fragment_meal.*

class MealFragment : Fragment() {

    private var _binding: FragmentMealBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val mealViewModel by viewModels<MealViewModel> {
        VMFactory(
            RepoImp(DataSource())
        )
    }
    private lateinit var adapterMeals: AdapterMeals

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMealBinding.inflate(layoutInflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
        setupObserver()
        searchComida()
    }

    private fun setupRecycler() {
        binding.recyclerViewMeal.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapterMeals = AdapterMeals(arrayListOf())
            binding.recyclerViewMeal.adapter = adapterMeals
        }
    }

    private fun setupObserver() {
        mealViewModel.fetchMeals.observe(viewLifecycleOwner, Observer {
            it?.let { result ->
                when (result.status) {
                    Status.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    Status.SUCCESS -> {
                        binding.progressBar.visibility = View.INVISIBLE
                        binding.recyclerViewMeal.visibility = View.VISIBLE
                        adapterMeals = AdapterMeals(result.data!!.meals)
                        binding.recyclerViewMeal.adapter = adapterMeals
                        //result.data?.let { listMeals -> retrieveList(listMeals.meals) }
                    }
                    Status.ERROR -> {
                    }
                }

            }
        })

    }

    private fun retrieveList(list: ArrayList<Meals>) {
        adapterMeals.apply {
            getAddListMeals(list)
        }
    }

    private fun searchComida() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                mealViewModel.setComida(query.toString())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                //mealViewModel.setComida(newText.toString())
                return false
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}