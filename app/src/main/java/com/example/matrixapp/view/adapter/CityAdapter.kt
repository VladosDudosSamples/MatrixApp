package com.example.matrixapp.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.matrixapp.databinding.CityCardBinding
import com.example.matrixapp.model.City

class CityAdapter(
    private val list: List<City>,
    val context: Context,
    private val isLocked: Boolean,
    val onClick: (City) -> Unit = {}
) :
    RecyclerView.Adapter<CityAdapter.CityHolder>() {
    class CityHolder(val binding: CityCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityHolder {
        return CityHolder(CityCardBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: CityHolder, position: Int) {
        val city = list[position]
        with(holder.binding) {
            radioButtonConnected.isChecked = city.isSelected
            tvCityName.text = city.name
            if (isLocked) {
                radioButtonConnected.visibility = View.INVISIBLE
            } else {
                image.visibility = View.INVISIBLE
                root.setOnClickListener {
                    onClick(city)
                    notifyDataSetChanged()
                }
            }
        }
    }
}