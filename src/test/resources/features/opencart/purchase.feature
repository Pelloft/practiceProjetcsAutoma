
Feature: Compra de productos en OpenCart

  Scenario: Compra exitosa como invitado

    Given el usuario ingresa a la tienda OpenCart
    When el usuario agrega un producto al carrito
    And el usuario visualiza el carrito
    And el usuario realiza checkout como invitado
    Then la compra se realiza exitosamente