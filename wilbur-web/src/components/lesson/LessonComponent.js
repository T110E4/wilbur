import React from 'react';
import Container from 'react-bootstrap/Container';
import Card from 'react-bootstrap/Card';

import './LessonComponent.css';

/**
 * This component manages the student lesson list
 */
class LessonComponent extends React.Component {

    constructor(props) {
        super(props);
    }

    componentDidMount(){
        this.getLesson();
    }

    componentDidUpdate(prevProps) {
        if (prevProps.lessonId !== this.props.lessonId) {
            this.getLesson();
        }
    }

     //Get a lesson given a lessonId
    getLesson = () => {
        // read all entities
        fetch("http://localhost:8080/get-lesson?id=" + this.props.lessonId, {
            "method": "GET",
        })
            .then(response => response.json())
            .then(response => {
                this.setState({
                    lessonName: response.lessonName,
                    lessonSummary: response.lessonSummary,
                })
            })
            .catch(err => {
                console.log(err);
            });
    };

    render() {
        if (!this.state || !this.state.lessonName || !this.state.lessonSummary) {
            if (!this.props.lessonId){
                return <div>Loading Lesson...</div>
            } else {
                return <div> Loading Lesson {this.props.lessonId}</div>
            }
        }
        return (
            <Card bg="light">
            <Container>
                <h1>{this.state.lessonName}</h1> <h5>{this.props.lessonId}</h5>
                <h3>{this.state.lessonSummary}</h3>
            </Container>
            </Card>
        );
    }

}

export default LessonComponent;