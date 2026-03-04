package com.uniovi.sdi.grademanager.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class PO_LoginView extends PO_NavView {

    static public void fillLoginForm(WebDriver driver, String dnip, String passwordp) {
        // Usamos "username" que es el ID y NAME que tienes en tu HTML
        // Usamos "id" porque el switch de SeleniumUtils para "id" sí tiene la @ correctamente
        List<WebElement> dniField = PO_View.checkElementBy(driver, "id", "username");

        WebElement dni = dniField.get(0);
        dni.click();
        dni.clear();
        dni.sendKeys(dnip);

        // Localizamos el campo Password (id="password" name="password")
        WebElement password = driver.findElement(By.name("password"));
        password.click();
        password.clear();
        password.sendKeys(passwordp);

        // Pulsar el botón de envío.
        // En tu HTML es un <button type="submit" class="btn btn-primary">
        By boton = By.className("btn-primary");
        driver.findElement(boton).click();
    }

    static public void login(WebDriver driver, String dni, String password, String checkText) {
        // Ir al login
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        // Rellenar
        fillLoginForm(driver, dni, password);
        // Comprobar
        PO_View.checkElementBy(driver, "text", checkText);
    }
}