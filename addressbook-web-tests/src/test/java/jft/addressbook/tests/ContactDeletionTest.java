package jft.addressbook.tests;

import jft.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ContactDeletionTest extends TestBase {

    @Test
    public void testContactDelete(){
<<<<<<< HEAD
        List<ContactData> before = app.getContactsHelper().getContactsList();
        app.getContactsHelper().selectContact(before.size() - 1);
        app.getContactsHelper().deleteSelectedContacts();
        List<ContactData> after = app.getContactsHelper().getContactsList();
        Assert.assertEquals(after.size(), before.size() - 1);
=======
        app.getContactsHelper().selectContact();
        app.getContactsHelper().deleteContact();

>>>>>>> parent of 511d59c... added required conditions for task #8
    }

}
