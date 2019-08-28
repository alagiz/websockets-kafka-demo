import actionCable from 'actioncable';

const CableApp = {};

CableApp.cable = actionCable.createConsumer(`ws://${process.env.REACT_APP_TB_IP}:${process.env.REACT_APP_GATEWAY_PORT}/cable`);
// CableApp.cable = actionCable.createConsumer(`ws://localhost:3102/cable`);

export default CableApp;