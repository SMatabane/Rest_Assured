package com.api.utilities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class JsonReader {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger logs = Logger.getLogger(JsonReader.class);

    /**
     * Reads a JSON file and extracts a specific object based on the given key.
     *
     * @param path     The file path of the JSON.
     * @param key      The object key to extract (e.g., "register", "Session").
     * @param dtoClass The class type to deserialize into.
     * @param <T>      Generic return type.
     * @return The deserialized object of type T.
     */
    public static <T> T readFromJsonFile(String path, String key, Class<T> dtoClass) {
        try {
            logs.info("Reading JSON file: " + path);

            // Read full JSON tree
            JsonNode rootNode = objectMapper.readTree(new File(path));

            // Extract the specified object by key
            JsonNode targetNode = rootNode.get(key);
            if (targetNode == null) {
                throw new RuntimeException("Key '" + key + "' not found in JSON file: " + path);
            }

            // Convert the extracted JSON node to the specified DTO
            return objectMapper.treeToValue(targetNode, dtoClass);

        } catch (IOException e) {
            logs.error("Failed to read JSON file: " + e.getMessage());
            throw new RuntimeException("Failed to read JSON file: " + e.getMessage());
        }
    }
}
