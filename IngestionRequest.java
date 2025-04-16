package com.ingestion.dto;

import lombok.Data;
import java.util.List;

@Data
public class IngestionRequest {
    private String host;
    private int port;
    private String database;
    private String user;
    private String jwtToken;
    private String table;
    private List<String> columns;
    private String filePath;
    private String delimiter;
}
