package com.example.spaceinfo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.spaceinfo.R
import com.example.spaceinfo.databinding.NasaInfoFragmentBinding
import com.example.spaceinfo.fragments.view_models.NasaInfoViewModel
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.nasa_info_fragment.view.*

@AndroidEntryPoint
class NasaInfoFragment : Fragment() {

    private val viewModel: NasaInfoViewModel by viewModels()
    private val args : NasaInfoFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding :NasaInfoFragmentBinding = DataBindingUtil.inflate( inflater, R.layout.nasa_info_fragment, container,false)
        viewModel.item = args.item
        binding.viewModel = viewModel
        binding.root.save_item_btn.setOnClickListener {
            viewModel.saveOrDeleteCard().invokeOnCompletion{
                if ( it == null ){
                    viewModel.updateSaveIcon()
                }
                Toast.makeText(context, if (it == null) {R.string.success} else {R.string.failed}, Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.imgRes.observe( viewLifecycleOwner, Observer {
            binding.root.save_item_btn.setBackgroundResource(it)
        })

        Picasso.get().load(viewModel.item?.url).memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).into(binding.nasaImage)
        return binding.root
    }
}
