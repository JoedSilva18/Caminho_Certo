import mongoose from 'mongoose';

const PointSchema = new mongoose.Schema(
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

export default mongoose.model('Point', PointSchema);
