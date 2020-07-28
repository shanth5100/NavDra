package com.one24apps.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.one24apps.myapplication.adaptor.BillAdapter;
import com.one24apps.myapplication.model.Bills;
import com.one24apps.myapplication.viewModel.BillsViewModel;

import java.util.List;

public class HomeFragment extends Fragment {

    private BillsViewModel billsViewModel;

    @Nullable
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//      ViewModelProviders: library class, has Util methods of ViewModelStore class, and provide the obj of ViewModelProvider(when use of of())
        billsViewModel = ViewModelProviders.of(this).get(BillsViewModel.class); // ViewModelProviders is deprecated.
//        ViewModelProvider(this).get(SomeViewModel::class.java)
//        billsViewModel = ViewModelProvider(this).get(BillsViewModel.class);

        RecyclerView recyclerView = getView().findViewById(R.id.recycler_view);

//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
//        recyclerView.setLayoutManager(layoutManager);   // OR  recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        final BillAdapter billAdapter = new BillAdapter();
        recyclerView.setAdapter(billAdapter);

        billsViewModel.getBillsList()
        .observe(this, new Observer<List<Bills>>() {
            @Override
            public void onChanged (List<Bills> bills) {
                billAdapter.setBillsList(bills);
            }
        });

//        return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}
