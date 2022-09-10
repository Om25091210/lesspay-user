package com.cu.crazypocket.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.cu.crazypocket.R
import com.cu.crazypocket.databinding.FragmentMembershipPlanBinding

class MembershipPlanFragment : Fragment() {
    lateinit var binding: FragmentMembershipPlanBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =DataBindingUtil.inflate(inflater,R.layout.fragment_membership_plan, container, false)
        binding.apply {
            nextContinue.setOnClickListener {

                Navigation.findNavController(view!!).navigate(R.id.action_membershipPlanFragment_to_billingFragment);
            }
        }
        return binding.root
    }

}
