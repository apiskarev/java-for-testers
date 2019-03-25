package jft.addressbook.tests;

import jft.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactDeletionTest extends TestBase {

    @Test
    public void testContactDelete(){
        if (app.getContactsHelper().isContactPresent()){
            app.getContactsHelper().selectContact();
            app.getContactsHelper().contactDelete();
        } else {
            app.getContactsHelper().initNewContact();
            app.getContactsHelper().fillContact(new ContactData("John","Smith"));
            app.getContactsHelper().saveContact();
            app.getContactsHelper().returnToContactList();
            app.getContactsHelper().selectContact();
            app.getContactsHelper().contactDelete();
        }
    }

}
