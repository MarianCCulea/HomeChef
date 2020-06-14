package com.example.homechef.ui.history;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Toast;

import com.example.homechef.R;
import com.example.homechef.resource.History;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class HistoryFragment extends Fragment {
    private RecyclerView recyclerView;
    private HistoryAdapter mAdapter;
    private View root;

    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_history, container, false);
        recyclerView = root.findViewById(R.id.history_recycler);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user=mAuth.getCurrentUser();
        if(user!=null) {
            Toast.makeText(getActivity(), "Must be logged in to see history", Toast.LENGTH_SHORT).show();
        }else {

            new FirebaseDatabaseHelper().getHistory(new FirebaseDatabaseHelper.DataStatus() {
                @Override
                public void dataIsLoaded(List<History> histories, List<String> strings) {
                    root.findViewById(R.id.progressBar_history).setVisibility(View.GONE);
                    mAdapter = new HistoryAdapter(root.getContext(), histories, strings);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setAdapter(mAdapter);
                }

                @Override
                public void dataIsInserted() {

                }
            });
        }
        return root;
    }

}
