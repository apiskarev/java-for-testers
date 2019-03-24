package jft.addressbook.tests;

import jft.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactModificationTest extends TestBase {

    @Test
    public void testContactModification(){
        app.getContactsHelper().editContact(new ContactData("Wade", "Powers"));
        app.getContactsHelper().updateContact();
    }
}
