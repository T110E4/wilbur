import React from 'react';
import Container from 'react-bootstrap/Container';
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';

import StudentLessons from '../../components/student/StudentLessons';

/**
 * The student view is a collection of components that are
 * relevant to the student
 */
class StudentView extends React.Component {

  render() {
    return (
      <div className="StudentView">
        <h1>Student View</h1>
        <Container>
          <Row>
            <Col><h3>Lessons Available</h3><StudentLessons></StudentLessons></Col>
            <Col><h3>Lessons Taken</h3></Col>
          </Row>
        </Container>
      </div>
    );
  }
}

export default StudentView;
