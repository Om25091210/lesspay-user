package com.cu.crazypocket.Fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.view.ViewGroup.VISIBLE
import android.view.WindowManager
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.cu.crazypocket.Activity.SelectCityActivity
import com.cu.crazypocket.Adapter.MarketPlaceAdapter
import com.cu.crazypocket.Adapter.MarketPlaceAdapterTwo
import com.cu.crazypocket.Constant.Companion.list
import com.cu.crazypocket.DataClass.CommonModelData
import com.cu.crazypocket.FireBaseRealtimeDataBase.FireBaseRealTimeDataBase
import com.cu.crazypocket.FireBaseRealtimeDataBase.Model.CardAdapter
import com.cu.crazypocket.FireBaseRealtimeDataBase.Model.ImageData
import com.cu.crazypocket.R
import com.cu.crazypocket.Repository.Repository
import com.cu.crazypocket.ViewModel.MyViewModel
import com.cu.crazypocket.databinding.FragmentMarketPlaceBinding
import com.cu.crazypocket.models.DiscountModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MarketPlaceFragment : Fragment() {
    lateinit var binding: FragmentMarketPlaceBinding
    lateinit var repository: Repository
    lateinit var myViewModel: MyViewModel
    var array = ArrayList<CommonModelData>()
    var arrayTwo = ArrayList<CommonModelData>()
    var array2 = ArrayList<CommonModelData>()
    lateinit var adapter: MarketPlaceAdapter
    val arrayList = ArrayList<DiscountModel>()
    val list1=ArrayList<String>()
    var img1:String?=null
    var img2:String?=null
    var img3:String?=null
    val arraylist=ArrayList<ImageData>()
    var page=1
    var isLoading=false
    var limit=0
    var mainimage = arrayOfNulls<String>(3)



    companion object{
        var values:String?=null
    }

    lateinit var fireBaseRealTimeDataBase: FireBaseRealTimeDataBase


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_market_place, container, false)
        getBannerAds()
        try {
            activity!!.window.setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            )
        } catch (e: Exception) {
        }

        binding.map.setOnClickListener {
            val intent= Intent(context,SelectCityActivity::class.java)
            startActivity(intent)
        }
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    // Handle the back button even
                    if(!binding.loader.isVisible)
                    {
                        try {
                            activity!!.window.clearFlags(
                                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                            )
                        } catch (e: Exception) {
                        }
                        findNavController().navigateUp()

                    }
                }
            }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
        binding.apply {
            val params = binding.root.layoutParams as MarginLayoutParams
            params.setMargins(0, 0, 50, 0)
            binding.root.layoutParams = params
            Log.d("zero", "onCreateView: ${mainimage.get(0)}")


            //loadImages


            binding.tv1.setOnClickListener {
                Navigation.findNavController(view!!).navigate(R.id.action_marketPlaceFragment_to_searchAllCategoryFragment);
            }

            binding.back.setOnClickListener{
                Navigation.findNavController(view!!).navigate(R.id.action_marketPlaceFragment_to_homeFragment2);

            }
            fireBaseRealTimeDataBase = FireBaseRealTimeDataBase()
            GlobalScope.launch(Dispatchers.Main) {
                fireBaseRealTimeDataBase.retriveFromDataBase()
               // fireBaseRealTimeDataBase.getQr()

                fireBaseRealTimeDataBase.liveDataRetrive.observe(viewLifecycleOwner, Observer {

                    try {
                        activity!!.window.clearFlags(
                            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                        )
                    } catch (e: Exception) {
                    }
                    binding.linearOne.visibility= VISIBLE
                    binding.linearTwo.visibility= GONE
                    binding.loader.visibility=GONE

                    array.clear()
                    arrayTwo.clear()


                    for (i in it) {
                        if (i.category.equals("others")) {
                            Log.d("food", "onBindViewHolder: ${i.shopName}")
                            array.add(CommonModelData(
                                i.shopName,
                                i.shopImage,
                                i.shopAddress,
                                i.shopAddressLatitude,
                                i.shopAddressLongitude,

                            ))
                            array2.addAll(array)
                        }
                    }
                    adapter= MarketPlaceAdapter( context!!,
                        array,object: MarketPlaceAdapter.MyOnClickListner {
                            override fun onClick(position: Int) {
                                Log.e("hp", "onClick: ${position}" )
                                val mutableData = MutableLiveData<CommonModelData>()
                                list.clear()
                                for (i in 0..3){
                                    if (i < it.get(position).array.size){
                                        val map: Map<String, Any> =it.get(position).array.get(i) as Map<String, Any>
                                        val item = map.get("discountPercentage")
                                        Log.e("AmanChaman", "onCreateView: ${map.get("discountPercentage")}")
                                        list.add(DiscountModel(percentage = item.toString()))
                                    }
                                }
                                mutableData.value = array[position]
                                CommonListFragment.shopName = array[position].shopName
                                CommonListFragment.image = array[position].imageUrl
                                CommonListFragment.address =array[position].shopAddress
                                CommonListFragment.latitude=arrayTwo[position].shopAddressLatitude
                                CommonListFragment.longitude=arrayTwo[position].shopAddressLongitude
                                Log.d("Sname", "onClick: ${array[position].shopName}")
                                Navigation.findNavController(view!!)
                                    .navigate(R.id.action_marketPlaceFragment_to_merchantProfileFragment);

                            }
                        })
                    recyclerView?.layoutManager = LinearLayoutManager(
                        context,
                        LinearLayoutManager.HORIZONTAL, false
                    )
                    Log.d("cjeckSize", "onCreateView: " + FireBaseRealTimeDataBase.mylist.size)
                    recyclerView.adapter = adapter

                    for (j in it) {
                        if (j.category.equals("others")) {
                            Log.d("beauty", "onBindViewHolder: ${j.shopName}")
                            arrayTwo.add(CommonModelData(
                                j.shopName,
                                j.shopImage,
                                j.shopAddress,
                                j.shopAddressLatitude,
                                j.shopAddressLongitude,
                                j.array
                            ))


                        }
                    }
                    recyclerViewTwo?.layoutManager = LinearLayoutManager(
                        context, LinearLayoutManager.HORIZONTAL, false
                    )
                    Log.d("cjeckSize", "onCreateView: " + FireBaseRealTimeDataBase.mylist.size)
                    recyclerViewTwo.adapter = MarketPlaceAdapterTwo(
                        context!!,
                        arrayTwo,object: MarketPlaceAdapterTwo.MyOnClickListner {
                            override fun onClick(position: Int) {
                                Log.e("himanshuChaman1", "onClick: ${position}")
                                list.clear()
                                for (i in 0..3){
                                    if (i < it.get(position).array.size){
                                        val map: Map<String, Any> =it.get(position).array.get(i) as Map<String, Any>
                                        val item = map.get("discountPercentage")
                                        Log.e("AmanChaman", "onCreateView: ${map.get("discountPercentage")}")
                                        list.add(DiscountModel(percentage = item.toString()))
                                    }
                                }

                                val mutableData = MutableLiveData<CommonModelData>()
                                CommonListFragment.shopName =arrayTwo[position].shopName
                                CommonListFragment.image =arrayTwo[position].imageUrl
                                CommonListFragment.address =arrayTwo[position].shopAddress
                                Log.d("MAPP", "onClick: ${arrayTwo[position].shopAddressLatitude} ,${arrayTwo[position].shopAddressLatitude}")
                                CommonListFragment.latitude=arrayTwo[position].shopAddressLatitude
                                CommonListFragment.longitude=arrayTwo[position].shopAddressLongitude
                                mutableData.value = arrayTwo[position]
                                Log.d("Sname", "onClick: ${arrayTwo[position].shopName}")
                                //al bundle = bundleOf("shopName" to foodAndBevrages[position].shopName)
                                Navigation.findNavController(view!!)
                                    .navigate(R.id.action_marketPlaceFragment_to_merchantProfileFragment);
                            }
                        }//it as ArrayList<ShopDetails>
                    )


                    v1.setOnClickListener {
                        val bundle = bundleOf("value" to "Food & Beverages")
                        values="Food & Beverages"
                        FireBaseRealTimeDataBase.mykey = ""
                        Navigation.findNavController(view!!).navigate(R.id.action_marketPlaceFragment_to_commonListFragment,bundle);

                    }

                    foodAndBevrages.setOnClickListener {
                        val bundle = bundleOf("value" to "Food & Beverages")
                        values="Food & Beverages"
                        FireBaseRealTimeDataBase.mykey = ""
                        Navigation.findNavController(view!!).navigate(R.id.action_marketPlaceFragment_to_commonListFragment,bundle);


                    }
                    others.setOnClickListener {
                        val bundle = bundleOf("value" to "others")
                        values="others"
                        FireBaseRealTimeDataBase.mykey = ""
                        Navigation.findNavController(view!!).navigate(R.id.action_marketPlaceFragment_to_commonListFragment,bundle);

                    }
                    fashionLifeStyle.setOnClickListener {
                        val bundle = bundleOf("value" to "Fashion")
                        values="Fashion"
                        FireBaseRealTimeDataBase.mykey = ""
                        Navigation.findNavController(view!!).navigate(R.id.action_marketPlaceFragment_to_commonListFragment,bundle);

                    }
                    beautyCosmetic.setOnClickListener {
                        val bundle = bundleOf("value" to "other")
                        values="other"
                        FireBaseRealTimeDataBase.mykey = ""
                        Navigation.findNavController(view!!).navigate(R.id.action_marketPlaceFragment_to_commonListFragment,bundle);

                    }
                    stores.setOnClickListener {
                        val bundle = bundleOf("value" to "Stores")
                        values="Stores"
                        FireBaseRealTimeDataBase.mykey = ""
                        Navigation.findNavController(view!!).navigate(R.id.action_marketPlaceFragment_to_commonListFragment,bundle);

                    }
                    saloonSpa.setOnClickListener {
                        val bundle = bundleOf("value" to "Salon & spa")
                        values="Salon & spa"
                        FireBaseRealTimeDataBase.mykey = ""
                        Navigation.findNavController(view!!).navigate(R.id.action_marketPlaceFragment_to_commonListFragment,bundle);

                    }
                    v2.setOnClickListener {

                        val bundle = bundleOf("value" to "other")
                        values="other"
                        FireBaseRealTimeDataBase.mykey = ""
                        Navigation.findNavController(view!!).navigate(R.id.action_marketPlaceFragment_to_commonListFragment,bundle);

                    }
                })

            }






        }



        return binding.root
    }
    fun getBannerAds() {

        val databaseReference = FirebaseDatabase.getInstance().reference


        databaseReference.child("UserAppData").child("AdsData").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                Log.d("snap", "onDataChange: ${snapshot.value}")
                img1= snapshot.child("img1").value.toString()
                img2= snapshot.child("img2").value.toString()
                img3= snapshot.child("img3").value.toString()
                mainimage[0]=img1
                mainimage[1]=img2
                mainimage[2]=img3
              //  arraylist.add(ImageData(img1!!,img2!!,img3!!))
                Log.d("val", "onDataChange: ${mainimage[0]} ${mainimage[1]} ${mainimage[2]}")

                binding.viewPager.adapter = CardAdapter(requireContext(),mainimage)
                binding.tabIndicator.setViewPager(binding.viewPager)


            }



            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
                Log.d("fetchData", "onCancelled: $error")
            }

        })
    }



}
