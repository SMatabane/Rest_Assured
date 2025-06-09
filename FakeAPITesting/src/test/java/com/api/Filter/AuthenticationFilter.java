package com.api.Filter;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.apache.log4j.Logger;

public class AuthenticationFilter implements Filter {

 private  Logger logs=Logger.getLogger(AuthenticationFilter.class);
 private Response response;


    /**
     *@param requestSpec Represents the request specification (headers, parameters, body, etc.) of the HTTP request.
     *@param responseSpec Represents the expected response specification (such as expected status code or body content).
     *ctx: Provides access to the filter context, which allows to control the flow of the request and response
     */
    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {

        try{

            response = ctx.next(requestSpec, responseSpec);

            if(response.getStatusCode() != 200) {
                logs.error("ERROR: Request failed!");
                logs.error("Request: " + requestSpec.getMethod() + " " + requestSpec.getURI());
                logs.error("Response Code: " + response.getStatusCode());
                logs.error("Response Body: " + response.getBody().asString());
                logs.error("-------------------------------");
            }else {
                logs.info(" SUCCESS: Request to" +requestSpec.getURI()+ "\n returned" + response.getStatusCode());
            }

            return  response ;
        }catch (Exception e) {
            logs.error("Error serializing data",e);
            throw new RuntimeException("Error serializing UserDTO", e);

        }

    }


}
