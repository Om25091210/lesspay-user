package com.cu.crazypocket.Activity

import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.cu.crazypocket.R
import com.cu.crazypocket.databinding.ActivityTermsAndConditionBinding

class TermsAndConditionActivity : AppCompatActivity() {
    lateinit var binding: ActivityTermsAndConditionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_terms_and_condition)
        binding.back.setOnClickListener {
            finish()
        }

        binding.loader.visibility= View.VISIBLE
        val webSettings: WebSettings = binding.privacyWeb.getSettings()
        webSettings.javaScriptEnabled = true
        binding.privacyWeb.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, weburl: String) {

                binding.loader.visibility= View.GONE
            }
        }
        binding.privacyWeb.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, newProgress: Int) {


            }
        }
        binding.privacyWeb.loadUrl("https://gastosterms.netlify.app/")


    }
}
