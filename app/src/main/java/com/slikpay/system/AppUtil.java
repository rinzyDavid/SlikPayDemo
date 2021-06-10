package com.slikpay.system;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.slikpay.R;
import com.slikpay.di.anons.ActivityScope;

import javax.inject.Inject;

@ActivityScope
public class AppUtil {



private AlertDialog.Builder builder;
private AlertDialog dialog;


    @Inject
    public AppUtil(){
    }

    public void initDialog(Activity activity){
        if (builder == null) {
            builder = new AlertDialog.Builder(activity);

            // builder.setTitle("Loading...");

            final ProgressBar progressBar = new ProgressBar(activity);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            progressBar.setLayoutParams(lp);
            builder.setView(progressBar);
            dialog = builder.create();
        }
    }


    public void showErrorSnack(Context context,View view,String message){

       // Activity activity = (Activity) context;
        View layout;
        Snackbar snackbar = Snackbar
                .make(view, message, Snackbar.LENGTH_SHORT);
        layout = snackbar.getView();
        //setting background color
        layout.setBackgroundColor(context.getResources().getColor(R.color.error));
        snackbar.show();
    }

    public void showSuccessSnack(Context context,View view, String message){

        View layout;
        Snackbar snackbar = Snackbar
                .make(view, message, Snackbar.LENGTH_SHORT);
        layout = snackbar.getView();
        //setting background color
        layout.setBackgroundColor(context.getResources().getColor(R.color.success_green));
        snackbar.show();
    }

    public void showToast(Context context,String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }

public boolean checkLocationPermission(Activity context){
    return ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(context,
            Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }


    public void showDailog(String message){
       // builder.setTitle(message);
       dialog.setTitle(message);
       if(dialog.isShowing())
        dialog.dismiss();

       dialog.show();

    }

    public void dismiss(){
        dialog.dismiss();
    }


    public void setRVAnimation(RecyclerView rv, Activity activity) {

        AnimationSet set = new AnimationSet(true);

        // Fade in animation
        Animation fadeIn = new AlphaAnimation(0.0f, 1.0f);
        fadeIn.setDuration(500);
        fadeIn.setFillAfter(true);
        set.addAnimation(fadeIn);

        // Slide up animation from bottom of screen
        Animation slideUp = new TranslateAnimation(0, 0, getScreenHeight(activity), 0);
        slideUp.setInterpolator(new DecelerateInterpolator(4.f));
        slideUp.setDuration(500);
        set.addAnimation(slideUp);

        // Set up the animation controller              (second parameter is the delay)
        LayoutAnimationController controller = new LayoutAnimationController(set, 0.2f);
        rv.setLayoutAnimation(controller);


    }

    private int getScreenHeight(Activity activity){
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);

        return metrics.heightPixels;
    }




}
