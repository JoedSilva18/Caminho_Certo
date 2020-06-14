"use strict"; function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }Object.defineProperty(exports, "__esModule", {value: true});var _googlemapsservicesjs = require('@googlemaps/google-maps-services-js');
var _axios = require('axios'); var _axios2 = _interopRequireDefault(_axios);

const axiosInstance = _axios2.default.create(_axios.defaultConfig);

class MapsController {
  async calculateDistanceAndTime(req, res) {
    const { lat, lng } = req.query;
    const client = new (0, _googlemapsservicesjs.Client)({ axiosInstance });

    const response = await client.distancematrix({
      params: {
        origins: [{ lat, lng }],
        destinations: [{ lat: -22.9168223, lng: -47.1455917 }],
        key: process.env.GOOGLE_KEY,
      },
    });

    return res.status(200).json({
      distance: response.data.rows[0].elements[0].distance.text,
      duration: response.data.rows[0].elements[0].duration.text,
    });
  }
}

exports. default = new MapsController();
