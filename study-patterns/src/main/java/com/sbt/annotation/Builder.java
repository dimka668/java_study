package com.sbt.annotation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by SBT-Klyshov-DA on 26.06.2018.
 */
public class Builder {
    public static void main(String[] args) throws IOException {
        MessageListner messageListner = new MessageListner();
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        Boolean exitFlag = false;
        while (!exitFlag) {
            String cmd = console.readLine();
            MessageReceivedEvent event = new MessageReceivedEvent(cmd);
            messageListner.onMessageReceived(event);
            if (cmd.equals("exit")) exitFlag=true;
        }
    }
}
