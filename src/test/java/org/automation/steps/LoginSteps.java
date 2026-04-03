package org.automation.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.automation.pages.LoginPage;
import org.junit.jupiter.api.Assertions;

public class LoginSteps {

    /*La página se instancia aquí, no en el constructor.
      Cucumber crea esta clase por escenario, así el driver
      ya está listo cuando se llama el primer step.*/
    private LoginPage loginPage;

    @Given("el usuario está en la página de inicio de ParaBank")
    public void elUsuarioEstaEnLaPaginaDeInicio() {
        loginPage = new LoginPage();
    }

    @When("el usuario ingresa el username {string} y password {string}")
    public void elUsuarioIngresaCredenciales(String username, String password) {
        loginPage.ingresarUsername(username);
        loginPage.ingresarPassword(password);
    }

    @And("hace click en el botón {string}")
    public void haceClickEnElBoton(String boton) {
        if (boton.equals("Log In")) {
            loginPage.clickLogin();
        }
    }

    @Then("el sistema redirige al dashboard del usuario")
    public void elSistemaRedirigeDashboard() {
        String titulo = loginPage.obtenerTituloPagina();
        Assertions.assertEquals("Accounts Overview", titulo,
                "Se esperaba el dashboard pero se obtuvo: " + titulo);
    }

    @And("muestra el mensaje de bienvenida {string}")
    public void muestraMensajeBienvenida(String mensaje) {
        Assertions.assertTrue(
                loginPage.obtenerTituloPagina().contains(mensaje) ||
                        loginPage.logOutVisible(),
                "El mensaje de bienvenida no fue encontrado"
        );
    }

    @Then("el sistema muestra el mensaje de error {string}")
    public void elSistemaMuestraMensajeError(String mensajeEsperado) {
        String mensajeActual = loginPage.obtenerMensajeError();
        Assertions.assertTrue(
                mensajeActual.contains(mensajeEsperado),
                "Error esperado: '" + mensajeEsperado + "' | Obtenido: '" + mensajeActual + "'"
        );
    }

    @When("el usuario hace click en el botón {string} sin ingresar credenciales")
    public void clickLoginSinCredenciales(String boton) {
        loginPage.clickLogin();
    }

    @Given("el usuario ha iniciado sesión con username {string} y password {string}")
    public void elUsuarioHaIniciadoSesion(String username, String password) {
        loginPage = new LoginPage();
        loginPage.login(username, password);
    }

    @When("hace click en {string}")
    public void haceClickEn(String elemento) {
        if (elemento.equals("Log Out")) {
            loginPage.clickLogOut();
        }
    }

    @Then("el sistema redirige a la página de inicio")
    public void elSistemaRedirigePaginaInicio() {
        Assertions.assertTrue(
                loginPage.loginButtonVisible(),
                "Se esperaba ver el botón Log In después del logout"
        );
    }

    @And("el botón {string} está visible nuevamente")
    public void elBotonEstaVisible(String boton) {
        Assertions.assertTrue(
                loginPage.loginButtonVisible(),
                "El botón '" + boton + "' no está visible"
        );
    }
}
