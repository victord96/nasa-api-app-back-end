package com.example.prueba_java.databinding;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ComplexDataBindingJackson {


    public String returnneocount(NeoWsDataJackson neoWsDataJackson) {
        return "NEO count: " + neoWsDataJackson.elementCount;
    }

    public Map<String, List<String>> devolverdetalles(NeoWsDataJackson neoWsDataJackson, int days) {
        Map<String, List<String>> lista_asteroides = new HashMap<String, List<String>>();
        Map<String, List<NeoDetails>> mymap = neoWsDataJackson.nearEarthObjects;
        int cont = 1;
        Date fecha_ayer = new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24));
        Date fecha_limite = new Date(System.currentTimeMillis() + ((1000 * 60 * 60 * 24) * days));
        for (String key : mymap.keySet()) {
            List<NeoDetails> value = mymap.get(key);
            for (NeoDetails neoDetails : value) {
                Date fecha_asteroide = todate(neoDetails.getCloseApproachData().get(0).getCloseApproachDateFull());
                System.out.println(fecha_asteroide);
                /*Lo metemos en la lista si es potencialmente peligroso y se encuentra dentro de los dias indicados*/
                if (neoDetails.isPotentiallyHazardousAsteroid &&
                        fecha_asteroide.before(fecha_limite) &&
                        fecha_asteroide.after(fecha_ayer)) {
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

    public Double calcular_diametro(NeoDetails neo) {
        return neo.getEstimatedDiameter().getKilometers().getEstimatedDiameterMax() /
                neo.getEstimatedDiameter().getKilometers().getEstimatedDiameterMin();
    }

    public Map<String, List<String>> ordenar_map_segun_el_numero_de_su_key(Map<String, List<String>> map) {
        Map<String, List<String>> sortedMap = new LinkedHashMap<>();
        map.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEachOrdered(e -> sortedMap.put(e.getKey(), e.getValue()));
        return sortedMap;
    }

    Date todate(String fecha) {
        /*Calendar para los meses que estan con letras*/
        Calendar cal = Calendar.getInstance();

        String[] fecha_split = fecha.split("-");
        String[] fecha_split_2 = fecha.split(" ")[0].split("-");
        String[] fecha_split_3 = fecha.split(" ")[1].split(":");
        int year = Integer.parseInt(fecha_split[0]);
        int month = month_to_int(fecha_split[1]);
        /*System.out.println(month);*/
        int day = Integer.parseInt(fecha_split_2[2]);
        int hour = Integer.parseInt(fecha_split_3[0]);
        int minute = Integer.parseInt(fecha_split_3[1]);
        /*int second = Integer.parseInt(fecha_split_3[2]);*/
        /*System.out.println(year + month + day + hour + minute);*/
        return new Date(year - 1900, month, day, hour, minute);
    }

    public int month_to_int(String month) {
        switch (month) {
            case "Jan":
                return 0;
            case "Feb":
                return 1;
            case "Mar":
                return 2;
            case "Apr":
                return 3;
            case "May":
                return 4;
            case "Jun":
                return 5;
            case "Jul":
                return 6;
            case "Aug":
                return 7;
            case "Sep":
                return 8;
            case "Oct":
                return 9;
            case "Nov":
                return 10;
            case "Dec":
                return 11;
            default:
                return 0;
        }
    }
}