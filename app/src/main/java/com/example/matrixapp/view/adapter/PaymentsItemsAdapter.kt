package com.example.matrixapp.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.matrixapp.R
import com.example.matrixapp.databinding.MonthViewBinding
import com.example.matrixapp.databinding.PaymentItemViewBinding
import com.example.matrixapp.model.PaymentItem
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter

class PaymentsItemsAdapter(val context: Context, var list: List<PaymentItem>) :
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
        with(holder.binding) {
            when (list[position].type) {
                0 -> {
                    paymentFrame.strokeColor =
                        ContextCompat.getColor(context, R.color.green_transparent)
                    imagePaymentType.setImageResource(R.drawable.done)
                }

                1 -> {
                    paymentFrame.strokeColor =
                        ContextCompat.getColor(context, R.color.red_transparent)
                    imagePaymentType.setImageResource(R.drawable.close)
                }

                else -> {
                    paymentFrame.strokeColor =
                        ContextCompat.getColor(context, R.color.blue_transparent)
                    imagePaymentType.setImageResource(R.drawable.time)
                }
            }
            paymentDate.text = DateTimeFormatter.ofPattern("dd.MM.yy").format(list[position].date)
            paymentDescription.text = list[position].description
            paymentSum.text = if (list[position].amount<=0) "${list[position].amount}$" else "+${list[position].amount}$"
            paymentTitle.text = list[position].title
        }
    }

    override fun getItemCount(): Int = list.size

    class PaymentItemViewHolder(val binding: PaymentItemViewBinding) :
        RecyclerView.ViewHolder(binding.root)

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(updateList: List<PaymentItem>){
        this.list = updateList
        notifyDataSetChanged()
    }

    class PaymentItemViewHolderMonth(val binding: MonthViewBinding) :
        RecyclerView.ViewHolder(binding.root)
}