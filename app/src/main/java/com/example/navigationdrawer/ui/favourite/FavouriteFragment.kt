package com.example.navigationdrawer.ui.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.navigationdrawer.data.entities.MealEntity
import com.example.navigationdrawer.data.model.Meals
import com.example.navigationdrawer.databinding.FragmentFavouriteBinding
import com.example.navigationdrawer.ui.adapter.AdapterFavourites
import com.example.navigationdrawer.vo.Status
import org.koin.android.viewmodel.ext.android.viewModel

class FavouriteFragment : Fragment(), AdapterFavourites.OnMealsListener {
    //cambiar esto
    private var _binding: FragmentFavouriteBinding? = null
    private val binding get() = _binding!!
    lateinit var adapterFavourites: AdapterFavourites

   /* private val mealViewModel by viewModels<GalleryViewModel> {
        VMFactory(
            RepoImp(DataSource(AppDataBase.getDatabase(requireActivity().applicationContext)))
        )
    }*/

   private val mealViewModel:FavouriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavouriteBinding.inflate(layoutInflater, container, false)

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
                        Meals(it.id,it.title,it.image,it.description,protein = "",strYoutube = it.strYoutube)
                    }
                    binding.rvFavoritos.adapter = AdapterFavourites(lista,this)



                    //Log.d("Lista de Favoritos","${it.data}")
                }
                Status.ERROR ->{}
            }
        })
    }



    override fun deleteFavouriteListener(item: Meals, position: Int) {
        mealViewModel.deleteFavourite(MealEntity(item.id,item.title,item.image,item.description,item.strYoutube))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}