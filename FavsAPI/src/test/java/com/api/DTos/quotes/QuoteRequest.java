package com.api.DTos.quotes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QuoteRequest {
    private QuoteDTO quote;


    public QuoteDTO getQ() {
        return quote;
    }

    public void setQ(QuoteDTO quote) {
        this.quote = quote;
    }
}
