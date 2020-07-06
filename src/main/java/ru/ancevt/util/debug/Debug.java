package ru.ancevt.util.debug;


/**
 *
 * @author ancevt
 */
public class Debug {

    private static final String PROPERTY_NAME = "ancevt.debug";

    public static boolean isSet() {
        return System.getProperty(PROPERTY_NAME) != null
            && System.getProperty(PROPERTY_NAME).startsWith("1");
    }

    public static boolean contains(String string) {
        if (isSet()) {
            return System.getProperty(PROPERTY_NAME).contains(string);
        }
        return false;
    }
}
