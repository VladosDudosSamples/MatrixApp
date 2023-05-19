package com.example.matrixapp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.matrixapp.databinding.ListFaqBinding

class FaqAdapter(val context: Context, private val manager: LayoutManager) : RecyclerView.Adapter<FaqAdapter.FaqViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FaqViewHolder {
        return FaqViewHolder(ListFaqBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: FaqViewHolder, position: Int) {
        holder.binding.root.setOnClickListener {
            with(holder.binding){
                imageStatus.rotation += 180F
                questionFullTxt.visibility = if (questionFullTxt.visibility == View.VISIBLE) View.GONE else View.VISIBLE

                manager.scrollToPosition(position)
            }
        }

    }

    override fun getItemCount() = 6

    class FaqViewHolder(val binding: ListFaqBinding) : ViewHolder(binding.root)
}