package com.alrozhan.valorantagent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListAgentAdapter(private val listAgent: ArrayList<Agent>): RecyclerView.Adapter<ListAgentAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_agent, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listAgent.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, role, icon) = listAgent[position]
        holder.imgIcon.setImageResource(icon)
        holder.tvName.text = name
        holder.tvRole.text = role

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listAgent[holder.adapterPosition]) }
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgIcon: ImageView = itemView.findViewById(R.id.img_item_icon)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvRole: TextView = itemView.findViewById(R.id.tv_item_role)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Agent)
    }

}