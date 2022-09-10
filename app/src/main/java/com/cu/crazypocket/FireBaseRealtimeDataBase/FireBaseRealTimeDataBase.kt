package com.cu.crazypocket.FireBaseRealtimeDataBase

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cu.crazypocket.DataClass.CommonModelData
import com.cu.crazypocket.DataClass.ShopDetails
import com.cu.crazypocket.FireBaseRealtimeDataBase.Model.DataClass
import com.cu.crazypocket.FireBaseRealtimeDataBase.Model.ImageData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class FireBaseRealTimeDataBase {
    lateinit var databaseReference: DatabaseReference
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var firebaseUser: FirebaseUser
    val arraylist=ArrayList<ImageData>()

    public val mutableLiveDataOne = MutableLiveData<List<CommonModelData>>()
    val isLoadinggMydata = MutableLiveData<Boolean>(true)



    companion object {
        var key = ""
        var mykey = ""
        var mylist: ArrayList<ShopDetails> = ArrayList()
        var qrCodeStrings: ArrayList<String> = ArrayList()
    }

    lateinit var dd: String
    lateinit var monthmm: String
    lateinit var yy: String

    private val mutableLiveDataRetrive = MutableLiveData<List<ShopDetails>>()
    val liveDataRetrive: LiveData<List<ShopDetails>>
        get() = mutableLiveDataRetrive



    /* private fun getuserData(uid: String) {
         val db = FirebaseDatabase.getInstance().getReference("UserAppUsers")
         db.child(uid).addValueEventListener(object:ValueEventListener{
             override fun onDataChange(snapshot: DataSnapshot) {
                 var user=snapshot.child("name").getValue(String::class.java)
                 Log.d("Users", "onDataChange: $user")
             }

             override fun onCancelled(error: DatabaseError) {
                 TODO("Not yet implemented")
             }

         })
     }*/


    suspend fun retriveFromDataBase() {
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseUser = firebaseAuth.currentUser!!
        Log.d("UIDS", "retriveFromDataBase: ${firebaseAuth.uid}")



        Log.d("Keys", "retriveFromDataBase: ${firebaseAuth.uid}")
        databaseReference = FirebaseDatabase.getInstance().reference
        val QUERY: Query = if (key.isNullOrEmpty()) {
            databaseReference.child("Merchant_data").orderByKey().limitToFirst(15)
        } else {
            databaseReference.child("Merchant_data").orderByKey().startAfter(key).limitToFirst(10)
        }
        QUERY.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                //  mylist.clear()


                GlobalScope.launch(Dispatchers.IO) {
                    val arraylist = mutableListOf<CommonModelData>()
                    for (i in snapshot.children) {

                        var category: String? = i.child("Shop_Information").child("category")
                            .getValue(String::class.java)
                        var shopName: String? = i.child("Shop_Information").child("shopName")
                            .getValue(String::class.java)
                        var shopImage: String? =
                            i.child("Shop_Information").child("shopImageUri")
                                .getValue(String::class.java)
                        var shopImageOne: String? =
                            i.child("Shop_Information").child("shopImageUri1")
                                .getValue(String::class.java)
                        var shopAddress: String? =
                            i.child("Shop_Information").child("shopAddress")
                                .getValue(String::class.java)

                        var shopImageTwo: String? =
                            i.child("Shop_Information").child("shopImageUri2")
                                .getValue(String::class.java)
                        var shopImageThree: String? =
                            i.child("Shop_Information").child("shopImageUri3")
                                .getValue(String::class.java)
                        var shopImageFour: String? =
                            i.child("Shop_Information").child("shopImageUri4")
                                .getValue(String::class.java)
                        var shopAddressLatitude: String? =
                            i.child("Shop_Information").child("shopAddressLatitude")
                                .getValue(String::class.java)
                        var shopAddressLongitude: String? =
                            i.child("Shop_Information").child("shopAddressLongitude")
                                .getValue(String::class.java)
                        var shopArea: String? = i.child("Shop_Information").child("shopArea")
                            .getValue(String::class.java)
                        var shopDistrict: String? =
                            i.child("Shop_Information").child("shopDistrict")
                                .getValue(String::class.java)
                        var shopState: String? = i.child("Shop_Information").child("shopState")
                            .getValue(String::class.java)
                        var data: ArrayList<DataClass>? =
                            i.child("Shop_Information").child("discounts")
                                .getValue() as ArrayList<DataClass>?

                        Log.d("catt", "onDataChange: ${data}")

                        if (category != null && shopImage != null && shopName != null && data != null && shopImageOne != null && shopAddress != null
                            && shopImageTwo != null && shopAddressLatitude != null && shopImageThree != null
                            && shopState != null && shopArea != null && shopDistrict != null && shopImageFour != null && shopAddressLongitude != null
                        ) {
                            mylist.add(
                                ShopDetails(
                                    category = category,
                                    shopName = shopName,
                                    shopImage = shopImage,
                                    shopImageTwo = shopImageOne,
                                    shopAddress = shopAddress,
                                    shopImageThree = shopImageTwo,
                                    shopImageFour = shopImageThree,
                                    shopImageFive = shopImageFour,
                                    shopAddressLatitude = shopAddressLatitude,
                                    shopAddressDistrict = shopDistrict,
                                    shopAddressLongitude = shopAddressLongitude,
                                    shopState = shopState,
                                    shopArea = shopArea,
                                    array = data
                                )
                            )
                            Log.d("size", "onDataChange: ${i.key.toString()}")
                            key = i.key.toString()
                            arraylist.add(CommonModelData(
                                shopName,
                                shopImage,
                                shopAddress,
                                shopAddressLatitude,
                                shopAddressLongitude,
                                data
                            ))

                        }


                    }
                    withContext(Dispatchers.Main) {
                        mutableLiveDataRetrive.postValue(mylist)
                        mutableLiveDataOne.postValue(arraylist)
                    }
                }

            }

            /* var memberShip: String? =
                 i.child("membership").child("expiry").getValue(String::class.java)

             if (memberShip != null) {
                 val bool: Boolean = isExpire(memberShip)
                 if (bool)
                 {
                     Log.d("val", "onCreateView: $bool")
                     Log.d("Mship", "onDataChange: $memberShip")
                     var category: String? = i.child("Shop_Information").child("category")
                         .getValue(String::class.java)
                     var shopName: String? = i.child("Shop_Information").child("shopName")
                         .getValue(String::class.java)
                     var shopImage: String? =
                         i.child("Shop_Information").child("shopImageUri")
                             .getValue(String::class.java)
                     var shopImageOne: String? =
                         i.child("Shop_Information").child("shopImageUri1")
                             .getValue(String::class.java)
                     var shopAddress: String? =
                         i.child("Shop_Information").child("shopAddress")
                             .getValue(String::class.java)

                     var shopImageTwo: String? =
                         i.child("Shop_Information").child("shopImageUri2")
                             .getValue(String::class.java)
                     var shopImageThree: String? =
                         i.child("Shop_Information").child("shopImageUri3")
                             .getValue(String::class.java)
                     var shopImageFour: String? =
                         i.child("Shop_Information").child("shopImageUri4")
                             .getValue(String::class.java)
                     var shopAddressLatitude: String? =
                         i.child("Shop_Information").child("shopAddressLatitude")
                             .getValue(String::class.java)
                     var shopAddressLongitude: String? =
                         i.child("Shop_Information").child("shopAddressLongitude")
                             .getValue(String::class.java)
                     var shopArea: String? = i.child("Shop_Information").child("shopArea")
                         .getValue(String::class.java)
                     var shopDistrict: String? =
                         i.child("Shop_Information").child("shopDistrict")
                             .getValue(String::class.java)
                     var shopState: String? = i.child("Shop_Information").child("shopState")
                         .getValue(String::class.java)
                     var data: ArrayList<DataClass>? =
                         i.child("Shop_Information").child("discounts")
                             .getValue() as ArrayList<DataClass>?

                     Log.d("catt", "onDataChange: ${data}")

                     if (category != null && shopImage != null && shopName != null && data != null && shopImageOne != null && shopAddress != null
                         && shopImageTwo != null && shopAddressLatitude != null && shopImageThree != null
                         && shopState != null && shopArea != null && shopDistrict != null && shopImageFour != null && shopAddressLongitude != null
                     ) {
                         mylist.add(
                             ShopDetails(
                                 category = category,
                                 shopName = shopName,
                                 shopImage = shopImage,
                                 shopImageTwo = shopImageOne,
                                 shopAddress = shopAddress,
                                 shopImageThree = shopImageTwo,
                                 shopImageFour = shopImageThree,
                                 shopImageFive = shopImageFour,
                                 shopAddressLatitude = shopAddressLatitude,
                                 shopAddressDistrict = shopDistrict,
                                 shopAddressLongitude = shopAddressLongitude,
                                 shopState = shopState,
                                 shopArea = shopArea,
                                 array = data
                             )
                         )
                         Log.d("size", "onDataChange: $mylist")
                         mutableLiveDataRetrive.postValue(mylist)

                     }


                 }

             }*/


            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
                Log.d("fetchData", "onCancelled: $error")
            }

        })
    }

     private val imgData = MutableLiveData<List<ImageData>>()
     val imgLiveData: LiveData<List<ImageData>>
        get() = imgData

     fun getBannerAds() {

        val databaseReference = FirebaseDatabase.getInstance().reference

            databaseReference.child("Merchant_data").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                //  mylist.clear()


                GlobalScope.launch(Dispatchers.IO) {

                    for (i in snapshot.children) {

                        var img1: String = i.child("img1")
                            .getValue(String::class.java).toString()
                        var img2: String = i.child("img2")
                            .getValue(String::class.java).toString()
                        var img3: String =
                            i.child("img3")
                                .getValue(String::class.java).toString()
                        arraylist.add(ImageData(img1,img2,img3))


                    }
                    withContext(Dispatchers.Main) {
                        imgData.postValue(arraylist)

                    }
                }

            }



            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
                Log.d("fetchData", "onCancelled: $error")
            }

        })
    }


    fun getMyList(food: String) {
        isLoadinggMydata.value = true
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseUser = firebaseAuth.currentUser!!

        Log.d("Keysmya", "retriveFromDataBase: ${mykey}")
        databaseReference = FirebaseDatabase.getInstance().reference
        val QUERY: Query = if (mykey.isNullOrEmpty()) {
            databaseReference.child("Merchant_data").orderByKey().limitToFirst(40)
        } else {
            databaseReference.child("Merchant_data").orderByKey().startAfter(mykey).limitToFirst(40)
        }
        val asarraylist = mutableListOf<CommonModelData>()
        mutableLiveDataOne.postValue(emptyList())
        asarraylist.clear()
        QUERY.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (i in snapshot.children) {
                    mykey = i.key.toString()
                    Log.d("arrayitems", " $mykey, ${i.key}")

                    var category: String? = i.child("Shop_Information").child("category")
                        .getValue(String::class.java)
                    var shopName: String? = i.child("Shop_Information").child("shopName")
                        .getValue(String::class.java)
                    var shopImage: String? =
                        i.child("Shop_Information").child("shopImageUri")
                            .getValue(String::class.java)
                    var shopImageOne: String? =
                        i.child("Shop_Information").child("shopImageUri1")
                            .getValue(String::class.java)
                    var shopAddress: String? =
                        i.child("Shop_Information").child("shopAddress")
                            .getValue(String::class.java)

                    var shopImageTwo: String? =
                        i.child("Shop_Information").child("shopImageUri2")
                            .getValue(String::class.java)
                    var shopImageThree: String? =
                        i.child("Shop_Information").child("shopImageUri3")
                            .getValue(String::class.java)
                    var shopImageFour: String? =
                        i.child("Shop_Information").child("shopImageUri4")
                            .getValue(String::class.java)
                    var shopAddressLatitude: String? =
                        i.child("Shop_Information").child("shopAddressLatitude")
                            .getValue(String::class.java)
                    var shopAddressLongitude: String? =
                        i.child("Shop_Information").child("shopAddressLongitude")
                            .getValue(String::class.java)
                    var shopArea: String? = i.child("Shop_Information").child("shopArea")
                        .getValue(String::class.java)
                    var shopDistrict: String? =
                        i.child("Shop_Information").child("shopDistrict")
                            .getValue(String::class.java)
                    var shopState: String? = i.child("Shop_Information").child("shopState")
                        .getValue(String::class.java)
                    var data: ArrayList<DataClass>? =
                        i.child("Shop_Information").child("discounts")
                            .getValue() as ArrayList<DataClass>?

                    Log.d("catt", "onDataChange: ${data}")

                    if (category != null && shopImage != null && shopName != null && data != null && shopImageOne != null && shopAddress != null
                        && shopImageTwo != null && shopAddressLatitude != null && shopImageThree != null
                        && shopState != null && shopArea != null && shopDistrict != null && shopImageFour != null && shopAddressLongitude != null
                    ) {

                        Log.d("food", "onDataChange: $food")
                        Log.d("categoryccam", category.toString() + ", $shopName , $mykey")
                        if (category.equals(food)) {
                            Log.d("size", "onDataChange: ${i.key.toString()}")
//Food & Beverages, Fashion
                            asarraylist.add(CommonModelData(shopName, shopImage, shopAddress,shopAddressLatitude,shopAddressLongitude,data))
                        }
                        else if (food.equals("other") && !category.equals("Fashion") && !category.equals(
                                "Food & Beverages")
                        ) {
                            asarraylist.add(CommonModelData(
                                shopName,
                                shopImage,
                                shopAddress,
                                shopAddressLatitude,
                                shopAddressLongitude,
                                data
                            ))
                        }
                        else  if(food.equals("all")){
                            asarraylist.add(CommonModelData(
                                shopName,
                                shopImage,
                                shopAddress,
                                shopAddressLatitude,
                                shopAddressLongitude,
                                data))
                        }
                       /* else if (category.equals("stores"))
                         {
                            asarraylist.add(CommonModelData(
                                shopName,
                                shopImage,
                                shopAddress,
                                shopAddressLatitude,
                                shopAddressLongitude,
                                data
                            ))
                        }
                        else if (food.equals("Salon & spa"))
                         {
                            asarraylist.add(CommonModelData(
                                shopName,
                                shopImage,
                                shopAddress,
                                shopAddressLatitude,
                                shopAddressLongitude,
                                data
                            ))
                        }*/
                    }

                }
                mutableLiveDataOne.postValue(asarraylist)
                isLoadinggMydata.value = false
            }

            override fun onCancelled(error: DatabaseError) {
                isLoadinggMydata.value = false
                Log.d("fetchData", "onCancelled: $error")
            }

        })
    }


    fun isExpire(date: String): Boolean {
        return if (date.isEmpty() || date.trim { it <= ' ' } == "") {
            false
        } else {
            // val sdf = SimpleDateFormat("dd mm yyyy") // Jan-20-2015 1:30:55 PM

            val today: String = getToday()  //d-date1   d1-date2
            val date1 = Date(date)
            val date2 = Date(today)
            val result = date1.compareTo(date2)

            if (result == 0) {
                Log.d("M1", "Both dates are equal")
                return false
            } else if (result < 0) {
                Log.d("M1", "Expire")
                return false
            }//false
            else {
                //true
                Log.d("M1", "Not Expire")
                return true
            }
            return false
        }
    }

    fun getToday(): String {
        val sdf = SimpleDateFormat("dd")
        val sdfOne = SimpleDateFormat("MM")
        val sdfTwo = SimpleDateFormat("yyyy")
        val date = sdf.format(Date())
        var month = sdfOne.format(Date())
        val year = sdfTwo.format(Date())
        Log.d("Date", "onDataChange: ${date} ${month} ${year}")
        if (month == "01") {
            month = "Jan"
        } else if (month == "02") {
            month = "Feb"
        } else if (month == "02") {
            month = "Feb"
        } else if (month == "03") {
            month = "Mar"
        } else if (month == "04") {
            month = "Apr"
        } else if (month == "05") {
            month = "May"
        } else if (month == "06") {
            month = "Jun"
        } else if (month == "07") {
            month = "Jul"
        } else if (month == "08") {
            month = "Aug"
        } else if (month == "09") {
            month = "Sept"
        } else if (month == "10") {
            month = "Oct"
        } else if (month == "11") {
            month = "Nov"
        } else if (month == "12") {
            month = "Dec"
        }
        return date + " " + month + " " + year
    }


    suspend fun getQr()
    {
        val databaseReference = FirebaseDatabase.getInstance().reference

        databaseReference.child("Merchant_data").addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for(i in snapshot.children)
                {
                    Log.d("snapshorts", "onDataChange: ${i.child("Payment_Information").child("0").child("primary")}")
                    var valOne=i.child("Payment_Information").child("0").child("primary").value
                    var valTwo=i.child("Payment_Information").child("1").child("primary").value
                    Log.d("primaryQr", "onDataChange: $valOne")
                    if(valOne!=null)
                    {
                        if(valOne==true)
                        {
                            var val1=i.child("Payment_Information").child("0").child("rawString").value.toString()
                            qrCodeStrings.add(val1)
                            Log.d("valOnes", "onDataChange: ${val1}")
                        }


                    }
                    if(valTwo!=null)
                    {
                        if(valTwo==true)
                        {
                            var val2=i.child("Payment_Information").child("1").child("rawString").value.toString()
                            qrCodeStrings.add(val2)
                            Log.d("valTwos", "onDataChange: ${val2}")
                        }


                    }
                   // val map: Map<String, Any> =i.child("Payment_Information").child("rawString") as Map<String, Any>
                   // Log.d("maps", "onDataChange: $map")


                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }



}
