package jft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    private ContactsHelper contactsHelper;
    private NavigationHelper navigationHelper;
    private WebDriver wd;
    private GroupHelper groupHelper;
    private SessionHelper sessionHelper;

    public void init(String browser) {
        if (browser.equals(BrowserType.CHROME))
            wd = new ChromeDriver();
        else if (browser.equals(BrowserType.FIREFOX))
            wd = new FirefoxDriver();
        else if (browser.equals(BrowserType.IE))
            wd = new InternetExplorerDriver();
        groupHelper = new GroupHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wd.get("http://localhost/addressbook/");
        sessionHelper = new SessionHelper(wd);
        sessionHelper.login("admin", "secret");
        contactsHelper = new ContactsHelper(wd);
    }

    private void logout() {
        wd.findElement(By.linkText("Logout")).click();
    }

    public void stop() {
        logout();
        if (wd != null) wd.quit();
    }

    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public ContactsHelper getContactsHelper() {
        return contactsHelper;
    }
}
