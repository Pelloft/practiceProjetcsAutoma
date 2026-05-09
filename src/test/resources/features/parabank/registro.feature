
@parabank @registro
Feature: Registro de usuario en ParaBank
Como visitante del sitio
Quiero registrarme en ParaBank
Para poder acceder a los servicios bancarios

Background:
Given el usuario está en la página de inicio de ParaBank

@parabank
Scenario: Registro exitoso con datos válidos
When el usuario hace click en "Register"
And completa el formulario de registro con los siguientes datos:
| firstName | lastName | address     | city      | state | zipCode | phone      | ssn        | username    | password    |
| John      | Doe      | 123 Main St | Guayaquil | EC    | 090101  | 0991234567 | 123-45-678 | johndoe2025 | Password123 |
And hace click en el botón "Register"
Then el sistema muestra el mensaje "Your account was created successfully. You are now logged in."

Scenario: Registro fallido — usuario ya existe
When el usuario hace click en "Register"
And completa el formulario de registro con los siguientes datos:
| firstName | lastName | address     | city      | state | zipCode | phone      | ssn        | username | password    |
| Jane      | Doe      | 456 Oak Ave | Quito     | EC    | 170101  | 0987654321 | 987-65-432 | john     | Password123 |
And hace click en el botón "Register"
Then el sistema muestra un error de usuario ya existente "This username already exists."

Scenario: Registro fallido — campos obligatorios vacíos
When el usuario hace click en "Register"
And hace click en el botón "Register" sin completar el formulario
Then el sistema muestra mensajes de validación en los campos requeridos