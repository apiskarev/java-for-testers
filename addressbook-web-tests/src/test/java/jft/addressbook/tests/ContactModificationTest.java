package jft.addressbook.tests;

import jft.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class ContactModificationTest extends TestBase {

    @Test
    public void testContactModification(){
        List<ContactData> before = app.getContactsHelper().getContactsList();
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Wade", "Powers");
        app.getContactsHelper().editContact(before.size() - 1, contact);
        app.getContactsHelper().confirmContactUpdate();
        List<ContactData> after = app.getContactsHelper().getContactsList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }
}
