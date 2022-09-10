package com.cu.crazypocket.FireBaseRealtimeDataBase.Model


import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.cu.crazypocket.R

import com.squareup.picasso.Picasso


class CardAdapter(context: Context, val string: Array<String?>) : PagerAdapter() {


    override fun getCount(): Int {
        return 3
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var view: View = LayoutInflater.from(container.context).inflate(R.layout.card, container, false)

        val img = view.findViewById<ImageView>(R.id.img)
        val picasso = Picasso.get()

        Log.d("posi", "instantiateItem: ${string[position]}")
        picasso.load(string[position]!!.replace("\"", "")).into(img)

       // Glide.with(view.context).load(string[position]).into(img)

        container.addView(view)
        return view
    }
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}
