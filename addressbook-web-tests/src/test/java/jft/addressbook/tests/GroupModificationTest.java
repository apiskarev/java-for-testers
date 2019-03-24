package jft.addressbook.tests;

import jft.addressbook.model.GroupData;
import org.testng.annotations.Test;

public class GroupModificationTest extends TestBase {

    @Test
    public void testGroupModification(){
        app.getNavigationHelper().gotoGroupsPage();
        app.getGroupHelper().selectGroup(new GroupData("test1", "test2", "test3"));
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("modified1", "modified2", "modified3"));
        app.getGroupHelper().submitGroupModification();
    }
}
