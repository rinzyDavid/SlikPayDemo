package com.slikpay.view.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.slikpay.R;
import com.slikpay.databinding.ActivityLoginBinding;
import com.slikpay.view.home.HomeActivity;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

      setup();
    }

    private void setup(){

        binding.loginBtn.setOnClickListener(v -> authenticate());
    }



    private void authenticate(){

        startActivity(new Intent(this, HomeActivity.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

    }
}