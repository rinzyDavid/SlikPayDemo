package com.slikpay.di.modules;

import com.slikpay.dataManager.GatewayServiceImpl;
import com.slikpay.repository.GatewayService;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;
import dagger.hilt.android.scopes.ViewModelScoped;

/**
 * Application Module : Hilt module for injecting Gateway service dependencies into viewModel
 *
 *
 */

@Module
@InstallIn(ViewModelComponent.class)
public abstract class ApplicationModule {

    @Binds
    public abstract GatewayService bindGatewayService(GatewayServiceImpl gatewayService);

}
