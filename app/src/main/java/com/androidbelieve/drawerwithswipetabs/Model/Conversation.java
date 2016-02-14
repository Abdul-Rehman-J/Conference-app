package com.androidbelieve.drawerwithswipetabs.Model;

import java.util.Date;

/**
 * Created by TOSHIBA on 14.2.2016. Şubat
 * Dont worry !
 */
public class Conversation {

    public static final int STATUS_SENDING = 0;
    public static final int STATUS_SENT = 1;
    public static final int STATUS_FAILED = 2;

    private int status = STATUS_SENT;

    private String message_content;
    private Date message_date;
    private String message_sender;
    private String message_receiver;

    public Conversation(int status, String message_content, Date message_date, String message_sender, String message_receiver) {
        this.status = status;
        this.message_content = message_content;
        this.message_date = message_date;
        this.message_sender = message_sender;
        this.message_receiver = message_receiver;
    }

    public Conversation() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage_content() {
        return message_content;
    }

    public void setMessage_content(String message_content) {
        this.message_content = message_content;
    }

    public Date getMessage_date() {
        return message_date;
    }

    public void setMessage_date(Date message_date) {
        this.message_date = message_date;
    }

    public String getMessage_sender() {
        return message_sender;
    }

    public void setMessage_sender(String message_sender) {
        this.message_sender = message_sender;
    }

    public String getMessage_receiver() {
        return message_receiver;
    }

    public void setMessage_receiver(String message_receiver) {
        this.message_receiver = message_receiver;
    }


}
