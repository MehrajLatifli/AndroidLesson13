package com.example.androidlesson13.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidlesson13.models.UserResponseItem
import com.example.androidlesson13.databinding.ItemUserBinding

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val userList = arrayListOf<UserResponseItem>()

    inner class UserViewHolder(val itemUserBinding: ItemUserBinding) :
        RecyclerView.ViewHolder(itemUserBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]

     /*   holder.itemUserBinding.textViewCity.text = user.address?.city.toString()
        holder.itemUserBinding.textViewName.text = "${user.name?.firstname.toString()} ${user.name?.lastname.toString()}"
        holder.itemUserBinding.textViewEmail.text = user.email?.toString()

      */

        holder.itemUserBinding.user =user


    }

    fun updateList(newList: List<UserResponseItem>) {
        userList.clear()
        userList.addAll(newList)
        notifyDataSetChanged()
    }
}