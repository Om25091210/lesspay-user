package com.cu.crazypocket.Activity

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cu.crazypocket.Constant
import com.cu.crazypocket.FireBaseRealtimeDataBase.UserDataBase
import com.cu.crazypocket.ViewModel.MyViewModel
import com.cu.crazypocket.ViewModelFactory.MyViewModelFactory
import com.cu.crazypocket.R
import com.cu.crazypocket.Repository.Repository
import com.cu.crazypocket.databinding.EnterYourDobBinding
import com.google.android.material.snackbar.Snackbar

class EnterDOBActivity : AppCompatActivity() {

    lateinit var binding:EnterYourDobBinding
    lateinit var contact:String
    lateinit var name:String
    lateinit var email:String
    lateinit var repository:Repository
    lateinit var fireBaseRealTimeDataBase:UserDataBase
    lateinit var myViewModel: MyViewModel
    var value:Boolean=false
    var editText = arrayOfNulls<EditText>(6)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.enter_your_dob)
        val intent = intent
        if (intent != null) {
            contact = intent.getStringExtra("contact").toString()
            name = intent.getStringExtra("name").toString()
            email = intent.getStringExtra("email").toString()

        }
        binding.nextContinue.setOnClickListener(View.OnClickListener {
            if (binding.edt1.text.isEmpty()
                || binding.edt2.text.isEmpty()
                || binding.edt3.text.isEmpty()
                || binding.edt4.text.isEmpty()
                || binding.edt5.text.isEmpty()
                || binding.edt6.text.isEmpty()
            ) {
                val snackBar = Snackbar.make(
                    it, "Please Enter DOB",
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
            else
            {
                binding.loader.visibility=View.VISIBLE
                binding.nextContinue.visibility=View.GONE
                val dob=binding.edt1.text.toString()+"/"+ binding.edt2.text.toString()+"/"+binding.edt3.text.toString()+
                        binding.edt4.text.toString() +
                        binding.edt5.text.toString() +
                        binding.edt6.text.toString()
                Log.d("dobb", "onCreate: $dob")


                fireBaseRealTimeDataBase= UserDataBase()
                repository= Repository(fireBaseRealTimeDataBase)
                myViewModel= ViewModelProvider(this, MyViewModelFactory(repository))[MyViewModel::class.java]
                myViewModel.insertIntoDataBase(name,email,dob,contact,applicationContext)
                Log.d("myvalue", "onCreate: ${Constant.value}")

                fireBaseRealTimeDataBase.liveData.observe(this, Observer{
                    if(it==true)
                    {
                        binding.loader.visibility=View.VISIBLE
                        binding.nextContinue.visibility=View.GONE
                      /*  val i = Intent(applicationContext, Home::class.java)
                        startActivity(i)
                        finish()*/
                        binding.loader.visibility=View.GONE
                        binding.nextContinue.visibility=View.VISIBLE
                    }
                    else
                    {
                        binding.loader.visibility=View.GONE
                        binding.nextContinue.visibility=View.VISIBLE
                    }

                })
            }
        })
        editText[0] = findViewById<View>(R.id.edt1) as EditText
        editText[1] = findViewById<View>(R.id.edt2) as EditText
        editText[2] = findViewById<View>(R.id.edt3) as EditText
        editText[3] = findViewById<View>(R.id.edt4) as EditText
        editText[4] = findViewById<View>(R.id.edt5) as EditText
        editText[5] = findViewById<View>(R.id.edt6) as EditText

        editText[0]!!.requestFocus()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            editText[0]!!.addOnUnhandledKeyEventListener { v: View?, event: KeyEvent ->
                Log.i("UnhandledKeyEvent", "" + event)
                if (event.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_DEL) {
                    if (editText[0]!!.isFocused) {
                        Log.i("et[1]", "Focused")
                    }
                    if (editText[1]!!.isFocused) {
                        editText[1]!!.clearFocus()
                        editText[0]!!.requestFocus()
                        editText[0]!!.setSelection(editText[0]!!.text.length)
                    }
                    if (editText[2]!!.isFocused) {
                        editText[2]!!.clearFocus()
                        editText[1]!!.requestFocus()
                        editText[1]!!.setSelection(editText[1]!!.text.length)
                    }
                    if (editText[3]!!.isFocused) {
                        editText[3]!!.clearFocus()
                        editText[2]!!.requestFocus()
                        editText[2]!!.setSelection(editText[2]!!.text.length)
                    }
                    if (editText[4]!!.isFocused) {
                        editText[4]!!.clearFocus()
                        editText[3]!!.requestFocus()
                        editText[3]!!.setSelection(editText[3]!!.text.length)
                    }
                    if (editText[5]!!.isFocused) {
                        editText[5]!!.clearFocus()
                        editText[4]!!.requestFocus()
                        editText[4]!!.setSelection(editText[4]!!.text.length)
                    }
                }
                false
            }
        }
        else {
            editText[0]!!.setOnKeyListener { v: View?, keyCode: Int, event: KeyEvent ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL) {
                    Log.i("Hardware", "Event detected $event")
                    Log.i("Hardware", "Key detected $keyCode")
                }
                false
            }
            editText[1]!!.setOnKeyListener { v: View?, keyCode: Int, event: KeyEvent ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL) {
                    Log.i("Hardware", "Event detected $event")
                    Log.i("Hardware", "Key detected $keyCode")
                    editText[1]!!.clearFocus()
                    editText[0]!!.requestFocus()
                    editText[0]!!.setSelection(editText[0]!!.text.length)
                }
                false
            }
            editText[2]!!.setOnKeyListener { v: View?, keyCode: Int, event: KeyEvent ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL) {
                    Log.i("Hardware", "Event detected $event")
                    Log.i("Hardware", "Key detected $keyCode")
                    editText[2]!!.clearFocus()
                    editText[1]!!.requestFocus()
                    editText[1]!!.setSelection(editText[1]!!.text.length)
                }
                false
            }
            editText[3]!!.setOnKeyListener { v: View?, keyCode: Int, event: KeyEvent ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL) {
                    Log.i("Hardware", "Event detected $event")
                    Log.i("Hardware", "Key detected $keyCode")
                    editText[3]!!.clearFocus()
                    editText[2]!!.requestFocus()
                    editText[2]!!.setSelection(editText[2]!!.text.length)
                }
                false
            }
            editText[4]!!.setOnKeyListener { v: View?, keyCode: Int, event: KeyEvent ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL) {
                    Log.i("Hardware", "Event detected $event")
                    Log.i("Hardware", "Key detected $keyCode")
                    editText[4]!!.clearFocus()
                    editText[3]!!.requestFocus()
                    editText[3]!!.setSelection(editText[3]!!.text.length)
                }
                false
            }
            editText[5]!!.setOnKeyListener { v: View?, keyCode: Int, event: KeyEvent ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL) {
                    Log.i("Hardware", "Event detected $event")
                    Log.i("Hardware", "Key detected $keyCode")
                    editText[5]!!.clearFocus()
                    editText[4]!!.requestFocus()
                    editText[4]!!.setSelection(editText[4]!!.text.length)
                }
                false
            }
        }


        editText[0]!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                Log.i("Char", "editText[0] $s")
                Log.i("start", "editText[0] $start")
                Log.i("count", "editText[0] $count")
                Log.i("after", "editText[0] $after")
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (count > 0) {
                    editText[0]!!.clearFocus()
                    editText[1]!!.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable) {
                Log.i("Char", "editText[0] After $s")
            }
        })
        editText[1]!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                Log.i("Char", "editText[1] $s")
                Log.i("start", "editText[1] $start")
                Log.i("count", "editText[1] $count")
                Log.i("after", "editText[1] $after")
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (count > 0) {
                    editText[1]!!.clearFocus()
                    editText[2]!!.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })
        editText[2]!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                Log.i("Char length", "editText[2] " + s.length)
                Log.i("Char", "editText[2] $s")
                Log.i("start", "editText[2] $start")
                Log.i("count", "editText[2] $count")
                Log.i("after", "editText[2] $after")
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                Log.i("Char length", "editText[2] " + s.length)
                if (count > 0) {
                    editText[2]!!.clearFocus()
                    editText[3]!!.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable) {
                Log.i("Char length", "editText[2] " + s.length)
            }
        })
        editText[3]!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                Log.i("Char", "editText[3] $s")
                Log.i("start", "editText[3] $start")
                Log.i("count", "editText[3] $count")
                Log.i("after", "editText[3] $after")
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (count > 0) {
                    editText[3]!!.clearFocus()
                    editText[4]!!.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })
        editText[4]!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                Log.i("Char", "editText[4] $s")
                Log.i("start", "editText[4] $start")
                Log.i("count", "editText[4] $count")
                Log.i("after", "editText[4] $after")
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (count > 0) {
                    editText[4]!!.clearFocus()
                    editText[5]!!.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })
        editText[5]!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                Log.i("Char", "editText[5] $s")
                Log.i("start", "editText[5] $start")
                Log.i("count", "editText[5] $count")
                Log.i("after", "editText[5] $after")
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (count > 0) {
                    // editText[5].clearFocus();
                    //Intent to Signup Fragment here
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })

    }
}
