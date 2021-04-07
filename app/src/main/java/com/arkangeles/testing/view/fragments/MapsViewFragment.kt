package com.arkangeles.testing.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.arkangeles.testing.databinding.FragmentMapsViewBinding
import com.arkangeles.testing.interfaces.Constant.Companion.LAT_KEY
import com.arkangeles.testing.interfaces.Constant.Companion.LON_KEY
import com.arkangeles.testing.interfaces.Constant.Companion.MAPVIEW_BUNDLE_KEY
import com.arkangeles.testing.interfaces.Constant.Companion.NAME_KEY
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapsViewFragment : Fragment(), OnMapReadyCallback {

    private lateinit var binding: FragmentMapsViewBinding
    private lateinit var mapView: MapView

    private var lat : Double = 51.5085
    private var lon : Double =-0.1257
    private var name : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null){
            lat = arguments!!.getDouble(LAT_KEY, 0.0)
            lon = arguments!!.getDouble(LON_KEY, 0.0)
            name = arguments?.getString(NAME_KEY, "City")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMapsViewBinding.inflate(layoutInflater)

        mapView = binding.mapView

        val mapViewBundle = savedInstanceState?.getBundle(MAPVIEW_BUNDLE_KEY)

        mapView.onCreate(mapViewBundle)
        mapView.getMapAsync(this)

        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY) ?: Bundle().also {
            outState.putBundle(MAPVIEW_BUNDLE_KEY, it)
        }
        mapView.onSaveInstanceState(mapViewBundle)
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onMapReady(map: GoogleMap) {

        val coordinates = LatLng(lat, lon)
        val marker = MarkerOptions().position(coordinates).title(name)
        map.addMarker(marker)
        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(coordinates, 8f), 1000, null
        )

    }

    override fun onPause() {
        mapView.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        mapView.onDestroy()
        super.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }
}