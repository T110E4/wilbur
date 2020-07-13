import React from 'react';

import Button from 'react-bootstrap/Button';

import LessonComponent from '../../components/lesson/LessonComponent'
import PassageComponent from '../../components/lesson/PassageComponent';

import './LessonView.css';

/**
 * The lesson view is a collection of components that are
 * part of taking a lesson
 */
class LessonView extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            lessonId: this.props.match.params.id,
            answerA: false,
            answerB: false,
            answerC: false,
            answerD: false,
            answerE: false
        }
    }

    render() {
        return (
            <div className="LessonView">
                <LessonComponent key={this.props.match.params.id} lessonId={this.state.lessonId}/>
                <br />
                <PassageComponent 
                    lessonId={this.props.match.params.id} 
                />
            </div>
        );
    }

}

export default LessonView;