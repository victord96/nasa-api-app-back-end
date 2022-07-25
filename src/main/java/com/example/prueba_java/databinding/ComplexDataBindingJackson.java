package com.example.prueba_java.databinding;



import java.util.*;

public class ComplexDataBindingJackson {


        public String returnneocount(NeoWsDataJackson neoWsDataJackson){
            return "NEO count: " + neoWsDataJackson.elementCount;
        }

        public Map<String, List<String>> devolverdetalles(NeoWsDataJackson neoWsDataJackson){
            Map<String, List<String>> lista_asteroides = new HashMap<String, List<String>>();
            Map<String, List<NeoDetails>> mymap = neoWsDataJackson.nearEarthObjects;
            int cont = 1;
            for (String key: mymap.keySet()) {
                List<NeoDetails> value = mymap.get(key);
                for (NeoDetails neoDetails: value) {
                    if (neoDetails.isPotentiallyHazardousAsteroid) {
                        List<String> asteroide = new ArrayList<String>();
                        asteroide.add("nombre: " + neoDetails.getName());
                        asteroide.add("diametro: " + calcular_diametro(neoDetails));
                        asteroide.add("velocidad: " + neoDetails.getCloseApproachData().get(0).getRelativeVelocity().getKilometersPerHour());
                        asteroide.add("fecha: " + neoDetails.getCloseApproachData().get(0).getCloseApproachDate());
                        asteroide.add("planeta: " + neoDetails.getCloseApproachData().get(0).getOrbitingBody());
                        lista_asteroides.put("asteroide: " + cont, asteroide);
                        cont++;
                    }
                }
            }

           lista_asteroides = ordenar_map_segun_el_numero_de_su_key(lista_asteroides);

            return lista_asteroides;
        }

        public Double calcular_diametro(NeoDetails neo){
            return neo.getEstimatedDiameter().getKilometers().getEstimatedDiameterMax()/
                    neo.getEstimatedDiameter().getKilometers().getEstimatedDiameterMin();
        }

        public Map<String, List<String>> ordenar_map_segun_el_numero_de_su_key(Map<String, List<String>> map){
            Map<String, List<String>> sortedMap = new LinkedHashMap<>();
            map.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEachOrdered(e -> sortedMap.put(e.getKey(), e.getValue()));
            return sortedMap;
        }

}