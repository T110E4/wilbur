import React from 'react';
import Button from 'react-bootstrap/Button';
import Container from 'react-bootstrap/Container';
import Form from 'react-bootstrap/Form';
import Card from 'react-bootstrap/Card';
import Col from 'react-bootstrap/Col';
import ToggleButton from 'react-bootstrap/ToggleButton';
import ToggleButtonGroup from 'react-bootstrap/ToggleButtonGroup';
import PassageFeedback from './PassageFeedback';

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
            selectedValues: [],
            completed: false,
            correct: "init"
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
            default:
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
                passageId: passageId,
                answerText: "",
                studentAnswers: this.state.selectedValues
            })
          })
        .then(response => response.json())
        .then(response => {
            console.log("Finished resolving");
            this.handleNext(response);
        });
    }

    handleNext(answer) {
        this.setState({
            correct: answer.correct
        })
        if (answer.correct === false) {
            console.log("Answer Incorrect", answer.correct, this.state.correct);
            this.setState({
                correct: "init"
            })
            return;
        }
        if (this.state.passageIndex + 1 < this.state.passageCount - 1) {
            this.setState({ passageIndex: this.state.passageIndex + 1 });
        } else if (this.state.passageIndex + 1 === this.state.passageCount - 1) {
            this.setState({
                passageIndex: this.state.passageIndex + 1,
                buttonText: "Finish Lesson"
            });
        } else {
            console.log("Lesson complete");
            const isCompleted = true;
            this.setState({
                completed: isCompleted
            })
            this.props.onCompleted({isCompleted});
        }
        var selectedAnswers = []
        this.setState({ selectedValues: selectedAnswers });
    }

    handleChange = (val) => {
        var selectedAnswers = [];
        val.forEach(function (item, index) {
            selectedAnswers.push(item);
        });
        this.setState({ selectedValues: selectedAnswers });
        console.log(selectedAnswers);
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
                    <PassageFeedback 
                        passageId={this.state.passages[this.state.passageIndex].id}
                        correct={this.state.correct}
                    />
                </Card>
                <br />
                <Button
                    variant="info"
                    onClick={() => {this.saveAnswer(this.state.passages[this.state.passageIndex].id)} }
                >{this.state.buttonText}</Button>
            </Container>
        );
    }
}

export default PassageComponent;