"use strict"; function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }Object.defineProperty(exports, "__esModule", {value: true});var _mongoose = require('mongoose'); var _mongoose2 = _interopRequireDefault(_mongoose);

const UserSchema = new _mongoose2.default.Schema(
  {
    name: {
      type: String,
      required: true,
    },
    telephoneNumber: {
      type: Number,
      required: true,
    },
    lastLocation: {
      latitude: {
        type: Number,
        required: true,
      },
      longitude: {
        type: Number,
        required: true,
      },
    },
  },
  {
    timestamps: true,
  }
);

exports. default = _mongoose2.default.model('User', UserSchema);
