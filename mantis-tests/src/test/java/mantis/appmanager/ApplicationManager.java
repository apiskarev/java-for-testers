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

    private WebDriver wd;

    public ApplicationManager(String browser){
        this.browser = browser;
        properties = new Properties();
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
