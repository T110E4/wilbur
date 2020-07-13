import React, { Component } from 'react';
import Button from 'react-bootstrap/Button';
import Container from 'react-bootstrap/Container';
import Form from 'react-bootstrap/Form';
import Card from 'react-bootstrap/Card';
import Col from 'react-bootstrap/Col';
import ToggleButton from 'react-bootstrap/ToggleButton';
import ToggleButtonGroup from 'react-bootstrap/ToggleButtonGroup';

import './PassageComponent.css';

/**
 * This component manages the student lesson list
 */
class PassageComponent extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            passageIndex: 0,
            passageCount: 0,
            buttonText: "Next",
            answerA: false,
            answerB: false,
            answerC: false,
            answerD: false,
            answerE: false,
            selectedValues: []
        }
    }

    componentDidMount() {
        this.getPassages();
    }

    componentDidUpdate(prevProps) {
        if (prevProps.lessonId !== this.props.lessonId) {
            this.getPassages();
        }
    }

    /**
     * Get a human readable version of the text structure for display
     * @param {*} textStructure 
     */
    getTssHuman(textStructure) {
        switch (textStructure) {
            case 'COMPARISON':
                return "Comparison";
            case 'CAUSE_EFFECT':
                return "Cause & Effect";
            case 'SEQUENCE':
                return "Sequence"
            case 'DESCRIPTION':
                return "Description"
        }
    }

    //Get a passage given a lessonId
    getPassages = () => {
        // read all entities
        fetch("http://localhost:8080/get-passages?id=" + this.props.lessonId, {
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
                console.log("Loaded " + this.state.passageCount + " passages from the server for the lesson.");
            })
            .catch(err => {
                console.log(err);
            });
    };

    saveAnswer(passageId) {
        fetch("http://localhost:8080/save-answer", {
            "method": "POST",
            "headers": {
              "content-type": "application/json",
              "accept": "application/json"
            },
            "body": JSON.stringify({
                lessonId: this.props.lessonId,
                passageId: passageId,
                answerText: "",
            })
          })
        .then(response => response.json())
        .then(response => {
            console.log(response);
        })
        .catch(err => {
            console.log(err);
        });
    }


    handleNext = () => {

        this.setState({
            answerA: false,
            answerB: false,
            answerC: false,
            answerD: false,
            answerE: false
        });

        console.log(this.state.passageIndex);
        console.log(this.state.passageCount);
        this.saveAnswer(this.state.passageIndex);

        if (this.state.passageIndex + 1 < this.state.passageCount - 1) {
            this.setState({ passageIndex: this.state.passageIndex + 1 });
        } else if (this.state.passageIndex + 1 == this.state.passageCount - 1) {
            this.setState({
                passageIndex: this.state.passageIndex + 1,
                buttonText: "Finish Lesson"
            });
        } else {
            console.log("Lesson complete");
            //TODO: Complete the lesson & save lesson
        }
        console.log(this.state.passageIndex);
        console.log(this.state.passageCount);
    }

    handleChange = (val) => {
        var answerStates = {
            answerA: false,
            answerB: false,
            answerC: false,
            answerD: false,
            answerE: false
        }
        var selectedAnswers = [];
        val.forEach(function (item, index) {
            answerStates[item] = true;
            selectedAnswers.push(item);
        });
        this.setState(answerStates)
        this.setState({ selectedValues: selectedAnswers });
    }

    render() {
        if (!this.state || !this.state.passages) {
            if (!this.props.lessonId) {
                return <div>Loading Passage...</div>
            } else {
                return <div> Loading Passages for Lesson {this.props.lessonId}</div>
            }
        }

        return (
            <Container>
                <Card bg="dark" text="white">
                    <Card.Body>
                        <Card.Title>Passage - {this.getTssHuman(this.state.passages[this.state.passageIndex].textStructure)} </Card.Title>
                        <Card.Text>
                            {this.state.passages[this.state.passageIndex].passageText}
                        </Card.Text>
                    </Card.Body>
                </Card>
                <br />
                <Card bg="dark" text="white" >
                    <Card.Body>
                        <Card.Title>Question Prompt</Card.Title>
                        <h4>{this.state.passages[this.state.passageIndex].questionText}</h4>
                        <Form onSubmit={this.handleSubmitAnswer} className="passage-question">
                            <Col>
                                <ToggleButtonGroup
                                    vertical
                                    value={this.state.selectedValues}
                                    onChange={this.handleChange}
                                    type="checkbox" >
                                    <ToggleButton value={"answerA"}>{this.state.passages[this.state.passageIndex].answerAText} </ToggleButton>
                                    <ToggleButton value={"answerB"}>{this.state.passages[this.state.passageIndex].answerBText} </ToggleButton>
                                    <ToggleButton value={"answerC"}>{this.state.passages[this.state.passageIndex].answerCText} </ToggleButton>
                                    <ToggleButton value={"answerD"}>{this.state.passages[this.state.passageIndex].answerDText} </ToggleButton>
                                    <ToggleButton value={"answerE"}>{this.state.passages[this.state.passageIndex].answerEText} </ToggleButton>
                                </ToggleButtonGroup>
                            </Col>
                        </Form>
                    </Card.Body>
                </Card>
                <br />
                <Button
                    variant="primary"
                    onClick={this.handleNext}
                >{this.state.buttonText}</Button>
            </Container>
        );
    }
}

export default PassageComponent;