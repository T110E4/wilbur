import React from 'react';
import Button from 'react-bootstrap/Button';
import Container from 'react-bootstrap/Container';
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';

import { Link } from 'react-router-dom';

import './LessonComponent.css';

/**
 * This component manages the student lesson list
 */
class LessonComponent extends React.Component {

    constructor(props) {
        super(props);
      }

    componentDidMount() {
        this.getPassage();
    }

    getPassages = () => {
        // read all entities
        //fetch("http://localhost:8080/get-lesson/"+this.id, {
        fetch("http://localhost:8080/get-passages/"+this.id, {
            "method": "GET",
        })
            .then(response => response.json())
            .then(response => {
                this.setState({
                    retrievedPassage: response
                })
            })
            .catch(err => {
                console.log(err);
            });
    };


    render() {
        if (!this.state) {
            return <div>Loading Lesson...</div>
        }
        return (
            <div>
            </div>
        );
    }

}

export default LessonComponent;