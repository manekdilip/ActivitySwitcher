package com.demo.switcher;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.demo.switcher.animation.ActivitySwitcher;

import java.util.Calendar;


public class Activity1 extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1);
         /*set up toolbar*/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_screen_1);
        toolbar.setTitleTextColor(Color.WHITE);

         /*setup button click*/
        Button switchActivityBtn = (Button) findViewById(R.id.bSwitchActivity);
        switchActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animatedStartActivity();
            }
        });


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
        // we only animateOut this activity here.
        // The new activity will animateIn from its onResume() - be sure to implement it.
        final Intent intent = new Intent(getApplicationContext(), Activity2.class);
        intent.putExtra(ActivitySwitcher.ANIMATION_IN, true);
        // disable default animation for new intent
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        ActivitySwitcher.animationOut(findViewById(R.id.container), getWindowManager(), new ActivitySwitcher.AnimationFinishedListener() {
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

    void showDateRangeDialog() {

        final Calendar maxCal = Calendar.getInstance();
        maxCal.set(Calendar.YEAR, maxCal.get(Calendar.YEAR) - 18);

        View view = getLayoutInflater().inflate(R.layout.dialog_date_picker, null, false);
        final DatePicker datePicker = (DatePicker) view.findViewById(R.id.date_picker);
        datePicker.init(maxCal.get(Calendar.YEAR), maxCal.get(Calendar.MONTH), maxCal.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            }
        });
        datePicker.setMaxDate(maxCal.getTimeInMillis());

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setView(view);
        Button btnCancel = (Button) view.findViewById(R.id.btnCancel);
        Button btnOK = (Button) view.findViewById(R.id.btnOK);
        final AlertDialog alertDialog = builder.create();
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                cal.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), 0, 0, 0);

                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }


}