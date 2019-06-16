package jft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

class SessionHelper extends HelperBase{

    SessionHelper(WebDriver wd) {
        super(wd);
    }

    void login(String username, String password) {
        type(By.name("user"), username);
        type(By.name("pass"), password);
        click(By.cssSelector("input[value=Login]"));
    }

}
