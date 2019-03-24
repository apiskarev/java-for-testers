package jft.addressbook.tests;

import jft.addressbook.model.ContactData;
<<<<<<< HEAD
import org.testng.Assert;
=======
>>>>>>> parent of 511d59c... added required conditions for task #8
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class ContactModificationTest extends TestBase {

    @Test
    public void testContactModification(){
<<<<<<< HEAD
        List<ContactData> before = app.getContactsHelper().getContactsList();
        app.getContactsHelper().editContact(before.size() - 1);
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "John", "Smith");
        app.getContactsHelper().fillContact(contact);
        app.getContactsHelper().confirmContactUpdate();
        List<ContactData> after = app.getContactsHelper().getContactsList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
=======
        app.getContactsHelper().editContact();
        app.getContactsHelper().fillContact(new ContactData("Wade","M.","Powers",
                "Broadway street, 14","+7 800 964 11 17","powers@void.net"));
        app.getContactsHelper().updateContact();
        app.getContactsHelper().returnToContactList();
>>>>>>> parent of 511d59c... added required conditions for task #8
    }
}
