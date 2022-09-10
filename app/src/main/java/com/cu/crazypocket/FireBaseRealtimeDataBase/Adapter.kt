package com.cu.crazypocket.FireBaseRealtimeDataBase

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cu.crazypocket.Adapter.CommonListDataAdapter
import com.cu.crazypocket.DataClass.ShopDetails
import com.cu.crazypocket.R
import com.google.android.material.card.MaterialCardView

class Adapter(val context: Context,val onClickListener: CommonListDataAdapter.MyOnClickListner) : RecyclerView.Adapter<Adapter.myViewHolder>()
{
    var mlist = ArrayList<ShopDetails>()


    class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val image: ImageView =itemView.findViewById(R.id.image)
        val restaurantName: TextView =itemView.findViewById(R.id.restaurantName)
        val card: MaterialCardView =itemView.findViewById(R.id.card)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val layoutInflater= LayoutInflater.from(context)
        val view: View =layoutInflater.inflate(R.layout.singlerow_popular,parent,false)
        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        holder.restaurantName.text=mlist[position].shopName
        Glide.with(context).load(mlist[position].shopImage)
            .into(holder.image)
        holder.card.setOnClickListener {
            onClickListener.onClick(position)
        }

    }

    override fun getItemCount(): Int {
        return mlist.size
    }

   /* fun filterList(items: ArrayList<CommonModelData>) {
        this.arrayList=items
        notifyDataSetChanged()
    }
*/
    interface MyOnClickListner{
        fun onClick(position: Int)
    }
}
