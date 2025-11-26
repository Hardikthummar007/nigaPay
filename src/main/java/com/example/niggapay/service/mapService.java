package com.example.niggapay.service;

import com.example.niggapay.entity.PlaceDetailsDTO;
import com.example.niggapay.entity.suggetionDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.awt.*;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class mapService {

    @Autowired
    RestTemplate restTemplate;

    private final String accessToken = "pk.eyJ1IjoiaGFyZGlrdGh1bW1hciIsImEiOiJjbWlldzBqMm0wNWt3M2RzYjhzZGR2MTJyIn0.5xVfmMtLJNriMOAnhBrzWw";

    // ----------------------------
    // Get autocomplete suggestions
    // ----------------------------
    public ResponseEntity<?> getSuggestions(String query, String sessionToken)
            throws JsonProcessingException {

        String url = UriComponentsBuilder
                .fromHttpUrl("https://api.mapbox.com/search/searchbox/v1/suggest")
                .queryParam("q", query)
                .queryParam("limit", 5)
                .queryParam("session_token", sessionToken)
                .queryParam("access_token", accessToken)
                .toUriString();

        String json = restTemplate.getForObject(url, String.class);

        ObjectMapper mapper = new ObjectMapper();

        Map<String,Object> map = mapper.readValue(json, new TypeReference<>() {});
        List<Map<String,Object>> list = (List<Map<String, Object>>) map.get("suggestions");

        List<suggetionDto> result = new ArrayList<>();

        for (Map<String,Object> s : list) {

            suggetionDto dto = new suggetionDto();

            dto.setName((String) s.getOrDefault("name", null));
            dto.setMapboxId((String) s.getOrDefault("mapbox_id", null));
            dto.setFullAddress((String) s.getOrDefault("full_address", null));
            dto.setPlaceFormatted((String) s.getOrDefault("place_formatted", null));
            dto.setFeatureType((String) s.getOrDefault("feature_type", null));

            dto.setDistance((Integer) s.getOrDefault("distance", null));

            Map<String,Object> context = (Map<String, Object>) s.get("context");
            if (context != null) {
                Map<String,Object> country = (Map<String, Object>) context.get("country");
                dto.setCountry(country == null ? null : (String) country.get("name"));

                Map<String,Object> region = (Map<String, Object>) context.get("region");
                dto.setRegion(region == null ? null : (String) region.get("name"));

                Map<String,Object> place = (Map<String, Object>) context.get("place");
                dto.setPlace(place == null ? null : (String) place.get("name"));
            }

            result.add(dto);
        }

        return new ResponseEntity<>(result,HttpStatus.OK);
    }
    // ----------------------------
    // Get place details
    // ----------------------------
    public ResponseEntity<?> getPlaceDetails(String mapboxId, String sessionToken) throws JsonProcessingException {

        String url = UriComponentsBuilder
                .fromHttpUrl("https://api.mapbox.com/search/searchbox/v1/retrieve/" + mapboxId)
                .queryParam("session_token", sessionToken)
                .queryParam("access_token", accessToken)
                .toUriString();


String json=        restTemplate.getForObject(url, String.class);

        ObjectMapper mapper=new ObjectMapper();
   Map<String,Object>     map=mapper.readValue(
                json,
                new TypeReference<Map<String,Object>>() {
                }
        );


        List<Map<String,Object>> features=(List<Map<String, Object>>) map.get("features");

        Map<String,Object> feature=features.get(0);

        Map<String,Object> geometry=(Map<String,Object>)feature.get("geometry");
        List<Double> LongLat=(List<Double>) geometry.get("coordinates");

        double longi=LongLat.get(0);
        double lat=LongLat.get(1);


      Map<String,Object> properties = (Map<String, Object>) feature.get("properties");

      String fullAddress= properties.get("full_address").toString();

      String  name=properties.get("name").toString();
      String place_formatted=properties.get("place_formatted").toString();
      String humanReadableAddress=properties.get("name").toString();


        PlaceDetailsDTO dto = new PlaceDetailsDTO();
        dto.setLatitude(lat);
        dto.setLongitude(longi);
        dto.setName(name);
        dto.setPlaceFormatted(place_formatted);
        dto.setFullAddress(fullAddress);
        dto.setHumanReadableAddress(humanReadableAddress);




        return new ResponseEntity<>(dto, HttpStatus.OK);


    }





    public ResponseEntity<?> getDistance(double longi1, double lat1, double longi2, double lat2) throws Exception {

        String url = "https://api.mapbox.com/directions/v5/mapbox/driving/"
                + longi1 + "," + lat1 + ";" + longi2 + "," + lat2
                + "?overview=false&access_token=" + accessToken;

        String json = restTemplate.getForObject(url, String.class);

        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> map = mapper.readValue(json, new TypeReference<Map<String, Object>>() {});

        // Extract the "routes" array
        List<Map<String, Object>> routes = (List<Map<String, Object>>) map.get("routes");

        if (routes != null && !routes.isEmpty()) {
            Map<String, Object> firstRoute = routes.get(0);

            double distance = ((Number) firstRoute.get("distance")).doubleValue(); // in meters
            double duration = ((Number) firstRoute.get("duration")).doubleValue(); // in seconds

            System.out.println(duration/60);
            // Create a response DTO
            Map<String, Object> response = Map.of(
                    "distance", Math.ceil(distance/1000),
                    "duration_", duration/60
            );

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(404).body(Map.of("error", "No route found"));
        }
    }
}