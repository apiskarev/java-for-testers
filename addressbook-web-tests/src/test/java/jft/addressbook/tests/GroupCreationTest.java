package jft.addressbook.tests;

import jft.addressbook.model.GroupData;
import jft.addressbook.model.Groups;
import org.testng.annotations.*;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTest extends TestBase{

    @Test
    public void testGroupCreation() {
        app.goTo().groupPage();
        Groups before = app.group().all();
        GroupData group = new GroupData().withName("test1");
        app.group().create(group);
        Groups after = app.group().all();
        assertThat(app.group().count(), equalTo(before.size() + 1));
        assertThat(after, equalTo(
                before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

}
