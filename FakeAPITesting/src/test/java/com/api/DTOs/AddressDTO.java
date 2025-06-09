package com.api.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * AddressDTO is a Data Transfer Object (DTO) that represents an address.
 * It includes details such as city, street, number, zipcode, and geolocation.
 */

public class AddressDTO {

    @JsonProperty("city")
        private String city;
    @JsonProperty("street")
        private String street;
    @JsonProperty("number")
        private int number;
    @JsonProperty("zipcode")
        private String zipcode;
        private GeolocationDTO geolocation;

        // Constructors
        public AddressDTO() {}

        /**
         * @param city
         * @param street
         * @param number
         * @param zipcode
         * @param geolocation
         */
        public AddressDTO(String city, String street, int number, String zipcode, GeolocationDTO geolocation) {
            this.city = city;
            this.street = street;
            this.number = number;
            this.zipcode = zipcode;
            this.geolocation = geolocation;
        }


        // Getters and Setters
        /**
         * @return
         */
        public String getCity() { return city; }
        public void setCity(String city) { this.city = city; }

        public String getStreet() { return street; }
        public void setStreet(String street) { this.street = street; }

        public int getNumber() { return number; }
        public void setNumber(int number) { this.number = number; }

        public String getZipcode() { return zipcode; }
        public void setZipcode(String zipcode) { this.zipcode = zipcode; }

        public GeolocationDTO getGeolocation() { return geolocation; }
        public void setGeolocation(GeolocationDTO geolocation) { this.geolocation = geolocation; }
    }

