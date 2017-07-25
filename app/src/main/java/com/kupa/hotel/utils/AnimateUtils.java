package com.kupa.hotel.utils;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

/**
 * 动画集合.
 *
 * @author hailongqiu 356752238@qq.com
 */
public class AnimateUtils {

    private static AlphaAnimation alphaAnimation;

    public static void startAlphaAnimation(final ImageView imageView, final String version, final int url) {
        /**
         * @param fromAlpha 开始的透明度，取值是0.0f~1.0f，0.0f表示完全透明， 1.0f表示和原来一样
         * @param toAlpha 结束的透明度，同上
         */
        alphaAnimation = new AlphaAnimation(1.0f, 0.3f);
        alphaAnimation.setDuration(1000);
        alphaAnimation.setFillAfter(true);
        imageView.startAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageView.setImageResource(url);
//                ImageUtil.bindImage(imageView, version, url);
                alphaAnimation = new AlphaAnimation(0.3f, 1.0f);
                alphaAnimation.setDuration(1000);
                alphaAnimation.setFillAfter(true);
                imageView.startAnimation(alphaAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public static Animation zoomAnimation(float startScale, float endScale, long duration) {
        ScaleAnimation anim = new ScaleAnimation(startScale, endScale, startScale, endScale, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setFillAfter(true);
        anim.setDuration(duration);
        return anim;
    }
}
