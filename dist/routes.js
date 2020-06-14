"use strict"; function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }Object.defineProperty(exports, "__esModule", {value: true});var _express = require('express');
var _PointController = require('./app/controllers/PointController'); var _PointController2 = _interopRequireDefault(_PointController);
var _UserController = require('./app/controllers/UserController'); var _UserController2 = _interopRequireDefault(_UserController);
var _MapsController = require('./app/controllers/MapsController'); var _MapsController2 = _interopRequireDefault(_MapsController);

const routes = new (0, _express.Router)();

routes.get('/points', _PointController2.default.index);
routes.get('/point', _PointController2.default.show);
routes.post('/points', _PointController2.default.store);

routes.get('/user', _UserController2.default.show);
routes.post('/user', _UserController2.default.store);

routes.get('/distanceAndTime', _MapsController2.default.calculateDistanceAndTime);

exports. default = routes;
