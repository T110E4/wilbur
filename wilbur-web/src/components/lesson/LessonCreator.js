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
      passages: [{ 
        key: 0, 
        passageText: 'Enter your passage here!', 
        questionText: 'Enter your passage question here!',
        answerAText: 'Enter your answer text here!',
        answerBText: 'Enter your answer text here!',
        answerCText: 'Enter your answer text here!',
        answerDText: 'Enter your answer text here!',
        answerACheckbox: false,
        answerBCheckbox: false,
        answerCCheckbox: false,
        answerDCheckbox: false
      }],
    };

    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
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

  handleChange(event) {
    const target = event.target;
    const name = target.name;
    const value = target.value;


    this.setState({
      [name]: value
    });
  }

  handlePassageChange = idx => evt => {
    const newPassage = this.state.passages.map((passage, sidx) => {
      if (idx !== sidx) return passage;
      return { ...passage, passageText: evt.target.value };
    });
    this.setState({ passages: newPassage });
  }

  handleQuestionChange(pidx, stringKey, event) {
    const newPassage = this.state.passages.map((passage, sidx) => {
      if (pidx !== sidx) return passage;
      return { ...passage, stringKey: event.target.value };
    });
    this.setState({ passages : newPassage });
  };

  handleCheckboxChange(pidx, stringKey, event) {
    const newPassage = this.state.passages.map((passage, sidx) => {
      if (pidx !== sidx) return passage;
      return { ...passage, stringKey: event.target.checked };
    });
    this.setState({ passages : newPassage });
  };

  handleAddPassage = () => {
    this.setState({
      passages: 
      this.state.passages.concat([{ 
        key: this.state.passages.length, 
        passageText: 'Enter your passage here!', 
        questionText: 'Enter your passage question here!',
        answerAText: 'Enter your answer text here!',
        answerBText: 'Enter your answer text here!',
        answerCText: 'Enter your answer text here!',
        answerDText: 'Enter your answer text here!',
        answerACheckbox: false,
        answerBCheckbox: false,
        answerCCheckbox: false,
        answerDCheckbox: false,
      }])
    });
  };

  handleRemovePassage = () => {
    this.state.passages.splice(-1,1);
    console.log(this.state.passages.length);
    this.setState({
        passages: this.state.passages
      });
  };

  handleAnswerCheckboxChange(checkbox, pidx, qidx, event) {
    console.log(checkbox+" : "+event.target.checked+" : "+pidx)

    const passage = this.state.passages.map((passage, sidx) => {
      if (pidx !== sidx) return passage;
      return { ...passage, passageQuestion: event.target.value };
    });
    const newQuestion = passage.passageQuestions[qidx];

    if (checkbox == "A") {
      this.setState({answerACheckbox : event.target.checked})
    } else if (checkbox == "B") {
      this.setState({answerBCheckbox : event.target.checked})
    } else if (checkbox == "C") {
      this.setState({answerCCheckbox : event.target.checked})
    } else if (checkbox == "D") {
      this.setState({answerDCheckbox : event.target.checked})
    } else {
      console.log("Checkbox "+checkbox+" not recognized.");
    }

    this.setState({ passages: newQuestion });


  }

  render() {
    return (
      <Container>
        <Form onSubmit={this.handleSubmit} className="App-header">
          <Form.Group controlId="lessonName">
            <Form.Label>Lesson Name:</Form.Label>
            <Form.Control name="lessonName" size="lg" type="text" placeholder="Enter a Lesson Name" onChange={this.handleChange} />
          </Form.Group>
          <Form.Group>
            <Form.Label>Lesson Summary:</Form.Label>
            <Form.Control name="lessonSummary" size="lg" type="text" placeholder="Enter a Lesson Summary" onChange={this.handleChange} />
          </Form.Group>
          <Form.Group>
            <Form.Label>Difficulty:</Form.Label>
            <Form.Control name="lessonDifficulty" value={this.state.lessonDifficulty} onChange={this.handleChange} as="select" size="lg">
                <option value='EASY'>Easy</option>
                <option value='MEDIUM'>Medium</option>
                <option value='HARD'>Hard</option>
                <option value='CHALLENGE'>Challenging</option>            
            </Form.Control>
          </Form.Group>
          {this.state.passages.map((passage, idx) => (
            <Form.Group>
              <Form.Label>{`Passage #${idx + 1}:`}</Form.Label>
              <Form.Control placeholder={`Passage #${idx + 1} text. Key ${passage.key}`} as="textarea" rows="3" 
              onChange={this.handlePassageChange(idx)}>
              </Form.Control>

              <Form.Group>
              <Form.Label>{`Question #${idx + 1}:`}</Form.Label>
              <Form.Control placeholder={`Question #${idx + 1} text. Key ${passage.key}`} as="textarea" rows="3" 
              onChange={(e) => this.handleQuestionChange(idx, 'questionText', e)} />

              <Row>
                <Col>
                <Form.Control name="answerA" size="sm" type="text" placeholder="Enter Answer A" onChange={(e) => this.handleQuestionChange(idx, 'answerAText', e)} />
                </Col>
                <Col>
                <Form.Check 
                  custom
                  inline
                  type='checkbox'
                  id="answerA-checkbox"
                  label=""
                  checked={this.state.answerACheckbox}
                  onChange = {(e) => this.handleCheckboxChange(idx, 'answerACheckbox', e)}
                />
                </Col>
              </Row>

              <Row>
                <Col>
                <Form.Control name="answerB" size="sm" type="text" placeholder="Enter Answer B" onChange={(e) => this.handleQuestionChange(idx, 'answerBText', e)} />
                </Col>
                <Col>
                <Form.Check 
                  custom
                  inline
                  type='checkbox'
                  id="answerB-checkbox"
                  label=""
                  checked={this.state.answerBCheckbox}
                  onChange = {(e) => this.handleCheckboxChange(idx, 'answerBCheckbox', e)}
                />
                </Col>
              </Row>

              <Row>
                <Col>
                <Form.Control name="answerC" size="sm" type="text" placeholder="Enter Answer C" onChange={(e) => this.handleQuestionChange(idx, 'answerCText', e)} />
                </Col>
                <Col>
                <Form.Check 
                  custom
                  inline
                  type='checkbox'
                  id="answerC-checkbox"
                  checked={this.state.answerCCheckbox}
                  onChange = {(e) => this.handleCheckboxChange(idx, 'answerCCheckbox', e)}
                  label=""
                />
                </Col>
              </Row>


              <Row>
                <Col>
                <Form.Control name="answerD" size="sm" type="text" placeholder="Enter Answer D" onChange={(e) => this.handleQuestionChange(idx, 'answerDText', e)} />
                </Col>
                <Col>
                <Form.Check 
                  custom
                  inline
                  type='checkbox'
                  id="answerD-checkbox"
                  checked={this.state.answerDCheckbox}
                  onChange = {(e) => this.handleCheckboxChange(idx, 'answerDCheckbox', e)}
                  label=""
                />
                </Col>
              </Row>

            </Form.Group>
            </Form.Group>
          ))}

          <Row>
            <Col>
              <Button onClick={this.handleAddPassage} variant="primary">Add</Button>
            </Col>
            <Col>
            <Button onClick="Submit" type="submit" variant="success">Save</Button>
            </Col>
            <Col>
              <Button onClick={this.handleRemovePassage} variant="danger">Delete</Button>
            </Col>
          </Row>
        </Form>
      </Container>
    );
  }
}

export default LessonCreator;
