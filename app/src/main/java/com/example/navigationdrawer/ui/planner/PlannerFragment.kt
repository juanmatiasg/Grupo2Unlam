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
import com.example.navigationdrawer.vo.Status
import androidx.lifecycle.Observer
import com.example.navigationdrawer.data.entities.AfternoonSnackEntity
import com.example.navigationdrawer.data.entities.BreakfastEntity
import com.example.navigationdrawer.data.entities.DinnerEntity
import com.example.navigationdrawer.data.entities.LunchEntity
import com.example.navigationdrawer.ui.adapter.AdapterAfternoonSnack
import com.example.navigationdrawer.ui.adapter.AdapterBreakfast
import com.example.navigationdrawer.ui.adapter.AdapterDinner
import com.example.navigationdrawer.ui.adapter.AdapterLunch
import org.koin.android.viewmodel.ext.android.viewModel

class PlannerFragment : Fragment(), AdapterBreakfast.OnMealsListener,AdapterLunch.OnMealsListener, AdapterAfternoonSnack.OnMealsListener,AdapterDinner.OnMealsListener{
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
        setupObserverBreakfast()
        setupObserverLunch()
        setupObserverAfternoonSnack()
        setupObserverDinner()
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
    private fun setupObserverBreakfast() {

        mainViewModel.getBreakfast().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> {
                }
                Status.SUCCESS -> {
                    val lista = it.data!!.map {
                        Meals(
                            it.id,
                            it.title,
                            it.image,
                            description = it.description,
                            protein = "",
                            strYoutube = it.strYoutube
                        )
                    }
                    binding.rvDesayuno.adapter = AdapterBreakfast(lista, this)

                }
                Status.ERROR -> {
                }
            }
        })
    }

    override fun deleteBreakfastListener(item: Meals, position: Int) {
        mainViewModel.deleteBreakfast(
            BreakfastEntity(
                item.id,
                item.title,
                item.image,
                item.description,
                item.strYoutube
            )
        )
    }

    private fun setupObserverLunch() {

        mainViewModel.getLunch().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> {
                }
                Status.SUCCESS -> {
                    val lista = it.data!!.map {
                        Meals(
                            it.id,
                            it.title,
                            it.image,
                            description = it.description,
                            protein = "",
                            strYoutube = it.strYoutube
                        )
                    }
                    binding.rvAlmuerzo.adapter = AdapterLunch(lista, this)

                }
                Status.ERROR -> {
                }
            }
        })
    }

    override fun deleteLunchListener(item: Meals, position: Int) {
        mainViewModel.deleteLunch(
            LunchEntity(
                item.id,
                item.title,
                item.image,
                item.description,
                item.strYoutube
            )
        )
    }

    private fun setupObserverAfternoonSnack() {

        mainViewModel.getAfternoonSnack().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> {
                }
                Status.SUCCESS -> {
                    val lista = it.data!!.map {
                        Meals(
                            it.id,
                            it.title,
                            it.image,
                            description = it.description,
                            protein = "",
                            strYoutube = it.strYoutube
                        )
                    }
                    binding.rvMerienda.adapter = AdapterAfternoonSnack(lista, this)

                }
                Status.ERROR -> {
                }
            }
        })
    }

    override fun deleteAfternoonSnackListener(item: Meals, position: Int) {
        mainViewModel.deleteAfternoonSnack(
            AfternoonSnackEntity(
                item.id,
                item.title,
                item.image,
                item.description,
                item.strYoutube
            )
        )
    }
    private fun setupObserverDinner() {

        mainViewModel.getDinner().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> {
                }
                Status.SUCCESS -> {
                    val lista = it.data!!.map {
                        Meals(
                            it.id,
                            it.title,
                            it.image,
                            description = it.description,
                            protein = "",
                            strYoutube = it.strYoutube
                        )
                    }
                    binding.rvCena.adapter = AdapterDinner(lista, this)

                }
                Status.ERROR -> {
                }
            }
        })
    }

    override fun deleteDinnerListener(item: Meals, position: Int) {
        mainViewModel.deleteDinner(
            DinnerEntity(
                item.id,
                item.title,
                item.image,
                item.description,
                item.strYoutube
            )
        )
    }


}