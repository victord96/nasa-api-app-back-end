
package com.example.prueba_java.databinding;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "astronomical",
    "lunar",
    "kilometers",
    "miles"
})
@Generated("jsonschema2pojo")
public class MissDistance {

    @JsonProperty("astronomical")
    public String astronomical;
    @JsonProperty("lunar")
    public String lunar;
    @JsonProperty("kilometers")
    public String kilometers;
    @JsonProperty("miles")
    public String miles;

}
