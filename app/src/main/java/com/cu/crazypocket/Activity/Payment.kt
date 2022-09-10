package com.cu.crazypocket.Activity

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.cu.crazypocket.R
import com.cu.crazypocket.databinding.ActivityPaymentBinding
import java.util.*


class Payment : AppCompatActivity() {

    //Request Codes
    private val UPI_REQUEST_CODE = 123
//    private val GOOGLE_PAY = 1
//    private val PAYTM = 2
//    private val PHONE_PE = 3
//    private val BHARAT_PE = 4
//    private val AMAZON_PAY_UPI = 5
//    private val AIRTEL_PAY = 6
//    private val MERCHANT_TYPE = 1


    private val googlePayPackage = "com.google.android.apps.nbu.paisa.user"
    private val bhimUpiPackage = "in.org.npci.upiapp"
    private val googlePayBaseString =
        "upi://pay?pa=9988890048@okbizaxis&pn=N.K MEDICOS&mc=5912&aid=uGICAgIC3joqcAQ&tr=BCR2DN6TR6SKRMR2"
    private val paytmBaseString =
        "upi://pay?pa=paytmqr2810050501011gj3dtvouykl@paytm&pn=Paytm Merchant&mc=5499&mode=02&orgid=000000&paytmqr=2810050501011GJ3DTVOUYKL&sign=MEQCID5V1zKeK5cPAy1nQFPRffDc1VicVDEje9iL96JgZfiUAiB+CgEV6kbnm49jJ5G9yNi1aZj32eJibgjWkyf3EzDq4g=="
    private val phonePeBaseString =
        "upi://pay?mode=02&pa=Q355808006@ybl&purpose=00&mc=0000&pn=PhonePeMerchant&orgid=180001&sign=MEUCIQCgOK+Hp9+axAqSCvciooSDb7vWekq7SxgvHs09NCNwLwIgdgg7FDGQEySDDAuNM6cCKG4V+LvZ4tbTA2ZmCWpo0+Y="
    private val bharatPeBaseString =
        "upi://pay?pa=BHARATPE.0853260667@icici&pn=BharatPe Merchant&cu=INR&tn=Verified Merchant"
    private val amazonPayBaseString = "upi://pay?pa=AMZN0000127015@apl&pn=AmazonPay Merchant"
    private val airtelPayBaseString =
        "upi://pay?pa=a163545q@mairtel&pn=AirtelM a163545q@mairtel&tn=Payment made to Merchant&mc=7299"

    private var baseUpiString = googlePayBaseString


    lateinit var binding: ActivityPaymentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_payment)

        binding.apply {
            proceedButton.setOnClickListener {
//                    if (MERCHANT_TYPE == GOOGLE_PAY) {
//                        Toast.makeText(applicationContext, "This Merchant does not accecpt google pay", Toast.LENGTH_SHORT).show()
//                        return@setOnClickListener
//
//                    }
                    if (!isAppInstalled(googlePayPackage)) {
                        Toast.makeText(applicationContext, "Google pay not installed ", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener

                    }
                    else if (!isUpiReady(googlePayPackage)) {
                        Toast.makeText(applicationContext, "Google pay is not upi ready", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }

                    baseUpiString = "$baseUpiString&am="
                    baseUpiString = baseUpiString + amount.getText().toString().trim()
                    Log.v(
                        Payment::class.java.simpleName,
                        "amount " + amount.getText().toString()
                    )
                    Log.v(MainActivity::class.java.simpleName, "final string $baseUpiString")
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(baseUpiString)
                    intent.setPackage(googlePayPackage)
                    startActivityForResult(intent, UPI_REQUEST_CODE)
                }


        }



    }
    fun isAppInstalled(packageName: String?): Boolean {
        val pm = packageManager
        try {
            pm.getPackageInfo(packageName!!, PackageManager.GET_ACTIVITIES)
            return true
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return false
    }

    fun isUpiReady(packageName: String): Boolean {
        var appUpiReady = false
        val upiIntent = Intent(Intent.ACTION_VIEW, Uri.parse("upi://pay"))
        val pm = packageManager
        val upiActivities = pm.queryIntentActivities(upiIntent, 0)
        for (a in upiActivities) {
            if (a.activityInfo.packageName == packageName) {
                appUpiReady = true
            }
        }
        return appUpiReady
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == UPI_REQUEST_CODE) {
            if (RESULT_OK == resultCode || resultCode == 11) {
                if (data != null) {
                    val trxt = data.getStringExtra("response")
                    Log.d("UPI", "onActivityResult: $trxt")
                    val dataList: ArrayList<String> = ArrayList()
                    if (trxt != null) {
                        dataList.add(trxt)
                    }
                    upiPaymentDataOperation(dataList)
                } else {
                    Log.d("UPI", "onActivityResult: " + "Return data is null")
                    val dataList: ArrayList<String> = ArrayList()
                    dataList.add("nothing")
                    upiPaymentDataOperation(dataList)
                }
            } else {
                Log.d(
                    "UPI",
                    "onActivityResult: " + "Return data is null"
                ) //when user simply back without payment
                val dataList: ArrayList<String> = ArrayList()
                dataList.add("nothing")
                upiPaymentDataOperation(dataList)
            }
        }
    }
    fun isConnectionAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val netInfo = connectivityManager.activeNetworkInfo
            if (netInfo != null && netInfo.isConnected
                && netInfo.isConnectedOrConnecting
                && netInfo.isAvailable
            ) {
                return true
            }
        }
        return false
    }

    private fun upiPaymentDataOperation(data: ArrayList<String>) {
        if (isConnectionAvailable(this)) {
            var str = data[0]
            Log.d("UPIPAY", "upiPaymentDataOperation: $str")
            var paymentCancel = ""
            if (str == null) str = "discard"
            var status = ""
            var approvalRefNo = ""
            val response = str.split("&").toTypedArray()
            for (i in response.indices) {
                val equalStr = response[i].split("=").toTypedArray()
                if (equalStr.size >= 2) {
                    if (equalStr[0].lowercase(Locale.getDefault()) == "Status".lowercase(Locale.getDefault())) {
                        status = equalStr[1].lowercase(Locale.getDefault())
                    } else if (equalStr[0].lowercase(Locale.getDefault()) == "ApprovalRefNo".lowercase(
                            Locale.getDefault()
                        ) || equalStr[0].lowercase(Locale.getDefault()) == "txnRef".lowercase(
                            Locale.getDefault()
                        )
                    ) {
                        approvalRefNo = equalStr[1]
                    }
                } else {
                    paymentCancel = "Payment cancelled by user."
//                    resultTv.setText("Payment cancelled by user.")   //yaha failed screen
                }
            }
            if (status == "success") {
                //Code to handle successful transaction here.
                Toast.makeText(this, "Transaction successful.", Toast.LENGTH_SHORT)
                    .show()
                Log.d("UPI", "responseStr: $approvalRefNo")
//                resultTv.setText("Transaction Successful")           ///yaaha success screen show krwani hai
            } else if ("Payment cancelled by user." == paymentCancel) {
                Toast.makeText(this, "Payment cancelled by user.", Toast.LENGTH_SHORT)
                    .show()
//                resultTv.setText("Payment cancelled by user.")   //yaha failed screen
            } else {
                Toast.makeText(
                    this,
                    "Transaction failed.Please try again",
                    Toast.LENGTH_SHORT
                ).show()
//                resultTv.setText("Transaction failed.Please try again")  //yaha failed screen
            }
        } else {
            Toast.makeText(
                this,
                "Internet connection is not available. Please check and try again",
                Toast.LENGTH_SHORT
            ).show()
        }
    }


}