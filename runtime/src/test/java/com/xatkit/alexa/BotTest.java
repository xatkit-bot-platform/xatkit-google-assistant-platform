package com.xatkit.alexa;

import com.xatkit.Xatkit;

public class BotTest {

    public static void main(String[] args) {

        Xatkit.main(new String[]{"<Path of the bot's properties file>"});

        try {
            Thread.sleep(10000000);
        }catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
