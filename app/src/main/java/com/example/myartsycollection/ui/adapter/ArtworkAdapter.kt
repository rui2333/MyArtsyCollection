package com.example.myartsycollection.ui.adapter

import android.os.SystemClock
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import com.example.myartsycollection.R
import com.example.myartsycollection.databinding.ArtworkBinding
import com.example.myartsycollection.model.Artwork
import com.skydoves.bindables.BindingListAdapter
import com.skydoves.bindables.binding

class ArtworkAdapter : BindingListAdapter<Artwork, ArtworkAdapter.ArtworkViewHolder>(diffUtil) {
    private var onClickedAt = 0L

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArtworkAdapter.ArtworkViewHolder =
        parent.binding<ArtworkBinding>(R.layout.artwork).let ( ::ArtworkViewHolder )

    override fun onBindViewHolder(holder: ArtworkViewHolder, position: Int) =
        holder.bindArtwork(getItem(position))

    inner class ArtworkViewHolder constructor(
        private val binding: ArtworkBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init{
            binding.root.setOnClickListener{
                val position = bindingAdapterPosition.takeIf { it != NO_POSITION }
                    ?: return@setOnClickListener
                val currentClickedAt = SystemClock.elapsedRealtime()
                if (currentClickedAt - onClickedAt > binding.transformationLayout.duration) {
                    TODO()
                }
            }
        }

        fun bindArtwork(artwork: Artwork) {
            binding.artwork = artwork
            binding.executePendingBindings()
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<Artwork>() {

            override fun areItemsTheSame(oldItem: Artwork, newItem: Artwork): Boolean =
                oldItem.title == newItem.title

            override fun areContentsTheSame(oldItem: Artwork, newItem: Artwork): Boolean =
                oldItem == newItem
        }
    }
}