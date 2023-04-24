package org.sanValero;



import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {
    @Test
    public void ListProdictTest() {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\richa\\OneDrive\\Escritorio\\Instalacion\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.navigate().to("https://www.lidl.es/es/herramientas-electricas/c92");

        WebElement cookieButton  = driver.findElement(By.className("cookie-alert-extended-button"));
        cookieButton .click();

        List<WebElement> links = driver.findElements(By.className("lazy"));
        assertEquals (links.size(), 8);

        System.out.println("Número de enlaces en la página: " + links.size());

        driver.quit();

    }


}


