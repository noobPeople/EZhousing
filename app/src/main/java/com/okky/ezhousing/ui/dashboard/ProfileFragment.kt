package com.okky.ezhousing.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.okky.ezhousing.R
import com.okky.ezhousing.databinding.FragmentProfileBinding
import com.okky.ezhousing.model.UserModel
import com.okky.ezhousing.preference.UserPreference
import com.okky.ezhousing.ui.auth.login.LoginActivity

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var userPreference: UserPreference
    private lateinit var userModel: UserModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val profileViewModel =
            ViewModelProvider(this)[ProfileViewModel::class.java]

        userPreference = UserPreference(requireActivity())
        userModel = userPreference.getUser()

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.profileEdit.setOnClickListener (
            Navigation.createNavigateOnClickListener(R.id.action_navigation_profile_to_editProfileFragment)
        )

        binding.tvProfileLogout.setOnClickListener {
            userPreference.logoutUser()
            view?.findNavController()?.navigate(R.id.action_navigation_profile_to_loginActivity)
            activity?.finish()
//            val fragmentManager = parentFragmentManager;
//            val fragmentTransaction = fragmentManager.beginTransaction();
//
//            fragmentTransaction.remove(this);
//            fragmentTransaction.commit();
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}