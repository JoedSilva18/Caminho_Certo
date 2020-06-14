import User from '../schemas/User';

class UserController {
  async store(req, res) {
    const response = await User.create(req.body);

    return res.status(200).json(response);
  }

  async show(req, res) {
    const { telephoneNumber } = req.query;

    const response = await User.findOne({
      telephoneNumber,
    });

    return res.status(200).json(response);
  }
}

export default new UserController();
