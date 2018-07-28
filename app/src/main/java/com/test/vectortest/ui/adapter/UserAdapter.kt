package com.test.vectortest.ui.adapter

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.test.domain.model.User
import com.test.vectortest.R
import kotlinx.android.synthetic.main.item_user.view.*

class UserAdapter : ListAdapter<User, RecyclerView.ViewHolder>(USER_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = UserViewHolder.create(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = (holder as UserViewHolder).bin(getItem(position))

    companion object {
        private val USER_COMPARATOR = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean = oldItem == newItem
        }
    }
}

private class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bin(user: User) {
        itemView.run {
            user_name.text = "${user.login} ${user.id}"
            repository_url.text = user.url
            Glide.with(itemView).load(user.avatarUrl).into(user_avatar)
        }
    }

    companion object {
        fun create(parent: ViewGroup): UserViewHolder = UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false))
    }
}