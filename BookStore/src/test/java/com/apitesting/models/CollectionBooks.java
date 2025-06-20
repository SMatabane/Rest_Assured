package com.apitesting.models;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public class CollectionBooks {

        private String userId;
        private List<ISbn> collectionOfIsbns;

    public CollectionBooks(String userId, List<ISbn> collectionOfIsbns) {
        this.userId = userId;
        this.collectionOfIsbns = collectionOfIsbns;
    }

    public CollectionBooks() {
    }




    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<ISbn> getCollectionOfIsbns() {
        return collectionOfIsbns;
    }

    public void setCollectionOfIsbns(List<ISbn> collectionOfIsbns) {
        this.collectionOfIsbns = collectionOfIsbns;
    }
}


