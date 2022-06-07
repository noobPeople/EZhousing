package com.okky.ezhousing.ui.paymentpopup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.okky.ezhousing.R
import com.okky.ezhousing.databinding.FragmentDetailTanahBinding
import com.okky.ezhousing.databinding.FragmentPaymentPopupBinding

class PaymentPopupFragment : Fragment() {

    private var _binding: FragmentPaymentPopupBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPaymentPopupBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.btnPerkiraanHarga?.setOnClickListener (
            Navigation.createNavigateOnClickListener(R.id.action_paymentPopupFragment_to_prediksiFragment)
        )
    }
}