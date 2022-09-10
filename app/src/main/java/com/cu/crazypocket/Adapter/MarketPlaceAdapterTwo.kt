package com.cu.crazypocket.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cu.crazypocket.DataClass.CommonModelData
import com.cu.crazypocket.R
import com.google.android.material.card.MaterialCardView

class MarketPlaceAdapterTwo(
    val context: Context,
    private val list: ArrayList<CommonModelData>,
    val onClickListener: MarketPlaceAdapterTwo.MyOnClickListner,
): RecyclerView.Adapter<MarketPlaceAdapterTwo.MyViewHolder>()
{
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
       val restaurantName:TextView=itemView.findViewById(R.id.restaurantName)
       val card: MaterialCardView =itemView.findViewById(R.id.card)
        val discount :TextView = itemView.findViewById(R.id.percentage_discount)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
      val layoutInflater:LayoutInflater=LayoutInflater.from(context)
        val view:View=layoutInflater.inflate(R.layout.recommended_single_rows,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        if (position <= 10){
            holder.restaurantName.text= list[position].shopName
            holder.card.setOnClickListener {
                onClickListener.onClick(position)
                Log.d("Pos", "onBindViewHolder: $position")

            }
        }
        //percentage_discount

        if(list.get(position).data!!.size!=0)
        {
            val map: Map<String, Any> = list.get(position).data!!.get(0) as Map<String, Any>
            val item = map.get("discountPercentage")

            holder.discount.text= "${item}% Discount"
        }



    }

    override fun getItemCount(): Int {
       return if(list.size>10){10}else{list.size}
    }
    interface MyOnClickListner{
        fun onClick(position: Int)
    }

}
