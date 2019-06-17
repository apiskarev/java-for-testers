package jft.addressbook.tests;

import jft.addressbook.model.ContactData;
import jft.addressbook.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        if (app.contact().all().size() == 0){
            app.contact().create(new ContactData().withFirstName("John").withLastName("Smith"));
        }
    }

    @Test
    public void testContactModification(){
        Contacts before = app.contact().all();
        ContactData contactToModify = before.iterator().next();
        app.contact().edit(contactToModify.getId());
        ContactData contact = new ContactData().withId(contactToModify.getId()).withFirstName("John").withLastName("Powers");
        app.contact().modify(contact);
        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size()));

        assertThat(after, equalTo(before.without(contactToModify).withAdded(contact)));
    }

}
