package com.cu.crazypocket.views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.cu.crazypocket.Activity.MainActivity
import com.cu.crazypocket.Adapter.PageAdapter
import com.cu.crazypocket.R
import com.cu.crazypocket.databinding.ActivityOnBoardingBinding
import com.cu.crazypocket.models.PagerModel
import com.google.firebase.auth.FirebaseAuth
import java.util.*


class OnBoardingActivity : AppCompatActivity() {

    lateinit var mAuth: FirebaseAuth
    private  val list = listOf(PagerModel(
        R.drawable.screen_2_illustrator),
    PagerModel(R.drawable.screen_3_illustrator), PagerModel(
            R.drawable.screen_2_illustrator),PagerModel(R.drawable.screen_3_illustrator))

    lateinit var binding: ActivityOnBoardingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_on_boarding)
        mAuth = FirebaseAuth.getInstance()
        val adapter = PageAdapter(applicationContext,list)
        binding.apply {
            viewPager.adapter = adapter
           tabIndicator.setViewPager(viewPager)
            viewPager.addOnPageChangeListener(object : OnPageChangeListener {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    if (position==0){
                        heading1.text = getString(R.string.onBoarding_1)
                    }
                    if (position==1){
                        heading1.text = getString(R.string.onBoarding_2)
                    }
                    if (position==2){
                        heading1.text = getString(R.string.onBoarding_3)
                    }
                    if (position==3){
                        heading1.text = getString(R.string.onBoarding_4)
                    }
                }

                override fun onPageSelected(position: Int) {

                }
                override fun onPageScrollStateChanged(state: Int) {

                }
            })

           // preChecks()
        }


        binding.getStarted.setOnClickListener {
            val intent=Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }


    }

}
