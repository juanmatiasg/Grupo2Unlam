package com.example.navigationdrawer.ui.home

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.navigationdrawer.R
import com.example.navigationdrawer.data.entities.PlannerEntity
import com.example.navigationdrawer.data.model.Meals
import com.example.navigationdrawer.databinding.FragmentHomeBinding
import com.example.navigationdrawer.databinding.FragmentNewHomeBinding
import com.example.navigationdrawer.ui.adapter.AdapterHome
import com.example.navigationdrawer.ui.meal.MealViewModel
import com.example.navigationdrawer.vo.Status
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.items_main.view.*
import org.koin.android.ext.android.bind
import org.koin.android.viewmodel.ext.android.viewModel
import java.lang.Exception
import java.text.DateFormat
import java.util.*

class HomeFragment : Fragment(), AdapterHome.OnMealsListener {

    private var _binding: FragmentNewHomeBinding? = null
    private val binding get() = _binding!!
    lateinit var mAuth: FirebaseAuth
    private val db = Firebase.firestore

    /*private val mainViewModel by viewModels<HomeViewModel> {
        VMFactory(
            RepoImp(DataSource(AppDataBase.getDatabase(requireActivity().applicationContext)))
        )
    }*/

    private val mainViewModel: HomeViewModel by viewModel()

    private lateinit var adapterHome: AdapterHome
    private lateinit var meal: Meals

    private val REQUIRED_PERMISSION = arrayOf(Manifest.permission.CAMERA)
    private lateinit var registerPermissionLaunchar: ActivityResultLauncher<Array<String>>



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        _binding = FragmentNewHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAuth = FirebaseAuth.getInstance()

        setDate()
        setName()
        setCount()
        setupObserver()
        setupRecycler()
        navegarAFragmentMeal()
        //limpiarPlanner()


        createPermissionLauncher()
        binding.buttonQr.setOnClickListener { launchCameraClicked() }

    }

    private fun createPermissionLauncher() {
        registerPermissionLaunchar =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permission ->
                if (permission[Manifest.permission.CAMERA] == true) {
                    launchCamera()
                }
                else{
                    Toast.makeText(requireContext(),"Se necesita los permisos para lanzar la camara",Toast.LENGTH_SHORT).show()
                }
            }

    }

    private fun launchCameraClicked(){
        if(arePermissionGranted()){
            launchCamera()
        }
        else{
            askPermission() // como pido permiso si no fueron otorgados
        }
    }

    private fun launchCamera() {
        findNavController().navigate(R.id.action_nav_home_to_cameraFragment)
    }

    private fun askPermission() {
        registerPermissionLaunchar.launch(REQUIRED_PERMISSION)
    }

    private fun arePermissionGranted(): Boolean = REQUIRED_PERMISSION.all{
        ContextCompat.checkSelfPermission(requireContext(),it) == PackageManager.PERMISSION_GRANTED
    }


    /*private fun limpiarPlanner() {
        binding.btnClearMenu.setOnClickListener {
            mainViewModel.deleteAllPlanner()
            it.findNavController().navigate(R.id.action_nav_home_to_nav_mealFragment)
            Toast.makeText(requireContext(), "Comenzó un nuevo menú", Toast.LENGTH_SHORT).show()
        }
    }*/


    private fun setDate() {
        val calendar = Calendar.getInstance()
        val currentDay = DateFormat.getDateInstance().format(calendar.time)
        binding.date.text = currentDay
    }

    private fun setName(){
        try {
            db.collection("users").document(mAuth.uid.toString()).get().addOnSuccessListener {
                binding.name.text = it.getString("name")
            }
        }
        catch (e: Exception){
            Log.e("ProfileFragment","Error")
        }
    }

    private fun setCount(){
        var count: Int = 0

        binding.addGlass.setOnClickListener {
          binding.countNumber.text = "${++count}"
        }

        binding.removeGlass.setOnClickListener {
            if(count >= -0) {
                binding.countNumber.text = "${--count}"
            }else{
                binding.countNumber.text = "${0}"
            }
        }
    }


    private fun setupRecycler() {

        binding.recyclerViewMain.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            /*adapterHome = AdapterHome(arrayListOf(),this)
            binding.recyclerViewMain.adapter = adapterHome*/
        }
    }

    private fun setupObserver() {

        mainViewModel.getMealsHome().observe(viewLifecycleOwner, Observer {
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
                    binding.recyclerViewMain.adapter = AdapterHome(lista, this)

                    //Log.d("Lista de Favoritos","${it.data}")
                }
                Status.ERROR -> {
                }
            }
        })
    }
    private fun navegarAFragmentMeal() {
        binding.btnAddMealMain.setOnClickListener {
            findNavController().navigate(R.id.action_nav_home_to_nav_mealFragment)
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun deleteFavouriteListener(item: Meals, position: Int) {
        mainViewModel.deleteFromPlanner(
            PlannerEntity(
                item.id,
                item.title,
                item.image,
                item.description,
                item.strYoutube
            )
        )
    }

}


