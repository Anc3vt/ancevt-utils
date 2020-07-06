package ru.ancevt.util;

import ru.ancevt.util.string.ToStringBuilder;

/**
 *
 * @author ancevt
 */
public class Range {

    private static final String DELIM = "-";

    private int from;
    private int to;
    
    public Range(String source) {
        this(source, false);
    }

    public Range(String source, boolean minusOne) {
        try {
            source = source.trim();
            final String[] s = source.split(DELIM);
            
            if(s.length == 1) {
                from = Integer.valueOf(s[0]) - (minusOne ? 1 : 0);
                to = 0;
                return;
            }
            
            final String left = s[0];
            final String right = s[1];

            from = Integer.valueOf(left) - (minusOne ? 1 : 0);
            to = Integer.valueOf(right) - (minusOne ? 1 : 0);
        } catch (Exception ex) {
            ex.printStackTrace();
            from = 0;
            to = 99;
        }
        
        if(to < from) {
            throw new RuntimeException("invalid range \"" + source + "\"");
        }
    }

    public final int getFrom() {
        return from;
    }

    public final int getTo() {
        return to;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("from", from)
                .append("to", to)
                .build();
    }

}
