package com.apitesting.dataproviders;

import com.apitesting.utils.FileConstants;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class JsonReader {

    /**
     * this method helps with reading data from json file.
     * @param key
     * @return
     */
    public static Object[][] getTestData(String key) {
        List<Object[]> data = new LinkedList<>();

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(new File(FileConstants.JSON_TEST_DATA));
            JsonNode arrayNode = root.get(key);

            if (arrayNode != null && arrayNode.isArray()) {
                for (JsonNode node : arrayNode) {
                    data.add(new Object[]{node});
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return data.toArray(new Object[0][]);
    }


    @DataProvider(name = "accountUser")
    public static Object[][] loginData() {
        return getTestData("user");
    }

    @DataProvider(name = "addbooks")
    public static Object[][] booksData() {
        return getTestData("books");
    }

    @DataProvider(name = "bookIsbn")
    public static Object[][] isbnsData() {
        return getTestData("isbns");
    }

    @DataProvider(name = "edit")
    public static Object[][] editBooksData() {
        return getTestData("editBook");
    }

    @DataProvider(name = "delete")
    public static Object[][] deleteBooksData() {
        return getTestData("deleteBook");
    }





}
