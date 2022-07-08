package com.example.prueba_java.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/asteroids")
public class TaskController {


    @GetMapping(value = "/callclienthello")
    private String getHelloClient() {
        String uri = "http://localhost:8080/hello";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        return result;
    }

    @GetMapping (value = "/asteroids")
    public JsonNode getAsteroids() throws JsonProcessingException {
        String url= "https://api.nasa.gov/neo/rest/v1/feed?start_date=2022-07-08&end_date=2022-07-12&api_key=DEMO_KEY";
        RestTemplate restTemplate = new RestTemplate();
        String asteroid = restTemplate.getForObject(url, String.class);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(asteroid);
    }


}
