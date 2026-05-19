package org.automation.steps.parabank;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.datatable.DataTable;
import org.automation.steps.CommonSteps;
import org.automation.utils.TestContext;
import org.automation.utils.TestDataGenerator;
import org.junit.jupiter.api.Assertions;

import java.util.Map;

public class RegisterSteps {

    private final CommonSteps commonSteps;

    public RegisterSteps(CommonSteps commonSteps) {
        this.commonSteps = commonSteps;
    }


    @And("completa el formulario de registro con los siguientes datos:")
    public void completaElFormulario(DataTable dataTable) {
        Map<String, String> datos = dataTable.asMaps().get(0);

        // ── Resuelve cada campo — si el valor es GENERATED
        //    lo reemplaza con un dato único para esta ejecución.
        String username = resolverValor(datos.get("username"),
                TestDataGenerator.generateUsername());

        String ssn = resolverValor(datos.get("ssn"),
                TestDataGenerator.generateSsn());

        String phone = resolverValor(datos.get("phone"),
                TestDataGenerator.generatePhone());

        // Guarda el username en contexto para reutilizarlo
        // si otro escenario necesita hacer login con este usuario.
        TestContext.set("username", username);
        TestContext.set("password", datos.get("password"));

        System.out.println("Username generado: " + username);

        commonSteps.getRegisterPage().completarFormulario(
                datos.get("firstName"),
                datos.get("lastName"),
                datos.get("address"),
                datos.get("city"),
                datos.get("state"),
                datos.get("zipCode"),
                phone,
                ssn,
                username,
                datos.get("password")
        );
    }

    // Si el valor del campo es "GENERATED" devuelve el valor dinámico,
    // de lo contrario devuelve el valor original del feature.
    private String resolverValor(String valorFeature, String valorGenerado) {
        return "GENERATED".equals(valorFeature) ? valorGenerado : valorFeature;
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