import React from 'react';
import Container from 'react-bootstrap/Container';
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';

/**
 * The lesson view is a collection of components that are
 * part of taking a lesson
 */
class LessonView extends React.Component {

    constructor(props) {
        super(props);
      } 

    componentDidMount() {
        this.getLesson();
    }

    getLesson = () => {
        this.getPassages();
        // read all entities
        fetch("http://localhost:8080/get-lesson?id="+this.props.match.params.id, {
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
        fetch("http://localhost:8080/get-passages?id="+this.props.match.params.id, {
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
                <h3>{this.state.passages[0].passageQuestion}</h3>
                <Container>
                <Row>
                </Row>
                </Container>
            </div>
        );
    }

}

export default LessonView;