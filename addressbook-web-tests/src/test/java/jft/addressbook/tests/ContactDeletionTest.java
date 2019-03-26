package jft.addressbook.tests;

import jft.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactDeletionTest extends TestBase {

    @Test
    public void testContactDelete(){
        if (!app.getContactsHelper().isContactPresent()){
            app.getContactsHelper().createNewContact(new ContactData("John","Smith"));
        }
            app.getContactsHelper().selectContact();
            app.getContactsHelper().contactDelete();
    }

}
