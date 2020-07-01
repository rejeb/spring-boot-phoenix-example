package com.rbr.phoenix;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Data
@AllArgsConstructor
public class PopulationEvent {
    private LocalDateTime eventTs;
    private String eventType;
    private String state;
    private String city;

    public String upsert(){
        return String.format("UPSERT INTO us_population_event(EVENT_TS,EVENT_TYPE,STATE,CITY) VALUES(timestamp'%s','%s','%s','%s')",eventTs.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),eventType,state,city);
    }

    public static String getQuery(){
        return "select * from us_population_event LIMIT 20";
    }
}
