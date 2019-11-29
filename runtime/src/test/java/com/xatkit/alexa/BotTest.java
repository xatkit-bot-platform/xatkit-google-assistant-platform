package com.xatkit.alexa;

import com.xatkit.Xatkit;

public class BotTest {

    public static void main(String[] args) {

        Xatkit.main(new String[]{"C:\\Users\\Gwendal\\Documents\\Dev\\xatkit\\xatkit-dev\\src\\platforms\\xatkit" +
                "-google-assistant-platform\\examples\\GoogleBasicReply\\GoogleBasicReply.properties"});

        try {
            Thread.sleep(10000000);
        }catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
