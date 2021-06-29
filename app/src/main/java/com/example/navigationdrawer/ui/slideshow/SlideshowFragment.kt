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
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
//import com.example.navigationdrawer.data.model.Maps
import com.example.navigationdrawer.data.model.MapsPojo
import com.example.navigationdrawer.databinding.FragmentSlideshowBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.database.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.math.log


class SlideshowFragment : Fragment(), OnMapReadyCallback {
    //cambiar esto
    private var _binding: FragmentSlideshowBinding? = null

    private val binding get() = _binding!!

    //cambiar esto
    private lateinit var slideshowViewModel: SlideshowViewModel
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var dataBase: DatabaseReference
    private lateinit var mMap:GoogleMap
    var tmpRealTimeMarker= ArrayList<Marker>()
    var realTimeMarker=ArrayList<Marker>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //cambiar esto
        slideshowViewModel =
            ViewModelProvider(this).get(SlideshowViewModel::class.java)
        _binding = FragmentSlideshowBinding.inflate(layoutInflater,container,false)


        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBase=FirebaseDatabase.getInstance().getReference()

        binding.mapView.getMapAsync(this)


        botonEmergencia()

        fusedLocationClient= LocationServices.getFusedLocationProviderClient(requireActivity())
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        fusedLocationClient.lastLocation.addOnSuccessListener {
            Log.i("latitud","${it.latitude} + ${it.longitude} longitud")
            val latLng = HashMap<String, Any>()
            latLng.put("latitud", it.latitude)
            latLng.put("longitud",it.longitude)
            dataBase.child("usuarios").push().setValue(latLng)


        }


    }

    private fun botonEmergencia() {
        binding.button.setOnClickListener {
            var i: Intent = Intent(Intent.ACTION_CALL);
            i.setData(Uri.parse("tel:+5491123995681"));
            startActivity(i);
        }
    }

    override fun onMapReady(p0: GoogleMap) {
        //mMap=p0

        p0.addMarker(MarkerOptions().position(LatLng(37.4219796,-122.0838577)).title("Aca"))

        /*dataBase.child("usuarios").addValueEventListener(object: ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (marker in realTimeMarker){
                    marker.remove()
                }

                for (snapshot in dataSnapshot.children){
                    var mp:MapsPojo= snapshot.getValue(MapsPojo::class.java)!!
                    var latitud:Double=mp.latitud
                    var longitud:Double=mp.longitud
                    var markerOptions= MarkerOptions()
                    markerOptions.position(LatLng(latitud,longitud))

                    tmpRealTimeMarker.add(mMap.addMarker(markerOptions))


                }
                realTimeMarker.clear()
                realTimeMarker.addAll(tmpRealTimeMarker)*
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })*/
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}