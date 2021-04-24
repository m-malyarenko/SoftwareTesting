package hw3.ex2;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DifElementsPage {
    private WebElement dropDownMenu;

    @FindBy(xpath = "//input[@type='checkbox']")
    private List<WebElement> checkBoxes;

    @FindBy(xpath = "//input[@type='radio']")
    private List<WebElement> radioButtons;

    @FindBy(xpath = "//div[@class='colors']/select")
    private WebElement selectBox;

    @FindBy(xpath = "//button[@name='Default Button']")
    private WebElement defaultButton;

    @FindBy(xpath = "//input[@value='Button']")
    private WebElement button;

    @FindBy(xpath = "//div[@name='navigation-sidebar']")
    private WebElement leftSection;

    @FindBy(xpath = "//div[@name='log-sidebar']")
    private WebElement rightSection;

    private WebDriver driver;
    
    public DifElementsPage(WebDriver webDriver) {
        driver = webDriver;
        dropDownMenu = driver.findElement(By.xpath("//div[starts-with(@class, uui-header)]//li[@class='dropdown']"));
        dropDownMenu.click();
        dropDownMenu.findElement(By.xpath("//a[text()='Different elements']")).click();
        PageFactory.initElements(driver, this);
    }

    public boolean checkCheckboxesNum(int checkBoxesNum) {
        return (checkBoxes.size() == checkBoxesNum);
    }

    public boolean areCheckBoxesDisplayed() {
        for (WebElement checkBoxElement : checkBoxes) {
            if (!checkBoxElement.isDisplayed()) {
                return false;
            }
        }
        return true;
    }

    public boolean checkRadioButtonsNum(int radioButtonsNum) {
        return (radioButtons.size() == radioButtonsNum);
    }

    public boolean areRadioButtonsDisplayed() {
        for (WebElement radioButtonElement : radioButtons) {
            if (!radioButtonElement.isDisplayed()) {
                return false;
            }
        }
        return true;
    }

    public boolean areButtonsDisplayed() {
        return (defaultButton.isDisplayed() && button.isDisplayed());
    }

    public boolean areSideSectionsDisplayed() {
        return (leftSection.isDisplayed() && rightSection.isDisplayed());
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

    public void clickCheckBox(int index) {
        checkBoxes.get(index).click();
    }

    public void clickRadioButton(int index) {
        radioButtons.get(index).click();
    }

    public void selectOption(int index) {
        WebElement option = selectBox.findElement(By.xpath(".//option[" + Integer.toString(index) + "]"));
        option.click();
    }

    public boolean isCheckBoxSelected(int index) {
        return checkBoxes.get(index).isSelected();
    }

    public boolean isLogDisplayed(int row) {
        List<WebElement> logElements = driver.findElements(By.xpath("//ul[starts-with(@class, 'panel-body-list')]/li"));
        return logElements.get(row).isDisplayed();
    }

    public String getLogText(int row) {
        List<WebElement> logElements = driver.findElements(By.xpath("//ul[starts-with(@class, 'panel-body-list')]/li"));
        String logText = logElements.get(row).getText().toUpperCase();
        return logText;
    }
}
