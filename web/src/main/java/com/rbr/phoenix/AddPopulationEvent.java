package com.rbr.phoenix;

import java.time.LocalDateTime;

public class AddPopulationEvent extends PopulationEvent {

    public AddPopulationEvent(String state, String city) {
        super(LocalDateTime.now(),"ADD", state, city);
    }

}
