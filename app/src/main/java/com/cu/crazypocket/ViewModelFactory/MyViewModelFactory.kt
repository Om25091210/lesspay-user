package com.cu.crazypocket.ViewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cu.crazypocket.FireBaseRealtimeDataBase.FireBaseRealTimeDataBase
import com.cu.crazypocket.ViewModel.MyViewModel
import com.cu.crazypocket.Repository.Repository
import com.cu.crazypocket.ViewModel.LoadImage

class MyViewModelFactory(private val repository: Repository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MyViewModel(repository) as T
    }
}

class MyViewModelFactoryOne(private val fireBaseRealTimeDataBase: FireBaseRealTimeDataBase):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoadImage(fireBaseRealTimeDataBase) as T
    }
}
