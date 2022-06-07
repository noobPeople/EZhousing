package com.okky.ezhousing.ui.prediksi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.okky.ezhousing.R
import com.okky.ezhousing.databinding.FragmentPrediksiBinding
import com.okky.ezhousing.databinding.FragmentTotalPopupBinding

class TotalPopupFragment : Fragment() {

    private var _binding: FragmentTotalPopupBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTotalPopupBinding.inflate(inflater, container, false)
        return binding?.root!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataTotal = TotalPopupFragmentArgs.fromBundle(arguments as Bundle).totalNumber

        binding?.textView2?.text = dataTotal
    }
}