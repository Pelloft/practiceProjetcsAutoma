package org.automation.runners;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@IncludeEngines("cucumber")

// Le dice a Cucumber dónde buscar los archivos .feature
@SelectClasspathResource("features/parabank")
@ConfigurationParameter(
        key   = GLUE_PROPERTY_NAME,
        // Le dice a Cucumber dónde están los Steps y los Hooks
        value = "org.automation.steps, org.automation.utils"
)
@ConfigurationParameter(
        key   = PLUGIN_PROPERTY_NAME,
        // pretty: muestra los pasos en consola con formato legible
        // html:   genera un reporte HTML en target/cucumber-reports
        value = "pretty, html:target/cucumber-reports/report.html"
)
@ConfigurationParameter(
        key   = FILTER_TAGS_PROPERTY_NAME,
        // Ejecuta solo los escenarios marcados con @smoke
        // Cambia a "@parabank" para ejecutar todos
        value = "@smoke"
)
public class TestRunner {

}
