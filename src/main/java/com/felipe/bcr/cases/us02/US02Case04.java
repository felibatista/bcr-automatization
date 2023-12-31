package com.felipe.bcr.cases.us02;

import com.felipe.bcr.entitys.Case;
import com.felipe.bcr.entitys.Element;
import com.felipe.bcr.Main;
import com.felipe.bcr.entitys.Status;
import com.felipe.bcr.entitys.UserStory;
import org.openqa.selenium.NoSuchElementException;

import java.time.Duration;

/*

Caso 4:
    Para este caso tenemos un conjunto formado (Publicar un producto - Comprar un producto - Hacer preguntas) con la misma salida y/o resultado esperado por lo que se utilizará uno de estos para no realizar más pruebas redundantes.
    ID: 004
    Descripción: Validar que se pueda iniciar sesión en la página de detalles de un producto
    Pre-condiciones:
    1. Abrir: https://www.mercadolibre.com.ar/
    2. No estar logueado.
    Entradas: N/A
    Pasos:
    1. Hacer click en un Card de algún “producto en venta” (ref. imagen-card) para ser redirigido a la página de detalles de producto.
    2. Al estar cargada la página, hacer click en el botón “Ingresar” en la parte superior de la pantalla
    Resultados esperados: Se debería abrir una nueva pantalla o pop-up con la opción para iniciar sesión.
    Condiciones posteriores: N/A
    Resultado: Aprobado


 */
public class US02Case04 {
    public static void run(){
        Case caseToTest = new Case(
                UserStory.US02,
                204,
                "Validar que se pueda iniciar sesión en la página de detalles de un producto",
                Status.NOT_EXECUTED
        );

        Main.getDriver().get("https://www.mercadolibre.com.ar/ofertas#nav-header");

        if (!Main.getLogginController().checkIsLoggedWithJoinButton()) {
            caseToTest.addLog("(Error en PRE-CONDICIÓN) El usuario está logueado");
            caseToTest.setStatus(Status.PRE_CONDITION_FAILED);
            return;
        }

        try {
            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

            Main.getDriver().get(Element.PRODUCT_CARD_OFFER.getElement().getAttribute("href"));

            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

            try {
                Element.JOIN_BUTTON.getElement();
            } catch (Exception e){
                caseToTest.addLog("(Error #02-01-0) No se pudo encontrar el botón de inicio de sesión");
                caseToTest.setStatus(Status.BLOCKED);
                return;
            }
        } catch (Exception e) {
            caseToTest.addLog("(Error #02-04-2) Hubo un error inesperado \n[" + e.getMessage() + "]");
            caseToTest.setStatus(Status.FAILED);

            return;
        }

        caseToTest.setStatus(Status.PASSED);
    }
}
