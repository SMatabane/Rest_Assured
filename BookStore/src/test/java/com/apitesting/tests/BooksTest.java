package com.apitesting.tests;

import com.apitesting.dataproviders.JsonReader;
import com.apitesting.models.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class BooksTest extends BaseTests{


    private static final String token="";

    /**
     * Test: get List of books and write the ISBN on CSV file
     * @throws IOException
     */
    @Test(priority = 1)
    public void getBooks() throws IOException {

        Response response=getAPIRequest("/BookStore/v1/Books",200);
        BookResponse booksResponse = readJson(response.asString(), BookResponse.class);
        List<Book> books = booksResponse.getBooks();

        writeIsbnsToCsv(books, "isbns.csv");

    }

    /**
     * Test: add books from store
     * @param data
     * @throws JsonProcessingException
     */
    @Test(priority = 2,dataProvider = "addbooks", dataProviderClass = JsonReader.class,enabled = true)
    public void createBooks(JsonNode data) throws JsonProcessingException {
        List<ISbn> collectionOfIsbns = Arrays.asList(
                mapper.treeToValue(data.get("collectionOfIsbns"), ISbn[].class)
        );
        CollectionBooks books=new CollectionBooks(data.get("userId").asText(),collectionOfIsbns);

        String requestBody = convertToJson(books);

        Response response=postAPIRequest("/BookStore/v1/Books",token,requestBody,201);
        String listbooks=response.jsonPath().getString("books");


        assertNotNull(listbooks,"books are null");
        test.pass("Books retrieved ");


    }

    /**
     * Test : get a book
     * @param data
     */

    @Test(priority = 3,dataProvider = "bookIsbn", dataProviderClass = JsonReader.class,enabled = false)
    public void getBook(JsonNode data){

        String isbnValue=data.get("isbn").asText();
        String url="/BookStore/v1/Book";
        Response resp;

        if (data.get("type").asText().equalsIgnoreCase("valid")) {
            resp = getRequest(url,isbnValue, 200);
            String title=resp.jsonPath().getString("title");
            assertNotNull(title,"title is null");
            logs.info("Retrieved book successfully ");
            test.pass("Retrieved book successfully");
        } else if (data.get("type").asText().equalsIgnoreCase("invalid")) {
            resp = getRequest(url,isbnValue,  400);
            String message=resp.jsonPath().getString("message");
            assertEquals(message,"ISBN supplied is not available in Books Collection!");
            logs.info("Received expected error status code: " + resp.getStatusCode());
            test.pass("Received expected error status code: " + resp.getStatusCode());
        }




    }

    /**
     * Test: delete all books
     */
    @Test(priority = 4)
    public void deleteBooks() {
        String userId = "";
        String uri = "/BookStore/v1/Books";

        Response resp=deleteRequest(uri,userId,token,204);

        if ( resp.getContentType().contains("application/json")) {
            String message = resp.jsonPath().getString("message");
            assertEquals(message, "Books deleted successfully");
            logs.info("Books deleted successfully");
            test.pass("Books deleted successfully");

        }

    }

    /**
     * Test: Editing a book
     * @param data
     */
    @Test(priority = 5,dataProvider = "edit", dataProviderClass = JsonReader.class,enabled = false)
    public void editBooks(JsonNode data) throws JsonProcessingException {
        String isbn="9781449325862";
        String uri="/BookStore/v1/Books" +isbn;
        ReplaceIsbn isbns=new ReplaceIsbn(data.get("userId").asText(),data.get("isbn").asText());
        String body=convertToJson(isbns);

        Response response=editBook(uri,body,token,200);








    }







    private void writeIsbnsToCsv(List<Book> books, String fileName) throws IOException {
        File file = new File("src/test/resources/" + fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("isbn\n"); // header
            for (Book book : books) {
                writer.write(book.getIsbn() + "\n");
            }
        }
       logs.info("ISBNs written to " + file.getAbsolutePath());
    }



}
