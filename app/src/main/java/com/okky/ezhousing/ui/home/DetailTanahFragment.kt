package com.okky.ezhousing.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.okky.ezhousing.R
import com.okky.ezhousing.database.Tanah
import com.okky.ezhousing.database.TanahRepository
import com.okky.ezhousing.databinding.FragmentDetailTanahBinding
import com.okky.ezhousing.ui.factory.ViewModelFactory
import com.okky.ezhousing.ui.notifications.NotificationsViewModel
import com.okky.ezhousing.ui.paymentpopup.PaymentPopupFragment

class DetailTanahFragment : Fragment() {

    private var _binding: FragmentDetailTanahBinding? = null
    private val binding get() = _binding

//    private lateinit var repo: TanahRepository

    private lateinit var notificationsViewModel : NotificationsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailTanahBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory : ViewModelFactory = ViewModelFactory.getInstance(requireActivity())
        notificationsViewModel = ViewModelProvider(this, factory)[NotificationsViewModel::class.java]

        val dataTanah = DetailTanahFragmentArgs.fromBundle(arguments as Bundle).tanah
        val dataDeskripsi = DetailTanahFragmentArgs.fromBundle(arguments as Bundle).description
        val dataPhoto = DetailTanahFragmentArgs.fromBundle(arguments as Bundle).photo
        val dataLon = DetailTanahFragmentArgs.fromBundle(arguments as Bundle).lon
        val dataLat = DetailTanahFragmentArgs.fromBundle(arguments as Bundle).lat
        val dataCreated = DetailTanahFragmentArgs.fromBundle(arguments as Bundle).createdAt
        val dataId = DetailTanahFragmentArgs.fromBundle(arguments as Bundle).id

        showData(dataTanah, dataDeskripsi, dataPhoto)

        binding?.btnBeli?.setOnClickListener {
            PaymentPopupFragment().show(parentFragmentManager, "payment_dialog")
        }

        binding?.btnPerkiraanHarga?.setOnClickListener (
            Navigation.createNavigateOnClickListener(R.id.action_detailTanahFragment_to_prediksiFragment)
        )

        binding?.btnTambahKeranjang?.setOnClickListener {
            val tanah = Tanah(dataId, dataPhoto, dataTanah, dataDeskripsi, dataLon.toDouble(), dataLat.toDouble(), dataCreated)
            notificationsViewModel.saveTanah(tanah)
//            if (::repo.isInitialized) {
                Toast.makeText(context, "Cek keranjang kamu", Toast.LENGTH_SHORT).show()
//            }
        }
    }

    private fun showData(dataTanah: String, dataDeskripsi: String, dataPhoto: String) {
        binding?.tvTanahName?.text = dataTanah
        binding?.tvJenisTanah?.text = dataDeskripsi
        binding?.imgMain?.let {
            Glide.with(this)
                .load(dataPhoto)
                .into(it)
        }
    }
}