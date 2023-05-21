package com.example.matrixapp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.matrixapp.R
import com.example.matrixapp.databinding.PaymentCardBinding
import com.example.matrixapp.model.PaymentItem
import java.time.format.DateTimeFormatter

class PaymentCardAdapter(val context: Context, val list: List<PaymentItem>) : RecyclerView.Adapter<PaymentCardAdapter.CardViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return CardViewHolder(PaymentCardBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        with(holder.binding) {
            when (list[position].type) {
                0 -> {
                    paymentFrame.strokeColor =
                        ContextCompat.getColor(
                            context,
                            R.color.green_transparent
                        )
                    imagePaymentType.setImageResource(com.example.matrixapp.R.drawable.done)
                }

                1 -> {
                    paymentFrame.strokeColor =
                        ContextCompat.getColor(
                            context,
                            R.color.red_transparent
                        )
                    imagePaymentType.setImageResource(R.drawable.close)
                }

                else -> {
                    paymentFrame.strokeColor =
                        ContextCompat.getColor(
                            context,
                            R.color.blue_transparent
                        )
                    imagePaymentType.setImageResource(R.drawable.time)
                }
            }
            paymentDate.text =
                DateTimeFormatter.ofPattern("dd.MM.yy").format(list[position].date)
            paymentDescription.text = list[position].description
            paymentSum.text =
                if (list[position].amount <= 0) "${list[position].amount}$" else "+${list[position].amount}$"
            paymentTitle.text = list[position].title
        }
    }

    override fun getItemCount(): Int = list.size

    class CardViewHolder(val binding: PaymentCardBinding) : RecyclerView.ViewHolder(binding.root)
}