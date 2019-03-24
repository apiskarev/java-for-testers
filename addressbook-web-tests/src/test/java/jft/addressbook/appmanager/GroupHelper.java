package jft.addressbook.appmanager;

import jft.addressbook.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GroupHelper extends HelperBase{

    public GroupHelper(WebDriver wd){
        super(wd);
    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void submitGroupCreation() {
        click(By.name("submit"));
        returnToGroupPage();
    }

    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    public void initGroupCreation() {
        click(By.name("new"));
    }

    public void deleteGroup() {
        click(By.name("delete"));
        returnToGroupPage();
    }

    public void selectGroup(GroupData data) {
        if (isElementPresent(By.cssSelector("input[name='selected[]']"))){
            click(By.name("selected[]"));
        } else {
            initGroupCreation();
            fillGroupForm(data);
            submitGroupCreation();
            click(By.name("selected[]"));
        }

    }

    public void initGroupModification() {
        click(By.name("edit"));
    }

    public void submitGroupModification() {
        click(By.name("update"));
        returnToGroupPage();
    }
}
