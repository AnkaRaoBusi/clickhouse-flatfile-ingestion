import React, { useState } from 'react';

const MainForm = () => {
  const [form, setForm] = useState({
    host: '', port: 8123, database: '', user: '', jwtToken: '',
    table: '', filePath: 'output.csv', delimiter: ',', columns: []
  });
  const [columns, setColumns] = useState([]);
  const [selectedCols, setSelectedCols] = useState([]);
  const [status, setStatus] = useState('');

  const handleChange = e => setForm({...form, [e.target.name]: e.target.value});

  const fetchColumns = async () => {
    const res = await fetch('/api/fetch-columns', {
      method: 'POST',
      headers: {'Content-Type': 'application/json'},
      body: JSON.stringify(form)
    });
    const data = await res.json();
    setColumns(data);
  };

  const handleIngest = async () => {
    const res = await fetch('/api/clickhouse-to-file', {
      method: 'POST',
      headers: {'Content-Type': 'application/json'},
      body: JSON.stringify({...form, columns: selectedCols})
    });
    const msg = await res.text();
    setStatus(msg);
  };

  return (
    <div>
      <input name="host" placeholder="Host" onChange={handleChange} />
      <input name="database" placeholder="Database" onChange={handleChange} />
      <input name="user" placeholder="User" onChange={handleChange} />
      <input name="jwtToken" placeholder="JWT Token" onChange={handleChange} />
      <input name="table" placeholder="Table" onChange={handleChange} />
      <input name="filePath" placeholder="CSV Path" onChange={handleChange} />

      <button onClick={fetchColumns}>Load Columns</button>
      <div>
        {columns.map(col => (
          <label key={col}>
            <input type="checkbox" value={col} onChange={() => {
              setSelectedCols(prev =>
                prev.includes(col) ? prev.filter(c => c !== col) : [...prev, col]
              );
            }} />
            {col}
          </label>
        ))}
      </div>

      <button onClick={handleIngest}>Ingest</button>
      <p>{status}</p>
    </div>
  );
};

export default MainForm;
