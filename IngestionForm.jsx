import React, { useState } from "react";
import axios from "axios";

export default function IngestionForm() {
  const [file, setFile] = useState(null);
  const [columns, setColumns] = useState([]);
  const [selectedCols, setSelectedCols] = useState([]);
  const [recordCount, setRecordCount] = useState(0);

  const handleFileUpload = async () => {
    const form = new FormData();
    form.append("file", file);
    form.append("delimiter", ",");
    form.append("host", "localhost");
    form.append("database", "default");
    form.append("user", "default");
    form.append("token", "YOUR_JWT_TOKEN");
    form.append("table", "your_table");
    form.append("columns", selectedCols);

    const res = await axios.post("http://localhost:8080/api/ingest/flat-to-clickhouse", form);
    setRecordCount(res.data);
  };

  return (
    <div>
      <input type="file" onChange={e => setFile(e.target.files[0])} />
      {/* Add checkboxes here to pick columns from backend response */}
      <button onClick={handleFileUpload}>Upload & Ingest</button>
      <p>Records Ingested: {recordCount}</p>
    </div>
  );
}
