package com.cu.crazypocket.DataClass

import com.cu.crazypocket.FireBaseRealtimeDataBase.Model.DataClass

data class ShopDetails( val category:String="",val shopName:String="",val shopImage:String="",val array: ArrayList<DataClass>,
                        val shopAddress:String="",val shopAddressLatitude:String="",
                        val shopAddressLongitude:String="",val shopArea:String="",
                        val shopAddressDistrict:String="",val shopImageTwo:String="",
                        val shopImageThree:String="",val shopImageFour:String="",val shopImageFive:String="",val shopState:String="")
