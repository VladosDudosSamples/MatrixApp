package com.example.matrixapp.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.matrixapp.R
import com.example.matrixapp.databinding.PaymentItemViewBinding
import com.example.matrixapp.model.PaymentItem
import com.example.matrixapp.model.PaymentListModel
import java.lang.Exception
import java.time.format.DateTimeFormatter

class PaymentsItemsAdapter(val context: Context, var list: List<PaymentListModel>) :
    RecyclerView.Adapter<PaymentsItemsAdapter.PaymentItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentItemViewHolder {
        return PaymentItemViewHolder(
            PaymentItemViewBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: PaymentItemViewHolder, position: Int) {
        val item = list[position]
        with(holder.binding){
            mTxt.text = DateTimeFormatter.ofPattern("MMM").format(item.month)
            rvPaymentCards.layoutManager = LinearLayoutManager(context)
            rvPaymentCards.adapter = PaymentCardAdapter(context, item.payments)
        }
    }

    override fun getItemCount(): Int = list.size

    class PaymentItemViewHolder(val binding: PaymentItemViewBinding) :
        RecyclerView.ViewHolder(binding.root)

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(updateList: MutableList<PaymentListModel>) {
        this.list = updateList
        notifyDataSetChanged()
    }
}