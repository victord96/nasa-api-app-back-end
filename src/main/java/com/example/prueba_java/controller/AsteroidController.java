package com.example.prueba_java.controller;

import com.example.prueba_java.databinding.ComplexDataBindingJackson;
import com.example.prueba_java.databinding.DataSource;
import com.example.prueba_java.databinding.NeoWsDataJackson;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/asteroids")
public class AsteroidController {

    @GetMapping(value = "/{days}")
    public Map<String, List<String>> filtrar_asteroides_por_dias(@PathVariable int days) throws JsonProcessingException {

        /* Ìnstanciamos y utilizamos el objeto restTemplate  para parsear el resultado de la llamada a un objeto String */
        RestTemplate restTemplate = new RestTemplate();
        DataSource datasource = new DataSource();
        String json = restTemplate.getForObject(datasource.getUrl(), String.class);

        /* Utilizaremos Jackson para manipular el objeto json, por lo que
        instanciaremos un ObjectMapper como punto de entrada */
        NeoWsDataJackson NeoWsDataJackson = new ObjectMapper().
                registerModule(new JavaTimeModule()).
                readValue(json, NeoWsDataJackson.class);
        ComplexDataBindingJackson complexDataBindingJackson = new ComplexDataBindingJackson();
        Map<String, List<String>> asteroides = complexDataBindingJackson.devolverdetalles(NeoWsDataJackson, days);

        return asteroides;
    }

}

