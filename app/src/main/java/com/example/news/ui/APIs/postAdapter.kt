package com.example.news.ui.APIs

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news.R
import com.example.news.databinding.ArticleModelBinding

class PostAdapter(private val list: List<PostComponents>) :
    RecyclerView.Adapter<PostAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ArticleModelBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val title=binding.title
        val date=binding.date
        val description=binding.description
        val image=binding.image
        val content=binding.content
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ArticleModelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.date.text=list[position].publishedAt
        holder.title.text=list[position].title
        holder.description.text=list[position].description
        holder.content.text=list[position].content
        Glide.with(holder.binding.root).asBitmap().load(Uri.parse(list[position].urlToImage))
            .placeholder(R.drawable.image).into(holder.image)
    }

    override fun getItemCount(): Int {
        return list.size
    }
    }
