package com.okky.ezhousing.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.okky.ezhousing.R
import com.okky.ezhousing.databinding.FragmentDetailTanahBinding
import com.okky.ezhousing.ui.home.DetailTanahFragmentArgs
import com.okky.ezhousing.ui.paymentpopup.PaymentPopupFragment

class DetailTanahFragment : Fragment() {

    private var _binding: FragmentDetailTanahBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailTanahBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showData()

//        binding?.btnBeli?.setOnClickListener (
//            Navigation.createNavigateOnClickListener(R.id.action_detailTanahFragment_to_paymentPopupFragment)
//        )

        binding?.btnBeli?.setOnClickListener {
            PaymentPopupFragment().show(parentFragmentManager, "payment_dialog")
        }

        binding?.btnPerkiraanHarga?.setOnClickListener (
            Navigation.createNavigateOnClickListener(R.id.action_detailTanahFragment_to_prediksiFragment)
        )
    }

    private fun showData() {
        val dataTanah = DetailTanahFragmentArgs.fromBundle(arguments as Bundle).tanah
        val dataDeskripsi = DetailTanahFragmentArgs.fromBundle(arguments as Bundle).description
        val dataPhoto = DetailTanahFragmentArgs.fromBundle(arguments as Bundle).photo

        binding?.tvTanahName?.text = dataTanah
        binding?.tvJenisTanah?.text = dataDeskripsi
        binding?.imgMain?.let {
            Glide.with(this)
                .load(dataPhoto)
                .into(it)
        }
    }
}