package jft.addressbook.appmanager;

import jft.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
    }

    public void initNewContact() {
       click(By.linkText("add new"));
    }

    public void selectContact() {
        if (isElementPresent(By.cssSelector("tr[name=entry]"))){
            click(By.name("selected[]"));
        } else {
            makeNewContact(new ContactData("John","Smith"));
            click(By.name("selected[]"));
        }

    }

    public void editContact() {
        click(By.cssSelector("a[href*='edit.php?']"));
    }

    public void updateContact() {
        click(By.name("update"));
        returnToContactList();
    }

    public void saveContact(){
        click(By.name("submit"));
    }

    public void returnToContactList() {
        click(By.linkText("home page"));
    }

    public void contactDelete(){
        click(By.cssSelector("input[value='Delete']"));
        if (isAlertPresent()) wd.switchTo().alert().accept();
    }


    public boolean isContactPresent() {
        return isElementPresent(By.cssSelector("a[href*='edit.php?']"));
    }
}
