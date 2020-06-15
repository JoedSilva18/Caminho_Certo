const AssistantV2 = require('ibm-watson/assistant/v2');
const { IamAuthenticator } = require('ibm-watson/auth');
const twilio = require('twilio');

async function main(parametros) {
  try {
    const assistant = new AssistantV2({
      version: parametros.VERSION_ASSISTANT,
      authenticator: new IamAuthenticator({
        apikey: parametros.APIKEY_ASSISTANT,
      }),
      url: parametros.URL_ASSISTANT,
    });

    const session = await assistant.createSession({
      assistantId: parametros.ASSISTANT_ID,
    });

    const resposta = await assistant.message({
      assistantId: parametros.ASSISTANT_ID,
      sessionId: session.result.session_id,
      input: {
        message_type: 'text',
        text: parametros.Body,
      },
    });

    parametros.mensagem = resposta.result.output.generic[0].text;
    await callTwilio(parametros);

    return {
      statusCode: 200,
      headers: { 'Content-Type': 'application/json' },
      body: { Body: 'sucesso' },
    };
  } catch (err) {
    return Promise.reject({
      statusCode: 500,
      headers: { 'Content-Type': 'application/json' },
      body: { message: err.message },
    });
  }
}
async function callTwilio(parametros) {
  const client = new twilio(parametros.AccountSid, parametros.authToken);
  await client.messages.create({
    body: parametros.mensagem,
    to: parametros.From,
    from: parametros.To,
  });
}
