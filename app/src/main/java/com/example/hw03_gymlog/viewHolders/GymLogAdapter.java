package com.example.hw03_gymlog.viewHolders;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import com.example.hw03_gymlog.database.entities.GymLog;

/**
 * Author: Quratulain Siddiq
 * CST 338 GymLog
 * GymLogAdapter is a ListAdapter that manages GymLog items in a RecyclerView.
 * It uses GymLogViewHolder to bind each log entry to the layout.
 */
public class GymLogAdapter extends ListAdapter<GymLog, GymLogViewHolder> {

    /**
     * Creates a GymLogAdapter using the provided DiffUtil callback.
     *
     * @param diffCallback determines how list differences are calculated
     */
    public GymLogAdapter(@NonNull DiffUtil.ItemCallback<GymLog> diffCallback) {
        super(diffCallback);
    }

    /**
     * Creates a new ViewHolder when needed by the RecyclerView.
     */
    @Override
    @NonNull
    public GymLogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return GymLogViewHolder.create(parent);
    }

    /**
     * Binds a GymLog item to the ViewHolder for display.
     */
    @Override
    public void onBindViewHolder(@NonNull GymLogViewHolder holder, int position) {
        GymLog current = getItem(position);
        holder.bind(current.toString());
    }

    /**
     * DiffUtil class to efficiently determine changes in the GymLog list.
     */
    public static class GymLogDiff extends DiffUtil.ItemCallback<GymLog> {

        /**
         * Checks whether two GymLog objects represent the same item.
         */
        @Override
        public boolean areItemsTheSame(@NonNull GymLog oldItem, @NonNull GymLog newItem) {
            return oldItem == newItem;
        }

        /**
         * Checks whether the content of two GymLog objects is the same.
         */
        @Override
        public boolean areContentsTheSame(@NonNull GymLog oldItem, @NonNull GymLog newItem) {
            return oldItem.equals(newItem);
        }
    }
}