package com.uniovi.sdi.grademanager.pageobjects;
import com.uniovi.sdi.grademanager.util.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class PO_PrivateView extends PO_NavView {
    static public void fillFormAddMark(WebDriver driver, int userOrder, String descriptionp, String scorep)
    {
        //Esperamos 5 segundo a que carge el DOM porque en algunos equipos falla
        SeleniumUtils.waitSeconds(driver, 5);
        //Seleccionamos el alumnos userOrder
        new Select(driver.findElement(By.id("user"))).selectByIndex(userOrder);
        //Rellenemos el campo de descripción
        WebElement description = driver.findElement(By.name("description"));
        description.clear();
        description.sendKeys(descriptionp);
        WebElement score = driver.findElement(By.name("score"));
        score.click();
        score.clear();
        score.sendKeys(scorep);
        By boton = By.className("btn");
        driver.findElement(boton).click();
    }



    static public void logout(WebDriver driver) {
        //Comprobacion de logout
        String loginText = PO_HomeView.getP().getString("signup.message", PO_Properties.getSPANISH());
        //Logout
        PO_PrivateView.clickOption(driver, "logout", "text", loginText);
    }

    static public void clickMenuOption(WebDriver driver, String menuIdXpath, String optionXpath) {

        List<WebElement> menu = PO_View.checkElementBy(driver, "free", menuIdXpath);
        menu.getFirst().click();
        // Pinchamos en la subopción (ej: Añadir Nota)
        List<WebElement> subOption = PO_View.checkElementBy(driver, "free", optionXpath);
        subOption.getFirst().click();
    }
}