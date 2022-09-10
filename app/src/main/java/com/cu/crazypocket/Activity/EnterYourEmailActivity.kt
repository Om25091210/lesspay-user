package com.cu.crazypocket.Activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.cu.crazypocket.R
import com.cu.crazypocket.Regex.MyRegex
import com.cu.crazypocket.databinding.EnterYourEmailBinding
import com.google.android.material.snackbar.Snackbar

class EnterYourEmailActivity : AppCompatActivity() {
    lateinit var binding: EnterYourEmailBinding
    lateinit var contact:String
    lateinit var name:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.enter_your_email)
        val intent = intent
        if (intent != null) {
            contact = intent.getStringExtra("contact").toString()
            name = intent.getStringExtra("name").toString()

        }
        binding.email.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int)
            {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

                Log.d("hi", "onTextChanged: ${binding.email.text.toString().length}")
                if(binding.email.text.toString().length==1)
                {
                    binding.nextContinue.setBackgroundColor(Color.GREEN)
                }
            }

            override fun afterTextChanged(s: Editable) {

            }
        })
        binding.nextContinue.setOnClickListener(View.OnClickListener {

            val value=MyRegex.email(binding.email.text.toString())
            if(value){
                val i = Intent(applicationContext,
                    EnterDOBActivity::class.java)
                i.putExtra("name",name)
                i.putExtra("contact",contact)
                i.putExtra("email",binding.email.text.toString().trim())
                startActivity(i)
            }
            else if(binding.email.text.toString().trim().isEmpty())
            {
                val snackBar = Snackbar.make(
                    it, "Please Enter Your Email",
                    Snackbar.LENGTH_LONG
                ).setAction("Action", null)
                snackBar.setActionTextColor(Color.RED)
                val snackBarView = snackBar.view
                snackBarView.setBackgroundColor(Color.RED)
                val textView =
                    snackBarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
                textView.setTextColor(Color.WHITE)
                snackBar.show()
            }
            else{
                val snackBar = Snackbar.make(
                    it, "Please Enter Valid Email",
                    Snackbar.LENGTH_LONG
                ).setAction("Action", null)
                snackBar.setActionTextColor(Color.RED)
                val snackBarView = snackBar.view
                snackBarView.setBackgroundColor(Color.RED)
                val textView =
                    snackBarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
                textView.setTextColor(Color.WHITE)
                snackBar.show()
            }
        })
    }
}
