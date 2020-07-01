package com.rbr.phoenix;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
public class UsPopulation {
    private LocalDateTime updateTs;
    private String state;
    private String city;
    private Integer population;

    public String saveQuery() {
        return String.format("UPSERT INTO us_population(UPDATE_TS,STATE,CITY,POPULATION) VALUES(timestamp'%s','%s','%s',%s)",updateTs.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME), state, city, population);
    }

    public String deleteQuery(){
        return String.format("DELETE from us_population where UPDATE_TS>=timestamp'%s' and STATE='%s' and CITY='%s'",updateTs.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME), state, city);
    }

    public static String getQuery(){
        return "select * from us_population LIMIT 20";
    }
}
