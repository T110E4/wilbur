import React from 'react';
import Container from 'react-bootstrap/Container';
import Card from 'react-bootstrap/Card';
import Alert from 'react-bootstrap/Alert';
import Button from 'react-bootstrap/Button';

/**
 * This component manages the student lesson list
 */
class PassageFeedback extends React.Component {


    constructor(props) {
        super(props);
        this.state = {
            feedbackId: 0,
            feedbackString: "",
            showCorrectFeedback: false,
            showWrongFeedback: false
        }
    }

    //Get a lesson given a lessonId
    getFeedback = () => {
        fetch("http://localhost:8080/get-feedback?id=" + this.props.passageId, {
            "method": "GET",
        })
        .then(response => response.json())
        .then(response => {
            console.log(response);
            this.setState({
                feedbackId: response.feedbackId,
                feedbackString: response.feedbackString,
                textStructure: response.textStructure
            })
        })
        .catch(err => {
            console.log(err);
        });
    };

    componentDidMount() {
        this.getFeedback();
    }
    
    componentDidUpdate(prevProps, prevState) {
        if (prevProps.passageId !== this.props.passageId) {
          console.log(`Passage ID has changed to ${this.props.passageId}`);
          this.getFeedback();
        } else if (prevProps.correct !== this.props.correct){
            if (this.props.correct == true) {
                this.setShowFeedback(true, false);
            } else {
                this.setShowFeedback(false, true);
            }
        }
      }

    setShowFeedback(showCorrect, showIncorrect) {
        this.setState({
            showCorrectFeedback: showCorrect,
            showWrongFeedback: showIncorrect
        })
    }

    render() {
        if (!this.state) {
            return <div> Loading Feedback for Passage</div>
        }

        return (
            <Container>
                <Alert show={this.state.showWrongFeedback} variant="danger">
                    <Alert.Heading>Not quite!</Alert.Heading>
                    <p>
                        {this.state.feedbackString}
                    </p>
                    <hr />
                    <div className="d-flex justify-content-end">
                    <Button onClick={() => this.setShowFeedback(false, false)} variant="outline-danger">
                        Close me y'all!
                    </Button>
                    </div>
                </Alert>

                <Alert show={this.state.showCorrectFeedback} variant="success">
                    <Alert.Heading>Way to go!</Alert.Heading>
                    <p>
                        Great job answering the question!
                    </p>
                    <hr />
                    <div className="d-flex justify-content-end">
                    <Button onClick={() => this.setShowFeedback(false, false)} variant="outline-success">
                        Close me y'all!
                    </Button>
                    </div>
                </Alert>

            </Container>
        );
    }

}

export default PassageFeedback;