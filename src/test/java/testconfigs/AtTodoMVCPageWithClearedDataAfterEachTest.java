package testconfigs;

import org.junit.After;

import static com.codeborne.selenide.Selenide.executeJavaScript;

public class AtTodoMVCPageWithClearedDataAfterEachTest {
    @After
    public void clearData(){
        executeJavaScript("localStorage.clear()");
    }
}
