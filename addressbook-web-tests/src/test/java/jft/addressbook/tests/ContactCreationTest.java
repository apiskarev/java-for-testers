package jft.addressbook.tests;

import jft.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTest extends TestBase{

    @Test
    public void testContactCreation(){
        app.getContactsHelper().makeNewContact(new ContactData("John","Smith"));
    }



}
