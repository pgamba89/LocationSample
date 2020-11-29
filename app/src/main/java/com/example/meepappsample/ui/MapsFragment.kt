package com.example.meepappsample.ui

import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.meepappsample.R
import com.example.meepappsample.databinding.FragmentMapsBinding
import com.example.meepappsample.model.ResponseModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MapsFragment : Fragment(), GoogleMap.OnMarkerClickListener {

    private val modelView: MapsModelView by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentMapsBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_maps, container, false
        )

        binding.viewModel = modelView
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    private val callback = OnMapReadyCallback { googleMap ->

        googleMap.setOnMarkerClickListener(this)

        modelView.locationResponse.observe(viewLifecycleOwner, {
            googleMap.clear()
            val builder = LatLngBounds.Builder()
            it.forEach { ResponseModel ->
                if (ResponseModel.lat != null && ResponseModel.lon != null) {
                    val markerOptions = getMarkerCustomized(ResponseModel)
                    googleMap.addMarker(markerOptions)
                    builder.include(markerOptions.position)
                }
                googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 40))

            }
        })
    }

    private fun getMarkerCustomized(responseModel: ResponseModel): MarkerOptions {
        val markerOptions = MarkerOptions()
        val latLng = LatLng(
            responseModel.lat!!,
            responseModel.lon!!
        )
        val icon = when (responseModel.companyZoneId) {
            382 -> BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)
            402 -> BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)
            378 -> BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)
            else -> BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)
        }

        if (responseModel.name != null) {
            markerOptions.title(responseModel.name)
        }

        return markerOptions.position(latLng).icon(icon)
    }

    override fun onMarkerClick(marker: Marker?): Boolean {
        val addressList = getAddress(marker!!.position)

        if (addressList.isNotEmpty()) {
            val add = addressList[0].getAddressLine(0)
            marker.snippet = add
        }

        marker.showInfoWindow()
        return false
    }

    private fun getAddress(latLng: LatLng): List<Address> {
        val geocode = Geocoder(context, Locale.getDefault())
        return geocode.getFromLocation(latLng.latitude, latLng.longitude, 1)
    }
}