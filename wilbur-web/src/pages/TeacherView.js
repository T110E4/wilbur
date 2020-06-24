import React from 'react';
import Button from 'react-bootstrap/Button';
import { Link } from 'react-router-dom';
import LessonCreator from '../components/LessonCreator';

import '../resources/css/TeacherView.css';

function TeacherView() {
  return (
    <div className="TeacherView">
        <h1>Teacher View</h1>
        <h4>Welcome, Teacher!</h4>
    <div>
      <Link to='/create-lesson'>
        <Button variant="primary">Create a Lesson</Button>{' '}
        <Button variant="primary">View Lessons</Button>{' '}
        <Button variant="primary">View Students</Button>{' '}
      </Link>
    </div>
    </div>
  );
}

export default TeacherView;
