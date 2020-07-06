package ru.ancevt.util.debug;

/**
 *
 * @author ancevt
 */
public class WorkspaceChecker {
    public static final boolean check() {
        final String userDir = System.getProperty("user.dir");
        return userDir.contains("/ancevt/workspace");
    }
}
