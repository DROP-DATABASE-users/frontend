package com.dropdatabase.naszesasiedztwo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dropdatabase.naszesasiedztwo.R;
import com.dropdatabase.naszesasiedztwo.models.Listing;

import java.util.List;

public class ListingAdapter extends RecyclerView.Adapter<ListingAdapter.ViewHolder> {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView tvTitle;
        public TextView tvDescription;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            tvTitle  = (TextView) itemView.findViewById(R.id.listing_title);
            tvDescription = (TextView) itemView.findViewById(R.id.listing_description);
        }
    }

    private List<Listing> mListings;

    public ListingAdapter(List<Listing> listings) {
        mListings = listings;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public ListingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        // Inflate the custom layout
        View listingView = inflater.inflate(R.layout.listing, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(listingView);

        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(ListingAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        Listing listing = mListings.get(position);

        // Set item views based on your views and data model
        TextView titleView = holder.tvTitle;
        titleView.setText(listing.getTitle());

        TextView descriptionView = holder.tvDescription;
        descriptionView.setText(listing.getDescription());

    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mListings.size();
    }

}