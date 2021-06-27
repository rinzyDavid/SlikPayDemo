package com.slikpay.view.payment;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.slikpay.R;
import com.slikpay.data.Gateway;
import com.slikpay.data.GatewayData;
import com.slikpay.databinding.ActivityPaymentOptionBinding;

import com.slikpay.system.AppUtil;
import com.slikpay.view.adapter.GateWayViewAdapter;
import com.slikpay.view.viewmodel.PaymentOptionVM;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PaymentOptionActivity extends AppCompatActivity {

    private ActivityPaymentOptionBinding binding; //

     PaymentOptionVM vm;
    @Inject protected AppUtil appUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPaymentOptionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Initializes viewModel class
        vm = new ViewModelProvider(this).get(PaymentOptionVM.class);


        binding.paymentList.setLayoutManager(new LinearLayoutManager(this));
        binding.paymentList.setItemAnimator(new DefaultItemAnimator());

        Toolbar toolbar = binding.toolbar;
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    public void onResume(){
        super.onResume();
        binding.shimmerViewContainer.startShimmerAnimation();
        loadData();
    }

    public void onPause(){
        super.onPause();
        binding.shimmerViewContainer.stopShimmerAnimation();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void onBackPressed(){
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }



    private void loadData(){

        vm.getGateWays().observe(this, gatewayData -> {

            if(gatewayData.getError()!=null){
                appUtil.showErrorSnack(PaymentOptionActivity.this,binding.getRoot(),gatewayData.getError());
                return;
            }
            if(gatewayData.getGateways()!=null)
                    setView(gatewayData.getGateways());
        });


    }
// Initializes the view
    private void setView(List<Gateway> gateways){
        GateWayViewAdapter adapter =  new GateWayViewAdapter(PaymentOptionActivity.this,gateways);
        binding.paymentList.setAdapter(adapter);
        binding.shimmerViewContainer.stopShimmerAnimation();
        binding.shimmerViewContainer.setVisibility(View.GONE);
        appUtil.setRVAnimation(binding.paymentList,this);


    }
}