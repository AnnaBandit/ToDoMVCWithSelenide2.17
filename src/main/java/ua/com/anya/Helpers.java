package ua.com.anya;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Helpers {

    public static void doubleClick(WebElement element){
        Actions action = new Actions(getWebDriver());
        action.doubleClick(element).perform();
    }

    public static WebElement hover(WebElement element, WebDriver driver){
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();
        return element;
    }
}
