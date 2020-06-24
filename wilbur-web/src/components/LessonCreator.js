import React, { Component } from 'react';
import Card from 'react-bootstrap/Card';
import Form from 'react-bootstrap/Form';

import '../resources/css/LessonCreator.css';

class LessonCreator extends React.Component {

  constructor(properties) {
    super(properties);
    this.state = {
      name: '',
      difficulty: 'medium',
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

  handleSubmit(event) {
  }

  handlePassageChange = idx => evt => {
    const newPassage = this.state.passages.map((passage, sidx) => {
      if (idx !== sidx) return passage;
      return { ...passage, name: evt.target.value };
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
            Lesson Difficulty:
            <select name="difficulty" value={this.state.difficulty} onChange={this.handleChange}>
              <option value="easy">Easy</option>
              <option value="medium">Medium</option>
              <option value="hard">Hard</option>
              <option value="challenge">Challenging</option>
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
