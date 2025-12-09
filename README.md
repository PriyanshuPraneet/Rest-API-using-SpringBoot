# 🧾 Spring Boot REST API – Users & Posts

A fully functional RESTful API built using **Spring Boot**, providing CRUD operations for **Users** and their **Posts**.  
This project is designed to demonstrate API development concepts such as **Versioning**, **HATEOAS**, **JPA**, **Exception Handling**, and **Dockerized MySQL Integration**.

---

## 🚀 Features

🔹 Retrieve all users  
🔹 Get a user by ID  
🔹 Create a new user  
🔹 Delete a user  
🔹 Retrieve posts created by a user  
🔹 Create a post for a user  
🔹 Delete a post  
🔹 Implemented **API versioning**  
🔹 Integrated **Spring HATEOAS** for hypermedia navigation  
🔹 Custom **Global Exception Handling**  
🔹 Database connected through **Dockerized MySQL**

---

## 🛠 Tech Stack

| Technology | Purpose |
|------------|---------|
| **Java (Spring Boot)** | Main application framework |
| **Spring Data JPA** | Database persistence |
| **HATEOAS** | REST Hypermedia links |
| **MySQL (Docker Image)** | Database |
| **Maven** | Dependency management |
| **Eclipse** | Development IDE |

---


---

## 📡 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/jpa/users` | Fetch all users |
| GET | `/jpa/users/{id}` | Fetch user by ID |
| POST | `/jpa/users` | Create a new user |
| DELETE | `/jpa/users/{id}` | Delete user |
| GET | `/jpa/users/{id}/posts` | Get posts of a specific user |
| POST | `/jpa/users/{id}/posts` | Create a post for a user |
| DELETE | `/jpa/posts/{postId}` | Delete a specific post |
| GET | `/V1/person` | Fetch name of a person (Versioning)
| GET | `/V2/person` | Fetch name of a person (Versioning)

---

## 🐋 Docker MySQL Setup

Run this command to start MySQL using Docker:

```bash
docker run --name social-media-db \
-e MYSQL_ROOT_PASSWORD=dummypassword \
-e MYSQL_DATABASE=social-media-database \
-e MYSQL_USER=social-media-user \
-e MYSQL_PASSWORD=dummypassword \
-p 3306:3306 \
-d mysql:latest
```

## Application Configuration

```bash
spring.application.name=restapi
spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:mem:testdb
spring.jpa.defer-datasource-initialization=true

#spring.datasource.url=jdbc:mysql://localhost:3306/social-media-database
#spring.datasource.username=social-media-user
#spring.datasource.password=dummypassword
#
#spring.jpa.hibernate.ddl-auto=update
#spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

## THANK YOU
