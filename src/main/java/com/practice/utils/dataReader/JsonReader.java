package com.practice.utils.dataReader;

import com.jayway.jsonpath.JsonPath;
import com.practice.utils.Indexes;
import com.practice.utils.logs.LogsManager;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class JsonReader {

    String jsonReader;
    String jsonFileName;

    /**
     * Constructs a JsonReader instance by loading and parsing a JSON file.
     * Reads the JSON file from the TEST_DATA_PATH directory and converts it to a string.
     * If the file cannot be read, initializes with an empty JSON object to prevent null pointer exceptions.
     *
     * @param jsonFileName the name of the JSON file (without the .json extension)
     */
    public JsonReader(String jsonFileName) {
        this.jsonFileName = jsonFileName;
        try {
            JSONObject data = (JSONObject) new JSONParser().parse(new FileReader(Indexes.TEST_DATA_PATH + jsonFileName + ".json"));
            jsonReader = data.toJSONString();
        } catch (Exception e) {
            LogsManager.warn("Failed to load JSON test data:", jsonFileName, "Reason:", e.getMessage());
            jsonReader = "{}"; // Initialize to an empty JSON object to avoid null pointer exceptions
        }
    }

    /**
     * Retrieves a value from the loaded JSON data using a JSONPath expression.
     * JSONPath allows for flexible querying of nested JSON structures.
     *
     * @param jsonPath the JSONPath expression to locate the desired value
     * @return the value found at the specified JSONPath, or an empty string if not found or an error occurs
     */
    public String getJsonData(String jsonPath) {
        try {
            return JsonPath.read(jsonReader, jsonPath);
        } catch (Exception e) {
            LogsManager.warn("Failed to resolve JSON path:", jsonPath, "Reason:", e.getMessage());
            return "";
        }
    }
}