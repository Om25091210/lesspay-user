package com.cu.crazypocket.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.cu.crazypocket.FireBaseRealtimeDataBase.Model.Model
import com.cu.crazypocket.FireBaseRealtimeDataBase.UserDataBase
import com.cu.crazypocket.R
import com.cu.crazypocket.databinding.ActivitySplashScreenBinding
import com.cu.crazypocket.views.OnBoardingActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class SplashScreen : AppCompatActivity() {
    lateinit var mAuth: FirebaseAuth
    lateinit var fireBaseRealTimeDataBase: UserDataBase
    lateinit var binding: ActivitySplashScreenBinding
    var dob: String = ""
    var name: String = ""
    var contact: String = ""
    var email: String = ""

    companion object {
        var array = ArrayList<Model>()
        var userKey = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen)
        mAuth = FirebaseAuth.getInstance()
        Log.d("GetUsers", "onCreate: ${mAuth.currentUser?.phoneNumber}")
        Handler().postDelayed({
            preChecksData()

        }, 3000)

    }
    /* private fun checkRegisteredOrNot(any: String) {
         val userRef = FirebaseDatabase.getInstance().getReference("UserAppData")
         userRef.child(mAuth.currentUser!!.uid)
             .addValueEventListener(object :
                 ValueEventListener {
                 override fun onDataChange(dataSnapshot: DataSnapshot) {

                     if (dataSnapshot.value != null) {

                         dataSnapshot.children.forEach {
                             val dob = it.child("dob").getValue(String::class.java).toString()
                            val  email = it.child("email").getValue(String::class.java).toString()
                            val  contact = it.child("contact").getValue(String::class.java).toString()
                            val name = it.child("name").getValue(String::class.java).toString()
                             Log.d("dataValue", "onDataChange: ${dob},${email},${name},${contact}")
                         }
                         val i = Intent(applicationContext, Home::class.java)
                         startActivity(i)
                       *//*  i.putExtra("contact", contact)
                        i.putExtra("email", email)
                        i.putExtra("name", name)
                        i.putExtra("dob", dob)
                        startActivity(i)
                        OTPActivity.array.add(Model(name, email, dob, contact))*//*

                        Log.d("dataValue", "onDataChange: ${dataSnapshot.value.toString()}")
                        *//*   val dob=dataSnapshot
                               .getValue(String::class.java)*//*


                        //   Log.d("Vallues", "onDataChange: ${dob}")
                        Log.d("register", "Already Registered")
                     *//*   binding.nextContinue.visibility = View.GONE
                        binding.loader.visibility = View.VISIBLE*//*

                    } else {

                        val i =
                            Intent(applicationContext, EnterFullNameActivity::class.java)
                     //   i.putExtra("contact", contact)
                        startActivity(i)
                        // Log.d("register", "not Registered")
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Log.d("checkiserror", "$databaseError")
                }
            })


    }*/

    private fun preChecksData() {
        if (mAuth.currentUser != null) {

            Log.d("userPhone", "preChecksData: ${mAuth.currentUser?.phoneNumber!!}")
            val userRef = FirebaseDatabase.getInstance().getReference("UserAppData")
            userRef.orderByChild("contact")
                .equalTo(mAuth.currentUser?.phoneNumber!!.replace("+91", ""))
                .addValueEventListener(object :
                    ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {


                        if (dataSnapshot.value != null) {

                            dataSnapshot.children.forEach {
                                Log.d("gvaa", "onDataChange: ${it.key}")
                                userKey = it.key.toString()
                                dob = it.child("dob").getValue(String::class.java).toString()
                                email = it.child("email").getValue(String::class.java).toString()
                                contact =
                                    it.child("contact").getValue(String::class.java).toString()
                                name = it.child("name").getValue(String::class.java).toString()
                                Log.d("splash", "onDataChange: ${dob},${email},${name},${contact}")
                            }
                            val i = Intent(applicationContext, Home::class.java)
                            startActivity(i)
                            array.add(Model(name, email, dob, contact))
                            finish()

                            Log.d("dataValue", "onDataChange: ${dataSnapshot.value.toString()}")
                            Log.d("register", "Already Registered")
                        } else {

                            val i =
                                Intent(applicationContext, MainActivity::class.java)
                            i.putExtra("contact", contact)
                            startActivity(i)
                            finish()
                            // Log.d("register", "not Registered")
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        Log.d("checkiserror", "$databaseError")
                    }
                })


        } else {
            binding.loader.visibility = View.GONE
            val intent = Intent(applicationContext, OnBoardingActivity::class.java)
            startActivity(intent)
            finish()
        }

    }


    private fun preChecks() {
        if (mAuth.currentUser != null) {
            val mDatabase = FirebaseDatabase.getInstance().reference
            Objects.requireNonNull(mAuth.uid)?.let {
                mDatabase.child("UserAppUsers").child(it)
                    .child("contact").get()
                    .addOnCompleteListener { task ->
                        if (!task.isSuccessful) {
                            binding.loader.visibility = View.GONE
                            Log.e("Firebase", "There is error to get the data")
                        } else {
                            if (task.result.exists()) {
                                fireBaseRealTimeDataBase = UserDataBase()
                                GlobalScope.launch {
                                    //  fireBaseRealTimeDataBase.retrieveCurrentUser()
                                }

                                binding.loader.visibility = View.GONE
                                val i = Intent(applicationContext, Home::class.java)
                                startActivity(i)
                                finish()
                            } else {
                                startMainActivity()
                            }
                        }
                    }.addOnFailureListener {
                        Toast.makeText(
                            this,
                            "Something went wrong check connection.",
                            Toast.LENGTH_SHORT
                        ).show()
                        startMainActivity()
                    }
            }
        } else {
            startMainActivity()
        }
    }

    private fun startMainActivity() {
        val intent = Intent(applicationContext, OnBoardingActivity::class.java)
        startActivity(intent)
        finish()
    }
}
