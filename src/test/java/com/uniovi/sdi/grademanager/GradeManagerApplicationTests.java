package com.uniovi.sdi.grademanager;

import com.uniovi.sdi.grademanager.pageobjects.*;
import com.uniovi.sdi.grademanager.util.SeleniumUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GradeManagerApplicationTests {

    static String PathFirefox = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
    static String Geckodriver = "C:\\Users\\uo302282\\IdeaProjects\\sdi-2526-404-lab-spring\\PL-SDI-Sesión5-material\\geckodriver-v0.36.0-win64.exe";

    static WebDriver driver = getDriver(PathFirefox, Geckodriver);
    static String URL = "http://localhost:8090";

    public static WebDriver getDriver(String PathFirefox, String Geckodriver) {
        System.setProperty("webdriver.firefox.bin", PathFirefox);
        System.setProperty("webdriver.gecko.driver", Geckodriver);
        return new FirefoxDriver();
    }

    @BeforeEach
    public void setUp(){
        driver.navigate().to(URL);
    }

    @AfterEach
    public void tearDown(){
        driver.manage().deleteAllCookies();
    }

    @BeforeAll
    static public void begin() {}

    @AfterAll
    static public void end() {
        if (driver != null) {
            driver.quit();
        }
    }

    // --- Test Cases ---

    @Test
    @Order(1)
    void PR01A() {
        PO_HomeView.checkWelcomeToPage(driver, PO_Properties.getSPANISH());
    }

    @Test
    @Order(2)
    void PR01B() {
        List<WebElement> welcomeMessageElement = PO_HomeView.getWelcomeMessageText(driver,
                PO_Properties.getSPANISH());
        Assertions.assertEquals(welcomeMessageElement.getFirst().getText(),
                PO_HomeView.getP().getString("welcome.message", PO_Properties.getSPANISH()));
    }

    //PR02. Opción de navegación. Pinchar en el enlace Registro en la página home
    @Test
    @Order(3)
    public void PR02() {
        PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
    }

    //PR03. Opción de navegación. Pinchar en el enlace Identifícate en la página home
    @Test
    @Order(4)
    public void PR03() {
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
    }

    //PR04. Opción de navegación. Cambio de idioma de Español a Inglés y vuelta a Español
    @Test
    @Order(5)
    public void PR04() {
        PO_HomeView.checkChangeLanguage(driver, "btnSpanish", "btnEnglish",
                PO_Properties.getSPANISH(), PO_Properties.getENGLISH());
    }

    //PR05. Prueba del formulario de registro. registro con datos correctos
    @Test
    @Order(6)
    public void PR05() {
        //Vamos al formulario de registro
        PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
        //Rellenamos el formulario.
        PO_SignUpView.fillForm(driver, "77777778A", "Josefo", "Perez", "77777", "77777");
        //Comprobamos que entramos en la sección privada y nos nuestra el texto a buscar
        String checkText =  "Notas del usuario";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.getFirst().getText());
    }
    //PR06A. Prueba del formulario de registro. DNI repetido en la BD
    // Propiedad: Error.signup.dni.duplicate
    @Test
    @Order(7)
    public void PR06A() {
        PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
        PO_SignUpView.fillForm(driver, "99999990A", "Josefo", "Perez", "77777", "77777");
        List<WebElement> result = PO_SignUpView.checkElementByKey(driver, "Error.signup.dni.duplicate",
                PO_Properties.getSPANISH());
        //Comprobamos el error de DNI repetido.
        String checkText = PO_HomeView.getP().getString("Error.signup.dni.duplicate",
                PO_Properties.getSPANISH());
        Assertions.assertEquals(checkText, result.getFirst().getText());
    }

    //PR06B. Prueba del formulario de registro. Nombre corto.
    // Propiedad: Error.signup.dni.length
    @Test
    @Order(8)
    public void PR06B() {
        PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
        PO_SignUpView.fillForm(driver, "99999990B", "Jose", "Perez", "77777", "77777");
        List<WebElement> result = PO_SignUpView.checkElementByKey(driver, "Error.signup.name.length",
                PO_Properties.getSPANISH());
        //Comprobamos el error de Nombre corto de nombre corto .
        String checkText = PO_HomeView.getP().getString("Error.signup.name.length",
                PO_Properties.getSPANISH());
        Assertions.assertEquals(checkText, result.getFirst().getText());
    }

    @Test
    @Order(9)
    public void PR07() {
        //Vamos al formulario de logueo.
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        //Rellenamos el formulario
        PO_LoginView.fillLoginForm(driver, "99999990A", "123456");
        //Comprobamos que entramos en la pagina privada de Alumno
        String checkText =  "Notas del usuario";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result. getFirst().getText());
    }

    // PR08: Identificación válida con usuario de ROL profesor (99999993D/123456)
    @Test
    @Order(10)
    public void PR08() {
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "99999993D", "123456");
        // Los profesores suelen ver "Notas del usuario" o un texto de gestión
        String checkText = "Notas del usuario";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.getFirst().getText());
    }

    // PR09: Identificación válida con usuario de ROL Administrador
    @Test
    @Order(11)
    public void PR09() {
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "99999988F", "123456");

        // IMPORTANTE: En tu list.html el título es <h2>Usuarios</h2>
        // Usamos "id" y "main-container" para que clickOption no falle con las aserciones internas
        List<WebElement> result = PO_View.checkElementBy(driver, "text", "Usuarios");
        Assertions.assertFalse(result.isEmpty());
    }

    // PR10: Identificación inválida con usuario de ROL alumno (99999990A/erronea)
    // Nota: El enunciado decía 123456 pero "inválida", así que uso una contraseña mal para que falle.
    @Test
    @Order(12)
    public void PR10() {
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "99999990A", "contraseña_falsa");
        // Comprobamos que seguimos en la pantalla de login (no entró)
        PO_View.checkElementBy(driver, "text", "Identifícate");
    }

    @Test
    @Order(13)
    public void PR11() {
        // 1. Loguearse
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "99999990A", "123456");

        // 2. Desconectarse
        // Usamos el ID del botón de idioma como destino porque es único y seguro (sin tildes)
        PO_NavView.clickOption(driver, "logout", "id", "btnLanguage");

        // 3. Verificación final: Buscamos el texto de login traducido (Identifícate)
        // Esto confirma que estamos en la Home y que el usuario ya no está autenticado
        String loginText = PO_HomeView.getP().getString("login.message", PO_Properties.getSPANISH());
        List<WebElement> result = PO_View.checkElementBy(driver, "text", loginText);

        Assertions.assertFalse(result.isEmpty(), "No se encontró el enlace de login tras el logout");
    }

    // PR12. Loguearse como estudiante, ver 4 filas y desconectarse
    @Test
    @Order(14)
    public void PR12() {
        //Login
        PO_LoginView.login(driver, "99999990A", "123456", "Notas del usuario");
        // Revisamos las notas
        List<WebElement> marksList = SeleniumUtils.waitLoadElementsBy(driver, "free", "//tbody/tr", PO_View.getTimeout());
        Assertions.assertEquals(4, marksList.size());
        // Logout
        PO_PrivateView.logout(driver);
    }

    // PR13. Loguearse como estudiante y ver detalle de "Nota A2"
    @Test
    @Order(15)
    public void PR13() {
        //Login
        PO_LoginView.login(driver, "99999990A", "123456", "Notas del usuario");

        // Localizar y click en detalle
        By enlace = By.xpath("//td[contains(text(), 'Nota A2')]/following-sibling::*[2]");
        driver.findElement(enlace).click();

        PO_View.checkElementBy(driver, "text", "Detalles de la nota");

        //Logout
        PO_PrivateView.logout(driver);
    }

    // PR14. Loguearse como profesor y Agregar Nota.
    @Test
    @Order(16)
    public void PR14() {
        //Login
        PO_LoginView.login(driver, "99999993D", "123456", "99999993D");

        // Clickamos la opcion en especifico del menu
        PO_PrivateView.clickMenuOption(driver, "//*[@id='myNavbar']/ul[1]/li[2]", "//a[contains(@href, 'mark/add')]");

        String checkText = "Nota sistemas distribuidos";
        PO_PrivateView.fillFormAddMark(driver, 3, checkText, "8");

        // Paginación y comprobación
        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//a[contains(@class, 'page-link')]");
        elements.getLast().click();

        PO_View.checkElementBy(driver, "text", checkText);

        //Logout
        PO_PrivateView.logout(driver);
    }

    // PR15. Loguearse como profesor y borrar la nota creada
    @Test
    @Order(17)
    public void PR15() {
        //Login
        PO_LoginView.login(driver, "99999993D", "123456", "99999993D");

        //Clickamos nuestra opcion del menu en especifico
        PO_PrivateView.clickMenuOption(driver, "//*[@id='myNavbar']/ul[1]/li[2]", "//a[contains(@href, 'mark/list')]");

        // Ir a la última página
        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//a[contains(@class, 'page-link')]");
        elements.getLast().click();

        // Borrar la nota
        String markText = "Nota sistemas distribuidos";
        elements = PO_View.checkElementBy(driver, "free", "//td[contains(text(), '"+markText+"')]/following-sibling::*/a[contains(@href, 'mark/delete')]");
        elements.getFirst().click();

        // Comprobar ausencia
        elements = PO_View.checkElementBy(driver, "free", "//a[contains(@class, 'page-link')]");
        elements.getLast().click();
        SeleniumUtils.waitTextIsNotPresentOnPage(driver, markText, PO_View.getTimeout());

        // Logout
        PO_PrivateView.logout(driver);


    }
}