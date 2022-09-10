package com.cu.crazypocket.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import com.cu.crazypocket.R
import com.cu.crazypocket.databinding.ActivityGreiVanceBinding

class GreiVance_Activity : AppCompatActivity() {
    lateinit var binding:ActivityGreiVanceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_grei_vance)
        binding.back.setOnClickListener {
            finish()
        }
        //  binding.privacyWeb.loadUrl("https://gastos-privacypolicy.netlify.app/")
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
        binding.privacyWeb.loadUrl("https://grivancepolicy.netlify.app/")

    }
}
