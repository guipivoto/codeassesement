package com.pivoto.codeassessment.features.home

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pivoto.codeassessment.features.home.databinding.HomeFragmentListItemBinding
import com.pivoto.codeassessment.features.home.models.ListItemModel
import com.pivoto.codeassessment.view.inflateViewBinding
import com.squareup.picasso.Picasso

class HomeFragmentListAdapter : RecyclerView.Adapter<HomeFragmentListViewHolder>() {

    var headerHeight: Int = 0
    var itemList: List<ListItemModel> = listOf()

    var itemClickListener: ((listItem: ListItemModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeFragmentListViewHolder {
        return if (viewType == ViewType.ITEM.ordinal) {
            val binding: HomeFragmentListItemBinding = parent.inflateViewBinding()
            val viewHolder =
                HomeFragmentListItemViewHolder(binding)
            binding.root.setOnClickListener {
                itemClickListener?.let { listener ->
                    viewHolder.dataItem?.let(listener)
                }
            }
            viewHolder
        } else {
            HomeFragmentListHeaderViewHolder(parent.inflateViewBinding())
        }
    }

    override fun getItemCount(): Int = if (itemList.isNotEmpty()) itemList.size + 1 else 0

    private fun getItem(viewPosition: Int): ListItemModel = itemList[viewPosition - 1]

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            ViewType.HEADER.ordinal
        } else {
            ViewType.ITEM.ordinal
        }
    }

    override fun onBindViewHolder(holder: HomeFragmentListViewHolder, position: Int) {
        if (holder is HomeFragmentListItemViewHolder) {
            with(getItem(position)) {
                holder.dataItem = this
                holder.title.text = title
                Picasso.get()
                    .load(thumbnail)
                    .resizeDimen(R.dimen.thumbnail_size, R.dimen.thumbnail_size)
                    .centerCrop()
                    .into(holder.thumbnail)
            }
        } else if (holder is HomeFragmentListHeaderViewHolder) {
            holder.space.layoutParams.height = headerHeight
        }
    }

    enum class ViewType {
        HEADER,
        ITEM
    }
}
