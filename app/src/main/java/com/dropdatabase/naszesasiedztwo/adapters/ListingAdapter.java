package com.dropdatabase.naszesasiedztwo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dropdatabase.naszesasiedztwo.R;
import com.dropdatabase.naszesasiedztwo.models.Listing;

import java.util.List;

public class ListingAdapter extends RecyclerView.Adapter<ListingAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle;
        public TextView tvDescription;

        public ViewHolder(View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.listing_title);
            tvDescription = itemView.findViewById(R.id.listing_description);
        }
    }

    private final List<Listing> mListings;

    public ListingAdapter(List<Listing> listings) {
        mListings = listings;
    }

    @NonNull
    @Override
    public ListingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View listingView = inflater.inflate(R.layout.listing, parent, false);

        return new ViewHolder(listingView);
    }

    @Override
    public void onBindViewHolder(ListingAdapter.ViewHolder holder, int position) {
        Listing listing = mListings.get(position);

        holder.tvTitle.setText(listing.getTitle());
        holder.tvDescription.setText(listing.getDescription());
    }

    @Override
    public int getItemCount() {
        return mListings.size();
    }

}