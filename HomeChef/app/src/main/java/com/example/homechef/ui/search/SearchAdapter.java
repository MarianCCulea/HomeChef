package com.example.homechef.ui.search;

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
import com.example.homechef.resource.Meal;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private List<Meal> mMeals;
    private Context mContext;
    private OnListItemClickListener listener;
    public SearchAdapter(Context context, OnListItemClickListener listener) {
        mMeals = new ArrayList<>();
        mContext = context;
        this.listener=listener;
    }

    public void setmMeals(List<Meal> mMeals) {
        this.mMeals = mMeals;
    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        //set values
        viewHolder.mName.setText(mMeals.get(i).getStrMeal());
        RequestOptions defaultOptions = new RequestOptions()
                .error(R.drawable.ic_launcher_background);
        Glide.with(mContext)
                .setDefaultRequestOptions(defaultOptions)
                .load(mMeals.get(i).getStrMealThumb())
                .into(viewHolder.mImage);
    }

    @Override
    public int getItemCount() {
        if(mMeals!=null)
        return mMeals.size();
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private CircleImageView mImage;
        private TextView mName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onListItemClick(mMeals.get(getAdapterPosition()));
                }
            });
            mImage = itemView.findViewById(R.id.image);
            mName = itemView.findViewById(R.id.image_name);
        }
    }

    public interface OnListItemClickListener {
        void onListItemClick(Meal meal);
    }

}
