package ru.ancevt.util.time;

/**
 *
 * @author ancevt
 */
public class MillisecondsFormatter {

    public static void main(String[] args) {
        System.out.println(format(30 * 60 * 1000 + 15 * 1000));
    }

    public static final String format(long ms) {

        long totalSeconds = ms / 1000;

        int d = 0;
        int h = 0;
        int m = 0;
        int s = 0;

        while (totalSeconds > 0) {
            totalSeconds--;
            s++;

            if (s > 59) {
                s = 0;
                m++;
            }

            if (m > 59) {
                m = 0;
                s = 0;
                h++;
            }

            if (h > 23) {
                h = 0;
                m = 0;
                s = 0;
                d++;
            }

        }

        final StringBuilder sb = new StringBuilder();
        if (d > 0) {
            sb.append(d).append("d ");
        }
        if (h > 0) {
            sb.append(fix(h)).append("h ");
        }
        if (m > 0) {
            sb.append(fix(m)).append("m ");
        }
        if (s > 0) {
            sb.append(fix(s)).append("s");
        }

        return sb.toString();
    }

    private static String fix(long l) {
        return l + "";//l < 10 ? "0" + l : "" + l;
    }
}
