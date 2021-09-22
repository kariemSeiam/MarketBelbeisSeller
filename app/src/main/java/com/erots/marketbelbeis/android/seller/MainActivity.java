package com.erots.marketbelbeis.android.seller;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.erots.marketbelbeis.android.seller.databinding.MainActivityBinding;

public class MainActivity extends AppCompatActivity {
    MainActivityBinding binding;
    private NavHostFragment navHostFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity);
        binding.setLifecycleOwner(this);

        setUpNavigation();

        binding.ivStoreFragment.setOnClickListener(view -> {
            navHostFragment.getNavController().navigate(R.id.postProductFragment);
        });
        binding.ivChatFragment.setOnClickListener(view -> {
            navHostFragment.getNavController().navigate(R.id.chatFragment);
        });
        binding.ivNotificationFragment.setOnClickListener(view -> {
            navHostFragment.getNavController().navigate(R.id.notificationFragment);
        });


    }

    private void setUpNavigation() {
        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        if (navHostFragment != null) {
            NavigationUI.setupWithNavController(binding.bottomNav, navHostFragment.getNavController());
        }


    }


}