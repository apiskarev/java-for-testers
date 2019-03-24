package jft.addressbook.tests;

import jft.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class ContactCreationTest extends TestBase{

    @Test
    public void testContactCreation(){
<<<<<<< HEAD
        List<ContactData> before = app.getContactsHelper().getContactsList();
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "John", "Smith");
        app.getContactsHelper().makeNewContact(contact);
        List<ContactData> after = app.getContactsHelper().getContactsList();
        Assert.assertEquals(after.size(), before.size() + 1);

        int max = 0;
        for (ContactData g : after){
            if (g.getId() > max){
                max = g.getId();
            }
        }
        contact.setId(max);
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
=======
        app.getContactsHelper().initNewContact();
        app.getContactsHelper().fillContact(new ContactData("John","R.","Smith",
                "Wall street, 19","+7 800 997 14 15", "john.smith@oracle.org"));
        app.getContactsHelper().saveContact();
>>>>>>> parent of 511d59c... added required conditions for task #8
    }

}
