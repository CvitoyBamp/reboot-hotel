package ru.reboot.hotel.entity.room;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Additional implements Serializable {

        @JsonProperty("fridge")
        Boolean fridge;

        @JsonProperty("wifi")
        Boolean wifi;

        @JsonProperty("tv")
        Boolean tv;

        @JsonProperty("conditioner")
        Boolean conditioner;

}
