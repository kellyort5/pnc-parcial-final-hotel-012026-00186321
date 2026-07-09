# PROMPTS.md

# Bitácora de uso de Inteligencia Artificial

## Prompt 1

**Herramienta:** ChatGPT

**Prompt utilizado:**

> Diseña la estructura de un proyecto backend en Spring Boot para un sistema de reservas de hotel utilizando arquitectura N-Capas. El proyecto debe incluir entidades para usuarios, hoteles, habitaciones, reservas y roles, siguiendo buenas prácticas de organización del código.

**Resultado generado:**

Se obtuvo una propuesta de estructura por capas con paquetes para entidades, repositorios, servicios, controladores, configuración y seguridad.

**Correcciones realizadas:**

Se adaptó la estructura al paquete base del proyecto proporcionado por el profesor y se reorganizaron algunos paquetes para mantener consistencia con el resto de la aplicación.

---

## Prompt 2

**Herramienta:** ChatGPT

**Prompt utilizado:**

> Implementa autenticación con Spring Security utilizando JWT, generando Access Token y Refresh Token con tiempos de expiración diferentes. Incluye el servicio para generar y validar tokens.

**Resultado generado:**

Se generó la configuración inicial de JWT, el filtro de autenticación, el servicio para generar tokens y la configuración de Spring Security.

**Correcciones realizadas:**

Se ajustó la configuración para utilizar las propiedades definidas en `application.yaml` y se adaptó el código a la versión utilizada de Spring Boot.

---

## Prompt 3

**Herramienta:** ChatGPT

**Prompt utilizado:**

> Implementa un sistema de autenticación con login mediante usuario y contraseña, devolviendo un Access Token y un Refresh Token. Incluye un endpoint para renovar el Access Token utilizando el Refresh Token.

**Resultado generado:**

Se desarrolló el controlador de autenticación, el servicio de login y el endpoint para refrescar el token.

**Correcciones realizadas:**

Se modificó la estructura de respuesta para ajustarla al formato requerido por el proyecto y se realizaron pruebas con usuarios de ejemplo.

---

## Prompt 4

**Herramienta:** ChatGPT

**Prompt utilizado:**

> Implementa autorización basada en roles para un sistema de reservas de hotel con los roles Administrador, Recepcionista y Huésped.

**Resultado generado:**

Se configuró la autorización utilizando anotaciones `@PreAuthorize` y la asignación de autoridades a partir del rol del usuario autenticado.

**Correcciones realizadas:**

Se ajustaron los nombres de los roles para que coincidieran exactamente con los definidos mediante el enum `RoleName`.

---

## Prompt 5

**Herramienta:** ChatGPT

**Prompt utilizado:**

> Implementa la regla de negocio donde un recepcionista únicamente pueda confirmar o gestionar reservas pertenecientes a la sucursal a la que está asignado.

**Resultado generado:**

Se propuso una validación comparando el hotel del usuario autenticado con el hotel asociado a la reserva.

**Correcciones realizadas:**

Se adaptó la validación al modelo de entidades del proyecto y se integró dentro del servicio de reservas para garantizar que la verificación se realizara antes de modificar el estado de una reserva.

---

## Prompt 6

**Herramienta:** ChatGPT

**Prompt utilizado:**

> Genera la configuración necesaria para ejecutar la API utilizando Docker y un contenedor de PostgreSQL mediante Docker Compose.

**Resultado generado:**

Se generaron el `Dockerfile` y el archivo `docker-compose.yml` para levantar la aplicación y la base de datos con un solo comando.

**Correcciones realizadas:**

Se modificaron los nombres de la base de datos, los puertos y las variables de entorno para ajustarlos a la configuración utilizada durante el desarrollo.

---

## Prompt 7

**Herramienta:** ChatGPT

**Prompt utilizado:**

> Crea un pipeline de GitHub Actions que compile el proyecto, ejecute las pruebas y realice verificaciones básicas de seguridad en cada push hacia la rama principal.

**Resultado generado:**

Se generó un workflow de GitHub Actions con compilación, ejecución de pruebas y análisis de seguridad.

**Correcciones realizadas:**

Se actualizaron las acciones utilizadas a versiones compatibles con el proyecto y se adaptó el pipeline para trabajar con Java 21.

---

## Prompt 8

**Herramienta:** ChatGPT

**Prompt utilizado:**

> Genera datos iniciales para facilitar las pruebas del sistema, incluyendo roles, usuarios, hoteles y habitaciones.

**Resultado generado:**

Se implementó un inicializador de datos utilizando `CommandLineRunner`.

**Correcciones realizadas:**

Se personalizaron los usuarios de prueba, las habitaciones y la sucursal inicial para facilitar las pruebas de autenticación y autorización durante el desarrollo.