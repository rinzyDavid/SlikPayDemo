package com.slikpay.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.slikpay.R;
import com.slikpay.databinding.HomeFragmentBinding;
import com.slikpay.view.payment.PaymentOptionActivity;

public class HomeFragment extends Fragment {

    private HomeFragmentBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        binding = HomeFragmentBinding.inflate(inflater, container, false);

        setupListener();
        return binding.getRoot();
    }

    private void setupListener(){
        binding.paymentBtn.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(),PaymentOptionActivity.class));
            getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });
    }
}
