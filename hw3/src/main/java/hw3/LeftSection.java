package hw3;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeftSection {
    @FindBy(xpath = "//ul[@class='sidebar-menu']/li[@class='menu-title']")
    private WebElement sideServiceMenu;

    private List<WebElement> serviceTitleElements;

    public LeftSection(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        serviceTitleElements = sideServiceMenu.findElements(By.tagName("li"));
    }

    public void toggleSideService() {
        sideServiceMenu.click();
    }

    public boolean checkSideServiceTitlesNum(int titlesNum) {
        return (serviceTitleElements.size() == titlesNum);
    }

    public boolean areSideServiceTitlesDisplayed() {
        for (WebElement serviseTitle : serviceTitleElements) {
            if (!serviseTitle.isDisplayed()) {
                return false;
            }
        }
        return true;
    }

    public List<String> getSideServiseTitles() {
        List<String> titles = new ArrayList<String>();
        for (WebElement titElement : serviceTitleElements) {
            WebElement titleSubElement = titElement.findElement(By.tagName("span"));
            titles.add(titleSubElement.getText().toUpperCase());
        }
        return titles;
    }
}
