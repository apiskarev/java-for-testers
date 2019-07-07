package jft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class NavigationHelper extends HelperBase{

    private final Properties properties;

    NavigationHelper(WebDriver wd) throws IOException {
        super(wd);
        properties = new Properties();
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    }

    public void groupPage() {
        click(By.linkText("groups"));
    }

    public void contactPage() {
        if (!wd.getCurrentUrl().equals(properties.getProperty("web.baseUrl")))
        wd.get(properties.getProperty("web.baseUrl"));
    }
}
