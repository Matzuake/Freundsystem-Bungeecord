package net.blocklords.freundsystem.lang;

import net.blocklords.freundsystem.Main;

public class MessageHandler {
    public static String getMessage(String code)
    {
        if (Main.lang.equalsIgnoreCase("ENGLISH")) {
            return English.getMessage(code);
        }
        if (Main.lang.equalsIgnoreCase("GERMAN")) {
            return German.getMessage(code);
        }
        return "Please use 'German' oder 'English' as a Language!";
    }
}
