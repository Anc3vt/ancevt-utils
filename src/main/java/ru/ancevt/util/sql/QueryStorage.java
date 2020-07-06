package ru.ancevt.util.sql;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import ru.ancevt.util.string.ToStringBuilder;

/**
 * @author ancevt
 */
public abstract class QueryStorage {

    private static final String DELIMITER = "@";

    private final Map<String, String> map;

    public QueryStorage() {
        map = new HashMap<>();
    }

    public void load(InputStream inputStream) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            final StringBuilder stringBuilder = new StringBuilder();
            String name = null;

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.trim().startsWith(DELIMITER)) {
                    if (name != null) {
                        map.put(name, stringBuilder.toString());
                        stringBuilder.setLength(0);
                    }
                    name = line.trim().substring(1).trim();
                } else {
                    stringBuilder.append(line).append('\n');
                }
            }

            if (name != null) {
                map.put(name, stringBuilder.toString().trim());
                stringBuilder.setLength(0);
            }
        }
    }

    public void load(File file) throws FileNotFoundException, IOException {
        load(new FileInputStream(file));
    }

    public String getQueryString(String name) {
        return map.get(name);
    }
    
    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("size", map.size())
            .build();
    }

}
