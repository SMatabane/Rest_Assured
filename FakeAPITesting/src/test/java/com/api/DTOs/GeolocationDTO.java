package com.api.DTOs;


import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * GeolocationDTO is a Data Transfer Object (DTO) that represents an geolocation
 */
public class GeolocationDTO {
    @JsonProperty("lat")
        private String lat;
        @JsonProperty("long")  // Maps "long" from JSON to "longitude" in your class
        private String longitude;

        // Constructors
        public GeolocationDTO() {}

        /**
         * @param lat
         * @param longitude
         */
        public GeolocationDTO(String lat, String longitude) {
            this.lat = lat;
            this.longitude = longitude;
        }

        // Getters and Setters
        public String getLat() { return lat; }
        public void setLat(String lat) { this.lat = lat; }

        public String getLongitude() { return longitude; }
        public void setLongitude(String longitude) { this.longitude = longitude; }
    }

