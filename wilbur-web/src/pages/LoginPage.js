import React from 'react';
import Button from 'react-bootstrap/Button';
import { Link } from 'react-router-dom'

import logo from '../resources/images/wilbur-cutout-white.svg';
import '../resources/css/LoginPage.css';

function Login() {
  return (
    <div className="LoginPage">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <container>
        <h1>
          Wilbur
        </h1>
        <h3>
          An Intelligent Reading Tutor
        </h3>
        </container>
        <container>
          <Link to='/teacher'>
            <Button variant="primary">I'm a Teacher</Button>{' '}
          </Link>
          <Link to='/student'>
            <Button variant="info">I'm a Student</Button>{' '}
          </Link>
        </container>
      </header>
    </div>
  );
}

export default Login;
