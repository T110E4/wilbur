import React from 'react';
import LessonCreator from '../../components/lesson/LessonCreator';

class LessonBuilder extends React.Component {

  render() {
    return (
      <div className="App-content">
          <h1>Lesson Creator</h1>
          <LessonCreator></LessonCreator>
      </div>
    );
  }
}

export default LessonBuilder;
