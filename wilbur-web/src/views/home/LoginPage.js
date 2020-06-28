import React from 'react';
import Button from 'react-bootstrap/Button';
import Container from 'react-bootstrap/Container';
import { Link } from 'react-router-dom'

import logo from '../../resources/images/wilbur-cutout-white.svg';
import './LoginPage.css';

function Login() {
  return (
    <div className="LoginPage">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <Container>
        <h1>
          Wilbur
        </h1>
        <h3>
          An Intelligent Reading Tutor
        </h3>
        </Container>
        <Container>
          <Link to='/teacher'>
            <Button variant="primary">I'm a Teacher</Button>{' '}
          </Link>
          <Link to='/student'>
            <Button variant="info">I'm a Student</Button>{' '}
          </Link>
        </Container>
      </header>
    </div>
  );
}

export default Login;
