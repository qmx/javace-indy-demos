package me.qmx.javace.indydemo;

import java.util.HashMap;
import java.util.Map;

public class JSObject {

    private Map<String, Map<String, Object>> properties = new HashMap<String, Map<String, Object>>();

    public Map<String, Map<String, Object>> getProperties() {
        return properties;
    }
}
