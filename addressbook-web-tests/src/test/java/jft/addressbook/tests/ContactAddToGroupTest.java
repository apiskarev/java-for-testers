package jft.addressbook.tests;

import jft.addressbook.model.ContactData;
import jft.addressbook.model.Contacts;
import jft.addressbook.model.GroupData;
import jft.addressbook.model.Groups;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactAddToGroupTest extends TestBase {

    private Groups groups;
    private Contacts contacts;

    @BeforeTest
    public void ensurePreconditions(){
        if (app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1").withHeader("test1").withFooter("test1"));
        }
        if (app.db().contacts().size() == 0){
            app.goTo().contactPage();
            app.contact().create(new ContactData().withFirstName("Wade").withLastName("Powers")
                    .withPhoto(new File("src/test/resources/stru.png")).withAddress("Corner street 16, flat 201")
                    .withMobilePhone("00 +7 99 234 23 21").withHomePhone("+7 234 23 23 21").withWorkPhone("991 32 52")
                    .withFirstEmail("power@z.com").withSecondEmail("w@de.com").withThirdEmail("wade.powers@gmail.com"));
        }
        contacts = app.db().contacts();
        groups = app.db().groups();
    }

    @Test
    public void addContactToGroup(){
        app.goTo().contactPage();
        Contacts before = contacts;
        ContactData contactToAdd = before.iterator().next();
        ContactData addedContact = contactToAdd.inGroup(groups.iterator().next());
        app.contact().addToGroup(contactToAdd);
        Contacts after = app.db().contacts();
        assertThat(before, equalTo(after.without(contactToAdd).withAdded(addedContact)));
    }
}
