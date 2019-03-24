package jft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactModificationTest extends TestBase {

    @Test
    public void testContactModification(){
        app.getContactsHelper().editContact();
        app.getContactsHelper().updateContact();
    }
}
