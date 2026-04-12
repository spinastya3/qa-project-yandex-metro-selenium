package ru.ya.metro;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected WebElement findElementByText(String text) {
        return driver.findElement(By.xpath(String.format(".//*[text()='%s']", text)));
    }

    protected void waitForElementVisibility(WebElement element) {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOf(element));
    }
}