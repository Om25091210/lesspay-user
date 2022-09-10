package com.cu.crazypocket.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.cu.crazypocket.R
import com.cu.crazypocket.databinding.ActivityHelpAndSupportBinding


class HelpAndSupport : AppCompatActivity() {
    private lateinit var binding: ActivityHelpAndSupportBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_help_and_support)
        binding.repostAnIssue.setOnClickListener(View.OnClickListener {
            val i = Intent(applicationContext, FaqActivity::class.java)
            startActivity(i)
        })
        binding.guideToGastosApp.setOnClickListener(View.OnClickListener {
            val i = Intent(applicationContext, GuideToGastos::class.java)
            startActivity(i)
        })
        binding.report.setOnClickListener(View.OnClickListener {
            val i = Intent(applicationContext, ReportAnIssue::class.java)
            startActivity(i)
        })

    }
}
