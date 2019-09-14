import React from 'react';
import ReactDOM from 'react-dom';
import {HashRouter as Router, Route} from 'react-router-dom';
import './index.css';

import DataSelection from './screens/jobs/component';
import Login from './screens/login/component';

ReactDOM.render(
    <Router basename="/ws">
        <div className="container">
            <Route exact path="/" component={DataSelection}/>
            <Route exact path="/login" component={Login}/>
        </div>
    </Router>
    , document.getElementById('root')
);
