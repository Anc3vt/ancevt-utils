package ru.ancevt.util;

import java.util.Scanner;

/**
 *
 * @author ancevt
 */
public class Version implements Comparable<Version> {

    public static void main(String[] args) {

        System.out.println("type current version");

        final Scanner scanner = new Scanner(System.in);
        final String currentVersionString = scanner.nextLine();
        final Version currentVersion = new Version(currentVersionString);

        System.out.println("type server version");
        final String serverVersionString = scanner.nextLine();
        final Version serverVersion = new Version(serverVersionString);

        if (currentVersion.compareTo(serverVersion) > 0) {
            System.out.println("current > server");
        } else if (currentVersion.compareTo(serverVersion) < 0) {
            System.out.println("current < server");
        } else {
            System.out.println("current == server");
        }

    }

    private int major;
    private int minor;
    private int release;
    private int build;

    public Version() {

    }

    public Version(String version) {
        final String[] s = version.split("\\.");
        this.major = Integer.valueOf(s[0]);
        this.minor = Integer.valueOf(s[1]);
        this.release = Integer.valueOf(s[2]);
        this.build = Integer.valueOf(s[3]);
    }

    public Version(int major, int minor, int release, int build) {
        this.major = major;
        this.minor = minor;
        this.release = release;
        this.build = build;
    }

    @Override
    public int compareTo(Version o) {
        return toString().compareTo(o.toString());
    }

    public int getMajor() {
        return major;
    }

    public void setMajor(int major) {
        this.major = major;
    }

    public int getMinor() {
        return minor;
    }

    public void setMinor(int minor) {
        this.minor = minor;
    }

    public int getRelease() {
        return release;
    }

    public void setRelease(int release) {
        this.release = release;
    }

    public int getBuild() {
        return build;
    }

    public void setBuild(int build) {
        this.build = build;
    }

    @Override
    public String toString() {
        return major + "." + minor + "." + release + "." + build + "";
    }

}
