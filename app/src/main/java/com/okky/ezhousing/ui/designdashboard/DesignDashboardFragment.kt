package com.okky.ezhousing.ui.designdashboard

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.okky.ezhousing.R

class DesignDashboardFragment : Fragment() {

    companion object {
        fun newInstance() = DesignDashboardFragment()
    }

    private lateinit var viewModel: DesignDashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.design_dashboard_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DesignDashboardViewModel::class.java)
        // TODO: Use the ViewModel
    }

}