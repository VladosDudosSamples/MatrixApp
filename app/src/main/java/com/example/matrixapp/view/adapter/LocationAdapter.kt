package com.example.matrixapp.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.matrixapp.R
import com.example.matrixapp.databinding.LocationCardBinding
import com.example.matrixapp.model.City
import com.example.matrixapp.model.Location
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LocationAdapter(
    val context: Context,
    private var locations: List<Location>,
    private val isLocked: Boolean,
    val onCardClickListener: (City) -> Unit = {},
) : RecyclerView.Adapter<LocationAdapter.LocationHolder>() {
    class LocationHolder(val binding: LocationCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationHolder {
        return LocationHolder(
            LocationCardBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return locations.size
    }

    @SuppressLint("ResourceAsColor", "NotifyDataSetChanged")
    override fun onBindViewHolder(holder: LocationHolder, position: Int) {
        val location = locations[position]
        val citiesAdapter = CityAdapter(
            location.cities,
            context,
            false,
            onClick = {
                onCardClickListener(it)
            }
        )
        with(holder.binding) {
            tvCountry.text = location.countryName
            rvCities.adapter = citiesAdapter
            rvCities.adapter!!.notifyDataSetChanged()
            if (location.cities.any { it.isSelected }) {
                imageConnect.setImageResource(R.drawable.radio_back_checked)
            } else {
                imageConnect.setImageResource(R.drawable.radio_back)
            }

                setStrokeColor(location.cities.any { it.isSelected }, holder)
            if (location.cities.any { it.isSelected }) {
                GlobalScope.launch {
                    delay(25)
                    root.strokeColor = ContextCompat.getColor(context, R.color.green)
                }
            }
            if (location.cities.size == 1) {
                tvCity.text = location.cities[0].name
                if (isLocked) {
                    imageStatus.setImageResource(R.drawable.red_lock)
                    imageConnect.visibility = View.GONE
                } else {
                    imageConnect.visibility = View.VISIBLE
                    imageStatus.visibility = View.INVISIBLE
                }
            } else {
                tvCity.text = "${location.cities.size} locations"
                imageStatus.setImageResource(R.drawable.arrow_expose)
                imageConnect.visibility = View.GONE
            }
            root.setOnClickListener {

                if (location.cities.size > 1) {
                    location.isExposed = true
                    if (rvCities.visibility == View.GONE) {
                        imageStatus.rotation = 180F
                        rvCities.visibility = View.VISIBLE
                        if (isLocked) {
                            with(rvCities) {
                                adapter = CityAdapter(
                                    location.cities,
                                    context,
                                    true
                                )
                                layoutManager = LinearLayoutManager(context)
                            }
                        } else {
                            with(rvCities) {
                                adapter = citiesAdapter
                                layoutManager = LinearLayoutManager(context)
                            }
                        }
                    } else {
                        imageStatus.rotation = 0F
                        rvCities.visibility = View.GONE
                    }
                } else {
                    if (!isLocked) {
                        onCardClickListener(location.cities[0])
                        notifyDataSetChanged()
                        updateList(locations)
                        citiesAdapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }

    private fun setStrokeColor(selected: Boolean, holder: LocationHolder) {
        if (!selected) {
            holder.binding.root.strokeColor = ContextCompat.getColor(context, R.color.dialogColor)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: List<Location>) {
        this.locations = list
        notifyDataSetChanged()
    }
}