# PÓS TECH - [Logistica]

## Features do sistema

### Cadastrar uma nova entrega

Esse cadastro será efetuado pela comunicação via SQS ao realizar um novo pedido

#### Vincular um motorista com a entrega

Após uma entrega ser solicitada, o motorista parceiro deve ser vinculado a ela através do endpoint
POST
/api/delivery/{id}/driver/{id} [aqui](http://localhost:8083/swagger-ui/index.html#/delivery-controller/addDriver)

### Iniciar rota de entrega

Para o cliente saber o status real do seu pedido, ao iniciar o trajeto de entrega, deve-se atualizar
o pedido de entrega através do entpoint POST /api/delivery/{id}/start_route, sua documentação
está [aqui](http://localhost:8083/swagger-ui/index.html#/delivery-controller/startRoute)

#### Cancelar entrega

Caso aconteça algo com a entrega, pode-se cancela-la através do endpoint POST
/api/delivery/{id}/cancel, a documentação
está [aqui](http://localhost:8083/swagger-ui/index.html#/delivery-controller/cancel).

### Finalizar entrega

Ao finalizar a entrega e atualizar o pedido, deve-se utilizar o endpoint POST
/api/delivery/{id}/terminate, sua documentação pode ser
consultada [aqui](http://localhost:8083/swagger-ui/index.html#/delivery-controller/terminate).

### Consultar entrega

Para consultar a entrega, utiliza-se o endpoint GET /api/delivery/{id}, sua documentação pode ser
encontrada [aqui](http://localhost:8083/swagger-ui/index.html#/delivery-controller/getDelivery).

### Consultar todos as entregas

Para ter uma listagem de todas as entregas, utiliza-se o endpoint GET /api/delivery, sua
documentação pode ser
encontrada [aqui](http://localhost:8083/swagger-ui/index.html#/delivery-controller/getDeliveries).

### Consultar entregas aguardando motorista

Para ter uma listagem de todas as entregas que aguardam coleta, utiliza-se o endpoint
GET /api/delivery/waiting, sua documentação pode ser
encontrada [aqui](http://localhost:8083/swagger-ui/index.html#/delivery-controller/getDeliveriesWaiting).

## Inicializando localmente a API

### Docker

Para inicializar a aplicação localmente é necessário o uso do Docker, caso não tenha instalado, faça
o download [aqui](https://docs.docker.com/engine/install/).

#### Docker compose

O docker compose, arquivo responsável por configurar os containers, está no package .\docker,
portanto pode rodar através da sua IDE diretamente ou pelo comando `docker compose up` diretamente
na pasta do arquivo docker-compose.yml

#### Iniciando a aplicação

Utilize a IDE que melhor lhe agrade para iniciar a API

## Utilizando a API

Para fazer requisições a API é necessário o uso de algum software que tenha essa funcionalidade (
Postman, Insomnia, Apidog, ...) ou pode utilizar o terminal para rodar comandos cURL.

Caso utilize o postman, pode importar a collection que está no package .\postman
