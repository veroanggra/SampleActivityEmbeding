package com.veroanggra.sampleactivityembeding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.veroanggra.sampleactivityembeding.data.News
import com.veroanggra.sampleactivityembeding.databinding.ItemListBinding

class NewsAdapter(private val onItemClickListener: (News) -> Unit) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    private lateinit var itemBinding: ItemListBinding

    private val differCallback = object : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.id == newItem.id
        }
    }
    val differ = AsyncListDiffer(this, differCallback)

    inner class NewsViewHolder : RecyclerView.ViewHolder(itemBinding.root) {
        fun setData(item: News, onItemClickListener: (News) -> Unit) {
            itemBinding.apply {
                tvTitle.text = item.title
                tvCategory.text = item.category
                tvDate.text = item.date
            }
            itemBinding.root.setOnClickListener {
                onItemClickListener.invoke(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        itemBinding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder()
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.setData(differ.currentList[position], onItemClickListener)
        holder.setIsRecyclable(false)
    }
}