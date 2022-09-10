package com.cu.crazypocket.Fragment

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.cu.crazypocket.R
import com.cu.crazypocket.databinding.FragmentEnBinding
import kotlin.system.exitProcess


class EnFragment : DialogFragment() {


    lateinit var binding:FragmentEnBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_en, container, false)
        binding.apply {
            close.setOnClickListener {
                exitProcess(0);
            }
        }
        return  binding.root
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        if(dismiss()!=null)
        {
            exitProcess(0);
            dismiss()
        }

    }


}
