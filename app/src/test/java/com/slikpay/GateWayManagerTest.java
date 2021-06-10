package com.slikpay;


import androidx.annotation.NonNull;

import com.slikpay.callback.GatewayCallback;
import com.slikpay.data.Gateway;
import com.slikpay.dataManager.GatewayManager;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;

import static org.junit.Assert.assertNotNull;

@RunWith(JUnit4.class)
public class GateWayManagerTest extends BaseUnitTest{


    //This is a test case to verify that the api does not return a null value

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Inject
    GatewayManager gatewayManager;

    TestActivityComponent component;


    @Before
    public void setup() throws Exception {
        MockitoAnnotations.openMocks(this);
        super.setUp();
       component = getAppComponent().testComponentFactory().create();
        component.inject(this);


    }

    //Used to override the default AndroidSchedulers thread returned from RxJava
    @BeforeClass
    public static void setUpRxSchedulers() {
        Scheduler immediate = new Scheduler() {
            @Override
            public Disposable scheduleDirect(@NonNull Runnable run, long delay, @NonNull TimeUnit unit) {
                // this prevents StackOverflowErrors when scheduling with a delay
                return super.scheduleDirect(run, 0, unit);
            }

            @Override
            public Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(Runnable::run,true);
            }
        };

        RxJavaPlugins.setInitIoSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitComputationSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitNewThreadSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitSingleSchedulerHandler(scheduler -> immediate);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> immediate);
    }

    @Test
    public void testManager(){

        assertNotNull(gatewayManager);

        gatewayManager.listGateway(new GatewayCallback() {
            @Override
            public void onGatewayList(List<Gateway> gateways) {

                assertNotNull(gateways);// Test that the api does not return null

            }

            @Override
            public void onError(String error) {

            }
        });






    }
}
