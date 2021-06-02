package com.example.navigationdrawer.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.navigationdrawer.R
import com.example.navigationdrawer.databinding.FragmentGalleryBinding
import com.example.navigationdrawer.databinding.FragmentSlideshowBinding
import com.example.navigationdrawer.ui.gallery.GalleryViewModel

class SlideshowFragment : Fragment() {
    //cambiar esto
    private var _binding: FragmentSlideshowBinding? = null

    private val binding get() = _binding!!

    //cambiar esto
    private lateinit var slideshowViewModel: SlideshowViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //cambiar esto
        slideshowViewModel =
            ViewModelProvider(this).get(SlideshowViewModel::class.java)
        _binding = FragmentSlideshowBinding.inflate(layoutInflater,container,false)

        //cambiar esto
        slideshowViewModel.text.observe(viewLifecycleOwner, Observer {
            binding.textSlideshow.text = it
        })
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}