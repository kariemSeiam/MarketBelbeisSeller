package com.erots.marketbelbeis.android.seller.ui.orders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.erots.marketbelbeis.android.seller.databinding.OrdersFragmentBinding;
import com.erots.marketbelbeis.android.seller.ui.product.ProductModel;
import com.erots.marketbelbeis.android.seller.viewmodels.OrdersViewModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class OrdersFragment extends Fragment {

    List<ProductModel> list = new ArrayList<>();
    private OrdersViewModel mViewModel;
    private OrdersFragmentBinding binding;
    FirebaseDatabase database = FirebaseDatabase.getInstance();


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = OrdersFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(OrdersViewModel.class);
        binding.setLifecycleOwner(this);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}