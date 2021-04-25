package hw3;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    @FindBy(xpath = "//div[@class='benefit-icon']")
    private List<WebElement> benefitImages;

    @FindBy(xpath = "//span[@class='benefit-txt']")
    private List<WebElement> benefitImagesCaptions;

    @FindBy(xpath = "//h3[@name='main-title']")
    private WebElement mainHeaderTitle;

    @FindBy(xpath = "//p[@name='jdi-text']")
    private WebElement mainHeaderText;

    @FindBy(xpath = "//iframe[@id='second_frame']")
    private WebElement centerIframe;

    @FindBy(xpath = "//h3[@class='text-center']/a")
    private WebElement subHeader;

    @FindBy(xpath = "//div[@name='navigation-sidebar']")
    private WebElement leftSection;

    @FindBy(xpath = "//div[@class='footer-bg']")
    private WebElement footer;

    private WebDriver driver;
    
    public HomePage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        driver = webDriver;
    }

    public boolean checkImagesNum(int imagesNum) {
        return (benefitImages.size() == imagesNum);
    }

    public boolean checkCaptionsNum(int captionsNum) {
        return (benefitImagesCaptions.size() == captionsNum);
    }

    public boolean areImagesDisplayed() {
        for (WebElement imageElement : benefitImages) {
            if (!imageElement.isDisplayed()) {
                return false;
            }
        }
        return true;
    }

    public boolean areCaptionsDisplayed() {
        for (WebElement captionElement : benefitImagesCaptions) {
            if (!captionElement.isDisplayed()) {
                return false;
            }
        }
        return true;
    }

    public List<String> getCaptionsText() {
        List<String> captions = new ArrayList<String>();
        for (WebElement captionElement : benefitImagesCaptions) {
            captions.add(captionElement.getText().replaceAll("…", "").replaceAll("\n", " ").toUpperCase());
        }
        return captions;
    }

    public String getMainHeaderTitle() {
        return mainHeaderTitle.getText().replaceAll("…", "").toUpperCase();
    }

    public String getMianHeaderText() {
        return mainHeaderText.getText().toUpperCase();
    }

    public boolean isCenterIframeDisplayed() {
        return centerIframe.isDisplayed();
    }

    public void switchToIframe() {
        driver.switchTo().frame(centerIframe);
    }

    public void switchToParentFrame() {
        driver.switchTo().parentFrame();
    }

    public boolean isLogoDisplayed() {
        WebElement epamLogo = driver.findElement(By.xpath("//img[@id='epam-logo']"));
        boolean isDisplayed = epamLogo.isDisplayed();
        return isDisplayed;
    }

    public boolean isSubHeaderTextDisplayed() {
        return subHeader.isDisplayed();
    }

    public String getSubHeaderText() {
        return subHeader.getText().toUpperCase();
    }

    public String getSubHeaderLink() {
        return subHeader.getAttribute("href");
    }

    public boolean isLeftSectionDisplayed() {
        return leftSection.isDisplayed();
    }

    public boolean isFooterDisplayed() {
        return footer.isDisplayed();
    }
}
