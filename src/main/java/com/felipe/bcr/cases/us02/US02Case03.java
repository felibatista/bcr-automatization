package com.felipe.bcr.cases.us02;

import com.felipe.bcr.Case;
import com.felipe.bcr.Main;
import com.felipe.bcr.Status;
import com.felipe.bcr.controller.LogginController;
import com.felipe.bcr.controller.ProtectionController;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.time.Duration;

/*

Caso 3:
    Para este caso tenemos un conjunto formado (Publicar un producto - Comprar un producto - Hacer preguntas) con la misma salida y/o resultado esperado por lo que se utilizará uno de estos para no realizar más pruebas redundantes.
    ID: 003
    Descripción: Validar si al intentar comprar un producto ya no es necesario volver a hacer la autenticación.
    Pre-condiciones:
    1. Abrir: https://www.mercadolibre.com.ar/
    1. Estar logueado.
    Entradas: N/A
    Pasos:
    1. Hacer click en un Card de algún “producto en venta” (ref. imagen-card) para ser redirigido a la página de detalles de producto.
    2. Al estar cargada la página, hacer click en el botón “Comprar ahora”
    Resultados esperados: Se debería abrir una pantalla para completar el pago y otras cuestiones de envío.
    Condiciones posteriores: N/A
    Resultado: Aprobado

 */
public class US02Case03 {
    public static void run(){
        Case caseToTest = new Case(
                3,
                "Validar si al intentar comprar un producto ya no es necesario volver a hacer la autenticación",
                Status.NOT_EXECUTED
        );

        //prevent to run if user is not logged
        if (Main.getLogginController().checkIsLoggedWithJoinButton()) {
            Main.getLogginController().runAutoLogIn("TEST", "TEST");
        }

        try {
            Main.getDriver().get("https://www.mercadolibre.com.ar/ofertas#nav-header");

            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

            WebElement randomCard = Main.getDriver().findElement(By.xpath("/html/body/main/div[2]/div[2]/div/ol/li[1]/div/a"));
            String href = randomCard.getAttribute("href");
            Main.getDriver().get(href);

            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

            WebElement buyButton = Main.getDriver().findElement(By.xpath("/html/body/main/div[2]/div[4]/div[1]/div[2]/div/div[1]/form/div[5]/div/button[1]"));
            buyButton.click();

            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

            //prevent protection page
            if (ProtectionController.hasProtection()){
                ProtectionController.solveProtection();
            }

            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

            try{
                WebElement createAccountButton = Main.getDriver().findElement(By.cssSelector("#registration-link"));
            } catch (NoSuchElementException e) {
                caseToTest.setStatus(Status.PASSED);
                return;
            } catch (Exception e) {
                caseToTest.setStatus(Status.FAILED);
                return;
            }

            caseToTest.setStatus(Status.FAILED);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            caseToTest.setStatus(Status.BLOCKED);
        } catch (Exception e) {
            e.printStackTrace();
            caseToTest.setStatus(Status.FAILED);
        }
    }
}