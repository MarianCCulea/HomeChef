package com.example.homechef.ui.random;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.homechef.R;
import com.example.homechef.resource.Meal;
import com.example.homechef.ui.shopping.ShoppingActivity;

public class RandomFragment extends Fragment {

    private Button random,getShopping;
    private Meal randomMeal;
    private ImageView imageView;
    private TextView name;

    private RandomViewModel randomViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_random, container, false);
        randomViewModel =
                new ViewModelProvider(this).get(RandomViewModel.class);
        randomViewModel.init();
        random=root.findViewById(R.id.button_random);
        getShopping=root.findViewById(R.id.button_getshopping);
        imageView=root.findViewById(R.id.image_random);
        name=root.findViewById(R.id.image_name_random);

        randomViewModel.getRandomMeal().observe(getViewLifecycleOwner(), new Observer<Meal>() {
            @Override
            public void onChanged(@Nullable Meal meal) {
                randomMeal=meal;
                Toast.makeText(getContext(), "This"+randomMeal.getStrIngredient1(), Toast.LENGTH_SHORT).show();
                RequestOptions defaultOptions = new RequestOptions()
                        .error(R.drawable.ic_launcher_background);
                Glide.with(getActivity())
                        .setDefaultRequestOptions(defaultOptions)
                        .load(randomMeal.getStrMealThumb())
                        .into(imageView);
                name.setText(randomMeal.getStrMeal());
            }
        });
        getShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randomMeal.prepare();
                Intent intent= new Intent(getActivity(), ShoppingActivity.class);
                intent.putExtra("Meal",randomMeal);
                startActivity(intent);
            }
        });
        randomViewModel.updateMeal();

        random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randomViewModel.updateMeal();
            }
        });

        return root;
    }
}
