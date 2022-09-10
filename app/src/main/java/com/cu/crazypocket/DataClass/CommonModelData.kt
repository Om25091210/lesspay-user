package com.cu.crazypocket.DataClass

import com.cu.crazypocket.FireBaseRealtimeDataBase.Model.DataClass

data class CommonModelData(
    val shopName: String = "",
    val imageUrl: String = "",
    val shopAddress: String,
    val shopAddressLatitude: String,
    val shopAddressLongitude: String,
    val data: ArrayList<DataClass>?=null
)//val discountList:ArrayList<MyDataClass>
