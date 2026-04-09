package org.automation.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.automation.pages.BillPayPage;
import org.automation.pages.LoginPage;
import org.automation.pages.RegisterPage;
import org.automation.pages.TransferPage;

public class CommonSteps {

    private LoginPage    loginPage;
    private RegisterPage registerPage;
    private BillPayPage  billPayPage;
    private TransferPage transferPage;

    // ── Navegación a página de inicio — usado en Background de login y registro
    @Given("el usuario está en la página de inicio de ParaBank")
    public void elUsuarioEstaEnPaginaInicio() {
        loginPage = new LoginPage();
    }

    // ── Sesión iniciada — usado en Background de retiro y transferencia
    @Given("el usuario ha iniciado sesión con username {string} y password {string}")
    public void elUsuarioHaIniciadoSesion(String username, String password) {
        loginPage = new LoginPage();
        loginPage.login(username, password);
    }

    // ── Navegación entre secciones del sistema
    @And("el usuario navega a la sección {string}")
    public void elUsuarioNavegaALaSeccion(String seccion) {
        switch (seccion) {
            case "Bill Pay":
                billPayPage = new BillPayPage();
                break;
            case "Transfer Funds":
                transferPage = new TransferPage();
                break;
            default:
                throw new IllegalArgumentException(
                        "Sección no reconocida: " + seccion
                );
        }
    }

    // ── Click en enlaces que incluyen "el usuario" como sujeto.
    //    Ejemplo: "el usuario hace click en "Register""
    @When("el usuario hace click en {string}")
    public void elUsuarioHaceClickEn(String elemento) {
        switch (elemento) {
            case "Register":
                registerPage = new RegisterPage();
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

    // ── Click en botones — sin "el usuario" como sujeto.
    //    Ejemplo: "hace click en el botón "Log In""
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

    // ── Click en enlaces sin "el usuario" como sujeto.
    //    Ejemplo: "hace click en "Log Out""
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

    // ── Getters — permiten a otros Steps acceder al estado de las páginas
    public LoginPage    getLoginPage()    { return loginPage;    }
    public RegisterPage getRegisterPage() { return registerPage; }
    public BillPayPage  getBillPayPage()  { return billPayPage;  }
    public TransferPage getTransferPage() { return transferPage; }
}
