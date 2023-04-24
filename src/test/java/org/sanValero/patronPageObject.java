package org.sanValero;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class patronPageObject {

    private WebDriver driver;
    private WebDriverWait wait;

    private By cookieButton = By.className("cookie-alert-extended-button");
    private By searchBox = By.name("query");
    private By addToCartButton = By.id("add-to-cart");
    private By successText = By.cssSelector("h4.basket-overlay__title");
    private By links = By.className("lazy");

    public void webDriver(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void goToPage(String url) {
        driver.navigate().to(url);
    }

    public void acceptCookies() {
        driver.findElement(cookieButton).click();
    }

    public void searchForProduct(String query) {
        WebElement searchField = driver.findElement(searchBox);
        searchField.sendKeys(query);
        searchField.submit();
    }

    public int getNumberOfLinks() {
        List<WebElement> linkList = driver.findElements(links);
        return linkList.size();
    }

    public void addItemToCart() {
        driver.findElement(addToCartButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(successText));
        assertEquals("¡Buena elección! El artículo ha sido añadido a tu cesta de la compra.", driver.findElement(successText).getText());
    }





    @Test
    public void ListProductTest() {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\richa\\OneDrive\\Escritorio\\Instalacion\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        patronPageObject pageObject = new patronPageObject();

        pageObject.webDriver(driver);

        pageObject.goToPage("https://www.lidl.es/es/herramientas-electricas/c92");

        pageObject.acceptCookies();

        int numberOfLinks = pageObject.getNumberOfLinks();

        assertEquals(8, numberOfLinks);

        driver.quit();
    }

    @Test
    public void verifyTitle() {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\richa\\OneDrive\\Escritorio\\Instalacion\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        patronPageObject page = new patronPageObject();

        page.webDriver(driver);

        page.goToPage("https://www.lidl.es/es/herramientas-electricas/c92");
        page.acceptCookies();

        page.searchForProduct("taladros");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleIs("Resultado de búsqueda | Lidl"));

        assertEquals("Resultado de búsqueda | Lidl", driver.getTitle());

        driver.quit();
    }


        @Test
    public void testAddItemToCart() {

        patronPageObject pageObject = new patronPageObject();
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\richa\\OneDrive\\Escritorio\\Instalacion\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        pageObject.webDriver(driver);
        pageObject.goToPage("https://www.lidl.es/es/taladro-de-columna-400-w/p36291?fromRecommendation=true&scenario=last_seen&list=reco_homepage_last_seen&position=1");
        pageObject.acceptCookies();

        driver.navigate().refresh();
        pageObject.addItemToCart();
        driver.quit();

    }

}

