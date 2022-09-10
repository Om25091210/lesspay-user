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
import com.cu.crazypocket.databinding.ActivityInsideAboutGastosBinding

class InsideAboutGastos : AppCompatActivity() {
    lateinit var binding: ActivityInsideAboutGastosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_inside_about_gastos)
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
        binding.privacyWeb.loadUrl("https://gastosaboutususerapp.netlify.app/")


    }
}
