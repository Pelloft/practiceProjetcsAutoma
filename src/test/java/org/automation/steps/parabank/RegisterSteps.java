package org.automation.steps.parabank;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.datatable.DataTable;
import org.junit.jupiter.api.Assertions;

import java.util.Map;

public class RegisterSteps {

    private final CommonSteps commonSteps;

    public RegisterSteps() {
        this(new CommonSteps());
    }

    public RegisterSteps(CommonSteps commonSteps) {
        this.commonSteps = commonSteps;
    }

    @And("completa el formulario de registro con los siguientes datos:")
    public void completaElFormulario(DataTable dataTable) {
        Map<String, String> datos = dataTable.asMaps().get(0);
        commonSteps.getRegisterPage().completarFormulario(
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

    @Then("el sistema muestra el mensaje {string}")
    public void elSistemaMuestraElMensaje(String mensajeEsperado) {
        String mensajeActual = commonSteps.getRegisterPage().obtenerMensajeResultado();
        Assertions.assertTrue(
                mensajeActual.contains(mensajeEsperado),
                "Mensaje esperado: '" + mensajeEsperado + "' | Obtenido: '" + mensajeActual + "'"
        );
    }

    @Then("el sistema muestra un error de usuario ya existente")
    public void elSistemaMuestraErrorUsuarioExistente() {
        String mensaje = commonSteps.getRegisterPage().obtenerMensajeResultado();
        Assertions.assertTrue(
                mensaje.contains("already exists") || mensaje.contains("taken"),
                "Se esperaba error de usuario existente pero se obtuvo: " + mensaje
        );
    }

    @Then("el sistema muestra mensajes de validación en los campos requeridos")
    public void elSistemaMuestraMensajesValidacion() {
        String titulo = commonSteps.getRegisterPage().obtenerTituloPagina();
        Assertions.assertEquals(
                "Signing up is easy!",
                titulo,
                "Se esperaba permanecer en el formulario de registro"
        );
    }

    @And("hace click en el botón {string} sin completar el formulario")
    public void haceClickSinCompletarFormulario(String boton) {
        commonSteps.getRegisterPage().clickRegistrar();
    }
}