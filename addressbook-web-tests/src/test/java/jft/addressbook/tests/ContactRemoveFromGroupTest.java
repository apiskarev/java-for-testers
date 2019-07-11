package jft.addressbook.tests;

import jft.addressbook.model.ContactData;
import jft.addressbook.model.Contacts;
import jft.addressbook.model.GroupData;
import jft.addressbook.model.Groups;
import org.hamcrest.CoreMatchers;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactRemoveFromGroupTest extends TestBase {

    private ContactData contactToRemove;
    private GroupData groupToRemoveFrom;

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

        for (ContactData contact : app.db().contacts()){
            if (contact.getGroups().size() == 1){
                contactToRemove = contact;
            }
        }

        if (contactToRemove == null){
            app.goTo().contactPage();
            contactToRemove = app.db().contacts().iterator().next().inGroup(app.db().groups().iterator().next());
            app.contact().addToGroup(contactToRemove);
        }

        groupToRemoveFrom = contactToRemove.getGroups().iterator().next();
    }

    @Test
    public void removeContactFromGroup(){
        app.goTo().contactPage();
        app.contact().removeContactFromGroup(contactToRemove);
        app.db().refreshContact(contactToRemove);
        app.db().refreshGroup(groupToRemoveFrom);
        assertThat(contactToRemove.getGroups(), CoreMatchers.not(hasItem(groupToRemoveFrom)));
        assertThat(groupToRemoveFrom.getContacts(), CoreMatchers.not(hasItem(contactToRemove)));
    }
}
