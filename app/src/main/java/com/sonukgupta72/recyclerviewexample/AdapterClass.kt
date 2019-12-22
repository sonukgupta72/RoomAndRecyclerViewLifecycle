package com.sonukgupta72.recyclerviewexample

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sonukgupta72.recyclerviewexample.db.DataModel
import kotlinx.android.synthetic.main.item_name_list.view.*

class AdapterClass  : RecyclerView.Adapter<AdapterClass.ViewHolder>() {

    private var list : List<DataModel> = ArrayList()
    private val LEFT_TYPE = 1
    private val RIGHT_TPE = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        lateinit var view : ViewHolder
        if (viewType == LEFT_TYPE) {
            view = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_name_list, parent,false))
        } else{
            view = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_name_list2, parent, false))
        }
        Log.d("LifeCycle", "onCreateViewHolder")
        return view;
    }

    override fun getItemCount(): Int {
        Log.d("LifeCycle", "onItemCount")
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        if (position % 2 == 0){
            return LEFT_TYPE
        } else{
            return RIGHT_TPE
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var name : String? = list.get(holder.adapterPosition).name
        Log.d("LifeCycle", "onBindViewHolder")
        holder.textView?.text = name
    }

    fun setNotes(list: List<DataModel>) {
        this.list = list
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var textView: TextView? = itemView.count
    }
}