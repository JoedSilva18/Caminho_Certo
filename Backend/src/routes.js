import { Router } from 'express';
import PointController from './app/controllers/PointController';
import UserController from './app/controllers/UserController';
import MapsController from './app/controllers/MapsController';

const routes = new Router();

routes.get('/points', PointController.index);
routes.get('/point', PointController.show);
routes.post('/points', PointController.store);

routes.get('/user', UserController.show);
routes.post('/user', UserController.store);

routes.get('/distanceAndTime', MapsController.calculateDistanceAndTime);

export default routes;
