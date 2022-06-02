package com.okky.ezhousing.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.okky.ezhousing.R
import com.okky.ezhousing.databinding.FragmentDetailTanahBinding
import com.okky.ezhousing.databinding.FragmentHomeBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailTanahFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailTanahFragment : Fragment() {

    private var _binding: FragmentDetailTanahBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailTanahBinding.inflate(inflater, container, false)
        return binding?.root!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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