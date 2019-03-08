package jft.addressbook.tests;

import jft.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactModificationTest extends TestBase {

    @Test
    public void testContactModification(){
        app.getContactsHelper().editContact();
        app.getContactsHelper().fillContact(new ContactData("Wade","M.","Powers",
                "Broadway street, 14","+7 800 964 11 17","powers@void.net"));
        app.getContactsHelper().updateContact();
        app.getContactsHelper().returnToContactList();
    }
}
