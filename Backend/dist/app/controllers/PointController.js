"use strict"; function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }Object.defineProperty(exports, "__esModule", {value: true});var _Point = require('../schemas/Point'); var _Point2 = _interopRequireDefault(_Point);

class PointController {
  async index(req, res) {
    const response = await _Point2.default.find();

    return res.status(200).json(response);
  }

  async store(req, res) {
    const response = await _Point2.default.create(req.body);

    return res.status(200).json(response);
  }

  async show(req, res) {
    const { name } = req.query;

    const response = await _Point2.default.findOne({
      name,
    });

    return res.status(200).json(response);
  }
}

exports. default = new PointController();
