package com.cu.crazypocket.Fragment

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.cu.crazypocket.Activity.AboutGastos
import com.cu.crazypocket.Activity.SplashScreen
import com.cu.crazypocket.R
import com.cu.crazypocket.databinding.FragmentProfileBinding
import com.google.firebase.database.FirebaseDatabase


class ProfileFragment : Fragment() {

    lateinit var binding: FragmentProfileBinding
    var mShip:String?=null;
    var uName:String=""
    var userEmail:String=""
    var userDob:String=""
    var userContact:String=""
    var contact:String?=""
    var name:String?=""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_profile, container, false)
        binding.apply {
          /*  val userRef = FirebaseDatabase.getInstance().getReference("UserAppData")
            userRef.child(SplashScreen.userKey).addValueEventListener(object:ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    mShip=snapshot.child("membership").getValue(String::class.java)
                    contact=snapshot.child("contact").getValue(String::class.java)
                    name=snapshot.child("name").getValue(String::class.java)
                    Log.d("Mship", "name: ${mShip} ${contact} ${name}")

                    if(mShip.equals("true"))
                    {
                        renewMembership.text="Membership Activated"
                        renewMembership.isEnabled=false
                        var lastFourDigits = "";   //substring containing last 4 characters

                        if (contact!!.length > 4)
                        {
                            lastFourDigits = contact!!.substring(contact!!.length - 4);
                        }
                        else
                        {
                            lastFourDigits = contact!!;
                        }
                        binding.userName.text=name
                        binding.number.text= "XXXXXX$lastFourDigits"
                    }
                    else
                    {
                        var lastFourDigits = "";   //substring containing last 4 characters

                        if (contact!!.length > 4)
                        {
                            lastFourDigits = contact!!.substring(contact!!.length - 4);
                        }
                        else
                        {
                            lastFourDigits = contact!!;
                        }
                        binding.userName.text=name
                        binding.number.text= "XXXXXX$lastFourDigits"
                    }

                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })*/


           /* if(SplashScreen.array.size!=0)
            {

                for(i in SplashScreen.array)
                {
                    if(i.membership.equals("true"))
                    {
                        Toast.makeText(context,"Membership Activated",Toast.LENGTH_LONG).show()
                        break;
                    }
                }
            }*/
            aboutUs.setOnClickListener {
                val intent=Intent(context,AboutGastos::class.java)
                startActivity(intent)
            }
            help.setOnClickListener {
                val dialog = AlertDialog.Builder(context)
                val mView = layoutInflater.inflate(R.layout.commingsoon, null)
                val dialogButton = mView.findViewById<View>(R.id.okay) as Button
                dialog.setView(mView)
                val alertDialog = dialog.create()
                alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                alertDialog.setCanceledOnTouchOutside(false)
                dialogButton.setOnClickListener { alertDialog.dismiss() }
                alertDialog.show()
               /* val i = Intent(context, HelpAndSupport::class.java)
                startActivity(i)*/
            }
            settingText.setOnClickListener {
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
            expense.setOnClickListener {
                val dialog = AlertDialog.Builder(context)
                val mView = layoutInflater.inflate(R.layout.commingsoon, null)
                val dialogButton = mView.findViewById<View>(R.id.okay) as Button
                dialog.setView(mView)
                val alertDialog = dialog.create()
                alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                alertDialog.setCanceledOnTouchOutside(false)
                dialogButton.setOnClickListener { alertDialog.dismiss() }
                alertDialog.show()
                /* val i = Intent(context, HelpAndSupport::class.java)
                 startActivity(i)*/
            }
            refer.setOnClickListener {
                val dialog = AlertDialog.Builder(context)
                val mView = layoutInflater.inflate(R.layout.commingsoon, null)
                val dialogButton = mView.findViewById<View>(R.id.okay) as Button
                dialog.setView(mView)
                val alertDialog = dialog.create()
                alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                alertDialog.setCanceledOnTouchOutside(false)
                dialogButton.setOnClickListener { alertDialog.dismiss() }
                alertDialog.show()
                /* val i = Intent(context, HelpAndSupport::class.java)
                 startActivity(i)*/
            }
            setting.setOnClickListener {
                val dialog = AlertDialog.Builder(context)
                val mView = layoutInflater.inflate(R.layout.commingsoon, null)
                val dialogButton = mView.findViewById<View>(R.id.okay) as Button
                dialog.setView(mView)
                val alertDialog = dialog.create()
                alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                alertDialog.setCanceledOnTouchOutside(false)
                dialogButton.setOnClickListener { alertDialog.dismiss() }
                alertDialog.show()
                /* val i = Intent(context, HelpAndSupport::class.java)
                 startActivity(i)*/
            }
            rateUs.setOnClickListener {
                val dialog = AlertDialog.Builder(context)
                val mView = layoutInflater.inflate(R.layout.commingsoon, null)
                val dialogButton = mView.findViewById<View>(R.id.okay) as Button
                dialog.setView(mView)
                val alertDialog = dialog.create()
                alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                alertDialog.setCanceledOnTouchOutside(false)
                dialogButton.setOnClickListener { alertDialog.dismiss() }
                alertDialog.show()
                /* val i = Intent(context, HelpAndSupport::class.java)
                 startActivity(i)*/
            }
            setting.setOnClickListener {
                val dialog = AlertDialog.Builder(context)
                val mView = layoutInflater.inflate(R.layout.commingsoon, null)
                val dialogButton = mView.findViewById<View>(R.id.okay) as Button
                dialog.setView(mView)
                val alertDialog = dialog.create()
                alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                alertDialog.setCanceledOnTouchOutside(false)
                dialogButton.setOnClickListener { alertDialog.dismiss() }
                alertDialog.show()
                /* val i = Intent(context, HelpAndSupport::class.java)
                 startActivity(i)*/
            }
            renewMembership.setOnClickListener {
                renewMembership.text="Membership Activated"
                renewMembership.isEnabled=false


                if(SplashScreen.array.size!=0)
                {
                    for (i in SplashScreen.array){

                        Log.d("ggg", "onCreateView: ${i.name},${i.dob},${i.contact},${i.dob}")
                        uName=i.name
                        userEmail=i.email
                        userDob=i.dob
                        userContact=i.contact

                    }

                }



                    val hashMap=HashMap<String,String>()
                    hashMap.put("membership","true")
                    val userRef = FirebaseDatabase.getInstance().getReference("UserAppData")
                    userRef.child(SplashScreen.userKey)
                        .updateChildren(hashMap as Map<String, Any>).addOnSuccessListener {
                            Toast.makeText(context,"Membership Updated Successfully",Toast.LENGTH_LONG).show()
                        }





            }



        }

        return binding.root


    }

}
