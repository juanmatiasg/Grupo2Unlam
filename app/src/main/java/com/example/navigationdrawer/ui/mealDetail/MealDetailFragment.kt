package com.example.navigationdrawer.ui.mealDetail

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.navigationdrawer.R
import com.example.navigationdrawer.data.DataSource
import com.example.navigationdrawer.data.database.AppDataBase
import com.example.navigationdrawer.data.entities.MealEntity
import com.example.navigationdrawer.data.entities.PlannerEntity
import com.example.navigationdrawer.data.model.Meals
import com.example.navigationdrawer.databinding.FragmentMealDetailBinding
import com.example.navigationdrawer.domain.RepoImp
import com.example.navigationdrawer.ui.adapter.AdapterFavourites
import com.example.navigationdrawer.ui.adapter.AdapterMeals
import com.example.navigationdrawer.ui.factory.VMFactory
import com.squareup.picasso.Picasso
import org.koin.android.viewmodel.ext.android.viewModel


class MealDetailFragment : Fragment() {
    private var _binding: FragmentMealDetailBinding? = null
    private val binding get() = _binding!!

    /*private val viewModel by activityViewModels<MealDetailViewModel> {
        VMFactory(
            RepoImp(
                DataSource(
                    AppDataBase.getDatabase(requireActivity().applicationContext)
                )
            )
        )
    }*/
    private val viewModel :MealDetailViewModel by viewModel()

    lateinit var meals: Meals

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMealDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        setupGuardarFavoritos()
        setUpGuardarPlanner()
    }

    private fun setupGuardarFavoritos() {
        binding.buttonFavourite.setOnClickListener {
            viewModel.insertMeal(MealEntity(meals.id,meals.title,meals.image))
            Toast.makeText(requireContext(), R.string.msjeFavoritos,Toast.LENGTH_SHORT).show()
        }
    }
    private fun setUpGuardarPlanner(){
        binding.buttonAddPlanner.setOnClickListener {
            viewModel.insertMealPlanner(PlannerEntity(meals.id,meals.title,meals.image))
            Toast.makeText(requireContext(), R.string.msjePlanner,Toast.LENGTH_SHORT).show()
        }
    }


    private fun setupObserver() {
        requireArguments().let {
            meals = it.getParcelable(AdapterMeals.MEALS_ITEMS)!!
            binding.textViewTitleDetail.text = meals.title
            binding.textViewTitleDescriptionDetail.text=meals.description

            Picasso.get().load(meals.image).into(binding.imageViewMealDetail)
        }


    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}