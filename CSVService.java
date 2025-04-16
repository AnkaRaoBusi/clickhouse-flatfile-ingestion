package com.ingestion.service;

import org.apache.commons.csv.*;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Service
public class CSVService {

    public void writeToCSV(List<String> headers, List<List<String>> rows, String filePath, String delimiter) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(headers.toArray(new String[0])).withDelimiter(delimiter.charAt(0)))) {
            for (List<String> row : rows) {
                csvPrinter.printRecord(row);
            }
        }
    }
}
