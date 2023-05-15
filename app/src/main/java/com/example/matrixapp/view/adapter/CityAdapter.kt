package com.example.matrixapp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.matrixapp.databinding.CityCardBinding
import com.example.matrixapp.model.City

class CityAdapter(val list: List<City>, val context: Context) :
    RecyclerView.Adapter<CityAdapter.CityHolder>() {
    class CityHolder(val binding: CityCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityHolder {
        return CityHolder(CityCardBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CityHolder, position: Int) {
        val city = list[position]
        with(holder.binding) {
            tvCityName.text = city.name
        }
    }
}