    package com.example.spaceinfo.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.spaceinfo.R
import com.example.spaceinfo.fragments.adapters.IntroViewPagerAdapter
import com.example.spaceinfo.fragments.view_models.IntroViewModel
import com.example.spaceinfo.getSharedPref
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_intro.view.*

    class IntroFragment : Fragment() {
    private  val viewModel by viewModels<IntroViewModel>()

    lateinit var navController: NavController
    private val adapter = IntroViewPagerAdapter()
    private lateinit var shared : SharedPreferences

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_intro, container, false)
        shared = requireActivity().getSharedPref(R.string.shared_preference)
        adapter.items = viewModel.introItems
        view.intro_view_pager.adapter = adapter
        TabLayoutMediator ( view.viewpager_tabs, view.intro_view_pager){ _, _ -> }.attach()

        viewModel.currentPage.observe(viewLifecycleOwner, Observer {  view.intro_view_pager.currentItem = it })
        viewModel.nextBtnText.observe(viewLifecycleOwner, Observer { view.intro_next.text = getString(it) })
        view.intro_view_pager.isUserInputEnabled = false

        view.intro_next.setOnClickListener {
            viewModel.onNextBtnClick()
            if (viewModel.currentPage.value  == 3){
                with ( shared.edit()){ putBoolean(getString(R.string.is_first_time), false).apply() }
                navController.navigate(R.id.action_introFragment_to_themeFragment)
            }
        }
        view.back_btn.setOnClickListener { viewModel.onBackBtnClick() }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }
}