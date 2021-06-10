package com.slikpay.view.intro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import com.slikpay.R;
import com.slikpay.view.auth.LoginActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

    }

    public void onResume(){
        super.onResume();
        startTimer();
    }




    private void startTimer(){
        new CountDownTimer(3000, 1000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {

                startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
            }



        }.start();
    }
}