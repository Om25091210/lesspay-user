package com.cu.crazypocket.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.cu.crazypocket.R
import com.cu.crazypocket.databinding.ActivityAboutGastosBinding


class AboutGastos : AppCompatActivity() {
    lateinit var binding: ActivityAboutGastosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_about_gastos)
        binding.back.setOnClickListener {
            finish()
        }

        //setContentView(R.layout.activity_about_gastos);
        binding.aboutUs.setOnClickListener(View.OnClickListener {
            val i = Intent(applicationContext, InsideAboutGastos::class.java)
            startActivity(i)
        })
        binding.greivances.setOnClickListener(View.OnClickListener {
            val i = Intent(applicationContext, GreiVance_Activity::class.java)
            startActivity(i)
        })
        binding.privacyPolicy.setOnClickListener(View.OnClickListener {
            val i = Intent(applicationContext, PrivacyPolicyActivity::class.java)
            startActivity(i)
        })
        binding.tandc.setOnClickListener(View.OnClickListener {
            val i = Intent(applicationContext, TermsAndConditionActivity::class.java)
            startActivity(i)
        })
        binding.openSource.setOnClickListener(View.OnClickListener {
            val i = Intent(applicationContext, OpenSourceLicence_Activity::class.java)
            startActivity(i)
        })
    }
}
