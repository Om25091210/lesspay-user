package com.cu.crazypocket.Fragment

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cu.crazypocket.Adapter.MyAdapterCustom
import com.cu.crazypocket.Constant
import com.cu.crazypocket.DataClass.CommonModelData
import com.cu.crazypocket.FireBaseRealtimeDataBase.FireBaseRealTimeDataBase
import com.cu.crazypocket.R
import com.cu.crazypocket.databinding.FragmentSearchAllCategoryBinding
import com.cu.crazypocket.models.DiscountModel

class SearchAllCategoryFragment : Fragment(), MyAdapterCustom.myOnClickListner {


    lateinit var binding:FragmentSearchAllCategoryBinding
    private lateinit var  myadapterCustom : MyAdapterCustom
    lateinit var fireBaseRealTimeDataBase: FireBaseRealTimeDataBase
    var filter = false
    var visibleItemCount=0
    private var loading = true
    var b:Boolean=false
    var currentItem:Int=0
    var totalItem:Int=0
    var lastVisible:Int=0
    var scrollOutItem:Int=0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_search_all_category, container, false)
        fireBaseRealTimeDataBase = FireBaseRealTimeDataBase()
        myadapterCustom = MyAdapterCustom(requireContext(), this)
        CommonListFragment.isFrag = "all"
        fireBaseRealTimeDataBase.getMyList(CommonListFragment.isFrag)
        fireBaseRealTimeDataBase.mutableLiveDataOne.observe(viewLifecycleOwner, Observer {
            Log.d("arrayitemsmmmm", "$it")
            if (!it.isNullOrEmpty()){
                myadapterCustom.listCall(it as ArrayList<CommonModelData>)
                myadapterCustom.notifyDataSetChanged()
            }
        })


      /*  Log.d("sizes", "onCreateView: ${FireBaseRealTimeDataBase.arrayLists.size}")
        for(i in FireBaseRealTimeDataBase.arrayLists)
        {
            Log.d("imedata", "onCreateView: ${i.data}")
        }*/

        fireBaseRealTimeDataBase.isLoadinggMydata.observe(viewLifecycleOwner){
            if (it){
                binding.loader.visibility=View.VISIBLE
            }else{
                binding.loader.visibility=View.GONE
                try {activity!!.window.clearFlags( WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE )
                } catch (e: Exception) { }
            }
        }
        binding.rec?.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL, false
        )
        binding.rec.visibility = View.VISIBLE
        binding.rec.adapter=myadapterCustom
        myadapterCustom.notifyDataSetChanged()


        binding.edt1.doOnTextChanged { text, start, before, count ->
            if (!text.isNullOrEmpty() && myadapterCustom.arrayList.size != 0){
                filter = true
                myadapterCustom.filterdata(text.toString())
                myadapterCustom.notifyDataSetChanged()

            }else{
                filter = false
                myadapterCustom.filterdata(text.toString())
                myadapterCustom.notifyDataSetChanged()
            }
        }
        binding.rec.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if(dy>0){
                    visibleItemCount=recyclerView.layoutManager!!.childCount//1
                    val layoutmanager = recyclerView.layoutManager as LinearLayoutManager
                    totalItem = layoutmanager.itemCount//2
                    lastVisible = layoutmanager.findFirstVisibleItemPosition()//3
                    Log.d("ContCounty", "onScrolled:${visibleItemCount} ${totalItem} ${lastVisible} ")
                    if ((visibleItemCount+lastVisible) >= totalItem){
                        Handler().postDelayed({
                            fireBaseRealTimeDataBase.getMyList(CommonListFragment.isFrag)
                            //loaddata()
                        }, 1500)
                    }
                }
                super.onScrolled(recyclerView, dx, dy)
            }
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

            }
        })
        myadapterCustom.registerAdapterDataObserver(object:RecyclerView.AdapterDataObserver(){
            override fun onChanged() {
                super.onChanged()
                if(myadapterCustom.itemCount==0){
                    binding.empty.visibility=View.VISIBLE
                    binding.rec.visibility=View.GONE
                }
                else
                {
                    binding.empty.visibility=View.GONE
                    binding.rec.visibility=View.VISIBLE
                }
            }
        })


        try {
            activity!!.window.setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            )
        } catch (e: Exception) {
        }


        return binding.root
    }

    override fun onClick(position: CommonModelData) {
        Constant.list.clear()
        FireBaseRealTimeDataBase.mykey = ""
        CommonListFragment.shopName =position.shopName
        CommonListFragment.image =position.imageUrl
        CommonListFragment.address =position.shopAddress
        CommonListFragment.latitude =position.shopAddressLatitude
        CommonListFragment.longitude =position.shopAddressLongitude
        for (i in 0..2){
            if (i < position.data!!.size){
                val map: Map<String, Any> =position.data.get(i) as Map<String, Any>
                val item = map.get("discountPercentage")

                Log.e("dataShort", "onCreateView: ${map.get("discountPercentage")}")
                Constant.list.add(DiscountModel(percentage = item.toString()))
            }
        }

        Navigation.findNavController(view!!).navigate(R.id.action_searchAllCategoryFragment_to_merchantProfileFragment);

    }


}
