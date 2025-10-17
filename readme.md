# Precificacao Api 
---

## Sumario

- [Introduction](#introduction)
- [Architeture](#architeture)
- [Features](#features)
- [Requirements](#requirements)
- [Quick Start](#quick-start)
- [Testing](#testing)
- [Others](#others)
- [Documentation](#docs)

---

## Introduction

Api que disponibiliza os serviços de precificação de products e soluções de adquirência


### Padrão GitFlow Tripag
https://confluence.tribanco.com.br/pages/viewpage.action?pageId=25692442

## Architeture

<img src="./imgs/diagrama.png" alt="Arquitetura">

## Features

### Example
* TODO 1
* TODO 2
* TODO 3

## Requirements

Essa aplicação utiliza do Docker para subir suas dependencias:

* Oracle DB 12g


## Quick Start

1. Instale o docker disponível em [https://www.docker.com/products/docker-desktop](https://www.docker.com/products/docker-desktop)

2. Cria uma rede virtual docker:

`$ docker network create docker.local `
3. Rode o docker-compose para baixar as imagens e criar os containers

`$ docker-composer up -d`

4. Verifique se todos os container estão ativos:

`$ docker-composer ps`


### Docker Compose Básico

1. Pausa todos os containers

`$ docker-composer stop`

2. Inicia todos os containers

`$ docker-composer start`

3. Reinicia todos os containers

`$ docker-composer restart`

## Testing

* [Spring Boot Test](https://docs.spring.io/spring/docs/current/spring-framework-reference/web-reactive.html#spring-webflux)

## Others

### Pipeline Jenkins

O arquivo [Jenkinsfile](./Jenkinsfile) utiliza do padrão do GitFlow para fazer todo processo de build e deploy o job esta disponível
no em [Precificacao Jenkins Pipeline](http://jenkinsdsv.tribanco.com.br/job/adquirencia/job/tripag-precificacao-api/)

### Migration

Os script de Migration devem ficar localizados em `src/main/resouces/migration`


#### Rodando a aplicação local

```
$ mvn clean package -P[local|dsv|hml|prd] '-Djnk.pwd.adqfin=PASS_ENCRIPT' '-Djnk.pwd.dbsgad=PASS_ENCRIPT' '-Djnk.pwd.dbprocessadora=PASS_ENCRIPT'
```

#### Build
Para rodar o build da aplicação basta utilizar o comando abaixo:

```
$ mvn clean package
```

## [Docker](https://docs.docker.com/)

### Comandos básicos

* `$ docker stop $(docker ps -a -q) && docker rm $(docker ps -a -q) `
* `$ docker ps `
* `$ docker exec -it CONTAINER bash`


#### View Ip container

`$ docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' CONTAINER_ID`


## Documentation

