package ua.com.anya;

import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.actions;

public class Helpers {

    public static void doubleClick(WebElement element){
        actions().doubleClick(element).perform();
    }
}
