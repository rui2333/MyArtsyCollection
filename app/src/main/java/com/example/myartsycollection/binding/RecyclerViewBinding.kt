package com.example.myartsycollection.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myartsycollection.ui.main.ArtworkViewModel
import com.skydoves.baserecyclerviewadapter.RecyclerViewPaginator
import com.skydoves.bindables.BindingListAdapter
import com.skydoves.bindables.BindingViewModel
import com.skydoves.whatif.whatIfNotNullAs

object RecyclerViewBinding {

    @JvmStatic
    @BindingAdapter("adapter")
    fun bindAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
        view.adapter = adapter.apply {
            stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }
    }

    @JvmStatic
    @BindingAdapter("submitList")
    fun bindSubmitList(view: RecyclerView, itemList: List<Any>?) {
        view.adapter.whatIfNotNullAs<BindingListAdapter<Any, *>> { adapter ->
            adapter.submitList(itemList)
        }
    }

    @JvmStatic
    @BindingAdapter("paginationCollection")
    fun paginationCollection(view: RecyclerView, viewModel: ArtworkViewModel) {
        RecyclerViewPaginator(
            recyclerView = view,
            isLoading = { viewModel.isLoading },
            loadMore = {viewModel.fetchNextCollection() },
            onLast = { false }
        ).run {
            threshold = 8
        }
    }
}