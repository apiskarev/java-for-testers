package jft.addressbook.appmanager;

import jft.addressbook.model.GroupData;
import jft.addressbook.model.Groups;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GroupHelper extends HelperBase{

    GroupHelper(WebDriver wd){
        super(wd);
    }

    private void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    private void submitGroupCreation() {
        click(By.name("submit"));
        returnToGroupPage();
    }

    private void fillGroupForm(GroupData groupData) {
        if (groupData.getName() != null) {
            type(By.name("group_name"), groupData.getName());
        }
        if (groupData.getHeader() != null){
            type(By.name("group_header"), groupData.getHeader());
        }
        if (groupData.getFooter() != null){
            type(By.name("group_footer"), groupData.getFooter());
        }
    }

    public void create(GroupData group){
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        groupCache = null;
    }

    public void modify(GroupData group) {
            selectGroupById(group.getId());
            initGroupModification();
            fillGroupForm(group);
            submitGroupModification();
            groupCache = null;
    }

    public void delete(GroupData group) {
        selectGroupById(group.getId());
        deleteGroup();
        groupCache = null;
    }

    private void initGroupCreation() {
        click(By.name("new"));
    }

    private void deleteGroup() {
        click(By.name("delete"));
        returnToGroupPage();
    }

    private void selectGroupById(int id) {
        wd.findElement(By.cssSelector("input[value='"+ id +"']")).click();
    }

    private void initGroupModification() {
        click(By.name("edit"));
    }

    private void submitGroupModification() {
        click(By.name("update"));
        returnToGroupPage();
    }

    public boolean isGroupPresent() {
        return isElementPresent(By.cssSelector("input[name='selected[]']"));
    }

    private Groups groupCache = null;

    public Groups all() {
        if (groupCache != null) {
            return new Groups(groupCache);
        }
        groupCache = new Groups();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements){
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groupCache.add(new GroupData().withId(id).withName(name));
        }
        return new Groups(groupCache);
    }


    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }
}
