package com.cu.crazypocket.Activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import com.budiyev.android.codescanner.*
import com.cu.crazypocket.R
import com.cu.crazypocket.databinding.ActivityScannerBinding

class ScannerActivity : AppCompatActivity() {
    lateinit var binding :ActivityScannerBinding
    private lateinit var codeScanner: CodeScanner
    private var isPermissionGranted = false
    private val RequestCameraPermissionId = 50
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_scanner)
        binding.apply {
            codeScanner = CodeScanner(this@ScannerActivity, scannerView)

            if (ActivityCompat.checkSelfPermission(
                    applicationContext,
                    Manifest.permission.CAMERA
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this@ScannerActivity,
                    arrayOf(Manifest.permission.CAMERA),
                    RequestCameraPermissionId
                )
                return
            }

            try {
                isPermissionGranted = true
                startScanner()
            } catch (e: Exception) {
                e.printStackTrace()
            }



        }



    }
    fun startScanner(){
        // Parameters (default values)
        codeScanner.camera = CodeScanner.CAMERA_BACK // or CAMERA_FRONT or specific camera id
        codeScanner.formats = CodeScanner.ALL_FORMATS // list of type BarcodeFormat,
        // ex. listOf(BarcodeFormat.QR_CODE)
        codeScanner.autoFocusMode = AutoFocusMode.SAFE // or CONTINUOUS
        codeScanner.scanMode = ScanMode.SINGLE // or CONTINUOUS or PREVIEW
        codeScanner.isAutoFocusEnabled = true // Whether to enable auto focus or not
        codeScanner.isFlashEnabled = false // Whether to enable flash or not

        // Callbacks
        codeScanner.decodeCallback = DecodeCallback {
            runOnUiThread {
                Toast.makeText(applicationContext, "Scan result: ${it.text}", Toast.LENGTH_LONG).show()
            }
        }
        codeScanner.errorCallback = ErrorCallback { // or ErrorCallback.SUPPRESS
            runOnUiThread {
                Toast.makeText(applicationContext, "Camera initialization error: ${it.message}",
                    Toast.LENGTH_LONG).show()
            }
        }

        binding.scannerView.setOnClickListener {
            codeScanner.startPreview()
        }
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }
    }
