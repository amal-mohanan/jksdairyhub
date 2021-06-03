package com.jksdairyhub.jksdairyhub.App.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jksdairyhub.jksdairyhub.App.Activities.AddCustomerActivity;
import com.jksdairyhub.jksdairyhub.App.Models.Customer;
import com.jksdairyhub.jksdairyhub.Utils.Storage.Preferences;
import com.jksdairyhub.jksdairyhub.databinding.FragmentCustomerBinding;


public class CustomerFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private FragmentCustomerBinding binding;
    private RecyclerView rcvCustomers;
    private SwipeRefreshLayout refreshLayout;
    private FloatingActionButton fabAdd;
    private Customer mCustomer;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCustomerBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        initViews();
        return root;
    }

    private void initViews() {
        refreshLayout = binding.refreshCustomers;
        rcvCustomers  =binding.rcvCustomers;
        fabAdd = binding.fabAddCustomers;

        initObjects();
    }

    private void initObjects() {
        refreshLayout.setOnRefreshListener(this);

        initListeners();
    }

    private void initListeners() {

        fabAdd.setOnClickListener(v-> startActivity(new Intent(getActivity(), AddCustomerActivity.class)));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onRefresh() {
        initData();
    }

    private void initData() {
        mCustomer = Preferences.getCustomers();
    }
}