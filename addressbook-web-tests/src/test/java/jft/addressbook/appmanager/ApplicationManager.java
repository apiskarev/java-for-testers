package jft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    private final Properties properties;

    public ApplicationManager(String browser){
        this.browser = browser;
        properties = new Properties();
    }

    private String browser;

    private ContactsHelper contactsHelper;
    private NavigationHelper navigationHelper;
    private WebDriver wd;
    private GroupHelper groupHelper;
    private SessionHelper sessionHelper;

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

        if (browser.equals(BrowserType.CHROME))
            wd = new ChromeDriver();
        else if (browser.equals(BrowserType.FIREFOX))
            wd = new FirefoxDriver();
        else if (browser.equals(BrowserType.IE))
            wd = new InternetExplorerDriver();
        groupHelper = new GroupHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wd.get(properties.getProperty("web.baseUrl"));
        sessionHelper = new SessionHelper(wd);
        sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
        contactsHelper = new ContactsHelper(wd);
    }

    protected void logout() {
        wd.findElement(By.linkText("Logout")).click();
    }

    public void stop() {
        logout();
        if (wd != null) wd.quit();
    }

    public GroupHelper group() {
        return groupHelper;
    }

    public NavigationHelper goTo() {
        return navigationHelper;
    }

    public ContactsHelper contact() {
        return contactsHelper;
    }
}
