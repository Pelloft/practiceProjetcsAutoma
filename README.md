# AutomationProjetcs

Anthony Flores - Automatización E2E
POM + Selenium + Cucumber

# Descripción
Proyecto de automatización E2E con dos suites de pruebas independientes:

1. ParaBank  (https://parabank.parasoft.com)
    - Registro de usuario
    - Login
    - Transferencia de fondos entre cuentas
    - Pago de facturas (bill pay)

2. OpenCart  (https://opencart.abstracta.us)
    - Agregar productos al carrito
    - Visualizar carrito
    - Checkout como invitado
    - Confirmación de compra

# Requisitos:

1. Java Development Kit (JDK) 11 o superior
   Verificar: java -version
   Descargar: https://adoptium.net/

2. Apache Maven 3.6 o superior
   Verificar: mvn -version
   Descargar: https://maven.apache.org/download.cgi

3. Google Chrome (versión reciente)
   El proyecto usa WebDriverManager (autodownload = true), por lo que
   el ChromeDriver se descarga automáticamente al ejecutar las pruebas.

4. Variables de entorno configuradas:
    - JAVA_HOME apuntando al directorio de instalación del JDK
    - Maven en el PATH del sistema

# Tecnologías utilizadas

1. Java 17
2. Selenium 4.18.1
3. Cucumber 7.15.0
4. JUnit 5.10.2
5. Maven 3.9.14
6. Webdrivermanager 6.1.0