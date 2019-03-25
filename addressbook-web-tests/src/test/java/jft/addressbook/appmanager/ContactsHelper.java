package jft.addressbook.appmanager;

import jft.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ContactsHelper extends HelperBase{

    ContactsHelper(WebDriver wd) {
        super(wd);
    }

    public void makeNewContact(ContactData data) {
        initNewContact();
        fillContact(data);
        saveContact();
        returnToContactList();
    }

    public void fillContact(ContactData contactData) {
        if (contactData.getFirstName() != null){
            type(By.name("firstname"), contactData.getFirstName());
        }
        if (contactData.getLastName() != null){
            type(By.name("lastname"), contactData.getLastName());
        }
    }

    private void initNewContact() {
       click(By.linkText("add new"));
    }

    public void selectContact(int index) {
        if (isElementPresent(By.cssSelector("tr[name=entry]"))){
            wd.findElements(By.name("selected[]")).get(index).click();
        } else {
            makeNewContact(new ContactData("John", "Smith"));
            wd.findElements(By.name("selected[]")).get(index).click();
        }

    }

    public void editContact(int index, ContactData data) {
        if (isElementPresent(By.cssSelector("a[href*='edit.php?']"))){
            wd.findElements(By.cssSelector("a[href*='edit.php?']")).get(index).click();
            fillContact(data);
        } else {
            makeNewContact(new ContactData("John", "Smith"));
            wd.findElements(By.cssSelector("a[href*='edit.php?']")).get(index).click();
            fillContact(data);
        }
    }

    public void confirmContactUpdate() {
        click(By.name("update"));
        returnToContactList();
    }

    private void saveContact(){
        click(By.name("submit"));
    }

    public void returnToContactList() {
        click(By.linkText("home"));
    }

    public void deleteSelectedContacts(){
        click(By.cssSelector("input[value='Delete']"));
        if (isAlertPresent()) wd.switchTo().alert().accept();
        returnToContactList();
    }


    public int getContactsCount() {
        return wd.findElements(By.name("entry")).size();
    }

    public List<ContactData> getContactsList() {
        List<ContactData> contacts = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        List<WebElement> cells = wd.findElements(By.cssSelector("tr[name=entry] td"));
        for (WebElement element : elements){
            String id = element.findElement(By.tagName("input")).getAttribute("value");
            ContactData data = new ContactData(
                    Integer.parseInt(id),
                    cells.get(2).getText(),
                    cells.get(1).getText());
            contacts.add(data);
            }
        return contacts;
    }

}
