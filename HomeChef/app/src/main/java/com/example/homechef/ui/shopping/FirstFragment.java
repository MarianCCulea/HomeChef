package com.example.homechef.ui.shopping;

import android.media.Image;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.homechef.R;
import com.example.homechef.resource.Meal;

public class FirstFragment extends Fragment {

    private TextView ingredients,name,area;
    private ImageView imageView;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shopping_list, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
        ingredients=view.findViewById(R.id.ingredients);
        name=view.findViewById(R.id.text_name_shoppinglist);
        area=view.findViewById(R.id.text_area_shoppinglist);
        imageView=view.findViewById(R.id.image_shoppinglist);


        Meal meal=ShoppingActivity.getCurrentMeal();
        name.setText(meal.getStrMeal());
        area.setText(meal.getStrArea());



        RequestOptions defaultOptions = new RequestOptions()
                .error(R.drawable.ic_launcher_background);
        Glide.with(getActivity())
                .setDefaultRequestOptions(defaultOptions)
                .load(meal.getStrMealThumb())
                .into(imageView);

        String ing="";

        for(int i=0;i<meal.getStrIngredient().size();i++){
            if(!(meal.getStrIngredient().get(i).isEmpty()))
            ing+=meal.getStrIngredient().get(i)+"-"+meal.getStrMeasure().get(i)+" \n";
        }

        ingredients.setText(ing);

    }
}
