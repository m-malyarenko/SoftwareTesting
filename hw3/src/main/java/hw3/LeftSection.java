package hw3;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeftSection {
    @FindBy(className = "menu-title")
    private WebElement sideServiceMenu;

    @FindBy(xpath = "//*[@class='sub']/li")
    private List<WebElement> sideServiceTitles;

    public LeftSection(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void openSideServiceMenu() {
        sideServiceMenu.click();
    }

    public boolean checkSideServiceTitlesNum(int titlesNum) {
        return (sideServiceTitles.size() == titlesNum);
    }

    public boolean areSideServiceTitlesDisplayed() {
        for (WebElement serviseTitle : sideServiceTitles) {
            if (!serviseTitle.isDisplayed()) {
                return false;
            }
        }
        return true;
    }

    public List<String> getSideServiseTitles() {
        List<String> titles = new ArrayList<String>();
        for (WebElement titElement : sideServiceTitles) {
            titles.add(titElement.getText().toUpperCase());
        }
        return titles;
    }
}
