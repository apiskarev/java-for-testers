package jft.addressbook.tests;

import jft.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTest extends TestBase {

    @Test
    public void testContactModification(){
        if (!app.getContactsHelper().isContactPresent()){
            app.getContactsHelper().createNewContact(new ContactData("John","Smith"));
        }
        List<ContactData> before = app.getContactsHelper().getContactsList();
        app.getContactsHelper().openContactForEdit(before.size() - 1);
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(),"Wade","Powers");
        app.getContactsHelper().fillContact(contact);
        app.getContactsHelper().updateContact();
        List<ContactData> after = app.getContactsHelper().getContactsList();

        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(after, before);
    }

}
