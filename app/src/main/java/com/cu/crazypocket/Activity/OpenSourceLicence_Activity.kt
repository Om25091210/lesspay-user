package com.cu.crazypocket.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.cu.crazypocket.R
import com.cu.crazypocket.databinding.ActivityOpensourcelicenceBinding

class OpenSourceLicence_Activity : AppCompatActivity() {
    lateinit var binding: ActivityOpensourcelicenceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_opensourcelicence)

    }
}
