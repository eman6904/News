package com.example.news.ui.APIs

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.news.R
import com.example.news.databinding.SourcesModelBinding
import com.example.news.ui.ui.Categories
import com.example.news.ui.ui.Categories.Companion.category
import com.example.news.ui.ui.Categories.Companion.color
import com.example.news.ui.ui.Home.Companion.selectedItems


class SourcesAdapter(private val list: List<SourceComponents>) :
    RecyclerView.Adapter<SourcesAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: SourcesModelBinding) :
        RecyclerView.ViewHolder(binding.root) {
       val sourceName=binding.sourceName
    }
    private var onClickListener:OnClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            SourcesModelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.sourceName.text = list[position].name

        holder.itemView.setOnClickListener {
            if (onClickListener != null) {
                onClickListener!!.onClick(position, list[position] )
            }
        }
        if(selectedItems[position]){
            setSelectedItemColor(holder)
        }else{
            setUnselectedItemColor(holder)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
    fun setOnClickListener(onClickListener:OnClickListener) {
        this.onClickListener = onClickListener
    }
    interface OnClickListener {
        fun onClick(position: Int, model:SourceComponents)
    }
    fun setSelectedItemColor(holder:ViewHolder)
    {
        when(category)
        {
            "Sports"-> {
                holder.sourceName.setTextColor((Color.parseColor("#FFFFFFFF")))
                holder.sourceName.setBackgroundResource(R.drawable.sports_background)
            }
            "رياضيات"-> {
                holder.sourceName.setTextColor((Color.parseColor("#FFFFFFFF")))
                holder.sourceName.setBackgroundResource(R.drawable.sports_background)
            }
            "Business"->{
                holder.sourceName.setTextColor((Color.parseColor("#FFFFFFFF")))
                holder.sourceName.setBackgroundResource(R.drawable.business_background)
            }
            "اعمال"->{
                holder.sourceName.setTextColor((Color.parseColor("#FFFFFFFF")))
                holder.sourceName.setBackgroundResource(R.drawable.business_background)
            }
            "Science"-> {
                holder.sourceName.setTextColor((Color.parseColor("#FFFFFFFF")))
                holder.sourceName.setBackgroundResource(R.drawable.science_background)
            }
            "علوم"-> {
                holder.sourceName.setTextColor((Color.parseColor("#FFFFFFFF")))
                holder.sourceName.setBackgroundResource(R.drawable.science_background)
            }
            "Technology"->{
                holder.sourceName.setTextColor((Color.parseColor("#FFFFFFFF")))
                holder.sourceName.setBackgroundResource(R.drawable.technology_background)
            }
            "تكنولوجيا"->{
                holder.sourceName.setTextColor((Color.parseColor("#FFFFFFFF")))
                holder.sourceName.setBackgroundResource(R.drawable.technology_background)
            }
        }
    }
    fun setUnselectedItemColor(holder:ViewHolder)
    {
        when(category)
        {
            "Sports"-> {
                holder.sourceName.setTextColor((Color.parseColor(color)))
                holder.sourceName.setBackgroundResource(R.drawable.sports_border)
            }
            "رياضيات"-> {
                holder.sourceName.setTextColor((Color.parseColor(color)))
                holder.sourceName.setBackgroundResource(R.drawable.sports_border)
            }
            "Business"->{
                holder.sourceName.setTextColor((Color.parseColor(color)))
                holder.sourceName.setBackgroundResource(R.drawable.business_border)
            }
            "أعمال"->{
                holder.sourceName.setTextColor((Color.parseColor(color)))
                holder.sourceName.setBackgroundResource(R.drawable.business_border)
            }
            "Science"-> {
                holder.sourceName.setTextColor((Color.parseColor(color)))
                holder.sourceName.setBackgroundResource(R.drawable.science_border)
            }
            "علوم"-> {
                holder.sourceName.setTextColor((Color.parseColor(color)))
                holder.sourceName.setBackgroundResource(R.drawable.science_border)
            }
            "Technology"->{
                holder.sourceName.setTextColor((Color.parseColor(color)))
                holder.sourceName.setBackgroundResource(R.drawable.technology_border)
            }
            "تكنولوجيا"->{
                holder.sourceName.setTextColor((Color.parseColor(color)))
                holder.sourceName.setBackgroundResource(R.drawable.technology_border)
            }
        }
    }
}