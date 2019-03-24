package jft.addressbook.appmanager;

import jft.addressbook.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
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

    public void fillGroupForm(GroupData groupData) {
        if (groupData.getName() != null){
            type(By.name("group_name"), groupData.getName());
        }
        if (groupData.getHeader() != null){
            type(By.name("group_header"), groupData.getHeader());
        }
        if (groupData.getFooter() != null){
            type(By.name("group_footer"), groupData.getFooter());
        }
    }

    private void initGroupCreation() {
        click(By.name("new"));
    }

    public void deleteGroup() {
        click(By.name("delete"));
        returnToGroupPage();
    }

    public void selectGroup(int index) {
        if (isElementPresent(By.name("selected[]"))){
            wd.findElements(By.name("selected[]")).get(index).click();
        } else {
            initGroupCreation();
            fillGroupForm(new GroupData("test1", "test2", "test3"));
            submitGroupCreation();
            wd.findElements(By.name("selected[]")).get(index).click();
        }

    }

    public void initGroupModification() {
        click(By.name("edit"));
    }

    public void submitGroupModification() {
        click(By.name("update"));
        returnToGroupPage();
    }

    public void createGroup(GroupData data) {
        initGroupCreation();
        fillGroupForm(data);
        submitGroupCreation();
    }

    public int getGroupCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<GroupData> getGroupList() {
        List<GroupData> groups = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements){
            String name = element.getText();
            String id = element.findElement(By.tagName("input")).getAttribute("value");
            GroupData group = new GroupData(Integer.parseInt(id), name, null, null);
            groups.add(group);
        }
        return groups;
    }
}
