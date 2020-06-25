import React, { Component } from 'react';
import Form from 'react-bootstrap/Form';

import '../resources/css/LessonCreator.css';

class LessonCreator extends React.Component {

  constructor(properties) {
    super(properties);
    this.state = {
      lessonName: 'test',
      lessonDifficulty: 'MEDIUM',
      passages: [{ key: 0, passageText: 'Enter your passage here!' }]
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

  save_lesson() {
    fetch("http://localhost:8080/add-lesson", {
    "method": "POST",
    "headers": {
      "content-type": "application/json",
      "accept": "application/json"
    },
    "body": JSON.stringify({
      lessonName: this.state.lessonName,
      lessonDifficulty: this.state.lessonDifficulty,
      passages: this.state.passages
      })
    })
    .then(response => response.json())
    .then(response => {
      console.log(response)
    })
    .catch(err => {
      console.log(err);
    });
  }

  handleSubmit(event) {
    //alert('Lesson Submitted: ' + this.state.lessonName);
    this.save_lesson();
    event.preventDefault();
  }

  handlePassageChange = idx => evt => {
    const newPassage = this.state.passages.map((passage, sidx) => {
      if (idx !== sidx) return passage;
      return { ...passage, passageText: evt.target.value };
    });

    this.setState({ passages: newPassage });
  };

  handleAddPassage = () => {
    this.setState({
      passages: this.state.passages.concat([{ key: this.state.passages.length, passageText: "" }])
    });
  };

  render() {
    return (
      <Form onSubmit={this.handleSubmit} className="App-header">
        <div>
          <Form.Label>
            <div>
              <label>
                Lesson Name:
                <input type="text" placeholder="Enter a Lesson Name" onChange={this.handleChange} />
              </label>
            </div>
            Lesson Difficulty:
            <select name="difficulty" value={this.state.lessonDifficulty} onChange={this.handleChange}>
              <option value="EASY">Easy</option>
              <option value="MEDIUM">Medium</option>
              <option value="HARD">Hard</option>
              <option value="CHALLENGE">Challenging</option>
            </select>
          </Form.Label>
        </div>

        {this.state.passages.map((passage, idx) => (
          <div className="passage" key={idx}>
            <textarea
              placeholder={`Passage #${idx + 1} text. Key ${passage.key}`}
              onChange={this.handlePassageChange(idx)}
            />
          </div>
        ))}
        <button
          type="button"
          onClick={this.handleAddPassage}
          className="small"
        >Add Passage</button>

        <input variant="primary" type="submit" value="Submit" />
      </Form>
    );
  }

}

export default LessonCreator;
