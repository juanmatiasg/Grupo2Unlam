package com.example.navigationdrawer.ui.help

import android.Manifest
import android.Manifest.permission.CALL_PHONE
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.navigationdrawer.R
import com.example.navigationdrawer.data.model.Polyline
import com.example.navigationdrawer.databinding.FragmentAyudaBinding
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.google.maps.model.EncodedPolyline
import org.koin.android.viewmodel.ext.android.viewModel


@SuppressLint("MissingPermission")
class HelpFragment : Fragment(), OnMapReadyCallback {

    //cambiar esto
    private var _binding: FragmentAyudaBinding? = null
    private val binding get() = _binding!!

    private val helpViewModel: HelpViewModel by viewModel()

    private lateinit var map: GoogleMap

    private lateinit var currentLocation: LatLng
    private val starbucks = LatLng(-34.644593, -58.565088)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //cambiar esto

        _binding = FragmentAyudaBinding.inflate(layoutInflater, container, false)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener { botonEmergencia() }
    }


    override fun onMapReady(googleMaps: GoogleMap) {

        map = googleMaps

        map.addMarker(MarkerOptions().position(starbucks).title("My House"))
        map.isMyLocationEnabled = arePermissionsGranted()
        configureMaps(map.uiSettings)
        retrieveMarkLocation()
        setUpBindings()
        setUpObservers()

    }

    private fun setUpObservers() {

        helpViewModel.currentDirections.observe(this.viewLifecycleOwner, { directions ->
            directions?.run {
                val path = mutableListOf<LatLng>()
                directions.routes.forEach { route ->
                    route.legs.forEach { leg ->
                        leg.steps.forEach { step ->
                            val encodedPoly = EncodedPolyline(step.polyline.points)
                            encodedPoly.decodePath().forEach { latLng ->
                                latLng?.run {
                                    path.add(LatLng(this.lat, this.lng))
                                }
                            }
                        }
                    }
                }

                if (path.size > 0) {
                    val opts =
                        PolylineOptions().addAll(path).color(Color.GREEN).width(15f).visible(true)
                    map.addPolyline(opts)
                }

                map.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 6f))
            }

        })

    }

    private fun setUpBindings() {
        binding.navigate.setOnClickListener { onNavigateClick() }
    }

    private fun onNavigateClick() {
        if (this::currentLocation.isInitialized) {
            helpViewModel.retrieveDirections(currentLocation, starbucks)
        }
    }

    private fun arePermissionsGranted() =
        ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED


    private fun configureMaps(uiSettings: UiSettings) {
        uiSettings.isZoomControlsEnabled = true
        uiSettings.isZoomGesturesEnabled = true
        uiSettings.isCompassEnabled = true
        //uiSettings.isMyLocationButtonEnabled = true
    }

    private fun retrieveMarkLocation() {
        val locationManager = LocationServices.getFusedLocationProviderClient(requireActivity())
        val locationRequest = LocationRequest.create().apply {
            interval = 10_000
            fastestInterval = 5_000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        if (arePermissionsGranted()) {
            locationManager.requestLocationUpdates(
                locationRequest, object : LocationCallback() {
                    override fun onLocationResult(result: LocationResult?) {

                        val locationResult = result!!.lastLocation
                        currentLocation = LatLng(locationResult.latitude, locationResult.longitude)
                        map.addMarker(
                            MarkerOptions().position(currentLocation)
                                .title("Marcador de posicion actual")
                        )
                        //map.moveCamera(CameraUpdateFactory.zoomTo(18f))
                        map.moveCamera(CameraUpdateFactory.newLatLng(currentLocation))
                    }
                }, Looper.getMainLooper()
            )
        }
    }

    private fun botonEmergencia() {
        var i = Intent(Intent.ACTION_CALL)
        i.data = Uri.parse("tel:08003330160")


        if (ContextCompat.checkSelfPermission(
                requireContext(),
                CALL_PHONE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            startActivity(i)
        } else {
            requestPermissions(arrayOf(Manifest.permission.CALL_PHONE), 1);
        }
    }


}


