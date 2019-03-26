package jft.addressbook.tests;

import jft.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactModificationTest extends TestBase {

    @Test
    public void testContactModification(){
        if (!app.getContactsHelper().isContactPresent()){
            app.getContactsHelper().createNewContact(new ContactData("John","Smith"));
        }
            app.getContactsHelper().editContact();
            app.getContactsHelper().fillContact(new ContactData("Wade","Powers"));
            app.getContactsHelper().updateContact();
    }

}
