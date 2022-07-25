
package com.example.prueba_java.databinding;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "links",
    "element_count",
    "near_earth_objects"
})
@Generated("jsonschema2pojo")
public class NeoWsDataJackson {

    @JsonProperty("links")
    public Links links;
    @JsonProperty("element_count")
    public long elementCount;
    @JsonProperty("near_earth_objects")
    public Map<String, List<NeoDetails>> nearEarthObjects;

}
