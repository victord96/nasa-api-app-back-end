
package com.example.prueba_java.databinding;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "kilometers_per_second",
    "kilometers_per_hour",
    "miles_per_hour"
})
@Data
@Generated("jsonschema2pojo")
public class RelativeVelocity {

    @JsonProperty("kilometers_per_second")
    public String kilometersPerSecond;
    @JsonProperty("kilometers_per_hour")
    public String kilometersPerHour;
    @JsonProperty("miles_per_hour")
    public String milesPerHour;

}
