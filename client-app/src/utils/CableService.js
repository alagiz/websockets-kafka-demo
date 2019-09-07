import actionCable from 'actioncable';

const CableApp = {};

CableApp.cable = actionCable.createConsumer(`ws://${window._env_.BACKEND_IP}:${window._env_.BACKEND_PORT}/cable`);
// CableApp.cable = actionCable.createConsumer(`ws://localhost:3102/cable`);

export default CableApp;