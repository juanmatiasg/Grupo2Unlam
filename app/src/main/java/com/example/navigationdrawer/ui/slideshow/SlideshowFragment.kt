package com.example.navigationdrawer.ui.slideshow

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
//import com.example.navigationdrawer.data.model.Maps
import com.example.navigationdrawer.data.model.MapsPojo
import com.example.navigationdrawer.databinding.FragmentSlideshowBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*
import com.google.android.gms.maps.R
import com.google.android.gms.maps.model.*
import com.google.firebase.database.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.math.log


class SlideshowFragment : Fragment(), OnMapReadyCallback {
    //cambiar esto
    private var _binding: FragmentSlideshowBinding? = null

    private val binding get() = _binding!!

    private lateinit var slideshowViewModel: SlideshowViewModel

    private lateinit var map:GoogleMap
    private lateinit var mapView: MapView

    companion object{
        const val REQUEST_CODE_LOCATION=0
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //cambiar esto
        slideshowViewModel =
            ViewModelProvider(this).get(SlideshowViewModel::class.java)
        _binding = FragmentSlideshowBinding.inflate(layoutInflater,container,false)

        mapView=binding.mapView
        mapView.getMapAsync(this)
        mapView.onCreate(savedInstanceState)

        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        botonEmergencia()

    }

    private fun botonEmergencia() {
        binding.button.setOnClickListener {
            var i: Intent = Intent(Intent.ACTION_CALL);
            i.setData(Uri.parse("tel:911"));
            startActivity(i);
        }
    }

    private fun isLocationPermissionGranted()= ContextCompat.checkSelfPermission(
        requireContext(),
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED

    private fun enableLocation(){
        if (!::map.isInitialized) return
        if (isLocationPermissionGranted()){
            if (ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            map.isMyLocationEnabled=true
        }else{
            requestLocationPermission()
        }
    }
    private fun requestLocationPermission(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(),Manifest.permission.ACCESS_FINE_LOCATION)){
            Toast.makeText(requireContext(),"active los permisos de ubicacion",Toast.LENGTH_SHORT).show()
        }else{
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_CODE_LOCATION)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            REQUEST_CODE_LOCATION-> if(grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                //Ignorar este error
                map.isMyLocationEnabled=true
            }else{
                Toast.makeText(requireContext(),"falta activar ubicacion",Toast.LENGTH_SHORT).show()
            }
            else->{}
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map=googleMap
        enableLocation()
        marcadores()
    }

    private fun marcadores() {
        val n1=LatLng(-34.6475238,-58.5632108)
        val n2=LatLng(-34.6497127,-58.5708497)
        val n3=LatLng(-34.66881,-58.6072419)
        val n4=LatLng(-34.6796804,-58.6710999)
        val n5=LatLng(-34.6573204,-58.6359523)

        map.addMarker(MarkerOptions()
            .position(n1)
            .title("Nutricionista"))
        map.addMarker(MarkerOptions()
            .position(n2)
            .title("Nutricionista"))
        map.addMarker(MarkerOptions()
            .position(n3)
            .title("Nutricionista"))
        map.addMarker(MarkerOptions()
            .position(n4)
            .title("Nutricionista"))
        map.addMarker(MarkerOptions()
            .position(n5)
            .title("Nutricionista"))
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
        if (!::map.isInitialized)return
        if (!isLocationPermissionGranted()){
            //ignorar este error
            map.isMyLocationEnabled=false
            Toast.makeText(requireContext(),"La ubicacion no esta activada",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }
}