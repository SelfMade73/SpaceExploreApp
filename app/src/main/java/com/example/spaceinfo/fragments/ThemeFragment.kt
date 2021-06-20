package com.example.spaceinfo.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.spaceinfo.*
import com.example.spaceinfo.fragments.adapters.CardThemeAdapter
import com.example.spaceinfo.fragments.adapters.OnRecyclerViewItemClickListener
import com.example.spaceinfo.fragments.view_models.Internet
import com.example.spaceinfo.fragments.view_models.LocalStorage
import com.example.spaceinfo.fragments.view_models.ThemeViewModel
import com.example.spaceinfo.models.ThemeItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.theme_fragment.*
import kotlinx.android.synthetic.main.theme_fragment.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ThemeFragment :
    Fragment() , OnRecyclerViewItemClickListener, SwipeRefreshLayout.OnRefreshListener{

    private lateinit var cardThemeAdapter : CardThemeAdapter
    private lateinit var navController: NavController
    private var twice = false
    private val viewModel by viewModels<ThemeViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.theme_fragment, container, false)
        view.space_cards.layoutManager = LinearLayoutManager( context )
        cardThemeAdapter = CardThemeAdapter(this)
        view.space_cards.adapter = cardThemeAdapter

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) { OnBackPressedCallback() }

        viewModel.cardLiveData.observe(viewLifecycleOwner, Observer { cardThemeAdapter.data = it })

        viewModel.isProcess.observe( viewLifecycleOwner, Observer { swipe_refresh.isRefreshing = it})
        viewModel.msg.observe(viewLifecycleOwner, Observer { context?.toast( it ) })

        view.swipe_refresh.setOnRefreshListener (this)

        view.appbar.setOnMenuItemClickListener { menuItem ->
            if ( menuItem.itemId == R.id.favorite ){
                    viewModel.loadingStrategy = if ( viewModel.loadingStrategy == Internet) {
                        LocalStorage
                    } else {
                        Internet
                    }
                    view.swipe_refresh.space_cards.smoothScrollToPosition(0)
                    onRefresh()
                    return@setOnMenuItemClickListener true
            }
            return@setOnMenuItemClickListener false
        }

        viewModel.heartIconResource.observe(viewLifecycleOwner, Observer {
            view.appbar.menu.findItem(R.id.favorite).setIcon(it)
        } )

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if ( viewModel.loadingStrategy == LocalStorage){
            viewModel.updateThemes()
        }
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    override fun onItemClick( item : ThemeItem ) {
        val action = ThemeFragmentDirections.actionThemeFragmentToNasaInfoFragment(item)
        navController.navigate(action);
    }

    override fun onRefresh() { viewModel.updateThemes() }

    private fun OnBackPressedCallback(){
        if ( twice ){
            requireActivity().finish()
        }
        context?.toast(R.string.exit)
        twice = true
        viewModel.viewModelScope.launch(Dispatchers.Main){
            delay(2000)
            twice = false
        }
    }

}