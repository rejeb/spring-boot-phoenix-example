package com.rbr.phoenix;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.RowSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
@Log
public class UsPopulationRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UsPopulationRepository(JdbcTemplate jdbcTemplate) throws Exception {
        this.jdbcTemplate=jdbcTemplate;
    }


    public void addPopulation(UsPopulation usPopulation) {

        try {
            jdbcTemplate.update(usPopulation.saveQuery());

            saveEvent(new AddPopulationEvent(usPopulation.getState(), usPopulation.getCity()));
        } catch (Throwable throwables) {
            log.severe(throwables.getMessage());
        }

    }

    public List<UsPopulation> populations() {

        List<UsPopulation> populations = new ArrayList<>();
        try {
            saveEvent(new GetPopulationEvent());
            SqlRowSet rs= jdbcTemplate.queryForRowSet(UsPopulation.getQuery());
            while (rs.next()) {
                populations.add(new UsPopulation(rs.getTimestamp(1).toLocalDateTime(), rs.getString(2), rs.getString(3), Integer.valueOf(rs.getString(4))));
            }
        } catch (Throwable throwables) {
            log.severe(throwables.getMessage());
        }

        return populations;
    }

    public void deletePopulation(UsPopulation usPopulation){
        try {
            jdbcTemplate.update(usPopulation.deleteQuery());

        } catch (Throwable throwables) {
            log.severe(throwables.getMessage());
        }
    }

    public List<PopulationEvent> populationsEvents() {

        List<PopulationEvent> populations = new ArrayList<>();
        try {
            saveEvent(new GetPopulationEvent());
            SqlRowSet rs =jdbcTemplate.queryForRowSet(PopulationEvent.getQuery());
            while (rs.next()) {
                populations.add(new PopulationEvent(rs.getTimestamp(1).toLocalDateTime(), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (Throwable throwables) {
            log.severe(throwables.getMessage());
        }

        return populations;
    }

    private void saveEvent(PopulationEvent populationEvent) {
        try {
            jdbcTemplate.update(populationEvent.upsert());
        } catch (Throwable throwables) {
            log.severe(throwables.getMessage());
        }
    }
}
