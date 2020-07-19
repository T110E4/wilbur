import React from 'react';

import LessonComponent from '../../components/lesson/LessonComponent'
import PassageComponent from '../../components/lesson/PassageComponent';
import LessonCompletion from '../../components/lesson/LessonCompletion';

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
            completed: false
        }
        this.handleCompleted = this.handleCompleted.bind(this);
    }

    handleCompleted = (completed) => {
        console.log("Lesson has been completed");
        this.setState({
            completed: completed
        })
    }

    render() {
        return (
            <div className="LessonView">
                <LessonComponent key={this.props.match.params.id} lessonId={this.state.lessonId}/>
                <br />
                <PassageComponent lessonId={this.props.match.params.id} onCompleted={this.handleCompleted} />
                <LessonCompletion completed={this.state.completed} />
            </div>
        );
    }

}

export default LessonView;