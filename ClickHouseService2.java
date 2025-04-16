@Service
public class ClickHouseService {
    public Connection connect(String host, String db, String user, String jwtToken) throws SQLException {
        String url = String.format("jdbc:clickhouse://%s/%s", host, db);
        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", jwtToken); // pass token as password
        return DriverManager.getConnection(url, props);
    }

    public List<String> getTables(Connection conn) throws SQLException {
        ResultSet rs = conn.getMetaData().getTables(null, null, "%", null);
        List<String> tables = new ArrayList<>();
        while (rs.next()) {
            tables.add(rs.getString("TABLE_NAME"));
        }
        return tables;
    }

    public List<String> getColumns(Connection conn, String table) throws SQLException {
        List<String> columns = new ArrayList<>();
        ResultSet rs = conn.getMetaData().getColumns(null, null, table, null);
        while (rs.next()) {
            columns.add(rs.getString("COLUMN_NAME"));
        }
        return columns;
    }

    public int writeData(Connection conn, String table, List<String> columns, List<List<Object>> rows) throws SQLException {
        String cols = String.join(",", columns);
        String placeholders = String.join(",", Collections.nCopies(columns.size(), "?"));
        String sql = "INSERT INTO " + table + " (" + cols + ") VALUES (" + placeholders + ")";
        PreparedStatement ps = conn.prepareStatement(sql);
        for (List<Object> row : rows) {
            for (int i = 0; i < columns.size(); i++) {
                ps.setObject(i + 1, row.get(i));
            }
            ps.addBatch();
        }
        int[] result = ps.executeBatch();
        return Arrays.stream(result).sum();
    }
}
