package com.tatam.minitrademe.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tatam.minitrademe.R
import com.tatam.minitrademe.data.network.models.Listing
import com.tatam.minitrademe.databinding.ItemListingBinding

class ListingAdapter(
    private val openListingDetails: (Listing) -> Unit,
    private val context: Context
) : RecyclerView.Adapter<ListingAdapter.ListingItemViewHolder>() {

    private var listingList = emptyList<Listing>()

    inner class ListingItemViewHolder(val binding: ItemListingBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListingItemViewHolder {
        val binding = ItemListingBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ListingItemViewHolder(binding)
    }

    //Using view binding here
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListingItemViewHolder, position: Int) {
        with(holder) {
            with(listingList[position]) {
                binding.listingTitle.text = title
                binding.listingLocation.text = region
                Picasso.with(context)
                    .load(pictureHref)
                    //.placeholder(R.color.bluff_oyster_600_text_light)
                    .into(binding.listingThumbnail)
                if (buyNowPrice != null && this.priceDisplay != null) {
                    binding.listingBuyNowPrice.text = "$${buyNowPrice}"
                }
                if (this.isClassified == true) {
                    binding.listingReservePrice.text = "$priceDisplay"
                }

                binding.listingBuyNowPrice.visibility =
                    if (this.buyNowPrice != null) View.VISIBLE else View.GONE
                binding.listingBuyNowText.visibility =
                    if (this.buyNowPrice != null) View.VISIBLE else View.GONE

                binding.listingContainer.setOnClickListener {
                    openListingDetails(this)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return listingList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(itemList: List<Listing>) {
        listingList = itemList
        notifyDataSetChanged()
    }
}