package com.pivoto.codeassessment.features.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.pivoto.codeassessment.features.home.databinding.DetailsScreenFragmentBinding
import com.pivoto.codeassessment.fragments.BaseFragment
import com.pivoto.codeassessment.fragments.viewBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment: BaseFragment() {

    private var _binding: DetailsScreenFragmentBinding? = null
    private val binding
        get() = _binding!!

    override val viewModel: DetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = viewBinding()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.photoDetails.collectFromStart {
            Picasso.get()
                .load(it.photoUrl)
                .into(binding.detailsImageView)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
