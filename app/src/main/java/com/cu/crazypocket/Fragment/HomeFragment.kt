package com.cu.crazypocket.Fragment

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.cu.crazypocket.Activity.OTPActivity
import com.cu.crazypocket.Activity.SplashScreen
import com.cu.crazypocket.BuildConfig
import com.cu.crazypocket.FireBaseRealtimeDataBase.FireBaseRealTimeDataBase
import com.cu.crazypocket.R
import com.cu.crazypocket.databinding.NewHomePageLayoutBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import okhttp3.*
import okio.IOException
import org.json.JSONObject


class HomeFragment : Fragment() {
    lateinit var binding:NewHomePageLayoutBinding

    var userName:String=""
    var userEmail:String=""
    var userDob:String=""
    var userContact:String=""

    lateinit var fireBaseRealTimeDataBase: FireBaseRealTimeDataBase
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.new_home_page_layout, container, false)
        getBannerAds()

        Log.d("ArraySize", "onCreateView: ${OTPActivity.array.size}")
        SplashScreen.array
        if(SplashScreen.array.size!=0)
        {
            for (i in SplashScreen.array){

                Log.d("ggg", "onCreateView: ${i.name},${i.dob},${i.contact},${i.dob}")
                userName=i.name
                userEmail=i.email
                userDob=i.dob
                userContact=i.contact

            }
        }
        else if(OTPActivity.array.size!=0)
        {
            for (i in OTPActivity.array){

                Log.d("ggg", "onCreateView: ${i.name},${i.dob},${i.contact},${i.dob}")
                userName=i.name
                userEmail=i.email
                userDob=i.dob
                userContact=i.contact

            }
        }

            fetchJson()

        var lastFourDigits = "";   //substring containing last 4 characters

        if (userContact.length > 4)
        {
            lastFourDigits = userContact.substring(userContact.length - 4);
        }
        else
        {
            lastFourDigits = userContact;
        }
        binding.username.text="Hi $userName!! \uD83D\uDC4B"
        binding.cardValue.text= lastFourDigits

        binding.apply {


           swipeBtn1.setOnActiveListener {
            /*   startActivity(Intent(context,ScannerActivity::class.java))
               dialog.setView(mView)*/

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
            c1.setOnClickListener {
               // exitProcess(0);
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
            c2.setOnClickListener {
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
            c3.setOnClickListener {
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
            c4.setOnClickListener {
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
            cardView.setOnClickListener {
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
            icon.setOnClickListener {
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
         /*   search.setOnClickListener {
                val dialog = AlertDialog.Builder(context)
                val mView = layoutInflater.inflate(R.layout.commingsoon, null)
                val dialogButton = mView.findViewById<View>(R.id.okay) as Button
                dialog.setView(mView)
                val alertDialog = dialog.create()
                alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                alertDialog.setCanceledOnTouchOutside(false)
                dialogButton.setOnClickListener { alertDialog.dismiss() }
                alertDialog.show()
            }*/
            notification.setOnClickListener {
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
    fun fetchJson() {
        val url = BuildConfig.URL
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            var mainHandler = Handler(requireContext().getMainLooper())
            override fun onResponse(call: Call, response: Response) {
                mainHandler.post {
                    val body = response.body?.string() ?: return@post
                    Log.e("qwerty", "onResponse: ${body}")

                    val jsonObject=JSONObject(body)

                    val staus=jsonObject.get("status").toString()
                   // Log.d("aff", "onResponse: $staus")


                    if(jsonObject.get("status").equals(true))
                    {

                       // Toast.makeText(context,"to",Toast.LENGTH_LONG).show()
                        Log.d("aff", "onResponse: asff")
                       // exitProcess(0)
                        Navigation.findNavController(view!!)
                            .navigate(R.id.action_homeFragment_to_enFragment);

                    }

                }
            }

            override fun onFailure(call: Call, e: IOException) {
                println("API execute failed")
            }
        })
    }
    fun getBannerAds() {

        val databaseReference = FirebaseDatabase.getInstance().reference


        databaseReference.child("UserAppData").child("HomeData").addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val picasso = Picasso.get()
                Log.d("snap", "onDataChange: ${snapshot.value}")
              //  img1= snapshot.child("img1").value.toString()
                picasso.load(snapshot.child("img1").value.toString()!!.replace("\"", "")).into(binding.img1)


            }



            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
                Log.d("fetchData", "onCancelled: $error")
            }

        })
    }





}
