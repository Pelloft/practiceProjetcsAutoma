package org.automation.utils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
    // @Before se ejecuta antes de cada escenario Cucumber.
    //    Aquí inicializamos el navegador para que esté listo.
    @Before
    public void setUp(Scenario scenario) {
        System.out.println("Iniciando escenario: " + scenario.getName());
        DriverManager.initDriver();
    }

    // @After se ejecuta después de cada escenario, pase o falle.
    //    Si el escenario falló, toma una captura de pantalla automáticamente.
    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            // Captura de pantalla en caso de fallo
            byte[] screenshot = ((org.openqa.selenium.TakesScreenshot)
                    DriverManager.getDriver())
                    .getScreenshotAs(org.openqa.selenium.OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "screenshot-fallo");
            System.out.println("Escenario fallido: " + scenario.getName());
        } else {
            System.out.println("Escenario exitoso: " + scenario.getName());
        }
        DriverManager.quitDriver();
    }
}
