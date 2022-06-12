package com.okky.ezhousing.ui.prediksi

import android.icu.math.BigDecimal
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.okky.ezhousing.databinding.FragmentPrediksiBinding
import com.okky.ezhousing.ml.Model
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.ByteBuffer
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*
import kotlin.math.roundToInt
import kotlin.math.roundToLong

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
            val total: FloatArray = counters()
//            val newTotal = (total[0].toDouble() * 100000000).roundToInt()
//            val newTotal = String.format("%.0f", total[0])
//            total.get()
            val dec = java.math.BigDecimal(total[0].toString())
            val totalan = dec.toEngineeringString()
            val newTotal = (total[0] * 10E45).roundToInt() * 1000
            Log.e("ini total: ", newTotal.toString())
            Log.e("ini totalan: ", totalan.toString())
            Log.e("ini total sebenernya: ", total[0].toString())
//            for (i in total.indices) {
//                Log.e("ini totalnya: ", total[i].toString())
//            }
            val strRupiah = newTotal.convertRupiah()

            TotalPopupFragment(strRupiah).show(parentFragmentManager, "payment_dialog")
        }
    }

    private fun counters(): FloatArray {
        val garasi = Integer.parseInt(binding?.tvNilaiGarasi?.text.toString())
        val luasBangunan = Integer.parseInt(binding?.tvNilaiLantai?.text.toString())
        val kmt = Integer.parseInt(binding?.tvNilaiKmt?.text.toString())
        val kmm = Integer.parseInt(binding?.tvNilaiKmm?.text.toString())
        val luasTanah = Integer.parseInt(binding?.tvNilaiLuas?.text.toString())

//        val garasi = binding?.tvNilaiGarasi?.text
//        val lantai = binding?.tvNilaiLantai?.text

//        return (garasi.toFloat() + lantai.toFloat() + kmt + kmm + luas) * 10000

        val byteBuffer : ByteBuffer = ByteBuffer.allocateDirect(5*4)
        byteBuffer.putFloat(luasBangunan.toFloat())
        byteBuffer.putFloat(luasTanah.toFloat())
        byteBuffer.putFloat(kmt.toFloat())
        byteBuffer.putFloat(kmm.toFloat())
        byteBuffer.putFloat(garasi.toFloat())

        val model = Model.newInstance(requireContext())

        val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 5), DataType.FLOAT32)
        inputFeature0.loadBuffer(byteBuffer)

        val outputs = model.process(inputFeature0)
        val outputFeature0 = outputs.outputFeature0AsTensorBuffer

        model.close()

        return outputFeature0.floatArray
    }

    private fun configureSlider() {
        val garasiSlider = binding?.sldrGarasi
        val luasBangunanSlider = binding?.sldrLantai
        val kmtSlider = binding?.sldrKmt
        val kmmSlider = binding?.sldrKmm
        val luasTanahSlider = binding?.sldrLuas

        garasiSlider?.apply {
            valueFrom = 0F
            valueTo = 27F
            addOnChangeListener { slider, value, fromUser ->
                binding?.tvNilaiGarasi?.text = value.roundToInt().toString()
            }
        }

        luasBangunanSlider?.apply {
            valueFrom = 0F
            valueTo = 100F
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

        luasTanahSlider?.apply {
            valueFrom = 0F
            valueTo = 100F
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