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

class MyAdapterCustom(val context: Context,  val onClickListener: MyAdapterCustom.myOnClickListner) : RecyclerView.Adapter<MyAdapterCustom.myViewHolder>()
{

     var arrayList = ArrayList<CommonModelData>()

    private var filterList = ArrayList<CommonModelData>()

    fun listCall(lists:ArrayList<CommonModelData>)
    {
        arrayList.addAll(lists)
        filterList = arrayList
        notifyDataSetChanged()
    }

    fun filterdata(vlaue : String){
        filterList = if (vlaue.isNullOrEmpty()){
            arrayList
        }else{
            arrayList.filter { it.shopName?.uppercase()?.contains(vlaue)== true || it.shopName?.lowercase()?.contains(vlaue)== true } as ArrayList<CommonModelData>
        }

    }

    class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val image: ImageView =itemView.findViewById(R.id.image)
        val restaurantName: TextView =itemView.findViewById(R.id.restaurantName)
        val card: MaterialCardView =itemView.findViewById(R.id.card)
        val discounts: TextView =itemView.findViewById(R.id.discounts)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val layoutInflater= LayoutInflater.from(context)
        val view: View =layoutInflater.inflate(R.layout.singlerow_popular,parent,false)
        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        holder.restaurantName.text=filterList[position].shopName
        Glide.with(context).load(filterList[position].imageUrl)
            .into(holder.image)
        holder.card.setOnClickListener {
            onClickListener.onClick(filterList[position])
        }
        if(filterList.get(position).data!!.size!=0)
        {
            val map: Map<String, Any> = filterList.get(position).data!!.get(0) as Map<String, Any>
            val item = map.get("discountPercentage")

            holder.discounts.text= "${item}% Discount"
        }

    }

    override fun getItemCount(): Int {
        return filterList.size
    }

    fun filterList(items: ArrayList<CommonModelData>) {
        this.arrayList=items
        notifyDataSetChanged()
    }

    interface myOnClickListner{
        fun onClick(position: CommonModelData)
    }
}
