package com.cu.crazypocket.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cu.crazypocket.DataClass.CommonModelData
import com.cu.crazypocket.R
import com.google.android.material.card.MaterialCardView

class CommonListDataAdapter(val context: Context, private var arrayList:ArrayList<CommonModelData>, val onClickListener: CommonListDataAdapter.MyOnClickListner) : RecyclerView.Adapter<CommonListDataAdapter.myViewHolder>()
{

     var list=arrayList



    fun listCall(lists:ArrayList<CommonModelData>)
    {
        list.addAll(lists)
        notifyDataSetChanged()
    }

    fun filterdata(vlaue : String){
        list = if (vlaue.isNullOrEmpty()){
            list
        }else{
            list.filter { it.shopName?.uppercase()?.contains(vlaue)== true || it.shopName?.lowercase()?.contains(vlaue)== true } as ArrayList<CommonModelData>
        }

    }

    class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val image: ImageView =itemView.findViewById(R.id.image)
        val restaurantName: TextView =itemView.findViewById(R.id.restaurantName)
        val card: MaterialCardView =itemView.findViewById(R.id.card)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
       val layoutInflater=LayoutInflater.from(context)
        val view:View=layoutInflater.inflate(R.layout.singlerow_popular,parent,false)
        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
       holder.restaurantName.text=list[position].shopName
        Glide.with(context).load(list[position].imageUrl)
            .into(holder.image)
        holder.card.setOnClickListener {
            onClickListener.onClick(position)
        }

    }

    override fun getItemCount(): Int {
       return list.size
    }

    fun filterList(items: ArrayList<CommonModelData>) {
        this.arrayList=items
        notifyDataSetChanged()
    }

    interface MyOnClickListner{
        fun onClick(position: Int)
    }
}
