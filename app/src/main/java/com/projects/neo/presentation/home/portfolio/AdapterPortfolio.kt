package com.projects.neo.presentation.home.portfolio

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.projects.neo.data.model.Portfolio
import com.projects.neo.data.util.AppHelper
import com.projects.neo.data.util.extensions.setValueColor
import com.projects.neo.databinding.ItemPortfolioBinding
import kotlin.math.abs

class AdapterPortfolio(context: Context, var itemList:ArrayList<Portfolio>): RecyclerView.Adapter<AdapterPortfolio.ItemViewHolder>() {
    var context = context
    fun getList():ArrayList<Portfolio>{
        return itemList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemPortfolioBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class ItemViewHolder(
        val binding:ItemPortfolioBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: Portfolio){
            binding.tvBalance.text = "$ ${AppHelper.getFormattedNumber(item.balance.toString())}"
            binding.tvPortfolioName.text = item.name
            binding.tvInvestmentType.text = item.investmentType
            binding.tvLatestDailyEarning.text = "$ ${AppHelper.getFormattedNumber(abs( item.latestDailyEarning).toString())}"
            binding.tvLatestDailyEarning.setValueColor(item.latestDailyEarning)
            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(item)
                }
            }
        }
    }
    private var onItemClickListener :((Portfolio)->Unit)?=null

    fun setOnItemClickListener(listener : (Portfolio)->Unit){
        onItemClickListener = listener
    }

}
