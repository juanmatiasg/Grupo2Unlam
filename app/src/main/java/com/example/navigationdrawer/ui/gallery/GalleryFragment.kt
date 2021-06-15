package com.example.navigationdrawer.ui.gallery

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.navigationdrawer.R
import com.example.navigationdrawer.data.DataSource
import com.example.navigationdrawer.data.database.AppDataBase
import com.example.navigationdrawer.databinding.FragmentGalleryBinding
import com.example.navigationdrawer.databinding.FragmentHomeBinding
import com.example.navigationdrawer.domain.RepoImp
import com.example.navigationdrawer.ui.factory.VMFactory
import com.example.navigationdrawer.ui.home.HomeViewModel
import com.example.navigationdrawer.ui.mealDetail.MealDetailViewModel
import com.example.navigationdrawer.vo.Status

class GalleryFragment : Fragment() {
    private val viewModel by activityViewModels<MealDetailViewModel> {
        VMFactory(
            RepoImp(
                DataSource(
                    AppDataBase.getDatabase(requireActivity().applicationContext)
                )
            )
        )

    }



    //cambiar esto
    private var _binding: FragmentGalleryBinding? = null

    private val binding get() = _binding!!

    //cambiar esto
    private lateinit var galleryViewModel: GalleryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //cambiar esto
        galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)
        _binding = FragmentGalleryBinding.inflate(layoutInflater,container,false)

        //cambiar esto
        galleryViewModel.text.observe(viewLifecycleOwner, Observer {
            binding.textGallery.text = it
        })
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMealsFavourites().observe(viewLifecycleOwner, Observer { result->
            when(result){
                Status.LOADING->{}
                Status.SUCCESS->{
                    Log.d("LISTA_FAVORITOS", "${result.data}")
                }
                Status.ERROR->{

                }
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}