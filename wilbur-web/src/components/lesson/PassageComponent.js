import React, { Component } from 'react';
import Button from 'react-bootstrap/Button';

/**
 * This component manages the student lesson list
 */
class PassageComponent extends React.Component {

    constructor(props) {
        super(props);
      }

    componentDidMount() {
        this.getPassage();
    }

    //TODO: Update to get only the required information, rather than all material for all lessons
    getPassage = () => {
        // read all entities
        fetch("http://localhost:8080/get-passage/"+this.passageId, {
            "method": "GET",
        })
            .then(response => response.json())
            .then(response => {
                this.setState({
                    retrievedPassage: response
                })
            })
            .catch(err => {
                console.log(err);
            });
    };


    render() {
        if (!this.state) {
            return <div>Loading Passage...</div>
        }
        return (
            <div>
            </div>
        );
    }

}

export default PassageComponent;