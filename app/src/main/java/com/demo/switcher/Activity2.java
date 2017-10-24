package com.demo.switcher;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.demo.switcher.animation.ActivitySwitcher;


public class Activity2 extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);

        /*set up toolbar*/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_screen_2);
        toolbar.setTitleTextColor(Color.WHITE);

        /*setup button click*/
        Button switchActivityBtn = (Button) findViewById(R.id.bSwitchActivity);
        switchActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        /*animate activity on back*/
        animatedStartActivity();
    }

    @Override
    protected void onResume() {
        //If there is boolean in Intent than animateIn this activity
        if (getIntent() != null) {
            if (getIntent().getBooleanExtra(ActivitySwitcher.ANIMATION_IN, false)) {
                ActivitySwitcher.animationIn(findViewById(R.id.container), getWindowManager());
            } else if (getIntent().getBooleanExtra(ActivitySwitcher.ANIMATION_OUT, false)) {
                ActivitySwitcher.animationOut2(findViewById(R.id.container), getWindowManager());
            }
        }
        super.onResume();
    }

    private void animatedStartActivity() {
        // We only animateOut this activity here.
        // The new activity will animateIn from its onResume() - be sure to implement it.
        final Intent intent = new Intent(getApplicationContext(), Activity1.class);
        // disable default animation for new intent
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.putExtra(ActivitySwitcher.ANIMATION_OUT, true);
        ActivitySwitcher.animationIn2(findViewById(R.id.container), getWindowManager(), new ActivitySwitcher.AnimationFinishedListener() {
            @Override
            public void onAnimationFinished() {
                startActivity(intent);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 200);
            }
        });
    }
}