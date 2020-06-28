import React, { Component } from 'react';
import Button from 'react-bootstrap/Button';
import Jumbotron from 'react-bootstrap/Jumbotron';

import './TeacherLessons.css';

/**
 * This component manages the teacher lesson list
 */
class TeacherLessons extends React.Component {

    componentDidMount() {
        this.getLessons();
    }

    //TODO: Update to get only the required information, rather than all material for all lessons
    getLessons = () => {
        // read all entities
        fetch("http://localhost:8080/get-lessons", {
            "method": "GET",
        })
            .then(response => response.json())
            .then(response => {
                this.setState({
                    retrievedLessons: response
                })
            })
            .catch(err => {
                console.log(err);
            });
    };


    render() {
        if (!this.state) {
            return <div>Loading Lesson List...</div>
        }
        return (
            <div>
                {this.state.retrievedLessons.map(lesson => (
                    <Jumbotron>
                        <h1>{lesson.lessonName}</h1>
                        <p>
                            {lesson.lessonSummary}
                        </p>
                        <p>
                            <Button variant="primary">Edit Lesson</Button>
                        </p>
                    </Jumbotron>
                ))}
            </div>
        );
    }

}

export default TeacherLessons;