package com.jose.platziconf.view.ui.fragments


//
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap

import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*


import com.jose.platziconf.model.Ubication

import com.jose.platziconf.R

/**
 * A simple [Fragment] subclass.
 */
class UbicationFragment : Fragment(),OnMapReadyCallback,GoogleMap.OnMarkerClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ubication, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment=childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        val ubication=Ubication()
        val zoom=16f
        val centerMap=LatLng(ubication.latitude,ubication.longitude)

        googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(centerMap,zoom))

        //Añadir un marcador
        val markerOptions=MarkerOptions()
        markerOptions.position(centerMap)
        markerOptions.title("Platzi Conf 2019")

        val bitMapDraw=context?.applicationContext?.let { ContextCompat.getDrawable(it,R.drawable.logo_platzi) }as BitmapDrawable
        val smallMarker=Bitmap.createScaledBitmap(bitMapDraw.bitmap,150,150,false)
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
        googleMap?.setOnMarkerClickListener(this)
        googleMap?.setMapStyle(MapStyleOptions.loadRawResourceStyle(context,R.raw.map_style))
    }

    override fun onMarkerClick(p0: Marker?): Boolean {
        findNavController().navigate(R.id.ubicationDetailFragmentDialog)
        return true
    }

}
