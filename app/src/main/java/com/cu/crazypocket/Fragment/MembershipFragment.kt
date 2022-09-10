package com.cu.crazypocket.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.cu.crazypocket.R
import com.cu.crazypocket.databinding.FragmentMembershipBinding

class MembershipFragment : Fragment() {
    lateinit var binding:FragmentMembershipBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_membership, container, false)
        binding.renewMembership.setOnClickListener {
           // Navigation.findNavController(it).navigate(R.id.action_membershipFragment_to_membershipPlanFragment);
        }
        return binding.root
    }


}
