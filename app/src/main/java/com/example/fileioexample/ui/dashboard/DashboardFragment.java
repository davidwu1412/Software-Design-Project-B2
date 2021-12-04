package com.example.fileioexample.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fileioexample.DashboardViewModel;
import com.example.fileioexample.R;
import com.example.fileioexample.databinding.FragmentDashboardBinding;

//public class DashboardFragment extends Fragment {
//
//    private DashboardViewModel dashboardViewModel;
//    private FragmentDashboardBinding binding;
//    private RecyclerView recyclerView;
//
//    @Override
//    public View onCreateView(
//            LayoutInflater inflater, ViewGroup container,
//            Bundle savedInstanceState
//    ) {
//
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
//
//        // Add the following lines to create RecyclerView
//        recyclerView = view.findViewById(R.id.cartrv);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
//        recyclerView.setAdapter(new ProdListAdapter(getString(R.string.order_file)));
//
//        return view;
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding = null;
//    }
//}