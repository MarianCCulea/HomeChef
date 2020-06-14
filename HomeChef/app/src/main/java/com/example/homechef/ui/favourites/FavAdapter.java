package com.example.homechef.ui.favourites;

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
import com.example.homechef.localstorage.Favourite;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.ViewHolder> {

    private List<Favourite> mMeals;
    private Context mContext;

    public FavAdapter(Context context) {
        mMeals = new ArrayList<>();
        mContext = context;
    }

    @NonNull
    @Override
    public FavAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        //set values
Favourite favor=mMeals.get(i);


        viewHolder.mName.setText(mMeals.get(i).getName());
        RequestOptions defaultOptions = new RequestOptions()
                .error(R.drawable.ic_launcher_background);
        Glide.with(mContext)
                .setDefaultRequestOptions(defaultOptions)
                .load(mMeals.get(i).getImgUrl())
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
            mImage = itemView.findViewById(R.id.image);
            mName = itemView.findViewById(R.id.image_name);
        }
    }

    public void setmMeals(List<Favourite> mMeals){
        this.mMeals=mMeals;
        notifyDataSetChanged();
    }

    public interface OnListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }
    public Favourite getFavat(int pos){
        return mMeals.get(pos);
    }


}
