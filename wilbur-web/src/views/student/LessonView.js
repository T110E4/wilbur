import React from 'react';
import Container from 'react-bootstrap/Container';
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';
import Form from 'react-bootstrap/Form';

/**
 * The lesson view is a collection of components that are
 * part of taking a lesson
 */
class LessonView extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            questionIndex: 0,
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
                })

            })
            .catch(err => {
                console.log(err);
            });
    };

    render() {
        if (!this.state || !this.state.passages || !this.state.lessonSummary) {
            return <div>Loading Lesson...</div>
        }
        return (
            <div className="LessonView">
                <h1>{this.state.lessonName}</h1> <h5>{this.props.match.params.id}</h5>
                <h3>{this.state.lessonSummary}</h3>
                <p></p>
                <h2>{this.state.passages[0].passageText}</h2>
                <h3>{this.state.passages[0].questionText}</h3>
                <Container>
                    <Form onSubmit={this.handleSubmit} className="passage-question">
                        <Form.Group>
                            <Form.Check
                                type='checkbox'
                                id="answerA-checkbox"
                                label={this.state.passages[this.state.questionIndex].answerAText}
                                checked={this.state.answerACheckbox}
                                onChange={(e) => this.handleCheckboxChange()}
                            />
                            <Form.Check
                                type='checkbox'
                                id="answerB-checkbox"
                                label={this.state.passages[this.state.questionIndex].answerBText}
                                checked={this.state.answerACheckbox}
                                onChange={(e) => this.handleCheckboxChange()}
                            />
                            <Form.Check
                                type='checkbox'
                                id="answerC-checkbox"
                                label={this.state.passages[this.state.questionIndex].answerCText}
                                checked={this.state.answerACheckbox}
                                onChange={(e) => this.handleCheckboxChange()}
                            />
                            <Form.Check
                                type='checkbox'
                                id="answerD-checkbox"
                                label={this.state.passages[this.state.questionIndex].answerDText}
                                checked={this.state.answerACheckbox}
                                onChange={(e) => this.handleCheckboxChange()}
                            />
                        </Form.Group>
                    </Form>
                </Container>

                <Container>
                    <Row>
                    </Row>
                </Container>
            </div>
        );
    }

}

export default LessonView;