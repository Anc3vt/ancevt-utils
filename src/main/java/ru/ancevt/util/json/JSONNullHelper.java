package ru.ancevt.util.json;

import org.json.JSONObject;

/**
 *
 * @author ancevt
 */
public class JSONNullHelper {
    
    public static final boolean isNullOrUndefined(JSONObject o, String property) {
        return !o.has(property) || o.isNull(property);
    }
    
    public static final String getStringOrNull(JSONObject o, String property) {
        if(isNullOrUndefined(o, property)) return null;
        return o.getString(property);
    }
    
    public static final int getIntOrZero(JSONObject o, String property) {
        if(isNullOrUndefined(o, property)) return 0;
        return o.getInt(property);
    }
    
    public static final JSONObject getJSONObjectOrNull(JSONObject o, String property) {
        if(isNullOrUndefined(o, property)) return null;
        return o.getJSONObject(property);
    }
}
