    package com.cu.crazypocket.ViewModel

    import android.content.Context
    import androidx.lifecycle.MutableLiveData
    import androidx.lifecycle.ViewModel
    import androidx.lifecycle.viewModelScope
    import com.cu.crazypocket.FireBaseRealtimeDataBase.FireBaseRealTimeDataBase
    import com.cu.crazypocket.FireBaseRealtimeDataBase.Model.ImageData
    import com.cu.crazypocket.Repository.Repository
    import kotlinx.coroutines.launch

    class MyViewModel(private val repository: Repository):ViewModel()
    {
        fun insertIntoDataBase(name: String, email: String,dob: String, contact: String, context: Context)
        {
            viewModelScope.launch {
                repository.insert(name,email,dob,contact,context)
            }
        }
    }
    class LoadImage(private val fireBaseRealTimeDataBase: FireBaseRealTimeDataBase)
    {
        val imgData:MutableLiveData<List<ImageData>>
            get()= fireBaseRealTimeDataBase.imgLiveData as MutableLiveData<List<ImageData>>

        suspend fun retriveImages()
        {
            fireBaseRealTimeDataBase.getBannerAds()
        }

    }
