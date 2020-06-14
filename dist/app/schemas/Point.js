"use strict"; function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }Object.defineProperty(exports, "__esModule", {value: true});var _mongoose = require('mongoose'); var _mongoose2 = _interopRequireDefault(_mongoose);

const PointSchema = new _mongoose2.default.Schema(
  {
    name: {
      type: String,
      required: true,
    },
    latitude: {
      type: Number,
      required: true,
    },
    longitude: {
      type: Number,
      required: true,
    },
    city: {
      type: String,
      required: true,
    },
    uf: {
      type: String,
      required: true,
    },
    evaluation: {
      type: Number,
      required: true,
    },
    benefits: [
      {
        title: {
          type: String,
          required: true,
        },
      },
    ],
  },
  {
    timestamps: true,
  }
);

exports. default = _mongoose2.default.model('Point', PointSchema);
