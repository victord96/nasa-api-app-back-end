
package com.example.prueba_java.databinding;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "estimated_diameter_min",
    "estimated_diameter_max"
})
@Data
@Generated("jsonschema2pojo")
public class Kilometers {

    @JsonProperty("estimated_diameter_min")
    public double estimatedDiameterMin;
    @JsonProperty("estimated_diameter_max")
    public double estimatedDiameterMax;

}
