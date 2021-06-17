package com.example.navigationdrawer.ui.mealDetail

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations.map
import com.example.navigationdrawer.R
import com.example.navigationdrawer.data.DataSource
import com.example.navigationdrawer.data.database.AppDataBase
import com.example.navigationdrawer.data.entities.MealEntity
import com.example.navigationdrawer.data.model.Meals
import com.example.navigationdrawer.databinding.FragmentMealBinding
import com.example.navigationdrawer.databinding.FragmentMealDetailBinding
import com.example.navigationdrawer.domain.Repo
import com.example.navigationdrawer.domain.RepoImp
import com.example.navigationdrawer.ui.adapter.AdapterMeals
import com.example.navigationdrawer.ui.factory.VMFactory
import com.example.navigationdrawer.ui.meal.MealViewModel
import com.example.navigationdrawer.vo.Status
import com.squareup.picasso.Picasso


class MealDetailFragment : Fragment() {
    private var _binding: FragmentMealDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<MealDetailViewModel> {
        VMFactory(
            RepoImp(
                DataSource(
                    AppDataBase.getDatabase(requireActivity().applicationContext)
                )
            )
        )
    }

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
    }

    private fun setupGuardarFavoritos() {
        binding.buttonFavourite.setOnClickListener {
            viewModel.insertMeal(MealEntity(meals.id,meals.title,meals.image))
        }
    }


    private fun setupObserver() {
        requireArguments().let {
            meals = it.getParcelable(AdapterMeals.MEALS_ITEMS)!!
            viewModel.setIdInformation(meals.id)
            binding.textViewTitleDetail.text = meals.title
            binding.textViewNumberCaloriesDetail.text = meals.calories
            Picasso.get().load(meals.image).into(binding.imageViewMealDetail)
        }
        viewModel.fetchMealsInformation.observe(viewLifecycleOwner, Observer { result ->
            when (result.status) {
                Status.LOADING -> {
                }
                Status.SUCCESS -> {
                    binding.textViewResumenDetail.text =
                        Html.fromHtml(result.data!!.summary).toString()
                }
                Status.ERROR -> {
                }
            }
        })

    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}