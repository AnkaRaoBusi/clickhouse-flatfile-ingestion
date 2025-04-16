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
   ```
   git clone https://github.com/YOUR_USERNAME/clickhouse-flatfile-ingestion.git
   cd clickhouse-flatfile-ingestion

Navigate to the backend folder and build the Spring Boot application:




cd backend
./mvnw spring-boot:run
The backend will be available at http://localhost:8080.

Frontend Setup (React):
Navigate to the frontend folder and install dependencies:




cd frontend
npm install
Start the React development server:




npm start
The frontend will be available at http://localhost:3000.

Docker Setup for ClickHouse:
To set up a local ClickHouse instance using Docker, follow the steps below.

Navigate to the docker folder and run:




docker-compose up
The ClickHouse instance will be available at http://localhost:8123 (HTTP) or https://localhost:8443 (HTTPS).

User Interface
Steps:
Connect to Data Source:

Select the data source (ClickHouse or Flat File).

For ClickHouse, provide the host, port, database, user, and JWT token.

For Flat File, upload a CSV file and select delimiters.

Column Selection:

For ClickHouse, the application will list available tables and columns.

For Flat File, the columns will be automatically identified.

Preview Data:

Before ingestion, preview the first 100 rows of the selected data.

Start Ingestion:

Once all selections are made, click the "Start Ingestion" button to transfer the data.

Completion:

After successful ingestion, the total number of records processed will be displayed.

API Documentation
Endpoints:
POST /api/connect

Connects to the data source (ClickHouse) and retrieves available tables.

Parameters: url, user, token

Response: List of available tables.

POST /api/columns

Fetches the columns of a specific table or Flat File.

Parameters: url, user, token, table

Response: List of column names.

POST /api/export

Exports data from ClickHouse to a Flat File (CSV).

Parameters: url, user, token, table, columns

Response: Number of records exported.

Error Handling
The application includes error handling for:

Connection Issues: If the connection to ClickHouse or Flat File fails.

Authentication Issues: If the JWT token is invalid or expired.

Query Failures: If the query to ClickHouse fails during ingestion/export.

File Issues: If the uploaded CSV file is invalid or has parsing issues.
Testing
Test Cases:
Single ClickHouse table → Flat File: Verify record count and correct export.

Flat File → New ClickHouse table: Verify record count and data integrity.

Multi-table Join (Bonus): Select multiple ClickHouse tables and join them before exporting to Flat File.

Test connection/authentication failures: Verify the system responds with appropriate error messages.

Data preview: Test the data preview functionality before full ingestion.
