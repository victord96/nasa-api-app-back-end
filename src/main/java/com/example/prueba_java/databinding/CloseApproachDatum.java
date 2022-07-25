
package com.example.prueba_java.databinding;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "close_approach_date",
    "close_approach_date_full",
    "epoch_date_close_approach",
    "relative_velocity",
    "miss_distance",
    "orbiting_body"
})
@Data
@Generated("jsonschema2pojo")
public class CloseApproachDatum {

    @JsonProperty("close_approach_date")
    public Date closeApproachDate;
    @JsonProperty("close_approach_date_full")
    public String closeApproachDateFull;
    @JsonProperty("epoch_date_close_approach")
    public long epochDateCloseApproach;
    @JsonProperty("relative_velocity")
    public RelativeVelocity relativeVelocity;
    @JsonProperty("miss_distance")
    public MissDistance missDistance;
    @JsonProperty("orbiting_body")
    public String orbitingBody;

}
