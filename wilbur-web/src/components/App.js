import React from 'react';
import Main from '../components/Main';
import Navbar from 'react-bootstrap/Navbar';

import '../resources/css/App.css';
import logo from '../resources/images/wilbur-cutout-white.svg';

function App() {
  return (
    <div className="App">
      <Navbar bg="dark" variant="dark">
        <Navbar.Brand href="/">
          <img
            src={logo}
            width="30"
            height="30"
            className="d-inline-block align-top"
            alt="React Bootstrap logo"
          />{' '}
          Wilbur
        </Navbar.Brand>
      </Navbar>
      
      <Main />
    </div>
  );
}

export default App;
