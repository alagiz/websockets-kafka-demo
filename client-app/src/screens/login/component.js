import React, {Component} from 'react';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faExclamationTriangle} from '@fortawesome/fontawesome-free-solid';
import './style.css';

import worker from '../../worker.png';
import AuthService from '../../utils/AuthService';

class Login extends Component {
    state = {showUnauthorizedWarning: false};

    constructor(props) {
        super(props);

        this.handleChange = this.handleChange.bind(this);
        this.handleFormSubmit = this.handleFormSubmit.bind(this);
        this.Auth = new AuthService();
    }

    componentWillMount() {
        if (this.Auth.isLoggedIn()) {
            this.props.history.replace('/');
        }
    }

    handleFormSubmit = event => {
        event.preventDefault();

        this.Auth.login(this.state.username, this.state.password)
            .then(() => {
                this.setState({showUnauthorizedWarning: false});
                this.props.history.replace('/');
            })
            .catch(() => {
                this.setState({showUnauthorizedWarning: true});
            })
    };

    handleChange = event => this.setState({[event.target.name]: event.target.value});

    unauthorizedWarningComponent =
        <div className="unauthorized-warning">
            <FontAwesomeIcon icon={faExclamationTriangle} size="1x" className="unauthorized-warning-icon"/>
            Wrong username or password
        </div>;

    render() {
        const optionalComponent = this.state.showUnauthorizedWarning ? this.unauthorizedWarningComponent :
            <div className="unauthorized-warning"/>;

        return (
            <div className="center">
                <div className="login-card">
                    <div className="login-card-left-side">
                        <div className="login-card-left-side-icon">
                            <img src={worker} alt="worker" className="worker-icon"/>
                        </div>
                        <div className="login-card-left-side-title">
                            Asynchronous workers
                        </div>
                        <div className="login-card-left-side-version">
                            version: 1.0
                        </div>
                    </div>
                    <div className="login-card-right-side">
                        <div className="login-card-right-side-container">
                            <div className="login-card-right-side-title">Login</div>
                            <div className="login-card-right-side-form">
                                <form onSubmit={this.handleFormSubmit}>
                                    <div className="form-label">Username</div>
                                    <input
                                        className="form-item"
                                        placeholder="Type in your username..."
                                        name="username"
                                        type="text"
                                        onChange={this.handleChange}
                                    />
                                    <div className="form-label">Password</div>
                                    <input
                                        className="form-item"
                                        placeholder="Type in your password..."
                                        name="password"
                                        type="password"
                                        onChange={this.handleChange}
                                    />
                                    {optionalComponent}
                                    <div className="form-button">
                                        <input
                                            className="form-submit"
                                            value="Log in"
                                            type="submit"
                                        />
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default Login;