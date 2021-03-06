# CodeNation-AceleraDev-Java-Online-Central-de-Erros
Projeto de conclusao do aceleraDev Java Online Da CodeNation

## Central de Erros

### Objetivo
Em projetos modernos é cada vez mais comum o uso de arquiteturas baseadas em serviços ou microsserviços. Nestes ambientes complexos, erros podem surgir em diferentes camadas da aplicação (backend, frontend, mobile, desktop) e mesmo em serviços distintos. Desta forma, é muito importante que os desenvolvedores possam centralizar todos os registros de erros em um local, de onde podem monitorar e tomar decisões mais acertadas. Neste projeto vamos implementar uma API Rest para centralizar registros de erros de aplicações.

Abaixo estão os requisitos desta API, o time terá total liberdade para tomar as decisões técnicas e de arquitetura da API, desde que atendam os requisitos abaixo.

### API

### Tecnologia
* Utilizar a tecnologia base da aceleração para o desenvolvimento (Exemplo: Java, Node.js)

**Premissas**&nbsp;


* A API deve ser pensada para atender diretamente um front-end
* Deve ser capaz de gravar os logs de erro em um banco de dados relacional
* O acesso a ela deve ser permitido apenas por requisições que utilizem um token de acesso válido &nbsp;

**Funcionalidades**&nbsp;
* Deve permitir a autenticação do systems que deseja utilizar a API gerando o Token de Acesso
* Pode ser acessado por multiplos sistemas
* Deve permitir gravar registros de events de log salvando informações de Level(error, warning, info), Descrição do Evento, LOG do Evento, ORIGEM(Sistema ou Serviço que originou o events), DATA(Data do events), QUANTIDADE(Quantidade de Eventos de mesmo tipo)
* Deve permitir a listagem dos events juntamente com a filtragem de events por qualquer parâmetro especificado acima
* Deve suportar Paginação
* Deve suportar Ordenação por diferentes tipos de atributos
* A consulta de listagem não deve retornar os LOGs dos Eventos
* Deve permitir a busca de um events por um ID, dessa maneira exibindo o LOG desse events em específico
