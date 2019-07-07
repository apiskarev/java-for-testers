package jft.addressbook.tests;

import jft.addressbook.model.ContactData;
import jft.addressbook.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        if (app.db().contacts().size() == 0){
            app.contact().create(new ContactData().withFirstName("John").withLastName("Smith")
                    .withPhoto(new File("src/test/resources/stru.png")));
        }
    }

    @Test
    public void testContactModification(){
        Contacts before = app.db().contacts();
        ContactData contactToModify = before.iterator().next();
        app.contact().edit(contactToModify.getId());
        ContactData contact = new ContactData().withId(contactToModify.getId()).withFirstName("Wade").withLastName("Powers")
                .withPhoto(new File("src/test/resources/stru.png")).withAddress("Corner street 16, flat 201")
                .withMobilePhone("00 +7 99 234 23 21").withHomePhone("+7 234 23 23 21").withWorkPhone("991 32 52")
                .withFirstEmail("power@z.com").withSecondEmail("w@de.com").withThirdEmail("wade.powers@gmail.com");
        app.contact().modify(contact);
        Contacts after = app.db().contacts();
        assertThat(after.size(), equalTo(before.size()));
        assertThat(after, equalTo(before.without(contactToModify).withAdded(contact)));
    }

}
