
@parabank @transferencia @transacciones
Feature: Transferencia de dinero entre cuentas en ParaBank
Como usuario autenticado
Quiero transferir dinero entre mis cuentas
Para gestionar mis fondos

Background:
Given el usuario ha iniciado sesión con username "john" y password "demo"
And el usuario navega a la sección "Transfer Funds"

@smoke
Scenario: Transferencia exitosa entre cuentas propias
When el usuario ingresa el monto "100.00" a transferir
And selecciona la cuenta de origen
And selecciona una cuenta de destino diferente
And hace click en el botón "Transfer"
Then el sistema muestra el mensaje "Transfer Complete!"
And confirma que "$100.00" fue transferido exitosamente

Scenario: Transferencia fallida — monto mayor al saldo disponible
When el usuario ingresa el monto "999999.00" a transferir
And selecciona la cuenta de origen
And selecciona una cuenta de destino diferente
And hace click en el botón "Transfer"
Then el sistema muestra un mensaje de error por fondos insuficientes

Scenario: Transferencia fallida — monto en cero
When el usuario ingresa el monto "0" a transferir
And hace click en el botón "Transfer"
Then el sistema muestra el error "Please enter a valid amount."