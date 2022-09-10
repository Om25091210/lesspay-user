package com.cu.crazypocket.Repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.cu.crazypocket.FireBaseRealtimeDataBase.UserDataBase

class Repository(val fireBaseRealTimeDataBase: UserDataBase)
{

   val mutableLiveData:MutableLiveData<Boolean>
           get()= fireBaseRealTimeDataBase.liveDataMembership as MutableLiveData<Boolean>





    suspend fun insert( name: String, email: String,dob: String, contact: String,context: Context)
    {
        fireBaseRealTimeDataBase.insertIntoDatabase(name,email,dob,contact,"",context)

    }


}


