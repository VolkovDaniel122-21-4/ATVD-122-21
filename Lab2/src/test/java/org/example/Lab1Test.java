package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Lab1Test {
  private WebDriver chromeDriver;
  private static final String baseUrl = "https://wallpaperscraft.com/";

  @BeforeClass(alwaysRun = true)
  public void setUp() {
    WebDriverManager.chromedriver().setup();
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.addArguments("--start-fullscreen");
    chromeOptions.addArguments("--remote-allow-origins=*");

    chromeOptions.setImplicitWaitTimeout(Duration.ofSeconds(15));
    this.chromeDriver = new ChromeDriver(chromeOptions);
  }


  @BeforeMethod
  public void precoditions() {
    chromeDriver.get(baseUrl);
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() {
    chromeDriver.quit();
  }

//  @Test
//  public void testHeaderElement() {
//    WebElement header = chromeDriver.findElement(By.id("header"));
//    Assert.assertNotNull(header);
//  }

  @Test
  public void testClickElement(){
    WebElement aElement = chromeDriver.findElement(By.xpath("/html/body/div/header/div[1]/div/div[2]/a/img"));
    Assert.assertNotNull(aElement);
    aElement.click();
    Assert.assertEquals(chromeDriver.getCurrentUrl(), baseUrl);
    System.out.println("I Found aElement on WallpaperCraft");
  }

  @Test
  public void testInputField() {
    WebElement searchInput = chromeDriver.findElement(By.xpath("/html/body/div/header/div[1]/div/div[3]/form/input"));

    String inputText = "Nature";
    searchInput.sendKeys(inputText);

    Assert.assertEquals(searchInput.getAttribute("value"), inputText, "Текст в поле не совпадает с ожидаемым");
    System.out.println("Text was successfully inputed in input field");
  }

  @Test
  public void foundElementBy() {
    WebElement searchElement = chromeDriver.findElement(By.className("logo"));
    Assert.assertNotNull(searchElement);
    System.out.println(
      String.format("Name of attribute is: %s ", searchElement.getAttribute("name")) +
      String.format("\n ID of attribute is: %s ", searchElement.getAttribute("id")) +
      String.format("\n Src value is: %s ", searchElement.getAttribute("src")) +
      String.format("\n Alt value is: %s ", searchElement.getAttribute("alt")) +
      String.format("\n Position of attribute is: (%d, %d) ", searchElement.getLocation().x, searchElement.getLocation().y) +
      String.format("\n Size of attribute is: %dx%d ", searchElement.getSize().width, searchElement.getSize().height)
    );
  }

  @Test
  public void testElementCondition() {
    WebElement logoElement = chromeDriver.findElement(By.xpath("//a[@href='/']"));

    Assert.assertTrue(logoElement.isDisplayed(), "Логотип не отображается на странице");

    String expectedLogoText = "WallpapersCraft";
    Assert.assertTrue(logoElement.getAttribute("title").contains(expectedLogoText), "Текст в логотипе не совпадает с ожидаемым");
    System.out.println("Логотип отображается корректно с ожидаемым текстом.");
  }

//  @Test
//  public void testSearchElement(){
//    String studentPageURL = "content/student_life/students/";
//    chromeDriver.get(baseUrl + studentPageURL);
//
//    WebElement searchElement = chromeDriver.findElement(By.tagName("input"));
//    Assert.assertNotNull(searchElement);
//    System.out.println(
//      String.format("Name of attribute is: %s ", searchElement.getAttribute("name")) +
//      String.format("\n ID of attribute is: %s ", searchElement.getAttribute("id")) +
//      String.format("\n Type of attribute is: %s ", searchElement.getAttribute("type")) +
//      String.format("\n Value of attribute is: %s ", searchElement.getAttribute("value")) +
//      String.format("\n Position of attribute is: (%d, %d) ", searchElement.getLocation().x, searchElement.getLocation().y) +
//      String.format("\n Size of attribute is: %dx%d ", searchElement.getSize().width, searchElement.getSize().height)
//    );
//
//    String inputValue = "Pls input some data";
//    searchElement.sendKeys(inputValue);
//    Assert.assertEquals(searchElement.getText(), inputValue);
//    searchElement.sendKeys(Keys.ENTER);
//    Assert.assertNotEquals(chromeDriver.getCurrentUrl(), studentPageURL);
//  }

//  @Test
//  public void testSlide() {
//    WebElement nextElement = chromeDriver.findElement(By.className("next"));
//    WebElement nextCSSElement = chromeDriver.findElement(By.cssSelector("a.next"));
//
//    Assert.assertEquals(nextElement, nextCSSElement);
//    WebElement buttonElement = chromeDriver.findElement(By.className("prev"));
//
//    for(int i = 0; i < 20; i++) {
//      if (nextElement.getAttribute("class").contains("disabled")) {
//        buttonElement.click();
//        Assert.assertTrue(buttonElement.getAttribute("class").contains("disabled"));
//        Assert.assertTrue(nextElement.getAttribute("class").contains("disabled"));
//      } else {
//        nextElement.click();
//        Assert.assertTrue(nextElement.getAttribute("class").contains("disabled"));
//        Assert.assertTrue(buttonElement.getAttribute("class").contains("disabled"));
//      }
//    }
//  }
}
