package mantis.appmanager;

import mantis.model.User;
import org.openqa.selenium.By;

public class RegistrationHelper extends HelperBase {

    RegistrationHelper(ApplicationManager app) {
        super(app);
    }

    public void start(String username, String email){
        wd.get(app.getProperty("web.baseUrl") + "signup_page.php");
        type(By.name("username"), username);
        type(By.name("email"), email);
        click(By.cssSelector("input[value='Зарегистрироваться']"));
    }

    public void finish(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("button.btn-success"));
    }

    public RegistrationHelper selectUser(User user) {
        click(By.xpath(String.format("//td/a[text()='%s']", user.getUsername())));
        return this;
    }

    public void changePassword(){
        click(By.xpath("//input[@value='Сбросить пароль']"));
    }
}
