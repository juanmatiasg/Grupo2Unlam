package com.example.navigationdrawer.ui.gallery

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.navigationdrawer.data.DataSource
import com.example.navigationdrawer.data.database.AppDataBase
import com.example.navigationdrawer.data.model.Meals
import com.example.navigationdrawer.databinding.FragmentGalleryBinding
import com.example.navigationdrawer.domain.RepoImp
import com.example.navigationdrawer.ui.adapter.AdapterFavourites
import com.example.navigationdrawer.ui.adapter.AdapterMeals
import com.example.navigationdrawer.ui.factory.VMFactory
import com.example.navigationdrawer.vo.Status

class GalleryFragment : Fragment() {
    //cambiar esto
    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!
    lateinit var adapterFavourites: AdapterFavourites

    private val mealViewModel by viewModels<GalleryViewModel> {
        VMFactory(
            RepoImp(DataSource(AppDataBase.getDatabase(requireActivity().applicationContext)))
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGalleryBinding.inflate(layoutInflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
        setupObserver()
    }


    private fun setupRecycler(){
        binding.rvFavoritos.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFavoritos.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun setupObserver() {
        mealViewModel.getMealsFavoritos().observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.LOADING ->{}
                Status.SUCCESS ->{
                    val lista = it.data!!.map {
                        Meals(it.id,it.title,it.image,calories = "",protein = "")
                    }
                    binding.rvFavoritos.adapter = AdapterFavourites(lista)

                     //Log.d("Lista de Favoritos","${it.data}")
                }
                Status.ERROR ->{}
            }
        })
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}