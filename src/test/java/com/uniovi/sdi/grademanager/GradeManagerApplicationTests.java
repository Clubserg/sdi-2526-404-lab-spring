package com.uniovi.sdi.grademanager;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GradeManagerApplicationTests {

    static String PathFirefox = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
    static String Geckodriver = "C:\\Users\\sergi\\IdeaProjects\\sdi-2526-404-lab-spring\\PL-SDI-Sesión5-material\\geckodriver-v0.36.0-win64.exe";

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
    void PR01() {
        Assertions.assertTrue(true);
    }

    @Test
    @Order(2)
    void PR02() {
        Assertions.assertTrue(true);
    }

    @Test
    @Order(3)
    void PR03() {
        Assertions.assertTrue(true);
    }

    @Test
    @Order(4)
    void PR04() {
        Assertions.assertTrue(true);
    }

    @Test
    @Order(5)
    void PR05() {
        Assertions.assertTrue(true);
    }

    @Test
    @Order(6)
    void PR06() {
        Assertions.assertTrue(true);
    }

    @Test
    @Order(7)
    void PR07() {
        Assertions.assertTrue(true);
    }

    @Test
    @Order(8)
    void PR08() {
        Assertions.assertTrue(true);
    }

    @Test
    @Order(9)
    void PR09() {
        Assertions.assertTrue(true);
    }

    @Test
    @Order(10)
    void PR10() {
        Assertions.assertTrue(true);
    }
}