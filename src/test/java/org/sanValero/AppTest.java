package org.sanValero;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {

  private WebDriver driver;

  /** Test 1 busca una lista y revisa que los elementos son los mismos que contados a mano */
  @Test
  public void ListProdictTest() {

    ChromeOptions options = new ChromeOptions();

    driver = new ChromeDriver(options);

    driver.navigate().to("https://www.lidl.es/es/herramientas-electricas/c92");

    WebElement cookieButton = driver.findElement(By.className("cookie-alert-extended-button"));
    cookieButton.click();

    List<WebElement> links = driver.findElements(By.className("lazy"));
    assertEquals(links.size(), 8);

    driver.quit();
  }

  /**Test2 Hace una búsqueda en concreto y confirma el
   * título de la búsqueda utilizada*/
  @Test
  public void VerifyTittle() {

    ChromeOptions options = new ChromeOptions();
    driver = new ChromeDriver(options);

    driver.navigate().to("https://www.lidl.es/es/herramientas-electricas/c92");

    WebElement cookieButton = driver.findElement(By.className("cookie-alert-extended-button"));
    cookieButton.click();

    WebElement searchBox = driver.findElement(By.name("query"));
    searchBox.sendKeys("taladros");
    searchBox.submit();

    String title = driver.getTitle();
    System.out.println(title);

    String expectedTitle = "Resultado de búsqueda | Lidl";
    String actualTitle = driver.getTitle();
    assertEquals(expectedTitle, actualTitle);

    driver.quit();
  }
/**Se coloca un producto al carrito y se
 *  busca el mensaje de confirmación de dicha acción*/
  @Test
  public void testAddItemToCart() {

    ChromeOptions options = new ChromeOptions();
    driver = new ChromeDriver(options);

    driver
        .navigate()
        .to(
            "https://www.lidl.es/es/taladro-de-columna-400-w/p36291?fromRecommendation=true&scenario=last_seen&list=reco_homepage_last_seen&position=1");

    int seconds = 10;

    Duration duration = Duration.ofSeconds(seconds);
    WebDriverWait wait = new WebDriverWait(driver, duration);

    WebElement cookieButton = driver.findElement(By.className("cookie-alert-extended-button"));
    cookieButton.click();

    driver.navigate().refresh();

    WebElement addToCartButton = driver.findElement(By.id("add-to-cart"));
    addToCartButton.click();

    wait.until(
        ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h4.basket-overlay__title")));
    WebElement successText = driver.findElement(By.cssSelector("h4.basket-overlay__title"));
    assertEquals(
        "¡Buena elección! El artículo ha sido añadido a tu cesta de la compra.",
        successText.getText());

    driver.quit();
  }
}

