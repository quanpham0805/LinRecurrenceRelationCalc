package com.example.linrecurrencerelation;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FragmentTerms extends Fragment {

    private RVAdapter mAdapter;
    MainViewModel mainViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public int[] makeArray(int len) {
        int[] temp = new int[len];
        for (int i = 0 ; i < len ; i ++) temp[i] = mainViewModel.getTerms(i);
        return temp;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_terms, container, false);
        mainViewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);


        int len = mainViewModel.getCounter();
        mAdapter = new RVAdapter(len, makeArray(len), 2);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(mAdapter);



        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        int[] temp = mAdapter.getData();
        for (int i = 0 ; i < temp.length ; i ++) {
            mainViewModel.setTerms(i, temp[i]);
        }
    }
}
