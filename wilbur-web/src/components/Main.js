import React from 'react';
import { Switch, Route } from 'react-router-dom';

import LoginPage from '../views/home/LoginPage';
import StudentView from '../views/student/StudentView';
import TeacherView from '../views/teacher/TeacherView';
import LessonBuilder from '../views/teacher/LessonBuilder';
import LessonView from '../views/student/LessonView';

const Main = () => {
  return (
    <Switch> {/* The Switch decides which component to show based on the current URL.*/}
      <Route exact path='/' component={LoginPage}></Route>
      <Route exact path='/login' component={LoginPage}></Route>
      <Route exact path='/student' component={StudentView}></Route>
      <Route exact path='/teacher' component={TeacherView}></Route>
      <Route exact path='/create-lesson' component={LessonBuilder}></Route>
      <Route exact path="/take-lesson/:id" component={LessonView}></Route>
    </Switch>
  );
}

export default Main;