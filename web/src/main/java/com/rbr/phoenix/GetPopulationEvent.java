package com.rbr.phoenix;

import java.time.LocalDateTime;

public class GetPopulationEvent extends PopulationEvent {

    public GetPopulationEvent() {
        super(LocalDateTime.now(),"GET", "", "");
    }

}
