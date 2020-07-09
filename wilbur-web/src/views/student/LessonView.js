import React from 'react';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Form from 'react-bootstrap/Form';
import Card from 'react-bootstrap/Card';
import Button from 'react-bootstrap/Button';

import './LessonView.css';

/**
 * The lesson view is a collection of components that are
 * part of taking a lesson
 */
class LessonView extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            passageIndex: 0,
            passageCount: 0
        }
    }

    componentDidMount() {
        this.getLesson();
    }

    getLesson = () => {
        this.getPassages();
        // read all entities
        fetch("http://localhost:8080/get-lesson?id=" + this.props.match.params.id, {
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

    getPassages = () => {
        // read all entities
        fetch("http://localhost:8080/get-passages?id=" + this.props.match.params.id, {
            "method": "GET",
        })
            .then(response => response.json())
            .then(response => {
                this.setState({
                    passages: response
                });
                this.setState({
                    passageCount: this.state.passages.length
                });
                console.log("Loaded "+this.state.passageCount + " passages from the server for the lesson.");
            })
            .catch(err => {
                console.log(err);
            });
    };

    handleNext = () => {
        this.setState( {passageIndex: this.state.passageIndex+1} );
    }

    render() {
        if (!this.state || !this.state.passages || !this.state.lessonSummary) {
            return <div>Loading Lesson...</div>
        }
        return (
            <div className="LessonView">
                <h1>{this.state.lessonName}</h1> <h5>{this.props.match.params.id}</h5>
                <h3>{this.state.lessonSummary}</h3>
                <p></p>
                <Card bg="light">
                    <Card.Body>
                    <Card.Title>Passage</Card.Title>
                    <Card.Text>
                        {this.state.passages[this.state.passageIndex].passageText}
                    </Card.Text>
                    </Card.Body>
                </Card>
                <Card bg="dark" text="white" >
                    <h3>{this.state.passages[this.state.passageIndex].questionText}</h3>
                    <Form onSubmit={this.handleSubmitAnswer} className="passage-question">
                        <Form.Group>
                            <Form.Check
                                type='checkbox'
                                id="answerA-checkbox"
                                label={this.state.passages[this.state.passageIndex].answerAText}
                                checked={this.state.answerACheckbox}
                                onChange={(e) => this.handleCheckboxChange()}
                            />
                            <Form.Check
                                type='checkbox'
                                id="answerB-checkbox"
                                label={this.state.passages[this.state.passageIndex].answerBText}
                                checked={this.state.answerACheckbox}
                                onChange={(e) => this.handleCheckboxChange()}
                            />
                            <Form.Check
                                type='checkbox'
                                id="answerC-checkbox"
                                label={this.state.passages[this.state.passageIndex].answerCText}
                                checked={this.state.answerACheckbox}
                                onChange={(e) => this.handleCheckboxChange()}
                            />
                            <Form.Check
                                type='checkbox'
                                id="answerD-checkbox"
                                label={this.state.passages[this.state.passageIndex].answerDText}
                                checked={this.state.answerACheckbox}
                                onChange={(e) => this.handleCheckboxChange()}
                            />
                        </Form.Group>
                    </Form>
                    <Button
                        variant="primary"
                        onClick={this.handleNext}
                    >Next</Button>
                </Card>
            </div>
        );
    }

}

export default LessonView;