package com.practice.utils.dataReader;

import com.jayway.jsonpath.JsonPath;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class JsonReader {

    private final String TEST_DATA_PATH = "src/test/resources/test-data/";
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
            JSONObject data = (JSONObject) new JSONParser().parse(new FileReader(TEST_DATA_PATH + jsonFileName + ".json"));
            jsonReader = data.toJSONString();
        } catch (Exception e) {
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
            return "";
        }
    }
}