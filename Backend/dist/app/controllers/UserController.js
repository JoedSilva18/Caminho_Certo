"use strict"; function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }Object.defineProperty(exports, "__esModule", {value: true});var _User = require('../schemas/User'); var _User2 = _interopRequireDefault(_User);

class UserController {
  async store(req, res) {
    const response = await _User2.default.create(req.body);

    return res.status(200).json(response);
  }

  async show(req, res) {
    const { telephoneNumber } = req.query;

    const response = await _User2.default.findOne({
      telephoneNumber,
    });

    return res.status(200).json(response);
  }
}

exports. default = new UserController();
