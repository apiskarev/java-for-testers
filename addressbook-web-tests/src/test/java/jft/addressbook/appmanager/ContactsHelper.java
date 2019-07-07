package jft.addressbook.appmanager;

import jft.addressbook.model.ContactData;
import jft.addressbook.model.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ContactsHelper extends HelperBase{

    ContactsHelper(WebDriver wd) {
        super(wd);
    }

    private void fillContact(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getmobilePhone());
        type(By.name("work"), contactData.getworkPhone());
        type(By.name("address2"), contactData.getAddress());
        type(By.name("email"), contactData.getFirstEmail());
        type(By.name("email2"), contactData.getSecondEmail());
        type(By.name("email3"), contactData.getThirdEmail());
        attach(By.name("photo"), contactData.getPhoto());
    }

    private void initNewContact() {
       click(By.linkText("add new"));
    }

    public void edit(int id) {
        wd.findElement(By.cssSelector("a[href='edit.php?id="+ id +"']")).click();
    }

    private void updateContact() {
        click(By.name("update"));
        returnToContactList();
    }

    private void saveContact(){
        click(By.name("submit"));
        returnToContactList();
    }

    private void returnToContactList() {
        click(By.linkText("home"));
    }

    public boolean isContactPresent() {
        return isElementPresent(By.cssSelector("a[href*='edit.php?']"));
    }

    public void create(ContactData data) {
        initNewContact();
        fillContact(data);
        saveContact();
        contactCache = null;
    }

    public void modify(ContactData contact) {
        fillContact(contact);
        updateContact();
        contactCache = null;
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteGroup();
        contactCache = null;
    }

    private void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[id='"+ id +"']")).click();
    }

    private void deleteGroup(){
        click(By.cssSelector("input[value='Delete']"));
        if (isAlertPresent()) wd.switchTo().alert().accept();
        returnToContactList();
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null){
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        int bound = elements.size();
        IntStream.range(0, bound).forEach(i -> {
            List<WebElement> tableCells = elements.get(i).findElements(By.cssSelector("td"));
            int id = Integer.parseInt(elements.get(i).findElement(By.tagName("input")).getAttribute("value"));
            String lastName = tableCells.get(1).getText();
            String firstName = tableCells.get(2).getText();
            String allEmails = tableCells.get(4).findElements(By.tagName("a")).stream().map(WebElement::getText).collect(Collectors.joining("\n"));
            String allPhones = tableCells.get(5).getText();
            contactCache.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName).withAllEmails(allEmails).withAllPhones(allPhones));
        });
        return new Contacts(contactCache);
    }


    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String firstEmail = wd.findElement(By.name("email")).getAttribute("value");
        String secondEmail = wd.findElement(By.name("email2")).getAttribute("value");
        String thirdEmail = wd.findElement(By.name("email3")).getAttribute("value");
        String address = wd.findElement(By.name("address2")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstName(firstName).withLastName(lastName)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
                .withFirstEmail(firstEmail).withSecondEmail(secondEmail).withThirdEmail(thirdEmail)
                .withAddress(address);
    }

    private void initContactModificationById(int id) {
        wd.findElement(By.cssSelector("a[href='edit.php?id="+ id +"']")).click();
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    private void ensureContactOperationDone(ContactData contactToAdd) {
        wd.findElement(By.xpath("//h1[text()='Groups']"));
        Assert.assertTrue(isElementPresent(By.linkText("group page \"" + contactToAdd.getGroups().iterator().next().getName() + "\"")));
    }

    public void addToGroup(ContactData contactToAdd) {
        Assert.assertEquals(contactToAdd.getGroups().size(), 1);
        selectContactById(contactToAdd.getId());
        Select groupSelector = new Select(wd.findElement(By.name("to_group")));
        groupSelector.selectByVisibleText(contactToAdd.getGroups().iterator().next().getName());
        wd.findElement(By.cssSelector("input[type=submit]")).click();
        ensureContactOperationDone(contactToAdd);
    }

    private void getGroupView(ContactData contact) {
        Assert.assertEquals(contact.getGroups().size(), 1);
        Select groupSelect = new Select(wd.findElement(By.name("group")));
        groupSelect.selectByVisibleText(contact.getGroups().iterator().next().getName());
    }

    public void removeContactFromGroup(ContactData contact){
        getGroupView(contact);
        selectContactById(contact.getId());
        wd.findElement(By.name("remove")).click();
        ensureContactOperationDone(contact);
    }
}
