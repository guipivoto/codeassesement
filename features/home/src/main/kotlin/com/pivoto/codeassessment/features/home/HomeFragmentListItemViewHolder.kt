package com.pivoto.codeassessment.features.home

import com.pivoto.codeassessment.features.home.databinding.HomeFragmentListItemBinding
import com.pivoto.codeassessment.features.home.models.ListItemModel

class HomeFragmentListItemViewHolder(binding: HomeFragmentListItemBinding) : HomeFragmentListViewHolder(binding) {
    var dataItem: ListItemModel? = null
    val title = binding.title
    val thumbnail = binding.thumbnail
}
