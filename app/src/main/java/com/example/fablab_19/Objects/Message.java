package com.example.fablab_19.Objects;

import java.util.Date;

public class Message {
    private String messageText;
    private String userName;
    private long messageTime;

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    private String uId;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    private String userEmail;


    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }




    public Message(String messageText, String userName, String userEmail, String uId){
        this.messageText = messageText;
        this.userName = userName;
        this.messageTime = new Date().getTime();
        this.userEmail = userEmail;
        this.uId = uId;
    }
    public Message(){

    }
}
