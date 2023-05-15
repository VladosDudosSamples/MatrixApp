package com.example.matrixapp.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.matrixapp.databinding.PrivacyListViewBinding
import com.example.matrixapp.model.PrivacyModel

class PrivacyAdapter(private val context: Context, private val listPrivacy: List<PrivacyModel>) : RecyclerView.Adapter<PrivacyAdapter.PrivacyVH>() {

    class PrivacyVH(val binding: PrivacyListViewBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrivacyVH {
        return PrivacyVH(
            PrivacyListViewBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PrivacyVH, position: Int) {
        val privacyExample = listPrivacy[position]

        holder.binding.title.text = " ${position+1}. ${privacyExample.title}"
        holder.binding.description.text = privacyExample.description
    }

    override fun getItemCount(): Int = listPrivacy.size
}