## 🚀 User Management REST API with Spring Boot
![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3-green)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue)
![MySQL](https://img.shields.io/badge/MySQL-8-blue)
![Docker](https://img.shields.io/badge/Docker-Compose-informational)
![License](https://img.shields.io/badge/license-MIT-lightgrey)

A REST API built with Spring Boot for user management.
It allows storing users either in memory 🧠 or in a database (MySQL / PostgreSQL) 🗄️, depending on the configured profile.

✨ Features

✅ Full User CRUD
🔒 Password validation before sensitive updates
🔄 Profile support (mem / db)
🐳 Docker compatible
⚡ Ready-to-use REST endpoints
📡 MySQL (Workbench) or PostgreSQL (pgAdmin) connection


## ⚙️ Requirements
- ☕ [Java 17+](https://adoptopenjdk.net/)
- 📦 [Maven](https://maven.apache.org/)
- 🚀 [Spring Boot](https://spring.io/projects/spring-boot)
- 🐳 [Docker Desktop](https://www.docker.com/products/docker-desktop) (opcional, para correr DB con Compose)

🔄 Profiles

In-memory:

spring.profiles.active=In-Memory


Database (MySQL / PostgreSQL):

spring.profiles.active=db

⚡ Configuration
🔹 Manual DB (MySQL Workbench)
CREATE DATABASE IF NOT EXISTS DB_SB_example;

CREATE USER IF NOT EXISTS 'DefaultUser'@'localhost' IDENTIFIED BY 'password';

GRANT ALL PRIVILEGES ON DB_SB_example.* TO 'DefaultUser'@'localhost';

FLUSH PRIVILEGES;


application.properties

spring.datasource.url=jdbc:mysql://127.0.0.1:3306/DB_SB_example?useSSL=false&serverTimezone=UTC
spring.datasource.username=DefaultUser
spring.datasource.password=password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA / Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

🔹 Manual DB (PostgreSQL)
CREATE DATABASE DB_SB_example;
CREATE USER DefaultUser WITH ENCRYPTED PASSWORD 'password';
GRANT ALL PRIVILEGES ON DATABASE userdb TO DefaultUser;


application.properties

spring.datasource.url=jdbc:postgresql://127.0.0.1:3036/DB_SB_example
spring.datasource.username=DefaultUser
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

🐳 Docker Compose

📄 docker-compose.yml (in project root):

▶️ Start containers:

docker-compose up -d

✅ Notes

🆔 id field is immutable
🔒 password field is hidden with @JsonIgnore

🌐 API Endpoints
🔹 In-Memory Controller

Add user

POST http://127.0.0.1:8080/users/add-user
Content-Type: application/json

{
  "id": 1,
  "name": "user1",
  "email": "user1a@example.com",
  "password": "12345"
}


Get all users

GET http://127.0.0.1:8080/users/get-users


Get user by ID

GET http://127.0.0.1:8080/users/get-user/1


Delete user

DELETE http://127.0.0.1:8080/users/delete-user/3


Update password

PUT http://127.0.0.1:8080/users/update-pass/4
Content-Type: application/json

{
  "email": "user1@example.com",
  "password": "12345",
  "newPassword":"pas123",
  "confirmPassword":"pas123"
}

🔹 Database Controller

Add user

POST http://127.0.0.1:8080/users/add-user
Content-Type: application/json

{
  "name": "user2",
  "email": "user2@example.com",
  "password": "12345"
}

                                                                     Version es español
# 🛠️ User Management API



API REST en **Spring Boot** para la gestión de usuarios.  
Permite almacenar usuarios en **memoria** 🧠 o en **base de datos** (MySQL / PostgreSQL) 🗄️, según el perfil configurado.  

---

## ✨ Features
- ✅ CRUD completo de usuarios
- 🔒 Validación de contraseña antes de cambios sensibles
- 🔄 Soporte para **perfiles** (`mem` / `db`)
- 🐳 Compatible con **Docker**
- ⚡ Endpoints REST listos para usar
- 📡 Conexión a MySQL (Workbench) o PostgreSQL (pgAdmin)

---

## ⚙️ Requisitos
- ☕ [Java 17+](https://adoptopenjdk.net/)
- 📦 [Maven](https://maven.apache.org/)
- 🚀 [Spring Boot](https://spring.io/projects/spring-boot)
- 🐳 [Docker Desktop](https://www.docker.com/products/docker-desktop) (opcional, para correr DB con Compose)

---
---------------------------------------------------Perfil en memoria
spring.profiles.active=In-Memory
----------------------------------------------------Perfil en BD
spring.profiles.active=db



## ⚡ Configuración

### 🔹 BD Manual (MySQL Workbench)

```sql
CREATE DATABASE IF NOT EXISTS DB_SB_example;

CREATE USER IF NOT EXISTS 'DefaultUser'@'localhost' IDENTIFIED BY 'password';

GRANT ALL PRIVILEGES ON DB_SB_example.* TO 'DefaultUser'@'localhost';

FLUSH PRIVILEGES;


#application.properties -----------------------------MySQL

spring.datasource.url=jdbc:mysql://127.0.0.1:3306/DB_SB_example?useSSL=false&serverTimezone=UTC
spring.datasource.username=DefaultUser
spring.datasource.password=password

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA / Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect



### 🔹 BD Manual (PostgreSQL Manual)
CREATE DATABASE DB_SB_example;
CREATE USER DefaultUser WITH ENCRYPTED PASSWORD 'password';
GRANT ALL PRIVILEGES ON DATABASE userdb TO DefaultUser;


#application.properties -----------------------------PostgreSQL

#spring.datasource.url=jdbc:postgresql://127.0.0.1:3036/DB_SB_example
#spring.datasource.username=DefaultUser
#spring.datasource.password=password
#spring.jpa.hibernate.ddl-auto=update
#spring.jpa.show-sql=true
#spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect


Docker Compose

📄 docker-compose.yml (en raíz del proyecto):

▶️ Levantar contenedores:
docker-compose up -d


✅ Notas

🆔 El campo id es inmutable

🔒 El campo password está oculto con @JsonIgnore

-----------------------------------------Controller in memory
http://127.0.0.1:8080/users/add-user
POST /users
Content-Type: application/json

{
  "id": 1,
  "name": "user1",
  "email": "user1a@example.com",
  "password": "12345"
}

get users
http://127.0.0.1:8080/users/get-users
get user by id
http://127.0.0.1:8080/users/get-user/1
Delete user
http://127.0.0.1:8080/users/delete-user/3
update password
http://127.0.0.1:8080/users/update-pass/4
{
	"email": "user1@example.com",
	"password": "12345",
	"newPassword":"pas123",
	"confirmPassword":"pas123"
	
}

-----------------------------------Controller in BD
http://127.0.0.1:8080/users/add-user
POST /users
Content-Type: application/json

{
  "name": "user2",
  "email": "user2@example.com",
  "password": "12345"
}
