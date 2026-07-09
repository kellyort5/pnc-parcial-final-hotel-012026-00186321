# Reflexión

## ¿Qué generó bien la IA?

La estructura del proyecto, la autenticación con JWT y la configuración inicial de Spring Security.

## ¿Qué tuvo que corregirse?

Fue necesario adaptar la lógica de autorización para que el recepcionista solo pudiera gestionar reservas de su propia sucursal.

## ¿Cómo detectaron esos errores?

Comparando los requisitos del parcial con el código generado y agregando validaciones adicionales.

## Explicación de la regla de negocio

Cada recepcionista pertenece a una sucursal específica. Antes de confirmar una reserva, 
el sistema compara la sucursal del usuario autenticado con la sucursal de la habitación reservada. 
Si ambas coinciden, la operación continúa; de lo contrario, se rechaza.