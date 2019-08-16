import React, {Component} from 'react';
import AuthService from './AuthService';

export default function withAuth(AuthComponent) {
  const Auth = new AuthService();

  return class AuthWrapped extends Component {
    constructor(props) {
      super(props);

      this.state = {user: null};
    }

    componentWillMount() {
      if (!Auth.isLoggedIn()) {
        this.props.history.replace('/login')
      } else {
        try {
          const profile = Auth.getProfile();

          this.setState({user: profile});
        } catch (err) {
          Auth.logout();

          this.props.history.replace('/login');
        }
      }
    }

    render() {
      return this.state.user ? (<AuthComponent history={this.props.history} user={this.state.user}/>) : null;
    }
  };
}

