import { Client, Status } from '@googlemaps/google-maps-services-js';
import axios, { defaultConfig } from 'axios';

const axiosInstance = axios.create(defaultConfig);

class MapsController {
  async calculateDistanceAndTime(req, res) {
    const { lat, lng } = req.query;
    const client = new Client({ axiosInstance });

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

export default new MapsController();
