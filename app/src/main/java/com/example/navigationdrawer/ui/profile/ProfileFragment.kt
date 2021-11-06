package com.example.navigationdrawer.ui.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.navigationdrawer.R
import com.example.navigationdrawer.data.entities.MealEntity
import com.example.navigationdrawer.data.entities.PlannerEntity
import com.example.navigationdrawer.data.model.Meals
import com.example.navigationdrawer.databinding.FragmentMealDetailBinding
import com.example.navigationdrawer.databinding.FragmentProfileBinding
import com.example.navigationdrawer.ui.adapter.AdapterMeals
import com.example.navigationdrawer.ui.login.LoginActivity
import com.example.navigationdrawer.ui.mealDetail.MealDetailViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.squareup.picasso.Picasso
import org.koin.android.viewmodel.ext.android.viewModel
import java.lang.Exception

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val db = Firebase.firestore
    lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAuth = FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDataProfile()
    }

    private fun getDataProfile(){
        try {
            db.collection("users").document(mAuth.uid.toString()).get().addOnSuccessListener {
                binding.tvEmail.text = mAuth.currentUser!!.email
                binding.textViewDate.text = it.getString("dateOfBirth")
                binding.tvGender.text = it.getString("gender")
                binding.tvHeight.text = "${it.getString("height")} cm"
                val name = it.getString("name")
                val surname = it.getString("surname")
                binding.tvNameandsurname.text = "${name} ${surname} "
                binding.tvWeight.text = "${it.getString("weight")} Kg"
                binding.spaceHeightAndWeight.minimumWidth = 70
            }
        }
        catch (e:Exception){
            Log.e("ProfileFragment","Error")
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
