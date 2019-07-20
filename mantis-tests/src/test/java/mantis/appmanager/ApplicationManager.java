package mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    private final Properties properties;
    private RegistrationHelper registrationHelper;
    private FtpHelper ftp;
    private MailHelper mail;
    private JamesHelper jamesHelper;
    private NavigationHelper navigationHelper;
    private UIHelper uiHelper;

    private WebDriver wd;
    private DbHelper dbHelper;

    public ApplicationManager(String browser){
        this.browser = browser;
        properties = new Properties();
        dbHelper = new DbHelper();
    }

    private String browser;

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    }

    public void stop() {
        if (wd != null) wd.quit();
    }

    public HttpSession newSession(){
        return new HttpSession(this);
    }

    public String getProperty(String key){
        return properties.getProperty(key);
    }

    public RegistrationHelper registration() {
        if (registrationHelper == null){
            registrationHelper = new RegistrationHelper(this);
        }
        return registrationHelper;
    }

    public FtpHelper ftp(){
        if (ftp == null){
            ftp = new FtpHelper(this);
        }
        return ftp;
    }

    public MailHelper mail(){
        if (mail == null) {
            mail = new MailHelper(this);
        }
        return mail;
    }

    public JamesHelper james(){
        if (jamesHelper == null){
            jamesHelper = new JamesHelper(this);
        }
        return jamesHelper;
    }

    public NavigationHelper navigate(){
        if (navigationHelper == null){
            navigationHelper = new NavigationHelper(this);
        }
        return navigationHelper;
    }

    public UIHelper ui(){
        if (uiHelper == null) uiHelper = new UIHelper(this);
        return uiHelper;
    }

    public DbHelper db(){
        return dbHelper;
    }

    WebDriver getDriver() {
        if (wd == null){
            switch (browser) {
                case BrowserType.CHROME:
                    wd = new ChromeDriver();
                    break;
                case BrowserType.FIREFOX:
                    wd = new FirefoxDriver();
                    break;
                case BrowserType.IE:
                    wd = new InternetExplorerDriver();
                    break;
            }
        }
        wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        return wd;
    }
}
