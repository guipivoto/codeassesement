package com.pivoto.codeassessment.features.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.pivoto.codeassessment.features.home.databinding.HomeFragmentBinding
import com.pivoto.codeassessment.fragments.BaseFragment
import com.pivoto.codeassessment.fragments.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private var _binding: HomeFragmentBinding? = null
    private val binding: HomeFragmentBinding
        get() = _binding!!

    override val viewModel: HomeViewModel by viewModels()

    private var _adapter: HomeFragmentListAdapter? = null
    private val adapter: HomeFragmentListAdapter
        get() = _adapter!!

    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            binding.homeScreenToolbar.let {
                if (dy > 0) {
                    it.translationY -= dy
                } else if ( dy < 0) {
                    if (it.translationY.toInt() in -it.measuredHeight until 0) {
                        // If still visible, follows the scroll movement
                        it.translationY = (it.translationY - dy).coerceAtMost(0f)
                    } else {
                        // If no longer visible, just display it
                        it.translationY = 0f
                    }
                }
            }
        }
    }

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
        binding.homeScreenToolbar.addOnLayoutChangeListener { _, _, top, _, bottom, _, _, _, _ ->
            val toolbarHeight = bottom - top
            if (toolbarHeight > 0) {
                if (toolbarHeight != adapter.headerHeight) {
                    adapter.headerHeight = toolbarHeight
                    adapter.notifyItemChanged(0)
                }
            }
        }

        _adapter = HomeFragmentListAdapter()
        adapter.itemClickListener = { viewModel.handleItemClicked(it) }

        binding.homeRecyclerView.adapter = adapter
        binding.homeRecyclerView.addOnScrollListener(scrollListener)

        viewModel.listItems.collectFromStart {
            adapter.itemList = it
            adapter.notifyItemRangeChanged(0, it.size)
        }

        viewModel.photoTitle.collectFromStart {
            if (it != null) {
                binding.photoTitle.text = it
            }
        }
    }

    override fun onDestroyView() {
        binding.homeRecyclerView.removeOnScrollListener(scrollListener)
        _adapter = null
        _binding = null
        super.onDestroyView()
    }
}
