package jft.addressbook.appmanager;

import jft.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ContactsHelper extends HelperBase{

    public ContactsHelper(WebDriver wd) {
        super(wd);
    }

<<<<<<< HEAD
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
=======
    public void fillContact(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("email"), contactData.getEmail());
>>>>>>> parent of 511d59c... added required conditions for task #8
    }

    public void initNewContact() {
       click(By.linkText("add new"));
    }

<<<<<<< HEAD
    public void selectContact(int index) {
        if (isElementPresent(By.cssSelector("tr[name=entry]"))){
            wd.findElements(By.name("selected[]")).get(index).click();
        } else {
            makeNewContact(new ContactData("John", "Smith"));
            wd.findElements(By.name("selected[]")).get(index).click();
        }

    }

    public void editContact(int index) {
        if (isElementPresent(By.cssSelector("a[href*='edit.php?']"))){
            wd.findElements(By.cssSelector("a[href*='edit.php?']")).get(index).click();
        } else {
            makeNewContact(new ContactData("Wade", "Powers"));
            wd.findElements(By.cssSelector("a[href*='edit.php?']")).get(index).click();
        }
=======
    public void selectContact() {
        click(By.name("selected[]"));
    }

    public void editContact() {
        click(By.cssSelector("a[href*='edit.php?']"));
>>>>>>> parent of 511d59c... added required conditions for task #8
    }

    public void confirmContactUpdate() {
        click(By.name("update"));
    }

    public void saveContact(){
        click(By.name("submit"));
    }

    public void returnToContactList() {
        click(By.linkText("home"));
    }

<<<<<<< HEAD
    public void deleteSelectedContacts(){
=======
    public void deleteContact(){
>>>>>>> parent of 511d59c... added required conditions for task #8
        click(By.cssSelector("input[value='Delete']"));
        if (isAlertPresent()) wd.switchTo().alert().accept();
        returnToContactList();
    }
<<<<<<< HEAD


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

=======
>>>>>>> parent of 511d59c... added required conditions for task #8
}
