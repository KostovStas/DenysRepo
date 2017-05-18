package com.company.util;

import com.company.core.logging.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5HashCounter {

    private Md5HashCounter() {
    }

    public static String countMd5HashFrom(final String text) {
        try {
            final MessageDigest md = MessageDigest.getInstance("MD5");
            final byte[] array = md.digest(text.getBytes());
            final StringBuilder sb = new StringBuilder();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (final NoSuchAlgorithmException e) {
            Logger.out.debug("Unable to count 'MD5' hash from following text: {}!", text);
            return null;
        }
    }
}
