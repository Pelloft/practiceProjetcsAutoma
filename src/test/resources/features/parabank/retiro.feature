
@parabank @retiro @transacciones
Feature: Retiro de fondos en ParaBank
Como usuario autenticado
Quiero realizar un retiro desde mi cuenta
Para disponer de mi dinero

Background:
Given el usuario ha iniciado sesión con username "john" y password "demo"
And el usuario navega a la sección "Bill Pay"

@smoke
Scenario: Retiro exitoso con saldo suficiente
When el usuario completa el formulario de pago con los siguientes datos:
| payeeName | address     | city      | state | zipCode | phone      | account   | verifyAccount | amount |
| John Smith| 789 Pine St | Guayaquil | EC    | 090102  | 0991111111 | 13344     | 13344         | 50.00  |
And hace click en el botón "Send Payment"
Then el sistema muestra el mensaje "Bill Payment Complete"
And confirma el pago de "$50.00" a "John Smith"

Scenario: Retiro fallido — monto inválido
When el usuario completa el formulario de pago con monto "0"
And hace click en el botón "Send Payment"
Then el sistema muestra el error de monto inválido

Scenario: Retiro fallido — campos vacíos
When el usuario hace click en "Send Payment" sin completar el formulario
Then el sistema muestra los mensajes de validación requeridos