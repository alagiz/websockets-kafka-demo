import React, {Component} from 'react';
import './style.css';
import {Steps} from "antd";

class JobRun extends Component {
    render() {
        const item = this.props.item;
        const steps = this.props.steps;

        return (
            <div className="job-run">
                <div className="job-info">
                    <div className="job-info-label">Status:</div>
                    {item.isJobDone ? "Finished" : `at step ${item.jobStep}`}
                    <div className="job-info-label">Triggered by:</div>
                    {item.userId}
                    <div className="job-info-label">Job id:</div>
                    {item.jobId}
                </div>
                <Steps>
                    {steps(item)}
                </Steps>
            </div>
        );
    }
}

export default JobRun;