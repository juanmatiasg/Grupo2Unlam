package com.example.navigationdrawer.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.navigationdrawer.R
import com.example.navigationdrawer.data.DataSource
import com.example.navigationdrawer.data.database.AppDataBase
import com.example.navigationdrawer.data.entities.MealEntity
import com.example.navigationdrawer.data.entities.PlannerEntity
import com.example.navigationdrawer.data.model.Meals
import com.example.navigationdrawer.databinding.FragmentHomeBinding
import com.example.navigationdrawer.domain.RepoImp
import com.example.navigationdrawer.ui.adapter.AdapterFavourites
import com.example.navigationdrawer.ui.adapter.AdapterHome
import com.example.navigationdrawer.ui.adapter.AdapterMeals
import com.example.navigationdrawer.ui.factory.VMFactory
import com.example.navigationdrawer.ui.gallery.GalleryViewModel
import com.example.navigationdrawer.ui.meal.MealViewModel
import com.example.navigationdrawer.vo.Status
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.DateFormat
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment(),AdapterHome.OnMealsListener {


    private var _binding: FragmentHomeBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    /*private val mainViewModel by viewModels<HomeViewModel> {
        VMFactory(
            RepoImp(DataSource(AppDataBase.getDatabase(requireActivity().applicationContext)))
        )
    }*/

    private val mainViewModel :HomeViewModel by viewModel()

    private lateinit var adapterHome: AdapterHome
    private lateinit var meal: Meals


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?

    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater,container,false)

        val view = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDate()
        setupObserver()
        setupRecycler()
        navegarAFragmentMeal()
    }


    private fun setDate() {
        val calendar= Calendar.getInstance()
        val currentDay= DateFormat.getDateInstance().format(calendar.time)
        binding.txtDayDateMain.text=currentDay
    }



    private fun setupRecycler() {

        binding.recyclerViewMain.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireContext(), 1)
            /*adapterHome = AdapterHome(arrayListOf(),this)
            binding.recyclerViewMain.adapter = adapterHome*/
        }
    }
    private fun setupObserver() {

        mainViewModel.getMealsHome().observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.LOADING ->{}
                Status.SUCCESS ->{
                    val lista = it.data!!.map {
                        Meals(it.id,it.title,it.image,description = "",protein = "",strYoutube = "")
                    }
                    binding.recyclerViewMain.adapter = AdapterHome(lista,this)

                    //Log.d("Lista de Favoritos","${it.data}")
                }
                Status.ERROR ->{}
            }
        })
    }

    private fun navegarAFragmentMeal(){
        binding.btnAddMealMain.setOnClickListener {
            findNavController().navigate(R.id.nav_mealFragment)
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun deleteFavouriteListener(item: Meals, position: Int) {
        mainViewModel.deleteFromPlanner(PlannerEntity(item.id,item.title,item.image))
    }

}