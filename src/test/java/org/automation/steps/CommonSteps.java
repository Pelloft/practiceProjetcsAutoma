package org.automation.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.automation.pages.BillPayPage;
import org.automation.pages.LoginPage;
import org.automation.pages.RegisterPage;
import org.automation.pages.TransferPage;

public class CommonSteps {

    private LoginPage loginPage;
    private RegisterPage registerPage;
    private BillPayPage billPayPage;
    private TransferPage transferPage;

    @Given("el usuario está en la página de inicio de ParaBank")
    public void elUsuarioEstaEnPaginaInicio() {
        loginPage = new LoginPage();
    }

    @Given("el usuario ha iniciado sesión con username {string} y password {string}")
    public void elUsuarioHaIniciadoSesion(String username, String password) {
        loginPage = new LoginPage();
        loginPage.login(username, password);
    }

    // ── Step compartido para navegación entre secciones.
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

    @And("hace click en el botón {string}")
    public void haceClickEnElBoton(String boton) {
        switch (boton) {
            case "Log In":
                loginPage.clickLogin();
                break;
            case "Register":
                registerPage = new RegisterPage();
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
                        "Botón no reconocido en CommonSteps: " + boton
                );
        }
    }

    // ── Step compartido para clicks en enlaces de navegación.
    @When("hace click en {string}")
    public void haceClickEn(String elemento) {
        switch (elemento) {
            case "Log Out":
                loginPage.clickLogOut();
                break;
            case "Register":
                registerPage = new RegisterPage();
                break;
            default:
                throw new IllegalArgumentException(
                        "Elemento no reconocido: " + elemento
                );
        }
    }

    // ── Getters para que otros Steps accedan al estado de las páginas.
    public LoginPage getLoginPage()    { return loginPage;    }
    public RegisterPage getRegisterPage() { return registerPage; }
    public BillPayPage  getBillPayPage()  { return billPayPage;  }
    public TransferPage getTransferPage() { return transferPage; }

}
