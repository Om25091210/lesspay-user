package com.cu.crazypocket.Fragment

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cu.crazypocket.Adapter.CommonListDataAdapter
import com.cu.crazypocket.Adapter.MyAdapterCustom
import com.cu.crazypocket.Constant
import com.cu.crazypocket.DataClass.CommonModelData
import com.cu.crazypocket.FireBaseRealtimeDataBase.FireBaseRealTimeDataBase
import com.cu.crazypocket.FireBaseRealtimeDataBase.Model.MyDataClass
import com.cu.crazypocket.R
import com.cu.crazypocket.databinding.FragmentCommonListBinding
import com.cu.crazypocket.models.DiscountModel


class CommonListFragment : Fragment() , MyAdapterCustom.myOnClickListner{
    lateinit var fireBaseRealTimeDataBase: FireBaseRealTimeDataBase
    lateinit var binding:FragmentCommonListBinding
    var foodAndBevrages = ArrayList<CommonModelData>()
    var searchData = ArrayList<CommonModelData>()
    var others = ArrayList<CommonModelData>()
    var fashion = ArrayList<CommonModelData>()
    var cosmetic = ArrayList<CommonModelData>()
    var myData = ArrayList<MyDataClass>()
    var visibleItemCount=0
    private var loading = true
    private lateinit var  myadapterCustom : MyAdapterCustom
    var b:Boolean=false
    var currentItem:Int=0
    var totalItem:Int=0
    var lastVisible:Int=0
    var scrollOutItem:Int=0
    var filter = false
    lateinit var adapter:CommonListDataAdapter

    companion object{
        var shopName:String?=null
        var image:String?=null
        var address:String?=null
        var isFrag = ""
        var latitude:String? = null
        var longitude:String?= null
        var listData : ArrayList<DiscountModel> = ArrayList()

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_common_list, container, false)


        binding.arrow.setOnClickListener {
            Navigation.findNavController(view!!).popBackStack()

        }
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    if(!binding.loader.isVisible){
                        try {activity!!.window.clearFlags( WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE )
                        } catch (e: Exception) { }
                        findNavController().navigateUp()
                    }
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
        fireBaseRealTimeDataBase = FireBaseRealTimeDataBase()
        myadapterCustom = MyAdapterCustom(requireContext(), this)
        isFrag = MarketPlaceFragment.values.toString()
        fireBaseRealTimeDataBase.getMyList(isFrag)
        fireBaseRealTimeDataBase.mutableLiveDataOne.observe(viewLifecycleOwner, Observer {
            Log.d("arrayitemsmmmm", "$it")
            if (!it.isNullOrEmpty()){
                myadapterCustom.listCall(it as ArrayList<CommonModelData>)
                myadapterCustom.notifyDataSetChanged()
            }
        })

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
                           fireBaseRealTimeDataBase.getMyList(isFrag)
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


        /*lifecycleScope.launch(Dispatchers.Main) {

            fireBaseRealTimeDataBase.retriveFromDataBase()


            fireBaseRealTimeDataBase.liveDataRetrive.observe(viewLifecycleOwner, Observer {

                try {
                    activity!!.window.clearFlags(
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                    )
                } catch (e: Exception) {
                }
                binding.loader.visibility=View.GONE

                val food = arguments!!.getString("value").toString()
                Log.d("Values", "onCreateView:$food")


                // Log.d("bundle", "onBindViewHolder: $bundle")

               *//* binding.rec.addOnScrollListener(object : RecyclerView.OnScrollListener(){
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                        currentItem = binding.rec?.layoutManager!!.childCount
                        totalItem = binding.rec?.layoutManager!!.childCount
                        scrollOutItem = binding.rec?.layoutManager!!.childCount

                        if(b && (currentItem+scrollOutItem==totalItem))
                        {
                            b=false

                        }
                    }

                    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                        super.onScrollStateChanged(recyclerView, newState)
                        if(newState ==AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
                        {
                            b=true

                        }
                    }
                })*//*



                if (food.equals("Food & Beverages")) {
                    foodAndBevrages.clear()
                    for (i in it) {
                        // Log.d("cat", "onBindViewHolder: ${i.category}")
                        if (i.category.equals("Food & Beverages")) {
                            foodAndBevrages.add(CommonModelData(i.shopName, i.shopImage,i.shopAddress))
                        }
                    }
                    binding.rec?.layoutManager = LinearLayoutManager(
                        context,
                        LinearLayoutManager.VERTICAL, false
                    )
                    Log.d("cjeckSize", "onCreateView: " + FireBaseRealTimeDataBase.mylist.size)


                     adapter = CommonListDataAdapter(
                        context!!,
                        foodAndBevrages,
                        object:CommonListDataAdapter.MyOnClickListner {
                            override fun onClick(position: Int) {
                                val mutableData=MutableLiveData<CommonModelData>()
                                mutableData.value=foodAndBevrages[position]
                                //al bundle = bundleOf("shopName" to foodAndBevrages[position].shopName)
                                shopName=foodAndBevrages[position].shopName
                                image=foodAndBevrages[position].imageUrl
                                address=foodAndBevrages[position].shopAddress

                                Navigation.findNavController(view!!).navigate(R.id.action_commonListFragment_to_merchantProfileFragment);
                            }
                        }
                    )

                       *//* adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
                            override fun onChanged() {
                               adapter.notifyDataSetChanged()
                            }
                        })*//*




                }
                else if (food.equals("others")) {
                    others.clear()
                    for (i in it) {
                        // Log.d("cat", "onBindViewHolder: ${i.category}")
                        if (i.category.equals("others")) {
                            Log.d("food", "onBindViewHolder: ${i.shopName}")
                            others.add(CommonModelData(i.shopName, i.shopImage,i.shopAddress))
                        }
                    }
                    binding.rec?.layoutManager = LinearLayoutManager(
                        context,
                        LinearLayoutManager.VERTICAL, false
                    )
                    Log.d("cjeckSize", "onCreateView: " + FireBaseRealTimeDataBase.mylist.size)
                    binding.rec.adapter = CommonListDataAdapter(
                        context!!,
                        others,object:CommonListDataAdapter.MyOnClickListner {
                            override fun onClick(position: Int) {
                                val mutableData=MutableLiveData<CommonModelData>()
                                mutableData.value=others[position]
                                shopName=others[position].shopName
                                image=others[position].imageUrl
                                address=others[position].shopAddress
                                Navigation.findNavController(view!!).navigate(R.id.action_commonListFragment_to_merchantProfileFragment);
                            }
                        }
                    )

                    binding.rec?.layoutManager = LinearLayoutManager(
                        context,
                        LinearLayoutManager.VERTICAL, false
                    )
                }
                else if (food.equals("fashion")) {
                    fashion.clear()
                    for (i in it) {
                        // Log.d("cat", "onBindViewHolder: ${i.category}")
                        if (i.category.equals("Fashion")) {
                            Log.d("food", "onBindViewHolder: ${i.shopName}")
                            fashion.add(CommonModelData(i.shopName, i.shopImage,i.shopAddress))
                        }
                    }
                    binding.rec?.layoutManager = LinearLayoutManager(
                        context,
                        LinearLayoutManager.VERTICAL, false
                    )
                    Log.d("cjeckSize", "onCreateView: " + FireBaseRealTimeDataBase.mylist.size)
                    binding.rec.adapter = CommonListDataAdapter(
                        context!!,
                        fashion,object:CommonListDataAdapter.MyOnClickListner{
                            override fun onClick(position: Int) {
                                val mutableData=MutableLiveData<CommonModelData>()
                                mutableData.value=fashion[position]
                                shopName=fashion[position].shopName
                                image=fashion[position].imageUrl
                                address=fashion[position].shopAddress
                                Navigation.findNavController(view!!).navigate(R.id.action_commonListFragment_to_merchantProfileFragment);
                            }
                        }
                    )

                    binding.rec?.layoutManager = LinearLayoutManager(
                        context,
                        LinearLayoutManager.VERTICAL, false
                    )
                }
                else if (food.equals("cosmetic")) {
                    cosmetic.clear()
                    for (i in it) {
                        // Log.d("cat", "onBindViewHolder: ${i.category}")
                        if (i.category.equals("others")) {
                            Log.d("food", "onBindViewHolder: ${i.shopName}")
                            cosmetic.add(CommonModelData(i.shopName, i.shopImage,i.shopAddress))
                        }
                    }
                    binding.rec?.layoutManager = LinearLayoutManager(
                        context,
                        LinearLayoutManager.VERTICAL, false
                    )
                    Log.d("cjeckSize", "onCreateView: " + FireBaseRealTimeDataBase.mylist.size)
                    binding.rec.adapter = CommonListDataAdapter(
                        context!!,
                        cosmetic,
                        object:CommonListDataAdapter.MyOnClickListner {
                            override fun onClick(position: Int) {
                                val mutableData=MutableLiveData<CommonModelData>()
                                mutableData.value=cosmetic[position]
                                shopName=cosmetic[position].shopName
                                image= cosmetic[position].imageUrl
                                address=cosmetic[position].shopAddress
                                Navigation.findNavController(view!!).navigate(R.id.action_commonListFragment_to_merchantProfileFragment);
                            }
                        }

                    )

                    binding.rec?.layoutManager = LinearLayoutManager(
                        context,
                        LinearLayoutManager.VERTICAL, false
                    )
                }

                Log.d("TAG", "onCreateView: ")
                // binding.rec.adapter.filterList()
            })

*//*
            binding.search.addTextChangedListener(object :TextWatcher{
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                }

                override fun afterTextChanged(s: Editable?) {
                    //filter(s.toString())
                }

            })
*//*


        }*/

        return binding.root
    }

    override fun onPause() {
        super.onPause()

    }

    override fun onResume() {
        FireBaseRealTimeDataBase.mykey = ""

        super.onResume()



    }

    override fun onClick(position: CommonModelData) {
        Constant.list.clear()
        FireBaseRealTimeDataBase.mykey = ""
        shopName=position.shopName
        image=position.imageUrl
        address=position.shopAddress
        latitude=position.shopAddressLatitude
        longitude=position.shopAddressLongitude
        for (i in 0..2){
            if (i < position.data!!.size){
                val map: Map<String, Any> =position.data.get(i) as Map<String, Any>
                val item = map.get("discountPercentage")

                Log.e("dataShort", "onCreateView: ${map.get("discountPercentage")}")
                Constant.list.add(DiscountModel(percentage = item.toString()))
            }
        }

        Navigation.findNavController(view!!).navigate(R.id.action_commonListFragment_to_merchantProfileFragment);

    }


}
