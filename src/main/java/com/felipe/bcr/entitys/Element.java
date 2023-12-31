package com.felipe.bcr.entitys;

import com.felipe.bcr.Main;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public enum Element {
    JOIN_BUTTON("Botón para iniciar sesión",
            "Se encuentra en la parte superior de la pantalla, donde está el navbar",
            "//a[contains(@data-link-id, 'login')]"),

    REGISTER_BUTTON("Botón para registrarse",
            "Se encuentra en la parte superior de la pantalla, donde está el navbar",
            "//a[contains(@data-link-id, 'registration')]"),

    USERNAME_INPUT("Input para el Usuario / Email",
            "Se encuentra en el primer formulario de inicio de sesión",
            "//input[@id='user_id']"),

    PASSWORD_INPUT("Input para la Contraseña",
            "Se encuentra en el segundo formulario de inicio de sesión",
            "/html/body/main/div/div[2]/div[2]/div/form/div[1]/div[1]/div/div[1]/input"),

    CONTINUE_BUTTON("Botón para continuar en el inicio de sesión",
            "Se encuentra debajo del formulario de inicio de sesión",
            "/html/body/main/div/div[1]/div[2]/div/form/div[2]/button"),

    BUY_BUTTON_ONE("Botón para comprar un producto [VERSIÓN 1]",
            "Se encuentra en la página de detalles de un producto",
            "/html/body/main/div[2]/div[4]/div[1]/div[2]/div/div[1]/form/div[5]/div/button[1]"),

    BUY_BUTTON_TWO("Botón para comprar un producto [VERSIÓN 2]",
            "Se encuentra en la página de detalles de un producto",
            "/html/body/main/div[2]/div[4]/div[1]/div[2]/div/div[1]/form/div[6]/div/button[1]"),

    FAV_BUTTON_ONE("Corazón que permite agregar a favoritos un producto [VERSIÓN 1]",
            "Se encuentra en todos los productos",
            "/html/body/main/div/div[4]/div/section/div[2]/div/div[1]/a"),

    FAV_BUTTON_TWO("Corazón que permite agregar a favoritos un producto [VERSIÓN 2]",
            "Se encuentra en todos los productos",
            "/html/body/main/div[2]/div[5]/div/div[1]/div/div[1]/div/div[1]/div/div[2]/form/button"),

    FAV_BUTTON_THREE("Corazón que permite agregar a favoritos un producto [VERSIÓN 3]",
            "Se encuentra en todos los productos",
            "/html/body/main/div[2]/div[5]/div[1]/div[1]/div/div[1]/div[2]/div[1]/form/button"),

    FAV_BUTTON_FOUR("Corazón que permite agregar a favoritos un producto [VERSIÓN 3]",
            "Se encuentra en todos los productos",
            "/html/body/main/div[2]/div[4]/div[1]/div[1]/div/div[1]/div[2]/div[1]/form/button"),

    FAV_NAVBAR_BUTTON("Botón para desplegar la lista de favoritos",
            "Se encuentra en la parte superior de la pantalla, donde está el navbar",
            "/html/body/header/div/div[6]/nav/a[2]"),

    PRODUCT_CARD_OFFER("Card de un producto en oferta",
            "Se encuentra en la sección de ofertas",
            "/html/body/main/div[2]/div[2]/div/ol/li[1]/div/a");

    private final String name;
    private final String description;
    private final String xpath;

    Element(String name, String description, String xpath) {
        this.name = name;
        this.description = description;
        this.xpath = xpath;
    }

    public String getName() {
        return name;
    }

    public String getXPath() {
        return xpath;
    }

    public String getDescription() {
        return description;
    }

    public WebElement getElement() {
        return Main.getDriver().findElement(By.xpath(this.getXPath()));
    }
}
