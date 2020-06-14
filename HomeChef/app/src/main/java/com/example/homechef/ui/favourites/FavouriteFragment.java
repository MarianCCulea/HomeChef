package com.example.homechef.ui.favourites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homechef.R;
import com.example.homechef.localstorage.Favourite;

import java.util.List;


public class FavouriteFragment extends Fragment {

    private FavViewModel favViewModel;
    private Button button;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        favViewModel =
                new ViewModelProvider(this).get(FavViewModel.class);
        View root = inflater.inflate(R.layout.fragment_favourites, container, false);
        button=root.findViewById(R.id.button_deleteAll);
        favViewModel.init();

        RecyclerView recyclerView=root.findViewById(R.id.favourite_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        recyclerView.setHasFixedSize(true);

        final FavAdapter adapter=new FavAdapter(root.getContext());
        recyclerView.setAdapter(adapter);

        favViewModel.getAllfavs().observe(getViewLifecycleOwner(), new Observer<List<Favourite>>() {
            @Override
            public void onChanged(@Nullable List<Favourite> s) {
                adapter.setmMeals(s);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favViewModel.deleteAll();
            }
        });


        return root;
    }

}
