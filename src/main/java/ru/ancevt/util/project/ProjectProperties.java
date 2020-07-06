package ru.ancevt.util.project;

import java.io.IOException;
import java.util.Properties;

/**
 * @author ancevt
 */
public class ProjectProperties {

    private static boolean loaded;

    private static String name;
    private static String version;
    private static String groupId;
    private static String artifactId;
    private static String description;
    private static String dirname;

    private static void loadResource() throws IOException {
        if (loaded) {
            return;
        }

        final Properties p = new Properties();
        p.load(ProjectProperties.class.getClassLoader().getResourceAsStream("project.properties"));

        name = p.getProperty("name");
        version = p.getProperty("version");
        groupId = p.getProperty("groupId");
        artifactId = p.getProperty("artifactId");
        description = p.getProperty("description");
        dirname = p.getProperty("dirname");

        loaded = true;
    }

    public static String getDirname() throws IOException {
        loadResource();
        return dirname;
    }

    public static final String getDescription() throws IOException {
        loadResource();
        return description;
    }

    public static final String getName() throws IOException {
        loadResource();
        return name;
    }

    public static final boolean isLoaded() throws IOException {
        loadResource();
        return loaded;
    }

    public static final String getVersion() throws IOException {
        loadResource();
        return version;
    }

    public static final String getGroupId() throws IOException {
        loadResource();
        return groupId;
    }

    public static final String getArtifactId() throws IOException {
        loadResource();
        return artifactId;
    }

    public static final String getNameVersion() throws IOException {
        loadResource();
        return name + " " + version;
    }

    public static final String getNameVersionDescription() throws IOException {
        loadResource();
        return name + " " + version + " (" + description + ")";
    }

}
