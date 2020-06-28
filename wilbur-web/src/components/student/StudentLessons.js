import React, { Component } from 'react';
import Button from 'react-bootstrap/Button';
import Jumbotron from 'react-bootstrap/Jumbotron';

import { Link } from 'react-router-dom';

import './StudentLessons.css';

/**
 * This component manages the student lesson list
 */
class StudentLessons extends React.Component {

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
                        <p>{lesson.lessonSummary}</p>
                        <Link to={{pathname: `/take-lesson/${lesson.id}` }}>
                            <Button variant="primary">Take Lesson</Button>
                        </Link>
                    </Jumbotron>
                ))}
            </div>
        );
    }

}

export default StudentLessons;