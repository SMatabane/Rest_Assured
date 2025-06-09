package com.api.filters;


import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.apache.log4j.Logger;

public class CustomFilters implements Filter {

    protected static final Logger logs= Logger.getLogger(CustomFilters.class);


    /**
     *@param requestSpec Represents the request specification (headers, parameters, body, etc.) of the HTTP request.
     *@param responseSpec Represents the expected response specification (such as expected status code or body content).
     *ctx: Provides access to the filter context, which allows to control the flow of the request and response
     */
    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {

        Response response = ctx.next(requestSpec, responseSpec);
        logRequest(requestSpec);

        if(response.getStatusCode() != 200) {
            logs.error("ERROR: Request failed!");
            logs.error("Request: " + requestSpec.getMethod() + " " + requestSpec.getURI());
            logs.error("Response Code: " + response.getStatusCode());
            logs.error("Response Body: " + response.getBody().asString());
            logs.error("-------------------------------");
        }else {
            logs.info(" SUCCESS: Request to" +requestSpec.getURI()+ "\n returned " + response.getStatusCode() +"\n" +response.getBody().asString());
        }

        return response;

    }

    /**
     * @param requestSpec
     */
    private void logRequest(FilterableRequestSpecification requestSpec){
        logs.info("REQUEST METHOD: " + requestSpec.getMethod() + "\n" +requestSpec.getURI());
        logs.info("Headers: "+ requestSpec.getHeaders());
        logs.info("Body: " +requestSpec.getBody());
    }
}
