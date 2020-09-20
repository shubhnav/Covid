package com.example.covid.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.covid.Country
import com.example.covid.R
import kotlinx.android.synthetic.main.item_covid.view.*

class Adapter(private val dataList: ArrayList<Country>): RecyclerView.Adapter<XviewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): XviewHolder {
         val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val t = inflater.inflate(R.layout.item_covid, parent, false)
        return XviewHolder(t)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: XviewHolder, position: Int) {
        val data = dataList[itemCount-position-1]
        holder.date.text = data.CountryCode
        holder.cases.text = data.Cases.toString()
    }
}

class XviewHolder(listItemView: View):RecyclerView.ViewHolder(listItemView){
    val date: TextView = listItemView.date
    val cases: TextView = listItemView.cases

}