package com.okky.ezhousing.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.okky.ezhousing.api.response.ListStoryItem
import com.okky.ezhousing.databinding.FragmentNotificationsBinding
import com.okky.ezhousing.ui.factory.ViewModelFactory

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!

    private lateinit var notificationsViewModel : NotificationsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory : ViewModelFactory = ViewModelFactory.getInstance(requireActivity())
        notificationsViewModel = ViewModelProvider(this, factory)[NotificationsViewModel::class.java]

        notificationsViewModel.getKeranjangTanah().observe(viewLifecycleOwner) {
            val convert = it.map { data ->
                ListStoryItem(
                    photoUrl = data.photo,
                    createdAt = data.created_at,
                    name = data.name,
                    description = data.description,
                    lon = data.lon,
                    lat = data.lat,
                    id = data.id
                )
            }
            showRecyclerList(convert as List<ListStoryItem>)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showRecyclerList(storyData: List<ListStoryItem>) {
        val layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        binding.rvKeranjang.layoutManager = layoutManager
        val listTanahAdapter = ListKeranjangAdapter(storyData as ArrayList<ListStoryItem>)
        binding.rvKeranjang.adapter = listTanahAdapter

        listTanahAdapter.setOnItemClickCallback(object : ListKeranjangAdapter.OnItemClickCallback {
            override fun onItemClicked(data: ListStoryItem) {
                showDetailedStory(data)
            }
        })
    }

    private fun showDetailedStory(data: ListStoryItem) {
        val toDetailTanahFragment = NotificationsFragmentDirections.actionNavigationNotificationsToDetailTanahFragment()
        toDetailTanahFragment.photo = data.photoUrl
        toDetailTanahFragment.tanah = data.name
        toDetailTanahFragment.description = data.description
        toDetailTanahFragment.lon = data.lon.toString()
        toDetailTanahFragment.lat = data.lat.toString()
        toDetailTanahFragment.id = data.id
        findNavController().navigate(toDetailTanahFragment)
    }
}