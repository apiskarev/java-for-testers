package jft.addressbook.tests;

import jft.addressbook.model.ContactData;
import jft.addressbook.model.Contacts;
import org.testng.annotations.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase{

    @Test
    public void testContactCreation(){
        Contacts before = app.contact().all();
        File photo  = new File("src/test/resources/stru.png");
        ContactData contact = new ContactData().withFirstName("John").withLastName("Smith").withPhoto(photo);
        app.contact().create(contact);
        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size() + 1));

        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt(c -> c.getId()).max().getAsInt()))));
    }


}
