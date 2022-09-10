package com.cu.crazypocket.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cu.crazypocket.DataClass.CommonModelDataOne
import com.cu.crazypocket.R
import java.util.*
import kotlin.collections.ArrayList

class CommonListDataAdapterOne(val context: Context, private var arrayList:ArrayList<CommonModelDataOne>) : RecyclerView.Adapter<CommonListDataAdapterOne.myViewHolder>() {
    var photosListFiltered: ArrayList<CommonModelDataOne> = ArrayList()


    class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.image)
        val restaurantName: TextView = itemView.findViewById(R.id.restaurantName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view: View = layoutInflater.inflate(R.layout.singlerow_popular, parent, false)
        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {

        for(i in arrayList)
        {
            photosListFiltered.add(CommonModelDataOne(i.shopName,i.imageUrl))
        }
        holder.restaurantName.text = arrayList[position].shopName
        Glide.with(context).load(arrayList[position].imageUrl)
            .into(holder.image)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }


}



