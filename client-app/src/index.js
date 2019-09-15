import React from 'react';
import ReactDOM from 'react-dom';
import {HashRouter as Router, Route} from 'react-router-dom';
import './index.css';

import Jobs from './screens/jobs/component';
import Login from './screens/login/component';

ReactDOM.render(
    <Router>
        <div className="container">
            <Route exact path="/" component={Jobs}/>
            <Route exact path="/login" component={Login}/>
        </div>
    </Router>
    , document.getElementById('root')
);
