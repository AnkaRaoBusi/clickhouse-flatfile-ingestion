package com.ingestion.service;

import com.ingestion.dto.IngestionRequest;
import java.sql.*;
import java.util.*;
import org.springframework.stereotype.Service;

@Service
public class ClickHouseService {

    public List<String> getColumnNames(IngestionRequest req) throws Exception {
        Connection conn = getConnection(req);
        String query = "DESCRIBE TABLE " + req.getDatabase() + "." + req.getTable();
        ResultSet rs = conn.createStatement().executeQuery(query);
        List<String> columns = new ArrayList<>();
        while (rs.next()) columns.add(rs.getString("name"));
        return columns;
    }

    public List<List<String>> getData(IngestionRequest req) throws Exception {
        Connection conn = getConnection(req);
        String cols = String.join(", ", req.getColumns());
        String query = String.format("SELECT %s FROM %s.%s", cols, req.getDatabase(), req.getTable());
        ResultSet rs = conn.createStatement().executeQuery(query);
        List<List<String>> rows = new ArrayList<>();
        while (rs.next()) {
            List<String> row = new ArrayList<>();
            for (String col : req.getColumns()) row.add(rs.getString(col));
            rows.add(row);
        }
        return rows;
    }

    private Connection getConnection(IngestionRequest req) throws Exception {
        String url = String.format("jdbc:clickhouse://%s:%d/%s", req.getHost(), req.getPort(), req.getDatabase());
        Properties props = new Properties();
        props.setProperty("user", req.getUser());
        props.setProperty("password", req.getJwtToken());
        return DriverManager.getConnection(url, props);
    }
}
