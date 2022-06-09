package com.okky.ezhousing.ui.prediksi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.okky.ezhousing.databinding.FragmentPrediksiBinding
import java.text.NumberFormat
import java.util.*
import kotlin.math.roundToInt

class PrediksiFragment : Fragment() {

    private var _binding: FragmentPrediksiBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPrediksiBinding.inflate(inflater, container, false)
        return binding?.root!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureSlider()

        binding?.btnTotalHarga?.setOnClickListener {
            val total: Int = counters()
            val strRupiah = total.convertRupiah()

            TotalPopupFragment(strRupiah).show(parentFragmentManager, "payment_dialog")
        }
    }

    private fun counters(): Int {
        val garasi = Integer.parseInt(binding?.tvNilaiGarasi?.text.toString())
        val lantai = Integer.parseInt(binding?.tvNilaiLantai?.text.toString())
        val kmt = Integer.parseInt(binding?.tvNilaiKmt?.text.toString())
        val kmm = Integer.parseInt(binding?.tvNilaiKmm?.text.toString())
        val luas = Integer.parseInt(binding?.tvNilaiLuas?.text.toString())

        return (garasi + lantai + kmt + kmm + luas) * 10000
    }

    private fun configureSlider() {
        val garasiSlider = binding?.sldrGarasi
        val lantaiSlider = binding?.sldrLantai
        val kmtSlider = binding?.sldrKmt
        val kmmSlider = binding?.sldrKmm
        val luasSlider = binding?.sldrLuas

        garasiSlider?.apply {
            valueFrom = 0F
            valueTo = 1000F
            addOnChangeListener { slider, value, fromUser ->
                binding?.tvNilaiGarasi?.text = value.roundToInt().toString()
            }
        }

        lantaiSlider?.apply {
            valueFrom = 0F
            valueTo = 27F
            addOnChangeListener { slider, value, fromUser ->
                binding?.tvNilaiLantai?.text = value.roundToInt().toString()
            }
        }

        kmtSlider?.apply {
            valueFrom = 0F
            valueTo = 27F
            addOnChangeListener { slider, value, fromUser ->
                binding?.tvNilaiKmt?.text = value.roundToInt().toString()
            }
        }

        kmmSlider?.apply {
            valueFrom = 0F
            valueTo = 27F
            addOnChangeListener { slider, value, fromUser ->
                binding?.tvNilaiKmm?.text = value.roundToInt().toString()
            }
        }

        luasSlider?.apply {
            valueFrom = 0F
            valueTo = 5000F
            addOnChangeListener { slider, value, fromUser ->
                binding?.tvNilaiLuas?.text = value.roundToInt().toString()
            }
        }
    }

    private fun Any.convertRupiah(): String {
        val localId = Locale("in", "ID")
        val formatter = NumberFormat.getCurrencyInstance(localId)
        val strFormat = formatter.format(this)
        return strFormat
    }
}