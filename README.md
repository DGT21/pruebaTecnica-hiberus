# pruebaTecnica-hiberus

Este proyecto es un simple Spring Boot REST API para manegar contactos. Incluye opercaiones Basicas CRUD y usa una base de datos en memoria H2..

## Tabla de contenidos
* [Requisitos](#requisitos)
* [Empezando](#empezando)
  * [Clona el repositorio  ](#clona-el-repositorio)
  * [Configuración](#configuración)
  * [Construyendo y ejecutando](#construyendo-y-ejecutando)
* [Endpoints API](#endpoints-api)
* [Testeos](#testeos)
* [Swagger UI](#swagger-ui)

## Requisitos

* Java 8 o superior

* Maven o Gradle

## Empezando

Para obtener una copia del proyecto ejecutandose en local, debes de seguir estos pasos.

### Clona el repositorio
Para clonar el repositorio debes copiar este codigo:

```    
git clone https://github.com/DGT21/pruebaTecnica-hiberus.git  cd contact-api
```

### Configuración

La aplicacion esta configurada para que se use en el puerto 8081 y usa una base dedatos en     memoria H2. Puedes modificar la configuración en el archivo 'application.properties.'

  ```
  server.port=8081
  server.servlet.context-path=/
  spring.jackson.date-format=org.openapitools.RFC3339DateFormat
  spring.jackson.serialization.WRITE_DATES_AS_TIMESTAMPS=false

  # H2 Database configuration
  spring.datasource.url=jdbc:h2:mem:testdb
  spring.datasource.driverClassName=org.h2.Driver
  spring.datasource.username=sa
  spring.datasource.password=password
  spring.h2.console.enabled=true
  spring.jpa.hibernate.ddl-auto=update
  springdoc.api-docs.enabled=true
  springdoc.swagger-ui.enabled=true
  # JPA configuration
  spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
  spring.jpa.hibernate.ddl-auto=update
  ```

  ### Construyendo y ejecutando
  Dependiendo de la plataforma que usemos (Maven o Gradle) deberemos introducir los               siguientes comandos para ejecutar la aplicación:

  **USANDO MAVEN**
    
  ```
  mvn clean install
  mvn spring-boot:run
 	
 ````
  **USANDO MAVEN**
    
  ```
  ./gradlew clean build
  ./gradlew bootRun
  ```
      
Siguiendo estos pasos la aplicación empezara en http://localhost:8081

## Endpoints API

  Los siguientes endpoints estan disponibles en Contacto API

### Crear Contacto
* URL: '/contactos'
* Metodo: 'POST'
* Request Body:
```
{
  "nombre": "John",
  "apellidos": "Doe",
  "email": "john.doe@example.com"
}
```
### Get All Contactos
* URL:'/contactos'
* Metodo: 'GET'
### Obtener contacto por ID
* URL: `/contactos/{id}`
* Metodo: 'GET'
### Actualizar Contacto
* URL: `/contactos/{id}`
* Metodo: 'PUT'
* Request Body:
```
{
  "nombre": "John",
  "apellidos": "Doe",
  "email": "john.doe@example.com"
}
```
### Borrar un contacto
* URL: `/contactos/{id}`
* Metodo: 'DELETE'
## Testeos
Puedes  ejecutar los tests usando tanto Maven como Gradle
**Usando MAven**
```
mvn test
```
**Usando Gradle**
```
./gradlew test
```
## Swagger UI
La aplicación usa Springdoc OpenAPI para generar documentación de la API. Puedes acceder al Swagger UI a traves de la siguiente url: http://localhost:8081/swagger-ui.html.
