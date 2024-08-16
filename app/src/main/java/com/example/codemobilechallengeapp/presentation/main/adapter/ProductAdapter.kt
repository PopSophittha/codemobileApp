package com.example.codemobilechallengeapp.presentation.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.codemobilechallengeapp.R
import com.example.codemobilechallengeapp.core.entity.Product
import com.example.codemobilechallengeapp.databinding.ViewholderProductListBinding
import kotlin.properties.Delegates

class ProductAdapter(
    private val context: Context
) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    var list by Delegates.observable(listOf<Product>()) { _, _, _ -> notifyDataSetChanged() }
    var isDisplayTotalPricePerEach by Delegates.observable(false) { _, _, _ -> notifyDataSetChanged() }

    var actionListener: ActionListener? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {
        val binding = DataBindingUtil.inflate<ViewholderProductListBinding>(
            LayoutInflater.from(parent.context),
            R.layout.viewholder_product_list,
            parent,
            false
        )
        return ProductViewHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        list.getOrNull(position)?.let { item ->
            holder.bind(item)
        }
    }

    inner class ProductViewHolder(binding: ViewholderProductListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private var holderBinding: ViewholderProductListBinding? =
            DataBindingUtil.bind(itemView)

        fun bind(item: Product) {
            holderBinding?.let { holderBinding ->
                holderBinding.executePendingBindings()
                holderBinding.dataItem = item

                Glide.with(context)
                    .load(item.thumbnail)
                    .centerInside()
                    .placeholder(R.drawable.ic_baseline_image_24)
                    .error(R.drawable.ic_baseline_broken_image_24)
                    .into(holderBinding.ivProduct)

                holderBinding.tvView.setOnClickListener {
                    actionListener?.onItemClicked(item)
                }

                holderBinding.tvRating.rating = item.rating?.toFloat() ?: 0f

                holderBinding.tvTotalPricePerEach.text = context.getString(R.string.sum, item.totalPricePerEach)
                holderBinding.tvTotalPricePerEach.visibility = if (isDisplayTotalPricePerEach) View.VISIBLE else View.GONE
            }
        }
    }

    interface ActionListener {
        fun onItemClicked(item: Product)
    }

}