
@parabank @login
Feature: Login de usuario en ParaBank
Como usuario registrado
Quiero iniciar sesión en ParaBank
Para acceder a mi cuenta bancaria

Background:
Given el usuario está en la página de inicio de ParaBank

@smoke
Scenario: Login exitoso con credenciales válidas
When el usuario ingresa el username "john" y password "demo"
And hace click en el botón "Log In"
Then el sistema redirige al dashboard del usuario
And muestra el mensaje de bienvenida "Welcome"

Scenario: Login fallido — credenciales incorrectas
When el usuario ingresa el username "usuario_falso" y password "clave_incorrecta"
And hace click en el botón "Log In"
Then el sistema muestra el mensaje de error "The username and password could not be verified."

Scenario: Login fallido — campos vacíos
When el usuario hace click en el botón "Log In" sin ingresar credenciales
Then el sistema muestra el mensaje de error "Please enter a username and password."

Scenario: Cierre de sesión exitoso
Given el usuario ha iniciado sesión con username "john" y password "demo"
When hace click en "Log Out"
Then el sistema redirige a la página de inicio
And el botón "Log In" está visible nuevamente