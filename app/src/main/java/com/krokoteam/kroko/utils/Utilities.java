package com.krokoteam.kroko.utils;

public class Utilities {
    private static Utilities mInstance;

    public static Utilities getInstance() {
        if (mInstance == null) {
            mInstance = new Utilities();
        }
        return mInstance;
    }

    public String getAlphaNumericString() {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(15);
        for (int i = 0; i < 15; i++) {
            int index = (int)(AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }
}
