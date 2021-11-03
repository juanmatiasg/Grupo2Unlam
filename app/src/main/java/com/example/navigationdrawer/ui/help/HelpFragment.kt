package com.example.navigationdrawer.ui.help

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
//import com.example.navigationdrawer.data.model.Maps
import com.example.navigationdrawer.databinding.FragmentAyudaBinding
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*
import java.util.*


class HelpFragment : Fragment(), OnMapReadyCallback {
    //cambiar esto
    private var _binding: FragmentAyudaBinding? = null
    private val binding get() = _binding!!

    private lateinit var helpViewModel: HelpViewModel

    private lateinit var map: GoogleMap
    private lateinit var mapView: MapView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //cambiar esto
        helpViewModel =
            ViewModelProvider(this).get(HelpViewModel::class.java)
        _binding = FragmentAyudaBinding.inflate(layoutInflater, container, false)

        mapView = binding.mapView
        mapView.getMapAsync(this)

        //mapView.onCreate(savedInstanceState)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener { botonEmergencia() }
    }

    private fun botonEmergencia() {
        var i: Intent = Intent(Intent.ACTION_CALL)
        i.data = Uri.parse("tel:08003330160")
        startActivity(i);
    }

    override fun onMapReady(googleMaps: GoogleMap) {
        map = googleMaps
        val sydney = LatLng(-34.0, 151.0)
        map.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney))

    }




}

/*private fun isLocationPermissionGranted()= ContextCompat.checkSelfPermission(
    requireContext(),
    Manifest.permission.ACCESS_FINE_LOCATION
) == PackageManager.PERMISSION_GRANTED

private fun enableLocation() {
    if (!::map.isInitialized) return
    if (isLocationPermissionGranted()) {
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
        map.isMyLocationEnabled = true
    } else {
        requestLocationPermission()
    }
}

private fun requestLocationPermission() {
    if (ActivityCompat.shouldShowRequestPermissionRationale(
            requireActivity(),
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    ) {
        Toast.makeText(requireContext(), "active los permisos de ubicacion", Toast.LENGTH_SHORT)
            .show()
    } else {
        ActivityCompat.requestPermissions(
            requireActivity(), arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            REQUEST_CODE_LOCATION
        )
    }
}

override fun onRequestPermissionsResult(
    requestCode: Int,
    permissions: Array<out String>,
    grantResults: IntArray
) {
    when (requestCode) {
        REQUEST_CODE_LOCATION -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //Ignorar este error
            map.isMyLocationEnabled = true
        } else {
            Toast.makeText(requireContext(), "falta activar ubicacion", Toast.LENGTH_SHORT)
                .show()
        }
        else -> {
        }
    }
}

override fun onMapReady(googleMap: GoogleMap) {
    map = googleMap
    launchMaps()
    marcadores()
}

private fun launchMaps() {

}

private fun marcadores() {
    val n1 = LatLng(-34.6475238, -58.5632108)
    val n2 = LatLng(-34.6497127, -58.5708497)
    val n3 = LatLng(-34.66881, -58.6072419)
    val n4 = LatLng(-34.6796804, -58.6710999)
    val n5 = LatLng(-34.6573204, -58.6359523)

    map.addMarker(
        MarkerOptions()
            .position(n1)
            .title("Nutricionista")
    )
    map.addMarker(
        MarkerOptions()
            .position(n2)
            .title("Nutricionista")
    )
    map.addMarker(
        MarkerOptions()
            .position(n3)
            .title("Nutricionista")
    )
    map.addMarker(
        MarkerOptions()
            .position(n4)
            .title("Nutricionista")
    )
    map.addMarker(
        MarkerOptions()
            .position(n5)
            .title("Nutricionista")
    )
}

companion object {
    const val REQUEST_CODE_LOCATION = 0
}

 override fun onStart() {
     super.onStart()
     mapView.onStart()
 }

 override fun onResume() {
     super.onResume()
     mapView.onResume()
     if (!::map.isInitialized) return
     if (!isLocationPermissionGranted()) {
         //ignorar este error
         map.isMyLocationEnabled = false
         Toast.makeText(requireContext(), "La ubicacion no esta activada", Toast.LENGTH_SHORT)
             .show()
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
 }*/
