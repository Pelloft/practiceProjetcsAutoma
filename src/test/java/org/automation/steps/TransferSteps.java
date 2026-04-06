package org.automation.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.automation.pages.TransferPage;
import org.junit.jupiter.api.Assertions;

public class TransferSteps {

    private final CommonSteps commonSteps;

    public TransferSteps() {
        this(new CommonSteps());
    }

    public TransferSteps(CommonSteps commonSteps) {
        this.commonSteps = commonSteps;
    }

    @When("el usuario ingresa el monto {string} a transferir")
    public void ingresaElMonto(String monto) {
        commonSteps.getTransferPage().ingresarMonto(monto);
    }

    @And("selecciona la cuenta de origen")
    public void seleccionaCuentaOrigen() {
        commonSteps.getTransferPage().seleccionarCuentaOrigen();
    }

    @And("selecciona una cuenta de destino diferente")
    public void seleccionaCuentaDestino() {
        commonSteps.getTransferPage().seleccionarCuentaDestino();
    }

    @Then("el sistema muestra el mensaje \"Transfer Complete!\"")
    public void verificarTransferenciaCompleta() {
        String titulo = commonSteps.getTransferPage().obtenerTituloResultado();
        Assertions.assertEquals(
                "Transfer Complete!",
                titulo,
                "Se esperaba confirmación de transferencia pero se obtuvo: " + titulo
        );
    }

    @And("confirma que {string} fue transferido exitosamente")
    public void confirmaTransferencia(String monto) {
        String detalle = commonSteps.getTransferPage().obtenerDetalleResultado();
        Assertions.assertTrue(
                detalle.contains(monto),
                "El detalle no contiene el monto esperado. Detalle: " + detalle
        );
    }

    @Then("el sistema muestra un mensaje de error por fondos insuficientes")
    public void verificarErrorFondosInsuficientes() {
        String error = commonSteps.getTransferPage().obtenerMensajeError();
        Assertions.assertFalse(
                error.isEmpty(),
                "Se esperaba un mensaje de error por fondos insuficientes"
        );
    }

    @Then("el sistema muestra el error {string}")
    public void verificarMensajeError(String mensajeEsperado) {
        String error = commonSteps.getTransferPage().obtenerMensajeError();
        Assertions.assertTrue(
                error.contains(mensajeEsperado),
                "Error esperado: '" + mensajeEsperado + "' | Obtenido: '" + error + "'"
        );
    }
}