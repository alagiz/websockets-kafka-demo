import decode from 'jwt-decode';

export default class AuthService {
  constructor() {
    this.login = this.login.bind(this);
    this.getProfile = this.getProfile.bind(this);
  }

  login = (username, password) => {
    // const apiGateway = `http://localhost:3102`;
    const apiGateway = `${window._env_.BACKEND_URL_HTTP}`;

    // Get a token
    return this.fetch(`${apiGateway}/authenticate`, {
      method: 'POST',
      body: JSON.stringify({
        username,
        password
      })
    }).then(response => {
      this.setToken(response.token);

      return Promise.resolve(response);
    })
  }

  isLoggedIn = () => {
    const token = this.getToken();

    return !!token && !this.isTokenExpired(token);
  }

  isTokenExpired = token => {
    try {
      const decoded = decode(token);

      return decoded.exp < Date.now() / 1000;
    }
    catch (err) {
      return false;
    }
  }

  setToken = idToken => localStorage.setItem('id_token', idToken);
  getToken = () => localStorage.getItem('id_token');
  logout = () => localStorage.removeItem('id_token');
  getProfile = () => decode(this.getToken());

  fetch = (url, options) => {
    // performs api calls sending the required authentication headers
    const headers = {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    }

    if (this.isLoggedIn()) {
      headers['Authorization'] = 'Bearer ' + this.getToken()
    }

    return fetch(url, {
      headers,
      ...options
    })
      .then(this._checkStatus)
      .then(response => response.json())
  }

  _checkStatus = response => {
    // raises an error in case response status is not a success
    if (response.status >= 200 && response.status < 300) {
      return response
    } else {
      const error = new Error(response.statusText);

      error.response = response;

      throw error;
    }
  }
}