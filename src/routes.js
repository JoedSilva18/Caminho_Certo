import { Router } from 'express';
import PointController from './app/controllers/PointController';

const routes = new Router();

routes.get('/points', PointController.index);
routes.get('/point', PointController.show);
routes.post('/points', PointController.store);

export default routes;
