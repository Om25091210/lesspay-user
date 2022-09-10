package com.cu.crazypocket.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cu.crazypocket.DataClass.CommonModelData
import com.cu.crazypocket.R
import com.google.android.material.card.MaterialCardView

class MarketPlaceAdapter(
    val context: Context,
    private val list: ArrayList<CommonModelData>,
    val onClickListener: MarketPlaceAdapter.MyOnClickListner
): RecyclerView.Adapter<MarketPlaceAdapter.MyViewHolder>(),Filterable
{
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val restaurantName:TextView=itemView.findViewById(R.id.restaurantName)
        val card:MaterialCardView=itemView.findViewById(R.id.card)
        val discount:TextView=itemView.findViewById(R.id.percentage_discount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater:LayoutInflater=LayoutInflater.from(context)
        val view:View=layoutInflater.inflate(R.layout.recommended_single_row,parent,false)
        return MyViewHolder(view)
    }
    fun listCall(lists:ArrayList<CommonModelData>)
    {
        lists.addAll(lists)
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.restaurantName.text= list[position].shopName
        holder.card.setOnClickListener {
            onClickListener.onClick(position)
        }
       /* if(list.get(position).data!!.size!=0)
        {
            val map: Map<String, Any> = list.get(position).data!!.get(0) as Map<String, Any>
            val item = map.get("discountPercentage")

            holder.discount.text= "${item}% Discount"
        }*/
//        list?.let {
//            for(i in  it)
//            {
//                if(i.category.equals("Food & Beverages"))
//                {
//                    Log.d("shopNames", "onBindViewHolder: ${i.shopName}")
//
//                }
//
//            }
//
//        }

    }

    interface MyOnClickListner{
        fun onClick(position: Int)
    }

    override fun getItemCount(): Int {
        return  list.size
      /*  return  if(list.size>10)
        {
             10
        }
        else{
            list.size
        }*/


    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList: ArrayList<CommonModelData> = ArrayList()
                if (constraint == null || constraint.isEmpty()) {
                    filteredList.addAll(list)
                } else {
                    val filterPattern = constraint.toString().toLowerCase().trim { it <= ' ' }
                    for (item in list) {
                        if (item.shopName.toLowerCase().contains(filterPattern)) {
                            filteredList.add(item)
                        }
                    }
                }
                val filterResults = FilterResults()
                filterResults.values = filteredList
                return filterResults
            }
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                list.clear()
                list.addAll(results?.values as List<CommonModelData>)
                notifyDataSetChanged()
            }
        }
    }

}
