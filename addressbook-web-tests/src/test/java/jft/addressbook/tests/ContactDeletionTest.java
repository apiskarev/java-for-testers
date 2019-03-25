package jft.addressbook.tests;

import jft.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class ContactDeletionTest extends TestBase {

    @Test
    public void testContactDelete(){
        List<ContactData> before = app.getContactsHelper().getContactsList();
        app.getContactsHelper().selectContact(before.size() - 1);
        app.getContactsHelper().deleteSelectedContacts();
        List<ContactData> after = app.getContactsHelper().getContactsList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }

}
