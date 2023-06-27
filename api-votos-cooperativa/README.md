# API-VOTOS-COOPERATIVA



No cooperativismo, cada associado possui um voto e as decisões são tomadas em assembleias, por votação. A partir disso, criei uma solução back-end para gerenciar essas sessões de votação. Essa solução é executada na nuvem e promove as seguintes funcionalidades através de uma API REST:

Cadastro de uma nova pauta;
Cadastro de um novo associado;
Abre uma sessão de votação em uma pauta (a sessão de votação fica aberta por um tempo determinado na chamada de abertura ou 1 minuto por default);
Recebe votos dos associados em pautas (os votos são apenas 'Sim'/'Não'. Cada associado é identificado por um id único e pode votar apenas uma vez por pauta);
Contabiliza os votos e da o resultado da votação na pauta.

As informações sobre associados, pautas e os votos são persistidos em banco de dados MySQL.
