package com.cu.crazypocket.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.cu.crazypocket.R
import com.cu.crazypocket.databinding.ActivityHomeBinding

class Home : AppCompatActivity() {
    lateinit var navController: NavController
    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_home)
        //navController=findNavController(R.id.fragmentContainerView)
        val navHostFragment=supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController=navHostFragment.navController
        setupWithNavController(binding.bottomNav,navController)


    }




}
