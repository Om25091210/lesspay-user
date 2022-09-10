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
import com.cu.crazypocket.databinding.FullnameBinding
import com.google.android.material.snackbar.Snackbar

class EnterFullNameActivity : AppCompatActivity() {
    lateinit var binding: FullnameBinding
    private lateinit var contact:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.fullname)
        val intent = intent
        if (intent != null) {
            contact = intent.getStringExtra("contact").toString()
        }
        binding.nextContinue.setOnClickListener(View.OnClickListener {
          if(binding.contact.text.toString().trim().length>0)
          {
              val i = Intent(applicationContext, EnterYourEmailActivity::class.java)
              i.putExtra("name",binding.contact.text.toString())
              i.putExtra("contact",contact)
              startActivity(i)
          }
            else{
              val snackBar = Snackbar.make(
                  it, "Please Enter Your Name",
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

        binding.contact.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int)
            {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

                Log.d("hi", "onTextChanged: ${binding.contact.text.toString().length}")
                if(binding.contact.text.toString().length==1)
                {
                    binding.nextContinue.setBackgroundColor(Color.GREEN)
                }
            }

            override fun afterTextChanged(s: Editable) {

            }
        })
    }
}
