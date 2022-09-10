
package com.cu.crazypocket.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.cu.crazypocket.FireBaseRealtimeDataBase.FireBaseRealTimeDataBase
import com.cu.crazypocket.R
import com.cu.crazypocket.databinding.ActivitySelectCityBinding

class SelectCityActivity : AppCompatActivity() {
    lateinit var fireBaseRealTimeDataBase: FireBaseRealTimeDataBase
    lateinit var binding:ActivitySelectCityBinding
    var a:Boolean=false
    var b:Boolean=false
    var c:Boolean=false
    var d:Boolean=false
    var e:Boolean=false
    var f:Boolean=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_select_city)
        fireBaseRealTimeDataBase = FireBaseRealTimeDataBase()
        for(i in FireBaseRealTimeDataBase.qrCodeStrings)
        {
            Log.d("valueOfI", "onCreate: ${i.length}")
        }
        binding.cardOne.setOnClickListener {
            if(a==false)
            {
                a=true
                b=false
                c=false
                d=false
                e=false
                f=false
                binding.cardOne.setBackgroundColor(ContextCompat.getColor(this,
                    com.google.android.libraries.places.R.color.quantum_bluegrey50));
                binding.cardTwo.setBackgroundColor(ContextCompat.getColor(this,
                    R.color.white));
                binding.cardThree.setBackgroundColor(ContextCompat.getColor(this,
                    R.color.white));
                binding.cardFour.setBackgroundColor(ContextCompat.getColor(this,
                    R.color.white));
                binding.cardFive.setBackgroundColor(ContextCompat.getColor(this,
                    R.color.white));
                binding.cardSix.setBackgroundColor(ContextCompat.getColor(this,
                    R.color.white));
                //quantum_bluegrey50
            }
        }
        binding.cardTwo.setOnClickListener {
            if(b==false)
            {
                binding.cardOne.setBackgroundColor(ContextCompat.getColor(this,
                   R.color.white));
                binding.cardTwo.setBackgroundColor(ContextCompat.getColor(this,
                    com.google.android.libraries.places.R.color.quantum_bluegrey50));
                binding.cardThree.setBackgroundColor(ContextCompat.getColor(this,
                    R.color.white));
                binding.cardFour.setBackgroundColor(ContextCompat.getColor(this,
                    R.color.white));
                binding.cardFive.setBackgroundColor(ContextCompat.getColor(this,
                    R.color.white));
                binding.cardSix.setBackgroundColor(ContextCompat.getColor(this,
                    R.color.white));

                a=false
                b=true
                c=false
                d=false
                e=false
                f=false
            }
        }
        binding.cardThree.setOnClickListener {
            if(c==false)
            {

                binding.cardOne.setBackgroundColor(ContextCompat.getColor(this,
                    R.color.white));
                binding.cardTwo.setBackgroundColor(ContextCompat.getColor(this,
                   R.color.white));
                binding.cardThree.setBackgroundColor(ContextCompat.getColor(this,
                    com.google.android.libraries.places.R.color.quantum_bluegrey50));
                binding.cardFour.setBackgroundColor(ContextCompat.getColor(this,
                    R.color.white));
                binding.cardFive.setBackgroundColor(ContextCompat.getColor(this,
                    R.color.white));
                binding.cardSix.setBackgroundColor(ContextCompat.getColor(this,
                    R.color.white));


                a=false
                b=false
                c=true
                d=false
                e=false
                f=false
            }

        }
        binding.cardFour.setOnClickListener {
            if(d==false)
            {


                binding.cardOne.setBackgroundColor(ContextCompat.getColor(this,
                    R.color.white));
                binding.cardTwo.setBackgroundColor(ContextCompat.getColor(this,
                    R.color.white));
                binding.cardThree.setBackgroundColor(ContextCompat.getColor(this,
                    R.color.white));
                binding.cardFour.setBackgroundColor(ContextCompat.getColor(this,
                    com.google.android.libraries.places.R.color.quantum_bluegrey50));
                binding.cardFive.setBackgroundColor(ContextCompat.getColor(this,
                    R.color.white));
                binding.cardSix.setBackgroundColor(ContextCompat.getColor(this,
                    R.color.white));

                a=false
                b=false
                c=false
                d=true
                e=false
                f=false
            }

        }
        binding.cardFive.setOnClickListener {
            if(e==false)
            {

                binding.cardOne.setBackgroundColor(ContextCompat.getColor(this,
                    R.color.white));
                binding.cardTwo.setBackgroundColor(ContextCompat.getColor(this,
                    R.color.white));
                binding.cardThree.setBackgroundColor(ContextCompat.getColor(this,
                    R.color.white));
                binding.cardFour.setBackgroundColor(ContextCompat.getColor(this,
                    R.color.white));
                binding.cardFive.setBackgroundColor(ContextCompat.getColor(this,
                    com.google.android.libraries.places.R.color.quantum_bluegrey50));
                binding.cardSix.setBackgroundColor(ContextCompat.getColor(this,
                    R.color.white));


                a=false
                b=false
                c=false
                d=false
                e=true
                f=false
            }

        }
        binding.cardSix.setOnClickListener {
            if(f==false)
            {
                binding.cardSix.setBackgroundColor(ContextCompat.getColor(this,
                    com.google.android.libraries.places.R.color.quantum_bluegrey50));

                binding.cardOne.setBackgroundColor(ContextCompat.getColor(this,
                    R.color.white));
                binding.cardTwo.setBackgroundColor(ContextCompat.getColor(this,
                    R.color.white));
                binding.cardThree.setBackgroundColor(ContextCompat.getColor(this,
                    R.color.white));
                binding.cardFour.setBackgroundColor(ContextCompat.getColor(this,
                    R.color.white));
                binding.cardFive.setBackgroundColor(ContextCompat.getColor(this,
                    R.color.white));
                binding.cardSix.setBackgroundColor(ContextCompat.getColor(this,
                    com.google.android.libraries.places.R.color.quantum_bluegrey50));

                a=false
                b=false
                c=false
                d=false
                e=false
                f=true
            }

        }

    }
}