# Backend de Salud RedNorte

Este repositorio contiene el backend de "Salud RedNorte", una aplicación de gestión sanitaria. Desarrollado con Spring Boot, este servicio gestiona la información de los pacientes, las citas médicas y un sistema inteligente de listas de espera. Incorpora un mecanismo de reasignación de citas asíncrono mediante RabbitMQ y Resilience4j para una mayor tolerancia a fallos.

## Funcionalidades principales

* **Gestión de pacientes**: Creación y recuperación de historiales clínicos.

* **Programación de citas**: Programación, cancelación y consulta de citas médicas.

* **Sistema de listas de espera**: Adición de pacientes a una lista de espera para diversos servicios médicos (consulta, cirugía, urgencia). El sistema asigna prioridad según el tipo de atención requerida mediante un patrón de diseño Factory.

* **Reasignación asíncrona**: Cuando se cancela una cita, el sistema ofrece automáticamente y de forma asíncrona el espacio al siguiente paciente en la lista de espera para la especialidad correspondiente a través de RabbitMQ.
* **Resiliencia**: Implementa un patrón de disyuntor (Resilience4j) para gestionar posibles fallos en la comunicación con servicios externos, garantizando la estabilidad del sistema.

## Arquitectura y Diseño

La aplicación está construida con una pila Java moderna e incorpora varios patrones de diseño clave:

* **Framework**: Spring Boot
* **Persistencia de datos**: Spring Data JPA con Hibernate, conectado a una base de datos Oracle.

* **Mensajería asíncrona**: RabbitMQ para desacoplar el proceso de cancelación y reasignación de citas.

* **Patrón de fábrica**: La `AtencionFactory` crea dinámicamente objetos para diferentes tipos de atención médica (`Consulta`, `Cirugia`, `Urgencia`), cada uno con un nivel de prioridad base específico.

* **Patrón de disyuntor**: El `ReasignacionService` utiliza Resilience4j para gestionar de forma segura los fallos en las comunicaciones externas, con un método de reserva para evitar fallos en cascada.

## Configuración e instalación

### Requisitos previos

* JDK 17 o superior
* Maven
* Una instancia activa de Oracle Database
* Una instancia activa de RabbitMQ Server

### Configuración

1. Clonar el repositorio:

```sh

git clone https://github.com/DoomedPlayer/RedNorte-Back.git

cd RedNorte-Back

```

2. Actualizar las propiedades de la base de datos y la conexión a RabbitMQ en `src/main/resources/application.properties`. Deberá proporcionar la ruta de su Oracle Wallet, nombre de usuario, contraseña y detalles del servidor RabbitMQ.

```propiedades

# --- RabbitMQ (Los valores predeterminados suelen ser suficientes si se ejecuta localmente) ---

spring.rabbitmq.host=localhost

spring.rabbitmq.port=5672

spring.rabbitmq.username=guest

spring.rabbitmq.password=guest

# --- Base de datos Oracle ---

spring.datasource.url=jdbc:oracle:thin:@your_db_connection_string?TNS_ADMIN=/path/to/your/Wallet

spring.datasource.username=TU_NOMBRE_DE_USUARIO

spring.datasource.password=TU_CONTRASEÑA

spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
```

### Ejecución de la aplicación

Puede ejecutar la aplicación utilizando el wrapper de Maven incluido en el proyecto:

```sh
./mvnw spring-boot:run
```

La aplicación se iniciará y Conéctese a la base de datos y al intermediario de mensajes configurados.

## Puntos finales de la API

Los siguientes son los principales puntos finales de la API que ofrece el servicio:

### Controlador de Pacientes (`/api/pacientes`)

* `POST /registro`: Registra un nuevo paciente.

* **Cuerpo**: Objeto JSON `Paciente`.

* `GET /{rut}`: Recupera los detalles de un paciente según su RUT.

### Controlador de Lista de Espera (`/api/espera`)

* `POST /registrar`: Agrega un paciente a la lista de espera para un tipo específico de atención médica.

* **Parámetro de consulta**: `tipoAtención` (p. ej., "CONSULTA", "CIRUGÍA", "URGENCIA").

* **Cuerpo**: Objeto JSON `PacienteEspera`.

* `GET /lista`: Obtiene la lista completa de pacientes que se encuentran actualmente en la lista de espera.

### Controlador de Citas (`/api/citas`)

* `POST /agendar`: Programa una nueva cita.

* **Cuerpo**: Objeto JSON `Cita`.

* `PUT /cancelar/{id}`: Cancela una cita existente por su ID.

* `GET /paciente/{rut}`: Recupera todas las citas de un RUT de paciente determinado.

### Controlador de Reasignación (`/api/reasignacion`)

* `POST /ejecutar`: Inicia manualmente el proceso de reasignación de una cita cancelada. Este endpoint se utiliza para el procesamiento síncrono con un disyuntor.

* **Parámetros de consulta**: `idCitaCancelada`, `especialidad`.
