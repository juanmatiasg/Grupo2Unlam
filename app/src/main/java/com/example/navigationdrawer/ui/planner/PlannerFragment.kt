package com.example.navigationdrawer.ui.planner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.navigationdrawer.R
import com.example.navigationdrawer.data.entities.MealEntity
import com.example.navigationdrawer.data.entities.PlannerEntity
import com.example.navigationdrawer.data.model.Meals
import com.example.navigationdrawer.databinding.FragmentMealDetailBinding
import com.example.navigationdrawer.databinding.FragmentPlannerBinding
import com.example.navigationdrawer.ui.adapter.AdapterMeals
import com.example.navigationdrawer.ui.mealDetail.MealDetailViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.squareup.picasso.Picasso
import org.koin.android.viewmodel.ext.android.viewModel

class PlannerFragment : Fragment() {
    private var _binding: FragmentPlannerBinding? = null
    private val binding get() = _binding!!

    private val viewModel : MealDetailViewModel by viewModel()

    lateinit var meals: Meals

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlannerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigateToAllMeals()
    }

    private fun navigateToAllMeals() {
        binding.buttonGoToAllMeals.setOnClickListener{
            findNavController().navigate(R.id.action_navigation_planner_to_navigation_meal)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}