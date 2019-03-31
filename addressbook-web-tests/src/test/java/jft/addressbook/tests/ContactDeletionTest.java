package jft.addressbook.tests;

import jft.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ContactDeletionTest extends TestBase {

    @Test
    public void testContactDelete() {
        if (!app.getContactsHelper().isContactPresent()){
            app.getContactsHelper().createNewContact(new ContactData("John","Smith"));
        }
        List<ContactData> before = app.getContactsHelper().getContactsList();
            app.getContactsHelper().selectContact(before.size() - 1);
            app.getContactsHelper().contactDelete();
        List<ContactData> after = app.getContactsHelper().getContactsList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(after, before);
    }

}
