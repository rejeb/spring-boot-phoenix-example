package com.rbr.phoenix;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Properties;

public class TestConnection {
    @Test
    public void testInsert() throws ClassNotFoundException, SQLException {
        Connection conn;
        Class.forName("org.apache.phoenix.queryserver.client.Driver");
        Properties prop = new Properties();
        conn = DriverManager.getConnection("jdbc:phoenix:thin:url=http://localhost:8765;serialization=PROTOBUF");
        conn.setAutoCommit(true);
        System.out.println("got connection");
        UsPopulation usPopulation = new UsPopulation(LocalDateTime.now(), "ny", "new york", 100000);
        conn.createStatement().executeUpdate(usPopulation.saveQuery());

        ResultSet rst = conn.createStatement().executeQuery(UsPopulation.getQuery());
        while (rst.next()) {
            System.out.println(rst.getTimestamp(1).toLocalDateTime() + " " + rst.getString(2) + " " + rst.getString(3) + " " + rst.getBigDecimal(4));
        }
    }
}
