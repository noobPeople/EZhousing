package com.okky.ezhousing.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.okky.ezhousing.api.response.ListStoryItem
import com.okky.ezhousing.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val homeViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[HomeViewModel::class.java]
        homeViewModel.setStoryData("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiJ1c2VyLTNQTkpUcThldERFREtqaUkiLCJpYXQiOjE2NTQxNDk0MzV9.liCkv87vuE7XK6LZNk6pcRezsQwv61RVbzW6U2VeXcQ")

        homeViewModel.story.observe(viewLifecycleOwner) {
            Log.e("errorlagi: ", it.toString())
            showRecyclerList(it)
        }

        homeViewModel.isLoading.observe(viewLifecycleOwner) {
            isLoading(it)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showRecyclerList(storyData: List<ListStoryItem>) {
        val layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        binding?.rvFavorit?.layoutManager = layoutManager
        val listTanahAdapter = ListTanahAdapter(storyData as ArrayList<ListStoryItem>)
        binding?.rvFavorit?.adapter = listTanahAdapter

        listTanahAdapter.setOnItemClickCallback(object : ListTanahAdapter.OnItemClickCallback {
            override fun onItemClicked(data: ListStoryItem) {
                showDetailedStory(data)
            }
        })
    }

    private fun showDetailedStory(data: ListStoryItem) {
        val toDetailTanahFragment = HomeFragmentDirections.actionNavigationHomeToDetailTanahFragment()
        toDetailTanahFragment.photo = data.photoUrl
        toDetailTanahFragment.tanah = data.name
        toDetailTanahFragment.description = data.description
        findNavController().navigate(toDetailTanahFragment)
    }

    private fun isLoading(isLoading: Boolean) {
        binding?.progressBar?.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}