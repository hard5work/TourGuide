package com.lintend.tourguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.lintend.tourguide.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ImageView imageView = findViewById(R.id.imageesplash);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.together);
        imageView.startAnimation(animation);
        Thread aa= new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(2000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }



                Intent i = new Intent(SplashScreen.this,MainActivity.class);
                startActivity(i);
            }

        });
        aa.start();
    }
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

}
