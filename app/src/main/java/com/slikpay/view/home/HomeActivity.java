package com.slikpay.view.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.slikpay.R;
import com.slikpay.databinding.ActivityHomeBinding;
import com.slikpay.view.fragment.HomeFragment;

public class HomeActivity extends AppCompatActivity {
    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupBottomNavigateion();
    }


    private void setupBottomNavigateion(){

        binding.bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment;
                switch (item.getItemId()){
                    case R.id.action_home:
                        fragment = new HomeFragment();
                        loadFragment(fragment);
                        return true;

                    case R.id.action_card:
                    case R.id.action_withdrawal:
                    case R.id.action_account:
                        break;
                }

                return false;
            }

        });

        HomeFragment fragment = new HomeFragment();
        loadFragment(fragment);


    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, fragment);
        //transaction.addToBackStack(null);
        transaction.commit();
    }
}