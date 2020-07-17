import React, { Component } from 'react';
import Modal from 'react-bootstrap/Modal';
import Button from 'react-bootstrap/Button';

import { Link } from 'react-router-dom'

class LessonCompletion extends React.Component {

    render() {
        return (
        <Modal
        show={this.props.completed}
        size="lg"
        aria-labelledby="contained-modal-title-vcenter"
        centered
        >
        <Modal.Header closeButton>
            <Modal.Title>Lesson Complete!</Modal.Title>
        </Modal.Header>

        <Modal.Body>
            <p>Congratulations! You have finished the lesson!</p>
        </Modal.Body>

        <Modal.Footer>
        <Link to='/student'>
            <Button variant="primary">Finished</Button>{' '}
        </Link>
        </Modal.Footer>
        </Modal>
        );
    }
};


export default LessonCompletion;