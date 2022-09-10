package com.cu.crazypocket.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.cu.crazypocket.R
import com.cu.crazypocket.databinding.FragmentRateUsBinding

class RateUsFragment : DialogFragment() {
    lateinit var binding:FragmentRateUsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_rate_us, container, false)
        return binding.root
    }
}