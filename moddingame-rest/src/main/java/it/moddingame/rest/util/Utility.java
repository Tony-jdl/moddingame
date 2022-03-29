package it.moddingame.rest.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 *
 */
public class Utility {

    public static boolean isNull(String str) {
        return str == null || str.trim().equals("");
    }

    public static boolean isNull(String[] str) {
        return str == null || str.length == 0;
    }

    public static boolean isNull(Integer integer) {
        return integer == null || integer.equals(new Integer("-1"));
    }

    public static boolean isNull(InputStream[] is) {
        return is == null || is.length == 0;
    }

    public static ByteArrayOutputStream inputStreamToByteArrayOutputStream(
            InputStream is) throws IOException {
        byte buf[] = new byte[4000];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int dataSize;
        try {
            while ((dataSize = is.read(buf)) != -1) {
                baos.write(buf, 0, dataSize);
            }
        } finally {
            baos.flush();
            if (is != null) {
                is.close();
            }
        }
        return baos;
    }

}
