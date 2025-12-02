package com.example.hw03_gymlog.viewHolders;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.hw03_gymlog.R;

/**
 * Author: Quratulain Siddiq
 * CST 338 GymLog
 * GymLogViewHolder is a ViewHolder for displaying GymLog items in a RecyclerView.
 * It holds the TextView used to show the log information.
 */
public class GymLogViewHolder extends RecyclerView.ViewHolder {
    private final TextView gymLogViewItem;

    /**
     * Creates a ViewHolder for a GymLog item.
     *
     * @param gymLogView the inflated layout for the item view
     */
    private GymLogViewHolder(View gymLogView) {
        super(gymLogView);
        gymLogViewItem = gymLogView.findViewById(R.id.recyclerItemTextView);
    }

    /**
     * Binds the GymLog text to the TextView for display.
     *
     * @param text the formatted GymLog text
     */
    public void bind(String text) {
        gymLogViewItem.setText(text);
    }

    /**
     * Creates and inflates a new GymLogViewHolder.
     *
     * @param parent the parent ViewGroup containing the RecyclerView
     * @return a new GymLogViewHolder instance
     */
    static GymLogViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gymlog_recycler_item, parent, false);
        return new GymLogViewHolder(view);
    }
}
