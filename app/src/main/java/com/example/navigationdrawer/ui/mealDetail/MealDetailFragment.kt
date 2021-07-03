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
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
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
        setUpGuardarPlanner()
    }

    private fun setupGuardarFavoritos() {
        binding.buttonFavourite.setOnClickListener {
            viewModel.insertMeal(MealEntity(meals.id,meals.title,meals.image,meals.description,meals.strYoutube))
            Toast.makeText(requireContext(), R.string.msjeFavoritos,Toast.LENGTH_SHORT).show()
        }
    }
    private fun setUpGuardarPlanner(){
        binding.buttonAddPlanner.setOnClickListener {
            viewModel.insertMealPlanner(PlannerEntity(meals.id,meals.title,meals.image,meals.description,meals.strYoutube))
            Toast.makeText(requireContext(), R.string.msjePlanner,Toast.LENGTH_SHORT).show()
        }
    }


    private fun setupObserver() {
        requireArguments().let {
            meals = it.getParcelable(AdapterMeals.MEALS_ITEMS)!!
            //viewModel.setIdInformation(meals.id)
            binding.textViewTitleDetail.text = meals.title
            //binding.textViewNumberCaloriesDetail.text = meals.strYoutube
            binding.textViewTitleDescriptionDetail.text=meals.description

            binding.youtubePlay.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    val expressionString= "https://www.youtube.com/watch?v="
                    val videoId = meals.strYoutube.replace(expressionString,"")
                    youTubePlayer.loadVideo(videoId, 0f)
                }
            })

            Picasso.get().load(meals.image).into(binding.imageViewMealDetail)
        }


    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}