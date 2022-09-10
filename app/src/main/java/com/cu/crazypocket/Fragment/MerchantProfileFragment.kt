package com.cu.crazypocket.Fragment

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.cu.crazypocket.Adapter.ViewAdapter
import com.cu.crazypocket.Constant
import com.cu.crazypocket.FireBaseRealtimeDataBase.FireBaseRealTimeDataBase
import com.cu.crazypocket.R
import com.cu.crazypocket.databinding.FragmentMerchantProfileBinding


class MerchantProfileFragment : Fragment() {
    lateinit var binding: FragmentMerchantProfileBinding
//    val list:ArrayList<DiscountModel> = arrayListOf(DiscountModel("50%"),
//        DiscountModel("60%"),
//        DiscountModel("70%"),
//        DiscountModel("80%")
//        , DiscountModel("90%"),
//        DiscountModel("100%"))


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    // Handle the back button even
                    FireBaseRealTimeDataBase.mykey = ""
                    findNavController().navigateUp()
                }
            }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_merchant_profile, container, false)
//        binding.swipeBtn1.setOnActiveListener {
//            Toast.makeText(requireContext(),"Swipe Button 1 is Active", Toast.LENGTH_LONG).show()
//        }

        Glide.with(context!!).load(CommonListFragment.image)
            .into(binding.dimBg)
        binding.cafeName.text=CommonListFragment.shopName
        binding.address.text=CommonListFragment.address
       // Log.d("shopAndUrl", "onCreateView: ${CommonListFragment.image}${CommonListFragment.address}")

        binding.apply {
            viewPager.adapter = ViewAdapter(requireContext(),Constant.list)
            tabIndicator.setViewPager(viewPager)

            binding.map.setOnClickListener{

                val lat = CommonListFragment.latitude
                val long = CommonListFragment.longitude

                Log.d("latLongi", "onCreateView: $lat,$long")

                val gmmIntentUri =
                    Uri.parse("google.navigation:q=$lat,$long")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                startActivity(mapIntent)


              /*  Navigation.findNavController(view!!)
                    .navigate(R.id.action_merchantProfileFragment_to_mapFragment);*/
            }
            binding.greenCard.setOnClickListener {
                val dialog = AlertDialog.Builder(context)
                val mView = layoutInflater.inflate(R.layout.commingsoon, null)
                val dialogButton = mView.findViewById<View>(R.id.okay) as Button
                dialog.setView(mView)
                val alertDialog = dialog.create()
                alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                alertDialog.setCanceledOnTouchOutside(false)
                dialogButton.setOnClickListener { alertDialog.dismiss() }
                alertDialog.show()
            }
            binding.c1.setOnClickListener{

                val dialog = AlertDialog.Builder(context)
                val mView = layoutInflater.inflate(R.layout.commingsoon, null)
                val dialogButton = mView.findViewById<View>(R.id.okay) as Button
                dialog.setView(mView)
                val alertDialog = dialog.create()
                alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                alertDialog.setCanceledOnTouchOutside(false)
                dialogButton.setOnClickListener { alertDialog.dismiss() }
                alertDialog.show()
            }
            binding.c2.setOnClickListener {
                val dialog = AlertDialog.Builder(context)
                val mView = layoutInflater.inflate(R.layout.commingsoon, null)
                val dialogButton = mView.findViewById<View>(R.id.okay) as Button
                dialog.setView(mView)
                val alertDialog = dialog.create()
                alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                alertDialog.setCanceledOnTouchOutside(false)
                dialogButton.setOnClickListener { alertDialog.dismiss() }
                alertDialog.show()
            }
            binding.d1.setOnClickListener {
                val dialog = AlertDialog.Builder(context)
                val mView = layoutInflater.inflate(R.layout.commingsoon, null)
                val dialogButton = mView.findViewById<View>(R.id.okay) as Button
                dialog.setView(mView)
                val alertDialog = dialog.create()
                alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                alertDialog.setCanceledOnTouchOutside(false)
                dialogButton.setOnClickListener { alertDialog.dismiss() }
                alertDialog.show()
            }

            binding.d2.setOnClickListener {
                val dialog = AlertDialog.Builder(context)
                val mView = layoutInflater.inflate(R.layout.commingsoon, null)
                val dialogButton = mView.findViewById<View>(R.id.okay) as Button
                dialog.setView(mView)
                val alertDialog = dialog.create()
                alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                alertDialog.setCanceledOnTouchOutside(false)
                dialogButton.setOnClickListener { alertDialog.dismiss() }
                alertDialog.show()
            }

            binding.d3.setOnClickListener {
                val dialog = AlertDialog.Builder(context)
                val mView = layoutInflater.inflate(R.layout.commingsoon, null)
                val dialogButton = mView.findViewById<View>(R.id.okay) as Button
                dialog.setView(mView)
                val alertDialog = dialog.create()
                alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                alertDialog.setCanceledOnTouchOutside(false)
                dialogButton.setOnClickListener { alertDialog.dismiss() }
                alertDialog.show()
            }

            binding.d4.setOnClickListener {
                val dialog = AlertDialog.Builder(context)
                val mView = layoutInflater.inflate(R.layout.commingsoon, null)
                val dialogButton = mView.findViewById<View>(R.id.okay) as Button
                dialog.setView(mView)
                val alertDialog = dialog.create()
                alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                alertDialog.setCanceledOnTouchOutside(false)
                dialogButton.setOnClickListener { alertDialog.dismiss() }
                alertDialog.show()
            }

        }


        return binding.root
    }
    companion object{

        var latitude=""
        var longitude=""
    }


}
