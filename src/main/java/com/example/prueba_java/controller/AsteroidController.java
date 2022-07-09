package com.example.prueba_java.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.spring.web.json.Json;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/asteroids")
public class AsteroidController {


    @GetMapping(value = "/callclienthello")
    private String getHelloClient() {
        String uri = "http://localhost:8080/hello";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        return result;
    }

    @GetMapping(value = "/asteroids")
    public JsonNode getAsteroids() throws JsonProcessingException {
        /* Obtenemos la API de la nasa */
        String url = "https://api.nasa.gov/neo/rest/v1/feed?start_date=2022-07-08&end_date=2022-07-12&api_key=DEMO_KEY";
        /* Ìnstanciamos y utilizamos el objeto restTemplate  para parsear el resultado de la llamada a un objeto String */
        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject(url, String.class);
        /* Utilizaremos Jackson para manipular el objeto json, por lo que
        instanciaremos un ObjectMapper como punto de entrada */
        ObjectMapper mapper = new ObjectMapper();
        /* Mapeamos el json para pasarlo como objeto JsonNode */
        JsonNode JsonNode = mapper.readTree(json);
        /* Invocamos una función personalizada para poder filtrar la información que necesitamos del JsonNode */
        JsonNode asteroide = filtrarJsonNodepornombre(JsonNode);
        /* Finalmente, retornaremos nuestro jsonnode con los names de los asteroides */
        return asteroide;
    }

    /* Función personalizada para poder filtrar el JsonNode utilizando los valores contenidos en name */
    public JsonNode filtrarJsonNodepornombre(JsonNode jsonNode) {
        /* Creamos ArrayList para obtener una clave, dentro de la cual se encuentran los valores name que necesitamos */
        ArrayList<String> listanombreasteroides = new ArrayList<String>();
        jsonNode = jsonNode.path("near_earth_objects");
        JsonNode jsonNodeneo;
        /* Recorremos el JsonNode y añadimos al ArrayList los valores name que necesitamos */
        for (JsonNode jsonnodevalues : jsonNode) {
            for (JsonNode neo : jsonnodevalues) {
                listanombreasteroides.add(neo.path("name").toString());
            }
        }
        /* Mapeamos el arraylist hacia un arraynode, que necesitaremos para añadirlo como objectnode
        * para poder crear un JSON a partir de este objeto */
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectnodeneo = mapper.createObjectNode();
        ArrayNode arraynodeneo = mapper.valueToTree(listanombreasteroides);
        ObjectNode neoNode = mapper.valueToTree(objectnodeneo);
        neoNode.putArray("names").addAll(arraynodeneo);
        JsonNode asteroide = mapper.createObjectNode().set("asteroides", neoNode);
        return asteroide;
    }
}

