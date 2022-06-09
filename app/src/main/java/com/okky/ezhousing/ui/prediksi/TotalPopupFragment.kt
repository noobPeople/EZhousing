package com.okky.ezhousing.ui.prediksi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.okky.ezhousing.R
import com.okky.ezhousing.databinding.FragmentPrediksiBinding
import com.okky.ezhousing.databinding.FragmentTotalPopupBinding

//class TotalPopupFragment : Fragment() {
//
//    private var _binding: FragmentTotalPopupBinding? = null
//    private val binding get() = _binding
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        _binding = FragmentTotalPopupBinding.inflate(inflater, container, false)
//        return binding?.root!!
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        val dataTotal = TotalPopupFragmentArgs.fromBundle(arguments as Bundle).totalNumber
//
//        binding?.textView2?.text = dataTotal
//    }
//}

class TotalPopupFragment(var total: String) : DialogFragment() {

    private var _binding: FragmentTotalPopupBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTotalPopupBinding.inflate(inflater, container, false)

        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)

        binding?.textView2?.text = total

        binding?.btnClear?.setOnClickListener {
            dismiss()
        }

        binding?.btnHome?.setOnClickListener {
            val navHost = parentFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main)
            val c = navHost?.let { it1 -> NavHostFragment.findNavController(it1) }
            c?.navigate(R.id.action_prediksiFragment_to_navigation_home)
            dismiss()
        }

        return binding?.root
    }
}