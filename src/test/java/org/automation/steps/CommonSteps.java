package org.automation.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.automation.pages.parabank.BillPayPage;
import org.automation.pages.parabank.LoginPage;
import org.automation.pages.parabank.RegisterPage;
import org.automation.pages.parabank.TransferPage;
import org.automation.utils.DriverManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

public class CommonSteps {

    private LoginPage loginPage;
    private RegisterPage registerPage;
    private BillPayPage billPayPage;
    private TransferPage transferPage;

    private WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    @Given("el usuario está en la página de inicio de ParaBank")
    public void elUsuarioEstaEnPaginaInicio() {
        loginPage = new LoginPage(getDriver());
    }

    @Given("el usuario ha iniciado sesión con username {string} y password {string}")
    public void elUsuarioHaIniciadoSesion(String username, String password) {
        loginPage = new LoginPage(getDriver());
        loginPage.login(username, password);
    }

    @And("el usuario navega a la sección {string}")
    public void elUsuarioNavegaALaSeccion(String seccion) {
        switch (seccion) {
            case "Bill Pay":
                billPayPage = new BillPayPage(getDriver());
                break;
            case "Transfer Funds":
                transferPage = new TransferPage(getDriver());
                break;
            default:
                throw new IllegalArgumentException(
                        "Sección no reconocida: " + seccion
                );
        }
    }

    @When("el usuario hace click en {string}")
    public void elUsuarioHaceClickEn(String elemento) {
        switch (elemento) {
            case "Register":
                registerPage = new RegisterPage(getDriver());
                break;
            case "Log Out":
                loginPage.clickLogOut();
                break;
            default:
                throw new IllegalArgumentException(
                        "Elemento no reconocido en elUsuarioHaceClickEn: " + elemento
                );
        }
    }

    @And("hace click en el botón {string}")
    public void haceClickEnElBoton(String boton) {
        switch (boton) {
            case "Log In":
                loginPage.clickLogin();
                break;
            case "Register":
                registerPage.clickRegistrar();
                break;
            case "Send Payment":
                billPayPage.clickEnviarPago();
                break;
            case "Transfer":
                transferPage.clickTransferir();
                break;
            default:
                throw new IllegalArgumentException(
                        "Botón no reconocido en haceClickEnElBoton: " + boton
                );
        }
    }

    @When("hace click en {string}")
    public void haceClickEn(String elemento) {
        switch (elemento) {
            case "Log Out":
                loginPage.clickLogOut();
                break;
            default:
                throw new IllegalArgumentException(
                        "Elemento no reconocido en haceClickEn: " + elemento
                );
        }
    }

    @Then("el sistema muestra el mensaje {string}")
    public void elSistemaМuestraElMensaje(String mensajeEsperado) {

        String mensajeActual = obtenerMensajeSegunPaginaActiva(mensajeEsperado);

        System.out.println("Mensaje esperado : " + mensajeEsperado);
        System.out.println("Mensaje obtenido : " + mensajeActual);

        Assertions.assertTrue(
                mensajeActual.contains(mensajeEsperado),
                "Mensaje esperado: '" + mensajeEsperado +
                        "' | Obtenido: '"    + mensajeActual   + "'"
        );
    }

    private String obtenerMensajeSegunPaginaActiva(String mensajeEsperado) {
        if (mensajeEsperado.contains("account was created") ||
                mensajeEsperado.contains("already exists")) {
            return registerPage.obtenerMensajeResultado();
        }
        if (mensajeEsperado.contains("Bill Payment Complete")) {
            return billPayPage.obtenerTituloResultado();
        }
        if (mensajeEsperado.contains("Transfer Complete")) {
            return transferPage.obtenerTituloResultado();
        }
        return loginPage.obtenerMensajeError();
    }

    public LoginPage getLoginPage() {
        return loginPage;
    }

    public RegisterPage getRegisterPage() {
        return registerPage;
    }

    public BillPayPage getBillPayPage() {
        return billPayPage;
    }

    public TransferPage getTransferPage() {
        return transferPage;
    }
}
