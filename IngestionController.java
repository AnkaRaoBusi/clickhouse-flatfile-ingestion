package com.ingestion.controller;

import com.ingestion.dto.IngestionRequest;
import com.ingestion.service.ClickHouseService;
import com.ingestion.service.CSVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class IngestionController {

    @Autowired private ClickHouseService clickHouseService;
    @Autowired private CSVService csvService;

    @PostMapping("/fetch-columns")
    public List<String> fetchColumns(@RequestBody IngestionRequest req) throws Exception {
        return clickHouseService.getColumnNames(req);
    }

    @PostMapping("/clickhouse-to-file")
    public String clickhouseToFile(@RequestBody IngestionRequest req) throws Exception {
        List<List<String>> data = clickHouseService.getData(req);
        csvService.writeToCSV(req.getColumns(), data, req.getFilePath(), req.getDelimiter());
        return "Ingestion complete. Records: " + data.size();
    }
}
