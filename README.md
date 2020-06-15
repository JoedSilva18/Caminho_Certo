<p align="center">
  <img src="https://i.imgur.com/TY4wKsk.png" height="260" width="320" />
  <img src="https://conteudos.xpi.com.br/wp-content/uploads/2019/10/ccr-logo-nobo.png" height="290" width="320">
</p>

# Caminho Certo | Hackathon CCR

### Introdução
Com uma das maiores frotas de caminhões do mundo o Brasil se movimenta pelas estradas e rodovias, carregando praticamente tudo, e mesmo em tempos de pandemia essa circulação não pode parar. 

### A Ideia

O aplicativo "Caminho Certo", nasceu com a intenção de ajudar o caminhoneiro no planejamento de suas rotas e com isso, diminuir seus custos de viagem, previnir de possiveis imprevistos e com isso tornar a viagem menos desgastante. O aplicativo planeja sua rota e te mostra os locais confiáveis para a sua parada, seja para descansar, almoçar ou apenas esticar as pernas.

### Como Funciona

O aplicativo Caminho Certo é uma mão na roda para o caminhoneiro. Basta baixar o aplicativo e acessá-lo. Você precisa efetuar um login rápido com uma conta do Google e pronto. Você já pode usar nossos serviços!

<p align="center">
  <img src="https://i.imgur.com/B8MPEEw.png" height="400" width="200">
</p>


Feito isso, aparece um mapa e você pode escolher um ponto de destino digitando na barra de pesquisa acima do mapa. 

<p align="center">
  <img src="https://i.imgur.com/KnIDHEo.png" height="400" width="200">
</p>

A partir do momento que escolher o endereço final, clique na lupa. O aplicativo Caminho Certo traça a melhor rota da sua localização atual até o ponto desejado. Uma tela mostrando alguns dados básicos como Tempo, Distância e Número de Paradas será disparada. Se você deseja confirmar essa rota, clique em Confirmar.

<p align="center">
  <img src="https://i.imgur.com/MbMrzaF.png" height="400" width="200">
</p>

Agora com sua rota confirmada, você pode visualizar no mapa o trajeto certinho, interagindo com ele e visualizando os pontos/postos de parada que o aplicativo Caminho Certo escolheu pra você! Não se preocupe, só escolhemos pontos seguros e que vão aumentar a eficiência da sua rota. 

<p align="center">
  <img src="https://i.imgur.com/VHWMplu.png" height="400" width="200">
</p>

Se você já está pronto para partir, clique em Iniciar e o aplicativo Caminho Certo vai acompanhar sua viagem, garantindo assim amplo suporte e mais segurança!
Além disso, a qualquer momento você pode clicar em um dos postos escolhidos para parada que estão no mapa. Ali, você encontrará dados básicos sobre quanto falta pra chegar nesse ponto, se é possível comer, ou até dormir por lá. Com a ajuda da comunidade de caminhoneiros, você pode visualizar a avaliação do posto também, pra verificar se ele é um posto de qualidade.

<p align="center">
  <img src="https://i.imgur.com/R8PllMR.png" height="400" width="200">
</p>

Mesmo assim, se você tiver qualquer dúvida, seja sobre sua rota, sobre nossos serviços, sobre postos, como usar o aplicativo ou qualquer outro tipo de questão, basta clicar no ícone de WhatsApp no canto inferior direito. Ali você será redirecionado para falar com bot Caminho Certo. Iremos te atender sempre da melhor maneira e sanar suas dúvidas! 

<p align="center">
  <img src="https://i.imgur.com/B670NXU.png" height="400" width="200">
</p>

### Ferramentas Utilizadas

### [Twilio]("https://www.twilio.com/")
Serviço que possibilita a troca de mensagens através do Whatsapp. No projeto utilizamos para fazer a troca de mensagem entre o Watson Assistant e o usuário.

### [IBM Watson Assistant:]("https://www.ibm.com/cloud/watson-assistant/")
Serviço que auxilia na criação de assistants inteligentes. No projeto utilizamos para reconhecer a intenção do usuário, onde o mesmo faz perguntas relacionadas ao aplicativo ou a rota que ele está fazendo.

O dashboard que usamos dentro do Watson Assistant  apresentado a seguir:
<p align="center">
<img src="https://i.imgur.com/IppLYlp.png" height="460" width="920">
</p>

### [IBM Cloud Functions:]("https://developer.ibm.com/api/view/cloudfunctions-prod:cloud-functions#Overview")

Para unir o Watson Assistant ao nosso backend e aos serviços da Twilio, criamos duas actions em NodeJS e hospedamos na Cloud Functions da IBM. Link do serviço: 

https://us-south.functions.cloud.ibm.com/api/v1/web/joedunicamp%40gmail.com_dev/default/watson-web

https://us-south.functions.cloud.ibm.com/api/v1/web/joedunicamp%40gmail.com_dev/default/whatsapp-two



Enjoy :)
