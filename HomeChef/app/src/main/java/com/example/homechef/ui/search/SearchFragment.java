package com.example.homechef.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homechef.R;
import com.example.homechef.resource.Meal;
import com.example.homechef.ui.main.MainActivity;
import com.example.homechef.ui.shopping.ShoppingActivity;

import java.util.List;

public class SearchFragment extends Fragment implements SearchAdapter.OnListItemClickListener {

    private SearchViewModel searchViewModel;
    private Button button;
    private RecyclerView mRecyclerView;
    private SearchAdapter mAdapter;
    private EditText editText;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_search, container, false);
        searchViewModel =
                new ViewModelProvider(this).get(SearchViewModel.class);
        searchViewModel.init();
        button=root.findViewById(R.id.button_search);
        editText=root.findViewById(R.id.editText_search);

        mRecyclerView = root.findViewById(R.id.recycler_view_search);
        mRecyclerView.hasFixedSize();

        //init recycler
        mRecyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        mAdapter = new SearchAdapter(root.getContext(),this);
        mRecyclerView.setAdapter(mAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchViewModel.updateMeal(editText.getText().toString());
            }
        });

        searchViewModel.getMeal().observe(getViewLifecycleOwner(), new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals) {
                mAdapter.setmMeals(meals);
                mAdapter.notifyDataSetChanged();
            }
        });


        return root;
    }

    @Override
    public void onListItemClick(Meal meal) {
        meal.prepare();
        Intent intent= new Intent(getActivity(), ShoppingActivity.class);
        intent.putExtra("Meal",meal);
        startActivity(intent);
    }
}
