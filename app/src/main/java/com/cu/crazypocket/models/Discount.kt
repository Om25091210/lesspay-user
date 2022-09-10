package com.cu.crazypocket.models

import com.cu.crazypocket.FireBaseRealtimeDataBase.Model.DataClass

data class Discount(var array: ArrayList<DataClass> = arrayListOf())
//data class Review(
//    var rating:Int=0,
//    var userId:String="",
//    var textR:String="",
//    //@ServerTimestamp var timestamp: Date? = null
//):Serializable{
//    constructor( rating: Float,userId: String):this() {
//        this.userId = userId
//        this.rating = rating
//    }
//}
