/**
 *
 * main() será executado quando você chamar essa ação
 *
 * @param As ações do Cloud Functions aceitam um único parâmetro, que deve ser um objeto JSON.
 *
 * @return A saída dessa ação, que deve ser um objeto JSON.
 *
 */

const axios = require('axios');

async function main(params) {
  // Numero telefone
  const { telephoneNumber } = params;

  // Busca localizacao do usuario

  const responseUser = await axios.get(
    `https://truckshawee.herokuapp.com/user?telephoneNumber=${telephoneNumber}`
  );

  const { lastLocation } = responseUser.data;

  const responseDurationAndTime = await axios.get(
    `https://truckshawee.herokuapp.com/distanceAndTime?lat=${lastLocation.latitude}&lng=${lastLocation.longitude}`
  );

  return {
    distance: responseDurationAndTime.data.distance,
    duration: responseDurationAndTime.data.duration,
  };
}
