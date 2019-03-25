package jft.addressbook.tests;

import jft.addressbook.model.GroupData;
import org.testng.annotations.Test;

public class GroupDeletionTest extends TestBase {

    @Test
    public void testGroupDeletion(){
        app.getNavigationHelper().gotoGroupsPage();
        if (app.getGroupHelper().isGroupPresent()){
            app.getGroupHelper().selectGroup();
            app.getGroupHelper().deleteGroup();
        } else {
            app.getGroupHelper().initGroupCreation();
            app.getGroupHelper().fillGroupForm(new GroupData("test1", "test2", "test3"));
            app.getGroupHelper().submitGroupCreation();
            app.getGroupHelper().selectGroup();
            app.getGroupHelper().deleteGroup();
        }

    }

}
