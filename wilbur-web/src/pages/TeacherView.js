import React from 'react';
import Button from 'react-bootstrap/Button';
import Container from 'react-bootstrap/Container';
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';
import TeacherLessons from '../components/TeacherLessons';

import { Link } from 'react-router-dom';

import '../resources/css/TeacherView.css';

function TeacherView() {
  return (
    <div className="TeacherView">
        <h1>Teacher View</h1>
        <h4>Welcome, Teacher!</h4>
    <div>
      <Link to='/create-lesson'>
        <Button variant="primary">Create a New Lesson</Button>{' '}
      </Link>
      <Container>
        <Row>
          <Col><h3>Created Lessons</h3><TeacherLessons></TeacherLessons></Col>
          <Col><h3>Students</h3></Col>
        </Row>
      </Container>
    </div>
    </div>
  );
}

export default TeacherView;
