package org.automation.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.datatable.DataTable;
import org.automation.pages.RegisterPage;
import org.junit.jupiter.api.Assertions;

import java.util.Map;

public class RegisterSteps {

    private RegisterPage registerPage;

    @When("el usuario hace click en {string}")
    public void elUsuarioHaceClickEn(String elemento) {
        if (elemento.equals("Register")) {
            registerPage = new RegisterPage();
        }
    }

    /*DataTable mapea la tabla Gherkin a un Map<String, String>.
     Cada columna del Gherkin se convierte en una clave del mapa.*/
    @And("completa el formulario de registro con los siguientes datos:")
    public void completaElFormulario(DataTable dataTable) {
        Map<String, String> datos = dataTable.asMaps().get(0);
        registerPage.completarFormulario(
                datos.get("firstName"),
                datos.get("lastName"),
                datos.get("address"),
                datos.get("city"),
                datos.get("state"),
                datos.get("zipCode"),
                datos.get("phone"),
                datos.get("ssn"),
                datos.get("username"),
                datos.get("password")
        );
    }

    @And("hace click en el botón \"Register\"")
    public void haceClickBotonRegister() {
        registerPage.clickRegistrar();
    }

    @Then("el sistema muestra el mensaje {string}")
    public void elSistemaMuestraElMensaje(String mensajeEsperado) {
        String mensajeActual = registerPage.obtenerMensajeResultado();
        Assertions.assertTrue(
                mensajeActual.contains(mensajeEsperado),
                "Mensaje esperado: '" + mensajeEsperado + "' | Obtenido: '" + mensajeActual + "'"
        );
    }

    @Then("el sistema muestra un error de usuario ya existente")
    public void elSistemaMuestraErrorUsuarioExistente() {
        String mensaje = registerPage.obtenerMensajeResultado();
        Assertions.assertTrue(
                mensaje.contains("already exists") || mensaje.contains("taken"),
                "Se esperaba error de usuario existente pero se obtuvo: " + mensaje
        );
    }

    @Then("el sistema muestra mensajes de validación en los campos requeridos")
    public void elSistemaMuestraMensajesValidacion() {
        String titulo = registerPage.obtenerTituloPagina();
        Assertions.assertEquals("Signing up is easy!", titulo,
                "Se esperaba permanecer en el formulario de registro"
        );
    }

    @And("hace click en el botón {string} sin completar el formulario")
    public void haceClickSinCompletarFormulario(String boton) {
        registerPage.clickRegistrar();
    }
}