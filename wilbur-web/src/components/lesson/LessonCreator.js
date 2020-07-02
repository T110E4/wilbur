import React, { Component } from 'react';
import Form from 'react-bootstrap/Form';
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';
import Container from 'react-bootstrap/Container';
import Button from 'react-bootstrap/Button';

import './LessonCreator.css';

class LessonCreator extends React.Component {

  constructor(properties) {
    super(properties);
    this.state = {
      lessonName: 'test',
      lessonDifficulty: 'MEDIUM',
      passages: [{ key: 0, passageText: 'Enter your passage here!', passageQuestion: 'Enter your passage question here!' }]
    };

    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange(event) {
    const target = event.target;
    const name = target.name;
    const value = target.value;


    this.setState({
      [name]: value
    });
  }

  saveLesson() {
    fetch("http://localhost:8080/add-lesson", {
      "method": "POST",
      "headers": {
        "content-type": "application/json",
        "accept": "application/json"
      },
      "body": JSON.stringify({
        lessonName: this.state.lessonName,
        lessonSummary: this.state.lessonSummary,
        lessonDifficulty: this.state.lessonDifficulty,
        passages: this.state.passages
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

  handleSubmit(event) {
    this.saveLesson();
    event.preventDefault();
  }

  handlePassageChange = idx => evt => {
    const newPassage = this.state.passages.map((passage, sidx) => {
      if (idx !== sidx) return passage;
      return { ...passage, passageText: evt.target.value };
    });
    this.setState({ passages: newPassage });
  }

    handleQuestionChange = idx => evt => {
      const newPassage = this.state.passages.map((passage, sidx) => {
        if (idx !== sidx) return passage;
        return { ...passage, passageQuestion: evt.target.value };
      });

    this.setState({ passages: newPassage });
  };

  handleAddPassage = () => {
    this.setState({
      passages: this.state.passages.concat([{ key: this.state.passages.length, passageText: "", passageQuestion: "" }])
    });
  };

  handleRemovePassage = () => {
    this.state.passages.splice(-1,1);
    console.log(this.state.passages.length);
    this.setState({
        passages: this.state.passages
      });
  };

  render() {
    return (
      <Container>
        <Form onSubmit={this.handleSubmit} className="App-header">
          <Row>
            <Col><Form.Label>Lesson Name:</Form.Label></Col>
            <Col>
              <input name="lessonName" type="text" placeholder="Enter a Lesson Name" onChange={this.handleChange} />
            </Col>
          </Row>
          <Row>
            <Col><Form.Label>Lesson Summary:</Form.Label></Col>
            <Col>
              <textarea name="lessonSummary" type="text" placeholder="Enter a Lesson Summary" onChange={this.handleChange} />
            </Col>
          </Row>
          <Row>
            <Col><Form.Label>Difficulty:</Form.Label></Col>
            <Col>
              <select name="lessonDifficulty" value={this.state.lessonDifficulty} onChange={this.handleChange} >
                <option value='EASY'>Easy</option>
                <option value='MEDIUM'>Medium</option>
                <option value='HARD'>Hard</option>
                <option value='CHALLENGE'>Challenging</option>
              </select>
            </Col>
          </Row>

          {this.state.passages.map((passage, idx) => (
            <div>
            <Row>
              <Col><Form.Label>{`Passage #${idx + 1}:`}</Form.Label></Col>
              <Col>
                <div className="passage" key={idx}>
                  <textarea
                    placeholder={`Passage #${idx + 1} text. Key ${passage.key}`}
                    onChange={this.handlePassageChange(idx)}
                  />
                </div>
              </Col>
            </Row>

            <Row>
            <Col><Form.Label>{`Question #${idx + 1}:`}</Form.Label></Col>
            <Col>
              <div className="question" key={idx}>
                <textarea
                  placeholder={`Question #${idx + 1} text. Key ${passage.key}`}
                  onChange={this.handleQuestionChange(idx)}
                />
              </div>
            </Col>
          </Row>
          </div>
          ))}

          <Row>
            <Col>
              <Button onClick={this.handleAddPassage} variant="primary">Add Passage</Button>
            </Col>
          </Row>
          <Row>
            <Col>
              <Button onClick={this.handleRemovePassage} variant="danger">Delete Last Passage</Button>
            </Col>
          </Row>
          <Row>
            <Button onClick="Submit" type="submit" variant="success">Save Lesson</Button>
          </Row>
        </Form>
      </Container>
    );
  }

}

export default LessonCreator;