[JAVA_BADGE]: https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white
[SPRING_BADGE]: https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white
[SQLSERVER_BADGE]: https://img.shields.io/badge/Microsoft%20SQL%20Server-CC2927?style=for-the-badge&logo=microsoft%20sql%20server&logoColor=white
[SWAGGER_BADGE]: https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white
[DOCKER_BADGE]: https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white

<h1 align="center" style="font-weight: bold;">DSCommerce 🏬💻</h1>


<div style="text-align: center;">

![java][JAVA_BADGE]
![spring][SPRING_BADGE]
![Docker][DOCKER_BADGE]
![Swagger][SWAGGER_BADGE]
![MicrosoftSQL Server][SQLSERVER_BADGE]

</div>


<p align="center">
 <a href="#tech">Tecnologias</a> • 
 <a href="#started">Getting Started</a> • 
  <a href="#routes">API Endpoints</a> •
 <a href="#colab">Colaboradores</a> •
</p>

<p align="center">
    <b>Projeto criado durante o curso Spring Specialist da instituição DevSuperior do professor Nélio Alves.</b>
</p>

<h2 id="tech">💻 Tecnologias</h2>

- Java
- Spring
- Spring Security
- Spring Data JPA
- Flyway
- Microsoft SQL Server
- Docker
- Swagger

<h2 id="started">🚀 Getting started</h2>

<h3>Pré-requisitos</h3>

- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)
- [Java 21](https://www.oracle.com/br/java/technologies/downloads/#java21)
- [Git](https://git-scm.com/)

<h3>Clone</h3>

```bash
git clone https://github.com/RafaelJaber/DsCommerce.git
```

<h3>Starting</h3>

Para iniciar o projeto:

```bash
cd dscommerce
docker-compose up -d
```

<h3>Seed</h3>

- Abra o arquivo de seed na pasta: 'src/main/resources/db/seed.sql'
- Execute os inserts no banco de dados.

<h3>Run</h3>

- Inicie a classe DscommerceApplication por uma IDE ou então compile o projeto pelo mavem com os seguintes passos:

```bash
./mvnw deploy
```

```bash
java -jar target/dscommerce-0.0.1-SNAPSHOT.jar
```

<h2 id="routes">📍 API Endpoints</h2>

| Rota                                 | Descrição                                                      |
|--------------------------------------|----------------------------------------------------------------|
| <kbd>GET /users/me</kbd>             | Buscar informações do usuário logado                           |
| <kbd>POST /oauth2/token</kbd>        | Realizar o login na aplicação                                  |
| <kbd>GET /products</kbd>             | Buscar produtos - Sem Autenticação                             |
| <kbd>GET /products/{productId}</kbd> | Buscar produto por id - Sem Autenticação                       |
| <kbd>POST /products</kbd>            | Cadastrar produto - Necessário permissão admin                 |
| <kbd>PUT /products/{productId}</kbd> | Atualizar produto informando o id - Necessário permissão admin |
| <kbd>DEL /products/{productId}</kbd> | Deletar produto informando o id - Necessário permissão admin   |


<h2 id="colab">🤝 Colaboradores</h2>

<table>
  <tr>
    <td align="center">
      <a href="#">
        <img src="https://github.com/rafaeljaber.png" width="100px;" alt="Fernanda Kipper Profile Picture"/><br>
        <sub>
          <b>Rafael Jáber</b>
        </sub>
      </a>
    </td>
    <td align="center">
      <a href="#">
        <img src="https://github.com/acenelio.png" width="100px;" alt="Foto Nélio Alves"/><br>
        <sub>
          <b>Nélio Alves</b>
        </sub>
      </a>
    </td>
    <td align="center">
      <a href="#">
        <img src="https://github.com/devsuperior.png" width="100px;" alt="Foto DevSuperior"/><br>
        <sub>
          <b>DevSuperior</b>
        </sub>
      </a>
    </td>
  </tr>
</table>