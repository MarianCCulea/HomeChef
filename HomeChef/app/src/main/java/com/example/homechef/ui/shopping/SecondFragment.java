package com.example.homechef.ui.shopping;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
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
import com.example.homechef.ui.main.MainActivity;

public class SecondFragment extends Fragment {

    ImageView image;
    TextView name,area,category,tag,link,instructions;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shopping_instructions, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
        view.findViewById(R.id.button_done).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        name=view.findViewById(R.id.text_name_instructions);
        image=view.findViewById(R.id.image_instructions);
        area=view.findViewById(R.id.text_area_instructions);
        category=view.findViewById(R.id.text_category_instructions);
        tag=view.findViewById(R.id.text_tags_instructions);
        link=view.findViewById(R.id.text_link_instructions);
        instructions=view.findViewById(R.id.text_instructions);


        Meal meal=ShoppingActivity.getCurrentMeal();


        RequestOptions defaultOptions = new RequestOptions()
                .error(R.drawable.ic_launcher_background);
        Glide.with(getActivity())
                .setDefaultRequestOptions(defaultOptions)
                .load(meal.getStrMealThumb())
                .into(image);
        name.setText(meal.getStrMeal());
        area.setText(meal.getStrArea());
        category.setText("");
        tag.setText(meal.getStrCategory());
        link.setText(meal.getStrYoutube());
        instructions.setText(meal.getStrInstructions());




        link.setMovementMethod(LinkMovementMethod.getInstance());

    }


}
