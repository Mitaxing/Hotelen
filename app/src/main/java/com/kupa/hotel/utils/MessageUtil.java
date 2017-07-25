package com.kupa.hotel.utils;

import android.content.Context;
import android.content.Intent;

import com.kupa.hotel.R;
import com.kupa.hotel.activity.PlayMovieActivity;
import com.kupa.hotel.entity.Message;
import com.kupa.hotel.view.TipDialog;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Mita on 2017/6/15.
 */

public class MessageUtil {

    /**
     * 处理推送数据
     *
     * @param context
     * @param result
     */
    public static void dealMessage(final Context context, String result) {
        final Message msg = MessageUtil.resolveUrl(result);
        switch (msg.getMsgType()) {

            case Contacts.PUSH_MOVIE:
                final String url = (String) msg.getMsgContent();
                final TipDialog dialog = new TipDialog(context, R.style.MyDialog);
                dialog.setMessage("点播成功，是否立即播放？");
                dialog.setCancleText("放弃");
                dialog.setYesText("播放");
                dialog.setNoClickListener(new TipDialog.OnNoClickListener() {
                    @Override
                    public void onNoClick() {
                        requestMoviePushReply(msg.getTarget(), msg.getId(), Contacts.FAUIL, msg.getOriginId());
                        dialog.dismiss();
                    }
                });
                dialog.setYesClickListener(new TipDialog.OnYesClickListener() {
                    @Override
                    public void onYesClick() {
                        MoviesUtils.savePushMovieId(context, msg.getId());
                        requestMoviePushReply(msg.getTarget(), msg.getId(), Contacts.SUCCESS, msg.getOriginId());
                        dialog.dismiss();
                        Intent intent = new Intent(context, PlayMovieActivity.class);
                        intent.putExtra("movieUrl", url);
                        intent.putExtra("movieId", msg.getId());
                        intent.putExtra("isPush", true);
                        context.startActivity(intent);
                    }
                });
                dialog.show();
                break;
        }
    }

    /**
     * 回复电影点播推送结果
     */
    private static void requestMoviePushReply(String target, int moveId, int status, String wxId) {
        RequestParams params = new RequestParams(com.kupa.hotel.utils.Contacts.API_REPLY_MOVIE_PUSH);
        params.addParameter("identifier", target);
        params.addParameter("movieId", moveId);
        params.addParameter("roomId", Contacts.ROOMID);
        params.addParameter("status", status);
        params.addParameter("wxId", wxId);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Utils.log("电影点播回复结果：" + result);
                if (result.contains("ok")) {
                    Utils.log("电影点播回复成功");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Utils.log("电影点播回复失败：" + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 解析推送消息
     *
     * @param result 服务器推送消息
     * @return Message
     */
    private static Message resolveUrl(String result) {
        Message msg = new Message();
        try {
            JSONTokener parse = new JSONTokener(result);
            JSONObject object = (JSONObject) parse.nextValue();
            msg.setMsgType(object.optInt("msgType"));
            msg.setTarget(object.optString("target"));
            msg.setMsgDescription(object.optString("msgDescription"));
            msg.setMsgContent(object.opt("msgContent"));
            msg.setOriginId(object.optString("originId"));
            msg.setId(object.optInt("id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return msg;
    }
}
