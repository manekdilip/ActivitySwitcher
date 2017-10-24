/*
 * Copyright (c) 2011 Robert Heim
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.demo.switcher.animation;

import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;

/**
 * This ActivitySwitcher uses a 3D rotation to animate an activity during its
 * start or finish.
 * <p/>
 * see: http://blog.robert-heim.de/karriere/android-startactivity-rotate-3d-animation-activityswitcher/
 */
public class ActivitySwitcher {

    public final static String ANIMATION_IN = "ANIMATION_IN";
    public final static String ANIMATION_OUT = "ANIMATION_OUT";
    private final static int DURATION = 250;
    private final static float DEPTH = 0.0f;
    private final static float DEPTH2 = 0.0f;
    /* ----------------------------------------------- */

    public interface AnimationFinishedListener {
        /**
         * Called when the animation is finished.
         */
        public void onAnimationFinished();
    }

	/* ----------------------------------------------- */

    public static void animationIn(View container, WindowManager windowManager) {
        animationIn(container, windowManager, null);
    }

    public static void animationIn(View container, WindowManager windowManager, AnimationFinishedListener listener) {
        apply3DRotation(90, 0, false, container, windowManager, listener);
    }

    public static void animationOut(View container, WindowManager windowManager) {
        animationOut(container, windowManager, null);
    }

    public static void animationOut(View container, WindowManager windowManager, AnimationFinishedListener listener) {
        apply3DRotation(0, -90, true, container, windowManager, listener);
    }

    public static void animationIn2(View container, WindowManager windowManager) {
        animationIn(container, windowManager, null);
    }

    public static void animationIn2(View container, WindowManager windowManager, AnimationFinishedListener listener) {
        apply3DRotation2(0, 90, false, container, windowManager, listener);
    }

    public static void animationOut2(View container, WindowManager windowManager) {
        animationOut2(container, windowManager, null);
    }

    public static void animationOut2(View container, WindowManager windowManager, AnimationFinishedListener listener) {
        apply3DRotation2(-90, 0, true, container, windowManager, listener);
    }

	/* ----------------------------------------------- */

    private static void apply3DRotation(float fromDegree, float toDegree, boolean reverse, View container, WindowManager windowManager, final AnimationFinishedListener listener) {
        Display display = windowManager.getDefaultDisplay();
        final float centerX = display.getWidth() / 2.0f;
        final float centerY = display.getHeight() / 2.0f;

        final Rotate3dAnimation a = new Rotate3dAnimation(fromDegree, toDegree, centerX, centerY, DEPTH, reverse);
        a.reset();
        a.setDuration(DURATION);
        a.setFillAfter(true);
        a.setInterpolator(new AccelerateInterpolator());
        if (listener != null) {
            a.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    listener.onAnimationFinished();
                }
            });
        }
        container.clearAnimation();
        container.startAnimation(a);
    }

    private static void apply3DRotation2(float fromDegree, float toDegree, boolean reverse, View container, WindowManager windowManager, final AnimationFinishedListener listener) {
        Display display = windowManager.getDefaultDisplay();
        final float centerX = display.getWidth() / 2.0f;
        final float centerY = display.getHeight() / 2.0f;

        final Rotate3dAnimation a = new Rotate3dAnimation(fromDegree, toDegree, centerX, centerY, DEPTH2, reverse);
        a.reset();
        a.setDuration(DURATION);
        a.setFillAfter(true);
        a.setInterpolator(new AccelerateInterpolator());
        if (listener != null) {
            a.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    listener.onAnimationFinished();
                }
            });
        }
        container.clearAnimation();
        container.startAnimation(a);
    }
}
