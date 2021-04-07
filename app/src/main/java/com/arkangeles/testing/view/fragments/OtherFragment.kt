package com.arkangeles.testing.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.arkangeles.testing.model.LocationsAdapter
import com.arkangeles.testing.databinding.FragmentOtherBinding
import com.arkangeles.testing.room.Location
import com.arkangeles.testing.room.LocationDao
import com.arkangeles.testing.room.LocationDb
import kotlinx.coroutines.launch


class OtherFragment : Fragment() {

    lateinit var locationDao: LocationDao
    private lateinit var binding: FragmentOtherBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOtherBinding.inflate(layoutInflater)
        locationDao = LocationDb.getInstance(requireContext()).locationDao()

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val locationsList = mutableListOf<Location>()

        val adapter = LocationsAdapter()
        binding.recyclerView.adapter = adapter


        lifecycleScope.launch {
            var locations = locationDao.getAll()
            adapter.submitList(locationsList)
            adapter.notifyDataSetChanged()
            for (locs in locations) {
                locationsList.add(locs)
            }
            Log.d("LOCATIONS", "${locations.size}")
        }
        return binding.root
    }
}