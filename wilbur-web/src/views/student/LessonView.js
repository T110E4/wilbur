import React from 'react';
import Container from 'react-bootstrap/Container';
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';

/**
 * The lesson view is a collection of components that are
 * part of taking a lesson
 */
class LessonView extends React.Component {

    componentDidMount() {
        this.getPassages();
    }

    //TODO: Update to actually get a lesson
    getLesson = () => {
        // read all entities
        fetch("http://localhost:8080/get-passages?id="+this.props.match.params.id, {
            "method": "GET",
        })
            .then(response => response.json())
            .then(response => {
                console.log(response);
                this.setState({
                    passages: response
                })
                console.log(this.state);
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
                console.log(response);
                this.setState({
                    passages: response
                })
                console.log(this.state);
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
            <div className="LessonView">
                <h1>Lesson View {this.props.match.params.id} </h1>
                <h3>{this.state.passages[0].lessonSummary}</h3>
                <p></p>
                <h2>{this.state.passages[0].passageText}</h2>
                <Container>
                <Row>
                </Row>
                </Container>
            </div>
        );
    }

}

export default LessonView;