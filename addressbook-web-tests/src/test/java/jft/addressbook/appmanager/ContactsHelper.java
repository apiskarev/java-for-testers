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

    public void fillContact(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
    }

    public void initNewContact() {
       click(By.linkText("add new"));
    }

    public void selectContact(int index) {
        wd.findElements(By.cssSelector("tr[name=entry] input")).get(index).click();
    }

    public void openContactForEdit(int index) {
        wd.findElements(By.cssSelector("a[href*='edit.php?']")).get(index).click();
    }

    public void updateContact() {
        click(By.name("update"));
        returnToContactList();
    }

    public void saveContact(){
        click(By.name("submit"));
    }

    public void returnToContactList() {
        click(By.linkText("home"));
    }

    public void contactDelete(){
        click(By.cssSelector("input[value='Delete']"));
        if (isAlertPresent()) wd.switchTo().alert().accept();
        returnToContactList();
    }


    public boolean isContactPresent() {
        return isElementPresent(By.cssSelector("a[href*='edit.php?']"));
    }

    public void createNewContact(ContactData data) {
        initNewContact();
        fillContact(data);
        saveContact();
        returnToContactList();
    }

    public List<ContactData> getContactsList() {
        List<ContactData> contacts = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements){
            List<WebElement> tableCells = element.findElements(By.cssSelector("td"));
                    //wd.findElements(By.cssSelector("tr[name=entry] td"));
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String lastName = tableCells.get(1).getText();
            String firstName = tableCells.get(2).getText();
            ContactData contact = new ContactData(id, firstName, lastName);
            contacts.add(contact);
        }
        return contacts;
    }
}
