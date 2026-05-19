package org.automation.steps.parabank;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.automation.steps.CommonSteps;
import org.junit.jupiter.api.Assertions;

public class LoginSteps {

    private final CommonSteps commonSteps;

    public LoginSteps() {
        this(new CommonSteps());
    }

    public LoginSteps(CommonSteps commonSteps) {
        this.commonSteps = commonSteps;
    }

    @When("el usuario ingresa el username {string} y password {string}")
    public void elUsuarioIngresaCredenciales(String username, String password) {
        commonSteps.getLoginPage().ingresarUsername(username);
        commonSteps.getLoginPage().ingresarPassword(password);
    }

    @When("el usuario hace click en el botón {string} sin ingresar credenciales")
    public void clickLoginSinCredenciales(String boton) {
        commonSteps.getLoginPage().clickLogin();
    }

    @Then("el sistema redirige al dashboard del usuario")
    public void elSistemaRedirigeDashboard() {
        String titulo = commonSteps.getLoginPage().obtenerTituloPagina();
        Assertions.assertEquals(
                "Accounts Overview",
                titulo,
                "Se esperaba el dashboard pero se obtuvo: " + titulo
        );
    }

    @And("muestra el mensaje de bienvenida {string}")
    public void muestraMensajeBienvenida(String mensaje) {
        Assertions.assertTrue(
                commonSteps.getLoginPage().logOutVisible(),
                "El mensaje de bienvenida no fue encontrado"
        );
    }

    @Then("el sistema muestra el mensaje de error {string}")
    public void elSistemaMuestraMensajeError(String mensajeEsperado) {
        String mensajeActual = commonSteps.getLoginPage().obtenerMensajeError();
        Assertions.assertTrue(
                mensajeActual.contains(mensajeEsperado),
                "Error esperado: '" + mensajeEsperado + "' | Obtenido: '" + mensajeActual + "'"
        );
    }

    @Then("el sistema redirige a la página de inicio")
    public void elSistemaRedirigePaginaInicio() {
        Assertions.assertTrue(
                commonSteps.getLoginPage().loginButtonVisible(),
                "Se esperaba ver el botón Log In después del logout"
        );
    }

    @And("el botón {string} está visible nuevamente")
    public void elBotonEstaVisible(String boton) {
        Assertions.assertTrue(
                commonSteps.getLoginPage().loginButtonVisible(),
                "El botón '" + boton + "' no está visible"
        );
    }

    /*@When("el usuario intenta acceder durante un error interno del servidor")
    public void elUsuarioIntentaAccederDuranteError() {
        // Simular un intento de acceso que resultará en error interno
        // En un escenario real, esto podría ser:
        // - Una URL que genera error en el servidor
        // - O esperar a que el servidor tenga un error y luego recargar
        loginPage = new LoginPage();
        // Aquí se podría simular el error, por ejemplo:
        // loginPage.navegarAURLQueGeneraError();
        // O forzar un estado de error en el servidor si es controlable
    } */
}
