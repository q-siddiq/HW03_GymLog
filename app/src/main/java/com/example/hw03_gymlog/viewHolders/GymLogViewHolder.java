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
 * It binds data to a TextView to display each GymLog entry.
 */
public class GymLogViewHolder extends RecyclerView.ViewHolder {
    private final TextView gymLogViewItem;

    private GymLogViewHolder(View gymLogView) {
        super(gymLogView);
        gymLogViewItem = gymLogView.findViewById(R.id.recyclerItemTextView);
    }

    public void bind(String text) {
        gymLogViewItem.setText(text);
    }

    static GymLogViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gymlog_recycler_item, parent, false);
        return new GymLogViewHolder(view);
    }
}
