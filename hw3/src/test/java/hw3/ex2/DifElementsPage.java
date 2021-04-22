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
    private List<WebElement> selectBoxes;

    @FindBy(xpath = "//button[@name='Default Button']")
    private WebElement defaultButton;

    @FindBy(xpath = "//input[@value='Button']")
    private WebElement button;

    @FindBy(xpath = "//div[@name='navigation-sidebar']")
    private WebElement leftSection;

    @FindBy(xpath = "//div[@name='log-sidebar']")
    private WebElement rightSection;
    
    public DifElementsPage(WebDriver webDriver) {
        dropDownMenu = webDriver.findElement(By.xpath("//div[starts-with(@class, uui-header)]//li[@class='dropdown']"));
        dropDownMenu.click();
        dropDownMenu.findElement(By.xpath("//a[text()='Different elements']")).click();
        PageFactory.initElements(webDriver, this);
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

    public boolean checkSelectBoxesNum(int selectBoxesNum) {
        return (selectBoxes.size() == selectBoxesNum);
    }

    public boolean areSelectBoxesDisplayed() {
        for (WebElement selectBoxElement : selectBoxes) {
            if (!selectBoxElement.isDisplayed()) {
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
}
