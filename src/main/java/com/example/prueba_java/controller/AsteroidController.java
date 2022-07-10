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
    @GetMapping(value = "/asteroids")
    public JsonNode getAsteroids() throws JsonProcessingException {
        /* Llamada a la API de la nasa */
        String url = "https://api.nasa.gov/neo/rest/v1/feed?start_date=2022-07-08&end_date=2022-07-12&api_key=DEMO_KEY";
        /* Ìnstanciamos y utilizamos el objeto restTemplate  para parsear el resultado de la llamada a un objeto String */
        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject(url, String.class);
        /* Utilizaremos Jackson para manipular el objeto json, por lo que
        instanciaremos un ObjectMapper como punto de entrada */
        ObjectMapper mapper = new ObjectMapper();
        /* Mapeamos el json para pasarlo como objeto JsonNode */
        JsonNode JsonNode = mapper.readTree(json);
        /* Invocamos una función para filtrar la información que necesitamos del JsonNode */
        JsonNode asteroide = filtrarJsoncondatossolicitados(JsonNode);
        /* Finalmente, retornaremos nuestro jsonnode con los datos de los asteroides */
        return asteroide;
    }

    /* Función personalizada para poder filtrar el JsonNode, almacenando los valores contenidos en la llamada a la API */
    public JsonNode filtrarJsoncondatossolicitados(JsonNode jsonNode) {
        /* Creamos ArrayList para introducir los datos que filtraremos para generar una request */
        ArrayList<String> listaasteroides = new ArrayList<String>();
        jsonNode = jsonNode.path("near_earth_objects");
        /* Recorremos el JsonNode y añadimos al ArrayList los valores que necesitamos */
        for (JsonNode jsonnodevalues : jsonNode) {
            for (JsonNode neo : jsonnodevalues) {
                /* Añadimos al Arraylist los datos contenidos en el jsonnode con valor de name */
                listaasteroides.add(neo.path("name").toString());
                /* Obtenemos el diametro correspondiente a cada asteroide a traves de la funcion path */
                for (JsonNode neo2 : neo) {
                    /* Como solo interesa la medida en KM, lo comprobamos
                    y calculamos la media del diametro para añadirla al arrayList */
                    if (neo2.has("kilometers")) {
                        JsonNode diametro_km = neo2.path("kilometers");
                        Float diameter_min = obtenciondefloatatravesdejson(diametro_km, "estimated_diameter_min");
                        Float diameter_max = obtenciondefloatatravesdejson(diametro_km, "estimated_diameter_max");
                        Float media = (diameter_min + diameter_max) / 2;
                        String media_str = String.valueOf(media);
                        listaasteroides.add(media_str);
                    }
                    for (JsonNode neo3 : neo2) {
                        /* Añadimos al Arraylist los datos contenidos en el jsonnode con valor de fecha */
                        if (neo3.has("close_approach_date")) {
                            String fecha = obtenciondestratravesdejson(neo3, "close_approach_date");
                            listaasteroides.add(fecha);
                        }
                        /* Añadimos al Arraylist los datos contenidos en el jsonnode con valor de planeta */
                        if (neo3.has("orbiting_body")) {
                            String planeta = obtenciondestratravesdejson(neo3, "orbiting_body");
                            listaasteroides.add(planeta);
                        }
                        for (JsonNode neo4 : neo3) {
                            /* Añadimos al Arraylist los datos contenidos en el jsonnode con valor de velocidad */
                            if (neo4.has("kilometers_per_hour")) {
                                String velocidad = obtenciondestratravesdejson(neo4, "kilometers_per_hour");
                                listaasteroides.add(velocidad);
                            }
                        }
                    }
                }
            }
        }
        /* Para poder devolver los datos filtrados, debemos transformar el ArrayList
         de tipo String en un JsonNode */
        JsonNode asteroides = deArrayListaAJsonNode(listaasteroides);
        return asteroides;
    }

    /* Mapeamos el arraylist hacia un arraynode, que necesitaremos para añadirlo como objectnode
     * para poder crear un JSON a partir de este objeto */
    public JsonNode deArrayListaAJsonNode(ArrayList<String> listaasteroides) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectnodeneo = mapper.createObjectNode();
        ArrayNode arraynodeneo = mapper.valueToTree(listaasteroides);
        ObjectNode neoNode = mapper.valueToTree(objectnodeneo);
        neoNode.putArray("names").addAll(arraynodeneo);
        JsonNode asteroides = mapper.createObjectNode().set("asteroides", neoNode);
        return asteroides;
    }
    /* Funcion para obtener los numeros solicitados del json dispuesto */
    public Float obtenciondefloatatravesdejson(JsonNode neo, String campo_clave) {
        Float resultado = neo.path(campo_clave).floatValue();
        return resultado;
    }

    /* Funcion para obtener los datos solicitados del json dispuesto */
    public String obtenciondestratravesdejson(JsonNode neo, String campo_clave) {
        String resultado = neo.path(campo_clave).toString();
        return resultado;
    }
}

