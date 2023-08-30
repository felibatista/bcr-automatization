package com.felipe.bcr;

import com.felipe.bcr.cases.us02.US02Case02;
import com.felipe.bcr.controller.LogginController;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    private static WebDriver driver;
    private static LogginController logginController;

    public static void main(String[] args) {
        driver = new ChromeDriver();
        logginController = new LogginController();

        System.out.println("Iniciando WebDriver...");

        driver.get("https://www.mercadolibre.com.ar/");

        String title = driver.getTitle();
        System.out.println("Chrome: " + title);

        System.out.println("Running case 2...");
        US02Case02.run();
        Case case1 = Case.getCaseByID(2);
        System.out.println("Case 2 finished. Status: " + case1.getStatus());

    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void setDriver(WebDriver driver) {
        Main.driver = driver;
    }

    public static LogginController getLogginController() {
        return logginController;
    }
}