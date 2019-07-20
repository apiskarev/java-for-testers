package mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class NavigationHelper extends HelperBase{

    NavigationHelper(ApplicationManager app) {
        super(app);
    }

    public void toLoginPage() {
        wd.get(app.getProperty("web.baseUrl"));
    }
}
