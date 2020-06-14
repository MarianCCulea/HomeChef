package com.example.homechef.ui.history;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.homechef.R;
import com.example.homechef.resource.History;
import com.example.homechef.resource.Meal;
import com.example.homechef.ui.search.SearchAdapter;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private List<History> mHistory = new ArrayList<>();
    private List<String> keys;
    private Context mContext;

    public HistoryAdapter(Context context, List<History> mHistory, List<String> keys) {
        this.mHistory = mHistory;
        this.mContext = context;
        this.keys = keys;
    }

    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_history, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.ViewHolder holder, int i) {
        //set values
        holder.mName.setText(mHistory.get(i).getStrMeal());
        holder.mDate.setText(mHistory.get(i).getDate());
        RequestOptions defaultOptions = new RequestOptions()
                .error(R.drawable.ic_launcher_background);
        Glide.with(mContext)
                .setDefaultRequestOptions(defaultOptions)
                .load(mHistory.get(i).getStrMealThumb())
                .into(holder.mImage);
        holder.key = keys.get(i);
    }

    @Override
    public int getItemCount() {
        if (mHistory != null)
            return mHistory.size();
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mName;
        private TextView mDate;
        private CircleImageView mImage;
        private String key;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.image_name_history);
            mDate = itemView.findViewById(R.id.date_history);
            mImage = itemView.findViewById(R.id.image_history);
        }
    }


}
