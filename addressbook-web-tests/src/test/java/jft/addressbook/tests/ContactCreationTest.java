package jft.addressbook.tests;

import jft.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTest extends TestBase{

    @Test
    public void testContactCreation(){
        app.getContactsHelper().initNewContact();
        app.getContactsHelper().fillContact(new ContactData("John","Smith"));
        app.getContactsHelper().saveContact();
        app.getContactsHelper().returnToContactList();
    }



}
