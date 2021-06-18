package com.example.navigationdrawer.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.navigationdrawer.R
import com.example.navigationdrawer.data.DataSource
import com.example.navigationdrawer.data.database.AppDataBase
import com.example.navigationdrawer.data.model.Meals
import com.example.navigationdrawer.databinding.FragmentHomeBinding
import com.example.navigationdrawer.domain.RepoImp
import com.example.navigationdrawer.ui.adapter.AdapterHome
import com.example.navigationdrawer.ui.adapter.AdapterMeals
import com.example.navigationdrawer.ui.factory.VMFactory
import com.example.navigationdrawer.ui.meal.MealViewModel
import com.example.navigationdrawer.vo.Status
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val mainViewModel by viewModels<HomeViewModel> {
        VMFactory(
            RepoImp(DataSource(AppDataBase.getDatabase(requireActivity().applicationContext)))
        )
    }
    private lateinit var adapterHome: AdapterHome

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?

    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater,container,false)

        mainViewModel.text.observe(viewLifecycleOwner, Observer {
            binding.textHome.text = it
        })
        val view = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun setupRecycler() {
        binding.recyclerViewMain.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireContext(), 1)
            adapterHome = AdapterHome(arrayListOf())
            binding.recyclerViewMain.adapter = adapterHome
        }
    }

    private fun setupObserver() {
        mainViewModel.fetchMeals.observe(viewLifecycleOwner, Observer {
            it?.let { result ->
                when (result.status) {
                    Status.LOADING -> {

                    }
                    Status.SUCCESS -> {

                        binding.recyclerViewMain.visibility = View.VISIBLE
                        adapterHome = AdapterHome(result.data!!.meals)
                        binding.recyclerViewMain.adapter = adapterHome
                        //result.data?.let { listMeals -> retrieveList(listMeals.meals) }
                    }
                    Status.ERROR -> {
                    }
                }

            }
        })

    }

    private fun retrieveList(list: ArrayList<Meals>) {
        adapterHome.apply {
            getAddListMeals(list)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}