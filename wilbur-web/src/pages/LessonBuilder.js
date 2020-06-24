import React from 'react';
import Button from 'react-bootstrap/Button';
import { Link } from 'react-router-dom';
import LessonCreator from '../components/LessonCreator';

import '../resources/css/LessonCreator.css';

function LessonBuilder() {
  return (
    <div className="App-content">
        <h1>Lesson Creator</h1>
        <LessonCreator></LessonCreator>
    </div>
  );
}

export default LessonBuilder;
