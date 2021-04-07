package com.arkangeles.testing.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arkangeles.testing.databinding.LocationsItemBinding
import com.arkangeles.testing.room.Location

class LocationsAdapter : ListAdapter<Location, LocationsAdapter.LocationsHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Location>() {

        override fun areItemsTheSame(oldItem: Location, newItem: Location): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Location, newItem: Location): Boolean {
            return oldItem == newItem
        }
    }

    inner class LocationsHolder(private val binding: LocationsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(location: Location) {
            binding.cityName.text = location.cityName
            binding.lat.text = "Lat: " + location.lat.toString()
            binding.lon.text = "Lon: " +location.lon.toString()

            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationsHolder {
        val binding = LocationsItemBinding.inflate(LayoutInflater.from(parent.context))
        return LocationsHolder(binding)
    }

    override fun onBindViewHolder(holder: LocationsHolder, position: Int) {
        val location = getItem(position)
        holder.bind(location)
    }
}