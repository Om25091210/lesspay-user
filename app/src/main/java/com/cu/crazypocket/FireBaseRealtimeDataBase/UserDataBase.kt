package com.cu.crazypocket.FireBaseRealtimeDataBase

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cu.crazypocket.FireBaseRealtimeDataBase.Model.Model
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*


class UserDataBase
{
    lateinit var databaseReference: DatabaseReference
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var firebaseUser: FirebaseUser


    private val mutableLiveData = MutableLiveData<Boolean>()
    val liveData: LiveData<Boolean>
        get() = mutableLiveData



    private val mutableLiveMembershipData = MutableLiveData<Boolean>()
    val liveDataMembership: LiveData<Boolean>
        get() = mutableLiveMembershipData

    fun insertIntoDatabase(
        name: String, email: String, dob: String, contact: String,membership: String, context: Context
    ) {

        databaseReference = FirebaseDatabase.getInstance().getReference("UserAppData")
        val users = Model(name = name, email = email, dob = dob, contact = contact,membership=membership)
        databaseReference.push().setValue(users).addOnSuccessListener {

            Toast.makeText(context, "SuccessFull", Toast.LENGTH_LONG).show()
            mutableLiveData.postValue(true)

        }.addOnFailureListener {
            Toast.makeText(context, "failed", Toast.LENGTH_LONG).show()
            mutableLiveData.postValue(false)


        }

    }

   /* suspend fun retrieveCurrentUser() {


        // val databaseReference = FirebaseDatabase.getInstance().getReference()


        val firebaseAuth = FirebaseAuth.getInstance().currentUser!!.uid
        databaseReference = FirebaseDatabase.getInstance().reference
        //val mDb = databaseReference.getReference()



        Log.d("UID", "retrieveCurrentUser: $user")
        databaseReference.child("UserAppData").child(firebaseAuth!!.uid)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        val acctname: String? = dataSnapshot.child("name").getValue(String::class.java)
                        Log.i("name", acctname!!)
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            })







        *//* databaseReference=FirebaseDatabase.getInstance().reference.child("UserAppData").child(FirebaseAuth.getInstance().uid!!)
         databaseReference.addValueEventListener(object : ValueEventListener {
             override fun onDataChange(dataSnapshot: DataSnapshot) {
                 val acctname: String? = dataSnapshot.child("name").getValue(String::class.java)
                 Log.i("name", acctname!!)
             }

             override fun onCancelled(databaseError: DatabaseError) {
                 Log.e("error", databaseError.message)
             }
         })
 *//*
    }*/

    suspend fun insertMembership(value:Boolean,context:Context)
    {
        val firebaseAuth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().getReference("UserAppData")
            .ref.child(firebaseAuth.currentUser!!.uid)
        databaseReference.setValue(value).addOnSuccessListener {


            mutableLiveMembershipData.postValue(true)
           /* val sharedPreferences: SharedPreferences =
                context.getSharedPreferences("MyName", Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("name", name)
            editor.putString("email", email)
            editor.putString("contact", contact)
            editor.putString("dob", dob)
            editor.apply()
            editor.commit()*/

        }.addOnFailureListener {
            Toast.makeText(context, "failed", Toast.LENGTH_LONG).show()
            mutableLiveData.postValue(false)


        }
    }

}
