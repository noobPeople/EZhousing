package com.okky.ezhousing.ui.paymentpopup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.okky.ezhousing.R
import com.okky.ezhousing.databinding.FragmentDetailTanahBinding
import com.okky.ezhousing.databinding.FragmentPaymentPopupBinding

class PaymentPopupFragment : DialogFragment() {

    private var _binding: FragmentPaymentPopupBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPaymentPopupBinding.inflate(inflater, container, false)

        dialog!!.window?.setBackgroundDrawableResource(android.R.color.transparent)

        binding?.btnPerkiraanHarga?.setOnClickListener {
            val navHost = parentFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main)
            val c = navHost?.let { it1 -> NavHostFragment.findNavController(it1) }
            c?.navigate(R.id.action_detailTanahFragment_to_prediksiFragment)
            dismiss()
        }

        binding?.btnClear?.setOnClickListener {
            dismiss()
        }

        return binding?.root
    }
}