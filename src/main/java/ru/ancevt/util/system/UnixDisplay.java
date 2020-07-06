package ru.ancevt.util.system;

/**
 *
 * @author ancevt
 */
public class UnixDisplay {

    public static void main(String[] args) {
        System.out.println(UnixDisplay.format("{r}red{}{b}blue{}"));
    }

    public static final String RESET = "\u001B[0m";

    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    public static final String BLACK_BACKGROUND = "\u001B[40m";
    public static final String RED_BACKGROUND = "\u001B[41m";
    public static final String GREEN_BACKGROUND = "\u001B[42m";
    public static final String YELLOW_BACKGROUND = "\u001B[43m";
    public static final String BLUE_BACKGROUND = "\u001B[44m";
    public static final String PURPLE_BACKGROUND = "\u001B[45m";
    public static final String CYAN_BACKGROUND = "\u001B[46m";
    public static final String WHITE_BACKGROUND = "\u001B[47m";

    private static final String B = "\\{a\\}";
    private static final String R = "\\{r\\}";
    private static final String G = "\\{g\\}";
    private static final String Y = "\\{y\\}";
    private static final String BL = "\\{b\\}";
    private static final String P = "\\{p\\}";
    private static final String C = "\\{c\\}";
    private static final String W = "\\{w\\}";

    private static final String BG_B = "\\{A\\}";
    private static final String BG_R = "\\{R\\}";
    private static final String BG_G = "\\{G\\}";
    private static final String BG_Y = "\\{Y\\}";
    private static final String BG_BL = "\\{B\\}";
    private static final String BG_P = "\\{P\\}";
    private static final String BG_C = "\\{C\\}";
    private static final String BG_W = "\\{W\\}";

    private static final String RES = "\\{\\}";

    private static final String EMPTY = "";

    private static boolean enabled = true;

    public static String format(String source) {
        if (!enabled) {
            source = source.replaceAll(B, EMPTY);
            source = source.replaceAll(R, EMPTY);
            source = source.replaceAll(G, EMPTY);
            source = source.replaceAll(Y, EMPTY);
            source = source.replaceAll(BL, EMPTY);
            source = source.replaceAll(P, EMPTY);
            source = source.replaceAll(C, EMPTY);
            source = source.replaceAll(W, EMPTY);

            source = source.replaceAll(BG_B, EMPTY);
            source = source.replaceAll(BG_R, EMPTY);
            source = source.replaceAll(BG_G, EMPTY);
            source = source.replaceAll(BG_Y, EMPTY);
            source = source.replaceAll(BG_BL, EMPTY);
            source = source.replaceAll(BG_P, EMPTY);
            source = source.replaceAll(BG_C, EMPTY);
            source = source.replaceAll(BG_W, EMPTY);

            source = source.replaceAll(RES, EMPTY);

            return source;
        }

        source = source.replaceAll(B, BLACK);
        source = source.replaceAll(R, RED);
        source = source.replaceAll(G, GREEN);
        source = source.replaceAll(Y, YELLOW);
        source = source.replaceAll(BL, BLUE);
        source = source.replaceAll(P, PURPLE);
        source = source.replaceAll(C, CYAN);
        source = source.replaceAll(W, WHITE);

        source = source.replaceAll(BG_B, BLACK_BACKGROUND);
        source = source.replaceAll(BG_R, RED_BACKGROUND);
        source = source.replaceAll(BG_G, GREEN_BACKGROUND);
        source = source.replaceAll(BG_Y, YELLOW_BACKGROUND);
        source = source.replaceAll(BG_BL, BLUE_BACKGROUND);
        source = source.replaceAll(BG_P, PURPLE_BACKGROUND);
        source = source.replaceAll(BG_C, CYAN_BACKGROUND);
        source = source.replaceAll(BG_W, WHITE_BACKGROUND);

        source = source.replaceAll(RES, RESET);

        return source;
    }

    public static boolean autodect() {
        try {
            setEnabled(System.getProperty("user.home").startsWith("/home/"));
        } catch (Exception ex) {
            setEnabled(false);
        }
        return isEnabled();
    }

    public static boolean isEnabled() {
        return enabled;
    }

    public static void setEnabled(boolean enabled) {
        UnixDisplay.enabled = enabled;
    }
    
    public final void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
