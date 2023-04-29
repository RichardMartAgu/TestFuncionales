package org.sanValero;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

/** Copia del ejercicion anterior pero usando el: Patron Page Object. */
public class PatronPageObjectTest {

  private WebDriver driver;
  private WebDriverWait wait;

  private final By cookieButton = By.className("cookie-alert-extended-button");
  private final By searchBox = By.name("query");
  private final By addToCartButton = By.id("add-to-cart");
  private final By successText = By.cssSelector("h4.basket-overlay__title");
  private final By links = By.className("lazy");

  /** Instancia el driver */
  public void webDriver(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  }
  /** Navega a la URL que le metemos por parámetro */
  public void goToPage(String url) {
    driver.navigate().to(url);
  }
  /** Acepta los cookies */
  public void acceptCookies() {
    driver.findElement(cookieButton).click();
  }
  /** Hace una búsqueda de lo introducido por parametro */
  public void searchForProduct(String query) {
    WebElement searchField = driver.findElement(searchBox);
    searchField.sendKeys(query);
    searchField.submit();
  }
  /** Busca una lista de elementos */
  public int getNumberOfLinks() {
    List<WebElement> linkList = driver.findElements(links);
    return linkList.size();
  }
  /** Añade un objeto al carrito */
  public void addItemToCart() {
    driver.findElement(addToCartButton).click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(successText));
    assertEquals(
        "¡Buena elección! El artículo ha sido añadido a tu cesta de la compra.",
        driver.findElement(successText).getText());
  }
  /** Test 1 busca una lista y revisa que los elementos son los mismos que contados a mano */
  @Test
  public void ListProductTest() {

    ChromeOptions options = new ChromeOptions();
    driver = new ChromeDriver(options);

    PatronPageObjectTest pageObject = new PatronPageObjectTest();

    pageObject.webDriver(driver);

    pageObject.goToPage("https://www.lidl.es/es/herramientas-electricas/c92");

    pageObject.acceptCookies();

    int numberOfLinks = pageObject.getNumberOfLinks();

    assertEquals(8, numberOfLinks);

    driver.quit();
  }
  /** Test2 Hace una búsqueda en concreto y confirma el título de la búsqueda utilizada */
  @Test
  public void verifyTitle() {

    ChromeOptions options = new ChromeOptions();
    driver = new ChromeDriver(options);

    PatronPageObjectTest page = new PatronPageObjectTest();

    page.webDriver(driver);

    page.goToPage("https://www.lidl.es/es/herramientas-electricas/c92");
    page.acceptCookies();

    page.searchForProduct("taladros");

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.titleIs("Resultado de búsqueda | Lidl"));

    assertEquals("Resultado de búsqueda | Lidl", driver.getTitle());

    driver.quit();
  }
  /** Test 1 busca una lista y revisa que los elementos son los mismos que contados a mano */
  @Test
  public void testAddItemToCart() {

    ChromeOptions options = new ChromeOptions();

    driver = new ChromeDriver(options);

    PatronPageObjectTest pageObject = new PatronPageObjectTest();

    pageObject.webDriver(driver);

    pageObject.goToPage(
        "https://www.lidl.es/es/taladro-de-columna-400-w/p36291?fromRecommendation=true&scenario=last_seen&list=reco_homepage_last_seen&position=1");

    pageObject.acceptCookies();

    driver.navigate().refresh();

    pageObject.addItemToCart();

    driver.quit();
  }
}
