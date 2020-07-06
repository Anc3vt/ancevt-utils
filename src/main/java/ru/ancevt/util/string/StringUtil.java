package ru.ancevt.util.string;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author ancevt
 */
public class StringUtil {

    public static final String EMPTY = "";
    public static final String END_LINE = String.format("%n");
    private static final int DEFAULT_RANDOM_STRING_LENGTH = 16;
    private static final String CHARACTERS
        = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        + "0123456789"
        + "abcdefghijklmnopqrstuvxyz";
    

    public static void main(String[] args) {
        final String text = generateLargeText();

        System.out.println("text.length(): " + text.length());
        System.out.println("ba.length: " + text.getBytes().length);
    }

    public static final boolean isNumeric(final char c) {
        return c >= '0' && c <= '9';
    }
    
    public static final String cut(String source, int factor) {
        if (source == null) {
            throw new NullPointerException("source must be not null");
        }

        if (source.length() > factor) {
            return source.substring(0, factor) + "..." + source.substring(source.length() - 10);
        }

        return source.trim();
    }

    public static String generateLargeText() {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 655360; i++) {
            sb.append(i).append(' ');
        }
        return sb.toString();
    }

    public static final String generateRandomString() {
        return generateRandomString(DEFAULT_RANDOM_STRING_LENGTH);
    }

    public static final String generateRandomString(int n) {
        final StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            int index = (int) (Math.random() * CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }

        return sb.toString();
    }
    
    public static final String getFormattedDate(boolean forFileName) {
        final String pattern = forFileName ? 
            "yyyy_MM_dd__hh_mm_ss_SSS" :
            "yyyy MM dd hh:mm:ss.SSS";
        
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, new Locale("ru", "RU"));
        final String date = simpleDateFormat.format(new Date());
        return date;
    }
}
