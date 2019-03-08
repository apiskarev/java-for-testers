package jft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTest extends TestBase {

    @Test
    public void testContactDelete(){
        app.getContactsHelper().selectContact();
        app.getContactsHelper().deleteContact();

    }


}
