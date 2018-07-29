package com.test.vectortest.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.test.domain.model.User
import com.test.vectortest.R
import kotlinx.android.synthetic.main.item_user.view.*

class UserAdapter(private val userList: List<User>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int = userList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = UserViewHolder.create(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = (holder as UserViewHolder).bin(userList[position])
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