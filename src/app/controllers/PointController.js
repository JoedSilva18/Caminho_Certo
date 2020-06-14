import Point from '../schemas/Point';

class PointController {
  async index(req, res) {
    const response = await Point.find();

    return res.status(200).json(response);
  }

  async store(req, res) {
    const response = await Point.create(req.body);

    return res.status(200).json(response);
  }

  async show(req, res) {
    const { name } = req.query;

    const response = await Point.findOne({
      name,
    });

    return res.status(200).json(response);
  }
}

export default new PointController();
