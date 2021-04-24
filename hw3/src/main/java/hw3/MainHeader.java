package hw3;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainHeader {
    @FindBy(xpath = "//div[starts-with(@class,'uui-header')]/nav/ul[1]/li/a")
    private List<WebElement> headerTitles;

    @FindBy(xpath = "//div[starts-with(@class, uui-header)]//li[@class='dropdown']")
    private WebElement serviceMenu;

    private List<WebElement> serviceTitles;

    public MainHeader(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        serviceTitles = serviceMenu.findElements(By.tagName("li"));
    }

    public boolean checkHeaderTitlesNum(int titlesNum) {
        return (headerTitles.size() == titlesNum);
    }

    public boolean checkServiceTitlesNum(int titlesNum) {
        return (serviceTitles.size() == titlesNum);
    }
    
    public boolean areHeaderTitlesDisplayed() {
        for (WebElement title : headerTitles) {
            if (!title.isDisplayed()) {
                return false;
            }
        }
        return true;
    }

    public boolean areServiceTitlesDisplayed() {
        for (WebElement title : serviceTitles) {
            if (!title.isDisplayed()) {
                return false;
            }
        }
        return true;
    }

    public List<String> getHeaderTitles() {
        List<String> titles = new ArrayList<String>();
        for (WebElement titleElement : headerTitles) {
            titles.add(titleElement.getText().toUpperCase());
        }
        return titles;
    }

    public List<String> getServiceTitles() {
        List<String> titles = new ArrayList<String>();
        for (WebElement titleElement : serviceTitles) {
            WebElement titleLink = titleElement.findElement(By.tagName("a"));
            titles.add(titleLink.getText().toUpperCase());
        }
        return titles;
    }
}
