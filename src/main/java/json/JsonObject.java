package json;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {
    private final Map<String, Json> pairs = new HashMap<>();

    public JsonObject(JsonPair... jsonPairs) {
        for (JsonPair pair : jsonPairs) {
            add(pair);
        }
    }

    @Override
    public String toJson() {
        StringBuilder stringObj = new StringBuilder();
        if (this.pairs.isEmpty()) {
            return "{}";
        }
        for (Map.Entry<String, Json> entry : this.pairs.entrySet()) {
            stringObj.append(entry.getKey());
            stringObj.append(": ");
            stringObj.append(entry.getValue().toJson());
            stringObj.append(", ");
        }
        return "{" + stringObj.substring(0, stringObj.length() - 2) + "}";
    }

    public void add(JsonPair jsonPair) {
        this.pairs.put(jsonPair.key, jsonPair.value);
    }

    public Json find(String name) {
        return this.pairs.get(name);
    }

    public JsonObject projection(String... names) {
        JsonObject filtered = new JsonObject();
        for (String name : names) {
            if (find(name) != null) {
                filtered.add(new JsonPair(name, find(name)));
            }
        }
        return filtered;
    }
}
