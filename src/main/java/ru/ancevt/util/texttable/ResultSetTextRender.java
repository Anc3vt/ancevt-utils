package ru.ancevt.util.texttable;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultSetTextRender {

    private static final int DEFAULT_MAX_STRING_LENGTH = 200;

    public static String run(ResultSet resultSet, int maxStringWidth) {
        final TextTable table = new TextTable();
        table.setMaxStringLength(maxStringWidth);

        try {
            final ResultSetMetaData metaData = resultSet.getMetaData();
            final List<String> columnNames = new ArrayList<>();

            for (int i = 0; i < metaData.getColumnCount(); i++) {
                columnNames.add(metaData.getColumnLabel(i + 1));
            }
            table.setColumnNames(columnNames.toArray(new String[]{}));

            while (resultSet.next()) {
                final List<Object> objects = new ArrayList<>();
                for (String columnName : columnNames) {
                    objects.add(resultSet.getString(columnName));
                }

                table.addRow(objects);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return table.render();
    }

    public static String run(ResultSet resultSet) {
        return run(resultSet, DEFAULT_MAX_STRING_LENGTH);
    }

    public static void println(ResultSet resultSet, int maxStringWidth) {
        System.out.println(run(resultSet, maxStringWidth));
    }

    public static void println(ResultSet resultSet) {
        println(resultSet, DEFAULT_MAX_STRING_LENGTH);
    }
}
