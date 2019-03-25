package jft.addressbook.tests;

import jft.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactModificationTest extends TestBase {

    @Test
    public void testContactModification(){
        if (app.getContactsHelper().isContactPresent()){
            app.getContactsHelper().editContact();
            app.getContactsHelper().fillContact(new ContactData("Wade","Powers"));
            app.getContactsHelper().updateContact();
        } else {
            app.getContactsHelper().initNewContact();
            app.getContactsHelper().fillContact(new ContactData("John","Smith"));
            app.getContactsHelper().saveContact();
            app.getContactsHelper().returnToContactList();
            app.getContactsHelper().editContact();
            app.getContactsHelper().fillContact(new ContactData("Wade","Powers"));
            app.getContactsHelper().updateContact();
        }
    }

}
