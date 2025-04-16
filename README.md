# clickhouse-flatfile-ingestion
# Bidirectional Data Ingestion Tool (ClickHouse & Flat File)

This is a web-based application that facilitates the bidirectional ingestion of data between a **ClickHouse** database and **Flat Files (CSV)**. The application supports:

- **ClickHouse → Flat File Ingestion** (selected columns)
- **Flat File → ClickHouse Ingestion** (CSV upload)
- **Column Selection** for data export/import
- **JWT Token-based Authentication** for ClickHouse connection
- **Multi-table Join** (bonus requirement)

## Features:
- **UI for Data Source Selection** (ClickHouse or Flat File)
- **Column Discovery** for both ClickHouse and CSV files
- **Data Preview** before ingestion (optional)
- **Record Count Reporting** upon successful data transfer
- **Error Handling** (connection/auth/ingestion issues)

## Technologies Used:
- **Backend:** Java (Spring Boot)
- **Frontend:** React
- **ClickHouse Integration:** JDBC
- **File Handling:** Apache Commons CSV
- **JWT Authentication:** ClickHouse JWT Token

## Setup Instructions:

### Backend Setup (Spring Boot):
1. Clone the repository:
   ```bash
   git clone https://github.com/YOUR_USERNAME/clickhouse-flatfile-ingestion.git
   cd clickhouse-flatfile-ingestion
