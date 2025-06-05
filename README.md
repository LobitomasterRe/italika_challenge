# 🏍️ Italika Backend Challenge

Este proyecto es una API RESTful desarrollada con **Spring Boot 3.4.6** como parte de un reto técnico. Gestiona un catálogo de productos permitiendo operaciones CRUD seguras mediante autenticación JWT y utilizando procedimientos almacenados en base de datos.

---

## 🚀 Tecnologías Usadas

- Java 21
- Spring Boot 3.4.6
- Spring Security 6
- JJWT 0.11.5
- Swagger (OpenAPI 3)
- JDBC con procedimientos almacenados
- PostgreSQL
- JUnit 5 + TestRestTemplate (para pruebas de integración)
- Postman para pruebas manuales

---

## 📁 Estructura del Proyecto

```
challenge/
├── src/main/java/
│   └── com.italika.challenge/
│       ├── application.service/        # Lógica de negocio
│       ├── domain.model/               # Entidades (Producto, Usuario)
│       ├── domain.port/                # Puertos (interfaces del dominio)
│       ├── domain.exception/           # Excepciones personalizadas
│       ├── infrastructure.adapter.controller/ # Controladores REST
│       ├── infrastructure.adapter.persistence/ # JDBC + procedimientos almacenados
│       ├── infrastructure.adapter.security/    # Filtro JWT y entrypoint
│       ├── infrastructure.config/      # Configuración Spring Security, Swagger, etc.
│       ├── infrastructure.dto/         # Clases de respuesta estándar (ApiResponse)
├── src/test/java/
│   └── com.italika.challenge/
│       └── ProductoControllerIntegrationTest.java # Tests completos con JWT
```

---

## 🧪 Endpoints Documentados

Accede a la documentación interactiva Swagger desde:  
📎 [`http://localhost:8080/swagger-ui.html`](http://localhost:8080/swagger-ui.html)

### 🔐 Autenticación

| Método | Endpoint          | Descripción            |
| ------ | ----------------- | ---------------------- |
| POST   | `/api/auth/login` | Devuelve un JWT válido |

### 📦 Productos

| Método | Endpoint                       | Descripción                     |
| ------ | ------------------------------ | ------------------------------- |
| GET    | `/api/productos`               | Lista todos los productos       |
| GET    | `/api/productos/{id}`          | Obtiene un producto por su ID   |
| POST   | `/api/productos`               | Crea un nuevo producto          |
| PUT    | `/api/productos/{id}`          | Actualiza un producto existente |
| PUT    | `/api/productos/bloquear/{id}` | Cambia el estado de "bloqueado" |
| DELETE | `/api/productos/{id}`          | Elimina un producto por ID      |

> ⚠️ Todos los endpoints requieren token JWT en el encabezado:  
> `Authorization: Bearer <token>`

---

## 🛠️ Cómo ejecutar

1. **Clona el repositorio**

   ```bash
   git clone https://github.com/LobitomasterRe/italika_challenge.git
   cd italika-backend-challenge
   ```

2. **Configura tu base de datos y otros parámetros**

   Edita el archivo `application.properties` con los datos de tu conexión JDBC y preferencias de configuración, así mismo, asegúrate de descargar el sql (challenge_query.sql) para ejecutar las querys en tu base de datos.

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/italika_db
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_password
   spring.application.name=challenge
   spring.jpa.hibernate.ddl-auto=none
   spring.jpa.show-sql=true
   spring.security.user.name=admin
   spring.security.user.password=admin123
   app.jwt.secret=123456789012345678901234567890123456789012345678901234567890abcd
   app.jwt.expiration=3600000
   server.port=8080
   ```

3. **Ejecuta el proyecto**

   ```bash
   mvn spring-boot:run
   ```

4. **Accede a Swagger**
   [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

5. **Restaura el collection para Postman**
   Descarga el archivo que contiene las APIS preconfiguradas para hacer pruebas manuales.
   No olvides realizar el cambio de token cuando inicies sesión en la configuración de la colección.

---

## ✅ Tests

Incluye tests de integración que validan:

- Login y generación de JWT
- CRUD completo de productos

Ejecuta los tests desde tu IDE o con:

```bash
mvn test
```

---

## 📌 Notas Finales

- El backend está preparado para conectarse a cualquier motor relacional compatible con JDBC.
- El manejo de errores devuelve estructuras JSON claras con `status`, `message` y `timestamp`.
- Swagger se integra y se protege correctamente, permitiendo pruebas seguras.

---

> Desarrollado por Renzo Garcia Auqui – 2025
