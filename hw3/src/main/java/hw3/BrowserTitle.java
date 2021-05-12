package hw3;

import org.openqa.selenium.WebDriver;

public class BrowserTitle {
    private String browserTitle;

    public BrowserTitle(WebDriver webDriver) {
        browserTitle = webDriver.getTitle();
    }

    public String getBowserTitle() {
        return browserTitle.toUpperCase();
    }
}
