<p align="center">
  <img src="https://i.imgur.com/TY4wKsk.png" height="260" width="320" />
</p>

# Caminho Certo | Hackathon CCR

### Introdução
Com uma das maiores frotas de caminhões do mundo o Brasil se movimenta pelas estradas e rodovias, carregando praticamente tudo, e mesmo em tempos de pandemia essa circulação não pode parar. 

### A Ideia

O aplicativo "Caminho Certo", nasceu com a intenção de ajudar o caminhoneiro no planejamento de suas rotas e com isso, diminuir seus custos de viagem, previnir de possiveis emprevistos e com isso tornar a viagem menos desgastante. O aplicativo planeja sua rota e te mostra os locais confiaveis para a sua parada, seja para descansar, almoçar ou apenas esticar as pernas.

### Ferramentas Utilizadas

### [Twilio]("https://www.twilio.com/")
Serviço que possibilita a troca de mensagens através do Whatsapp. No projeto utilizamos para fazer a troca de mensagem entre o Watson Assistant e o usuário.

### [IBM Watson Assistant:]("https://www.ibm.com/cloud/watson-assistant/")
Serviço que auxilia na criação de assistants inteligentes. No projeto utilizamos para reconhecer a intenção do usuário, onde o mesmo faz perguntas relacionadas ao aplicativo ou a rota que ele esta fazendo.

O dashboard que usamos dentro do Watson Assistant  apresentado a seguir:
<p align="center">
<img src="https://i.imgur.com/IppLYlp.png" height="460" width="920">
</p>

### [IBM Cloud Functions:]("https://developer.ibm.com/api/view/cloudfunctions-prod:cloud-functions#Overview")

Para unir o Watson Assistant ao nosso backend e aos serviços da Twilio, criamos duas actions em NodeJS e hospedamos na Cloud Functions da IBM. Link do serviço: 

https://us-south.functions.cloud.ibm.com/api/v1/web/joedunicamp%40gmail.com_dev/default/watson-web

https://us-south.functions.cloud.ibm.com/api/v1/web/joedunicamp%40gmail.com_dev/default/whatsapp-two



Enjoy :)
