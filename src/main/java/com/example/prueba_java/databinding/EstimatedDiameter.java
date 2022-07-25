
package com.example.prueba_java.databinding;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "kilometers",
    "meters",
    "miles",
    "feet"
})
@Data
@Generated("jsonschema2pojo")
public class EstimatedDiameter {

    @JsonProperty("kilometers")
    public Kilometers kilometers;
    @JsonProperty("meters")
    public Meters meters;
    @JsonProperty("miles")
    public Miles miles;
    @JsonProperty("feet")
    public Feet feet;

}
