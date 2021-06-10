package com.slikpay.view.payment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.slikpay.R;
import com.slikpay.callback.GatewayCallback;
import com.slikpay.data.Gateway;
import com.slikpay.dataManager.GatewayManager;
import com.slikpay.databinding.ActivityPaymentOptionBinding;
import com.slikpay.di.component.ActivityComponent;
import com.slikpay.di.component.ApplicationComponent;
import com.slikpay.system.AppUtil;
import com.slikpay.system.SlikApplication;
import com.slikpay.view.adapter.GateWayViewAdapter;
import com.slikpay.view.viewmodel.PaymentOptionVM;

import java.util.List;

import javax.inject.Inject;

public class PaymentOptionActivity extends AppCompatActivity {

    protected ActivityComponent appComponent ;

    private ActivityPaymentOptionBinding binding;
    private PaymentOptionVM vm;

    @Inject
        protected GatewayManager gatewayManager;

    @Inject protected AppUtil appUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        appComponent = ((SlikApplication) getApplicationContext())
        .getAppComponent()
                .activityComponentFactory().create();

        appComponent.inject(this);

        binding = ActivityPaymentOptionBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        vm = ViewModelProviders.of(this).get(PaymentOptionVM.class);
        vm.setGatewayManager(gatewayManager);

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

        vm.listGateWays(new GatewayCallback() {
            @Override
            public void onGatewayList(List<Gateway> gateways) {
                setView(gateways);
            }

            @Override
            public void onError(String error) {

                appUtil.showErrorSnack(PaymentOptionActivity.this,binding.getRoot(),error);
            }
        });

    }

    private void setView(List<Gateway> gateways){
        GateWayViewAdapter adapter =  new GateWayViewAdapter(PaymentOptionActivity.this,gateways);
        binding.paymentList.setAdapter(adapter);
        binding.shimmerViewContainer.stopShimmerAnimation();
        binding.shimmerViewContainer.setVisibility(View.GONE);
        appUtil.setRVAnimation(binding.paymentList,this);


    }
}