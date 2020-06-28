import React from 'react';
import Button from 'react-bootstrap/Button';
import Container from 'react-bootstrap/Container';
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';
import { Link } from 'react-router-dom';

import TeacherLessons from '../../components/teacher/TeacherLessons';

//import './TeacherView.css';

/**
 * The teacher view is a collection of components that are relevant to the teacher
 */
class TeacherView extends React.Component {

  render() {
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

}

export default TeacherView;
