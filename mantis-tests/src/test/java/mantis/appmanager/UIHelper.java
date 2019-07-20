package mantis.appmanager;

import org.openqa.selenium.By;
import org.testng.Assert;

public class UIHelper extends HelperBase {

    public UIHelper(ApplicationManager app) {
        super(app);
    }

    public UIHelper loginAsAdmin() {
        app.navigate().toLoginPage();
        By proceedButton = By.cssSelector("input.btn-success");
        String adminLogin = app.getProperty("web.adminLogin");
        type(By.name("username"), adminLogin);
        click(proceedButton);
        type(By.name("password"), app.getProperty("web.adminPassword"));
        click(proceedButton);
        Assert.assertTrue(element(By.xpath("//div[@id='breadcrumbs']/ul/li/a")).getText().equals(adminLogin));
        return this;
    }

    public UIHelper toggleMenu(){
        element(By.id("menu-toggler")).click();
        return this;
    }

    public UIHelper menagement(){
        click(By.xpath("//li/a/span[@class='menu-text'][text()=' Управление ']"));
        return this;
    }

    public UIHelper users(){
        click(By.xpath("//li/a[text()='Управление пользователями']"));
        return this;
    }

}
