package com.featuredapps.anasgdxj.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.featuredapps.anasgdxj.R
import com.featuredapps.anasgdxj.model.Post

class PostAdapter(private val onClick: (Post) -> Unit) : ListAdapter<Post, PostAdapter.PostVH>(Diff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostVH(view, onClick)
    }

    override fun onBindViewHolder(holder: PostVH, position: Int) {
        holder.bind(getItem(position))
    }

    class PostVH(itemView: View, val onClick: (Post) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.itemTitle)
        private val body: TextView = itemView.findViewById(R.id.itemBody)
        private var current: Post? = null

        init {
            itemView.setOnClickListener {
                current?.let { onClick(it) }
            }
        }

        fun bind(post: Post) {
            current = post
            title.text = post.title
            body.text = post.body
        }
    }

    object Diff : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Post, newItem: Post) = oldItem == newItem
    }
}
