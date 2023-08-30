package com.felipe.bcr.cases.us02;

import com.felipe.bcr.Main;
import com.felipe.bcr.controller.CaptchaController;
import com.felipe.bcr.entitys.Case;
import com.felipe.bcr.entitys.Element;
import com.felipe.bcr.entitys.Status;
import org.openqa.selenium.NoSuchElementException;

import java.time.Duration;

/*

ID: 008
    Descripción: Validar que se pueda iniciar sesión con nickname existente en la base de datos.
    Pre-condiciones:
    1. Abrir: https://www.mercadolibre.com.ar/
    2. No estar logueado.
    Entradas:
    Nickname válido: “FelipeBCR”
    Pasos:
    1. Hacer click en el botón “Ingresar” de la parte superior de la pantalla.
    2. En el formulario abierto escribir el “Nickname” en la sección de “Email, teléfono o usuario”
    3. Realizar el Captcha en caso de ser solicitado.
    4. Hacer click en el botón de “Continuar” para seguir el proceso.
    Resultados esperados: Se debería abrir un nuevo formulario para introducir la contraseña.
    Condiciones posteriores: N/A

 */
public class US02Case08 {
    public static void run(){
        Case caseToTest = new Case(
                8,
                "Validar que se pueda iniciar sesión con correo electrónico existente en la base de datos",
                Status.NOT_EXECUTED
        );

        //prevent to run if user is logged
        if (!Main.getLogginController().checkIsLoggedWithJoinButton()) {
            caseToTest.setStatus(Status.PRE_CONDITION_FAILED);
            return;
        }

        try {
            Element.JOIN_BUTTON.getElement().click();

            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

            Element.USERNAME_INPUT.getElement().sendKeys("HELLOJAVAA");

            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

            Element.CONTINUE_BUTTON.getElement().click();

            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

            //wait for captcha to be filled
            while (CaptchaController.hasCaptcha()){
                System.out.println("Esperando acción de rellenado de captcha manual...");

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            System.out.println("Captcha rellenado correctamente");

            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

            //check if password input is present, if not, the email is not registered
            Element.PASSWORD_INPUT.getElement();
        } catch (NoSuchElementException e) {
            caseToTest.setStatus(Status.BLOCKED);
            return;
        } catch (Exception e) {
            e.printStackTrace();
            caseToTest.setStatus(Status.FAILED);
            return;
        }

        caseToTest.setStatus(Status.PASSED);
    }
}