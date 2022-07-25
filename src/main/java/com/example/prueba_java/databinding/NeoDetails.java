
package com.example.prueba_java.databinding;

import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "links",
    "id",
    "neo_reference_id",
    "name",
    "nasa_jpl_url",
    "absolute_magnitude_h",
    "estimated_diameter",
    "is_potentially_hazardous_asteroid",
    "close_approach_data",
    "is_sentry_object"
})
@Data
@Generated("jsonschema2pojo")
public class NeoDetails {

    @JsonProperty("links")
    public Links__1 links;
    @JsonProperty("id")
    public double id;
    @JsonProperty("neo_reference_id")
    public double neoReferenceId;
    @JsonProperty("name")
    public String name;
    @JsonProperty("nasa_jpl_url")
    public String nasaJplUrl;
    @JsonProperty("absolute_magnitude_h")
    public double absoluteMagnitudeH;
    @JsonProperty("estimated_diameter")
    public EstimatedDiameter estimatedDiameter;
    @JsonProperty("is_potentially_hazardous_asteroid")
    public boolean isPotentiallyHazardousAsteroid;
    @JsonProperty("close_approach_data")
    public List<CloseApproachDatum> closeApproachData = null;
    @JsonProperty("is_sentry_object")
    public boolean isSentryObject;

}
