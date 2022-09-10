package com.cu.crazypocket.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.cu.crazypocket.R
import com.cu.crazypocket.models.DiscountModel
import com.google.android.material.card.MaterialCardView


class ViewAdapter(context: Context, val list :List<DiscountModel>) : PagerAdapter() {

    override fun getCount(): Int {
        return list.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var view: View = LayoutInflater.from(container.context).inflate(R.layout.card_market_place, container, false)

        val textView = view.findViewById<TextView>(R.id.percentage)
        textView.text = list[position].percentage.toString()
        val materialCardView = view.findViewById<MaterialCardView>(R.id.materialCardView)



        container.addView(view)

        return view
    }
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}
