package com.kupa.hotel.entity;

/**
 * Created by Mita on 2017/6/15.
 */

public class Message<T> {

    private Integer msgType;// 消息样式
    private String target;// 消息推送目标
    private T msgContent;// 消息内容
    private String msgDescription;// 消息说明
    private String originId;// 来源客户端ID
    private int id;//电影id

    public Message() {
    }

    public Message(Integer msgType, String target, T msgContent, String msgDescription, String originId, int id) {
        this.msgType = msgType;
        this.target = target;
        this.msgContent = msgContent;
        this.msgDescription = msgDescription;
        this.originId = originId;
        this.id = id;
    }

    public String getOriginId() {
        return originId;
    }

    public void setOriginId(String originId) {
        this.originId = originId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public T getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(T msgContent) {
        this.msgContent = msgContent;
    }

    public String getMsgDescription() {
        return msgDescription;
    }

    public void setMsgDescription(String msgDescription) {
        this.msgDescription = msgDescription;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    @Override
    public String toString() {
        return "MessageModel [msgType=" + msgType + ", target=" + target + ", msgContent=" + msgContent
                + ", msgDescription=" + msgDescription + "]";
    }
}
