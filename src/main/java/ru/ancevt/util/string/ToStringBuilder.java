package ru.ancevt.util.string;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author ancevt
 */
public class ToStringBuilder {

    private final StringBuilder stringBuilder;
    private final boolean formatted;
    private int propertyCount;
    private Object object;

    public ToStringBuilder(Object object) {
        this(object, false);
    }

    public ToStringBuilder(Object object, boolean formatted) {
        this.object = object;
        this.formatted = formatted;
        stringBuilder = new StringBuilder(object.getClass().getSimpleName());
        stringBuilder.append('[');

        propertyCount = 0;
    }

    public ToStringBuilder appendAll(String... keys) {
        for (int i = 0; i < keys.length; i++) {
            append(keys[i]);
        }
        return this;
    }

    public ToStringBuilder append(String key) {
        final Class clazz = object.getClass();

        try {
            final String get = createGetterNameGet(key);

            final Method method = clazz.getMethod(get);
            final Object result = method.invoke(object);

            return append(key, result);

        } catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            ex.printStackTrace();

        } catch (NoSuchMethodException ex) {
            try {
                final String is = createGetterNameIs(key);

                final Method method = clazz.getMethod(is);
                Object result;
                try {
                    result = method.invoke(object);
                    return append(key, result);
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex1) {
                    ex1.printStackTrace();
                }
            } catch (NoSuchMethodException ex1) {
                try {
                    final String has = createGetterNameHas(key);

                    final Method method = clazz.getMethod(has);
                    Object result = method.invoke(object);
                    return append(key, result);

                } catch (NoSuchMethodException |
                    SecurityException |
                    IllegalAccessException |
                    IllegalArgumentException |
                    InvocationTargetException ex2) {
                    ex2.printStackTrace();
                }
            }
        }
        return this;
    }

    public ToStringBuilder append(String key, Object value) {
        if (propertyCount > 0) {
            stringBuilder.append(',').append(' ');
        }

        if (formatted) {
            stringBuilder.append('\n').append('\t');
        }

        stringBuilder.append(key).append(':').append(' ');

        if (value instanceof String) {
            stringBuilder.append(StringUtil.cut(String.valueOf(value), 100));
        } else {
            stringBuilder.append(value);
        }
        propertyCount++;
        return this;
    }

    public final String build() {
        if (formatted) {
            stringBuilder.append('\n');
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    private static String createGetterNameGet(String key) {
        final char firstChar = key.charAt(0);
        final char upperCaseFirstChar = Character.toUpperCase(firstChar);
        final String restOfKey = key.substring(1);
        return "get" + upperCaseFirstChar + restOfKey;
    }

    private static String createGetterNameIs(String key) {
        final char firstChar = key.charAt(0);
        final char upperCaseFirstChar = Character.toUpperCase(firstChar);
        final String restOfKey = key.substring(1);
        return "is" + upperCaseFirstChar + restOfKey;
    }

    private static String createGetterNameHas(String key) {
        final char firstChar = key.charAt(0);
        final char upperCaseFirstChar = Character.toUpperCase(firstChar);
        final String restOfKey = key.substring(1);
        return "has" + upperCaseFirstChar + restOfKey;
    }
}
