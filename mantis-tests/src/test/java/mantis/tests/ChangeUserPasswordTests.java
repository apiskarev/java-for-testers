package mantis.tests;

import mantis.model.MailMessage;
import mantis.model.User;
import mantis.model.Users;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangeUserPasswordTests extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void changePasswordTest() throws MessagingException, IOException {
        Users before = app.db().getUsers();
        User toModify = before.iterator().next();
        app.ui().loginAsAdmin().toggleMenu().menagement().users();
        String newPassword = String.format("p%s", System.currentTimeMillis());
        app.registration().selectUser(toModify).changePassword();
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 30000);
        String passwordChangeLink = app.mail().findPasswordChangeLink(mailMessages, toModify.getEmail());
        app.registration().finish(passwordChangeLink, newPassword);
        toModify.setPassword(newPassword);
        assertTrue(app.newSession().login(toModify.getUsername(), toModify.getPassword()));
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }
}
