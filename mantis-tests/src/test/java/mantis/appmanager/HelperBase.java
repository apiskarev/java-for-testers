package mantis.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class HelperBase {

    protected ApplicationManager app;
    protected WebDriver wd;
    protected WebDriverWait wait;

    public HelperBase(ApplicationManager app) {
        this.app = app;
        this.wd = app.getDriver();
        wait = new WebDriverWait(wd, 10);
    }

    protected void click(By locator){
        try {
            element(locator).click();
        } catch (ElementNotVisibleException e){
            wait.until(wd -> element(locator).isDisplayed());
            wd.findElement(locator).click();
        }
    }

    protected void type(By locator, String text){
        click(locator);
        if (text != null){
            String existingText = element(locator).getAttribute("value");
            if (!text.equals(existingText)){
                element(locator).clear();
                element(locator).sendKeys(text);
            }
        }
    }

    protected void attach(By locator, File file){
        if (file != null){
            wd.findElement(locator).sendKeys(file.getAbsolutePath());
        }
    }

    protected boolean isElementPresent(By locator){
        try {
            wd.findElement(locator);
            return true;
        } catch (WebDriverException e){
            return false;
        }
    }

    protected WebElement element(By locator){
        return wd.findElement(locator);
    }

}
