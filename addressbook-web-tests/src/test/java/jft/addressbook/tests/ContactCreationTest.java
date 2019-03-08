package jft.addressbook.tests;

import jft.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTest extends TestBase{

    @Test
    public void testContactCreation(){
        app.getContactsHelper().initNewContact();
        app.getContactsHelper().fillContact(new ContactData("John","R.","Smith",
                "Wall street, 19","+7 800 997 14 15", "john.smith@oracle.org"));
        app.submit();
    }

}
