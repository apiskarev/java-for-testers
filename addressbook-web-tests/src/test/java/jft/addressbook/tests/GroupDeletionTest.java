package jft.addressbook.tests;

<<<<<<< HEAD
import jft.addressbook.model.GroupData;
import org.testng.Assert;
=======
>>>>>>> parent of 511d59c... added required conditions for task #8
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class GroupDeletionTest extends TestBase {

    @Test
    public void testGroupDeletion(){
        app.getNavigationHelper().gotoGroupsPage();
<<<<<<< HEAD
        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().selectGroup(before.size() - 1);
        app.getGroupHelper().deleteGroup();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);

        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

=======
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteGroup();
        app.getGroupHelper().returnToGroupPage();
>>>>>>> parent of 511d59c... added required conditions for task #8
    }

}
