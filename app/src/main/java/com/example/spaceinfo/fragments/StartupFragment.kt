package com.example.spaceinfo.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.spaceinfo.R
import com.example.spaceinfo.getSharedPref
import kotlinx.android.synthetic.main.fragment_startup.view.*

class StartupFragment : Fragment() {
    companion object{
        const val TIMEOUT = 3500L
    }
    private lateinit var navController: NavController
    var isFirstTime : Boolean = true
    private lateinit var shared : SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        shared = requireActivity().getSharedPref(R.string.shared_preference)
        isFirstTime = shared.getBoolean(getString(R.string.is_first_time),true)
        val view =  inflater.inflate(R.layout.fragment_startup, container, false)
        view.main_img.alpha = 0f;
        view.main_img.animate().setDuration(TIMEOUT).alpha(1f).withEndAction {
            navController.navigate( if (isFirstTime) R.id.action_startupFragment_to_introFragment else
                R.id.action_startupFragment_to_themeFragment)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }
}