import React from 'react';
import Container from 'react-bootstrap/Container';
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';

import '../resources/css/StudentView.css';
import '../components/StudentLessons';
import StudentLessons from '../components/StudentLessons';

function StudentView() {
  return (
    <div className="StudentView">
        <h1>Student View</h1>
        <Container>
          <Row>
            <Col>Lessons Available<StudentLessons></StudentLessons></Col>
            <Col>Lessons Taken</Col>
          </Row>
        </Container>
    </div>
  );
}

export default StudentView;
