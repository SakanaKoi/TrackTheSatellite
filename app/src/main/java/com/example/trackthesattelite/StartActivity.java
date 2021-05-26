package com.example.trackthesattelite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {
    ImageView picture;
    TextView name;
    Animation anim;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        setContentView(R.layout.activity_start);
        picture = findViewById(R.id.picture);
        name = findViewById(R.id.name);
        anim = AnimationUtils.loadAnimation(this, R.anim.jump_out);
        anim.setAnimationListener(animStart);
        intent = new Intent(StartActivity.this, MainActivity.class);

        picture.startAnimation(anim);
    }

    Animation.AnimationListener animStart = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            startActivity(intent);
            StartActivity.this.finish();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };
}