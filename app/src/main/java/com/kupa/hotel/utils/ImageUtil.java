package com.kupa.hotel.utils;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;

import org.xutils.common.Callback;
import org.xutils.image.ImageOptions;
import org.xutils.x;

/**
 * Created by Mita on 2017/6/13.
 */

public class ImageUtil {

    /**
     * 首页推荐绑定ImageView和URL
     *
     * @param imageView 背景
     * @param url       链接
     */
    public static void bindImage(ImageView imageView, String version, String url) {
        if (!TextUtils.isEmpty(url)) {
            ImageOptions options = new ImageOptions.Builder()
////设置加载过程中的图片
//                    .setLoadingDrawableId(R.drawable.ic_launcher)
////设置加载失败后的图片
//                    .setFailureDrawableId(R.drawable.ic_launcher)
//设置使用缓存
                    .setUseMemCache(true)
//设置显示圆形图片
//                    .setCircular(true)
//设置支持gif
                    .setIgnoreGif(false)    //以及其他方法
                    .build();
            url = Contacts.API_RECOMMEND_RES + version + URLencode.encodeUrl(url);
            x.image().bind(imageView, url, options, new Callback.CommonCallback<Drawable>() {
                @Override
                public void onSuccess(Drawable result) {
//                        Utils.log("图片加载成功");
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
//                        Utils.log("图片加载失败：" + ex.getMessage());
                }

                @Override
                public void onCancelled(CancelledException cex) {

                }

                @Override
                public void onFinished() {

                }
            });
        }
    }

}
