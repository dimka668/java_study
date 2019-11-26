package com.sbt.annotation;

/**
 * Created by SBT-Klyshov-DA on 26.06.2018.
 */
public class MessageReceivedEvent {
    String msg;

    public MessageReceivedEvent(String msg){
        this.msg = msg;
    }

    public String getMessage(){
        return this.msg;
    }
}
