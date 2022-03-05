import React from 'react';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import { Route, Routes } from 'react-router-dom';
import Login from './components/Login';
import Connected from './components/Connected';

function App() {
  return (
    <div className='app'>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/connected" element={<Connected />} />
      </Routes>
    </div>

    
  );
}

export default App;
