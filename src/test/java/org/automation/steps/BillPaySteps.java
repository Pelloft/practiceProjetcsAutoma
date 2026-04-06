package org.automation.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.automation.pages.BillPayPage;
import org.junit.jupiter.api.Assertions;

import java.util.Map;

public class BillPaySteps {

    private final CommonSteps commonSteps;

    public BillPaySteps() {
        this(new CommonSteps());
    }

    public BillPaySteps(CommonSteps commonSteps) {
        this.commonSteps = commonSteps;
    }

    @When("el usuario completa el formulario de pago con los siguientes datos:")
    public void completaFormularioPago(DataTable dataTable) {
        Map<String, String> datos = dataTable.asMaps().get(0);
        commonSteps.getBillPayPage().completarFormularioPago(
                datos.get("payeeName"),
                datos.get("address"),
                datos.get("city"),
                datos.get("state"),
                datos.get("zipCode"),
                datos.get("phone"),
                datos.get("account"),
                datos.get("verifyAccount"),
                datos.get("amount")
        );
    }

    @When("el usuario completa el formulario de pago con monto {string}")
    public void completaFormularioConMonto(String monto) {
        commonSteps.getBillPayPage().ingresarMonto(monto);
    }

    @When("el usuario hace click en {string} sin completar el formulario")
    public void clickSinCompletarFormulario(String boton) {
        commonSteps.getBillPayPage().clickEnviarPago();
    }

    @Then("el sistema muestra el mensaje \"Bill Payment Complete\"")
    public void verificarPagoCompleto() {
        String titulo = commonSteps.getBillPayPage().obtenerTituloResultado();
        Assertions.assertEquals(
                "Bill Payment Complete",
                titulo,
                "Se esperaba confirmación de pago pero se obtuvo: " + titulo
        );
    }

    @And("confirma el pago de {string} a {string}")
    public void confirmaElPago(String monto, String destinatario) {
        String detalle = commonSteps.getBillPayPage().obtenerDetalleResultado();
        Assertions.assertTrue(
                detalle.contains(monto) && detalle.contains(destinatario),
                "El detalle no contiene monto o destinatario. Detalle: " + detalle
        );
    }

    @Then("el sistema muestra el error de monto inválido")
    public void verificarErrorMontoInvalido() {
        Assertions.assertFalse(
                commonSteps.getBillPayPage().resultadoVisible(),
                "No debería mostrarse resultado con un monto inválido"
        );
    }

    @Then("el sistema muestra los mensajes de validación requeridos")
    public void verificarMensajesValidacion() {
        Assertions.assertFalse(
                commonSteps.getBillPayPage().resultadoVisible(),
                "No debería mostrarse resultado con el formulario vacío"
        );
    }
}