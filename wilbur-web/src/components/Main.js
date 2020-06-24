import React from 'react';
import { Switch, Route } from 'react-router-dom';

import LoginPage from '../pages/LoginPage';
import StudentView from '../pages/StudentView';
import TeacherView from '../pages/TeacherView';
import LessonBuilder from '../pages/LessonBuilder';

const Main = () => {
  return (
    <Switch> {/* The Switch decides which component to show based on the current URL.*/}
      <Route exact path='/' component={LoginPage}></Route>
      <Route exact path='/login' component={LoginPage}></Route>
      <Route exact path='/student' component={StudentView}></Route>
      <Route exact path='/teacher' component={TeacherView}></Route>
      <Route exact path='/create-lesson' component={LessonBuilder}></Route>
    </Switch>
  );
}

export default Main;