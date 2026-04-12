package ru.ya.metro.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.ya.metro.BasePage;
import java.util.List;

public class MetroHomePage extends BasePage {

    public MetroHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // локатор кнопки выпадающего списка городов
    @FindBy(className = "select_metro__button")
    private WebElement selectCityButton;

    // локатор поля ввода «Откуда»
    @FindBy(xpath = ".//input[@placeholder= 'Откуда']")
    private WebElement fieldFrom ;

    // локатор поля ввода «Куда»
    @FindBy(xpath = ".//input[@placeholder= 'Куда']")
    private WebElement fieldTo ;

    // локатор выпадающего списка станций
    @FindBy(css = "[data-block='station-suggest']")
    private WebElement stationList;

    // локатор коллекций станций «Откуда» и «Куда» маршрута по имени класса
    @FindBy(className = "route-details-block__terminal-station")
    private List<WebElement> routeStationFromToList;

    //локатор ссылки намаршрут
    @FindBy(className = "permalink-container")
    private WebElement getRoute;

    // локатор
    @FindBy(className= "route-list-item__time")
    private List<WebElement> routeTimeList;

    @Step("Проверка видимости кнопки выбора города")
    public void waitForLoadHomePage(){
        waitForElementVisibility(selectCityButton);
    }

    @Step("Выбор города по переданному названию")
    public void chooseCity(String cityName){

        selectCityButton.click();
        findElementByText(cityName).click();
    }

    @Step("Ввод названия станции в поле «Откуда»")
    public void setStationFrom(String stationFrom) {

        fieldFrom.click();
        fieldFrom.click();
        fieldFrom.sendKeys(stationFrom);
        new WebDriverWait(driver,15)
                .until(ExpectedConditions.elementToBeClickable(stationList));
        fieldFrom.sendKeys(Keys.DOWN, Keys.DOWN, Keys.ENTER);
    }

    @Step("Ввод названия станции в поле «Куда»")
    public void setStationTo(String stationTo){

        fieldTo.sendKeys(stationTo);
        new WebDriverWait(driver,15)
                .until(ExpectedConditions.elementToBeClickable(stationList));
        fieldTo.sendKeys(Keys.DOWN, Keys.ENTER);
    }

   @Step("Проверка видимости кнопки «Получить ссылку на маршрут»")
    public void waitForLoadRoute(){

        new WebDriverWait(driver, 10)
         .until(ExpectedConditions.visibilityOf(getRoute));
    }

    @Step("Построение маршрута")
    public void buildRoute(String stationFrom, String stationTo){

        setStationFrom(stationFrom);
        setStationTo(stationTo);
        waitForLoadRoute();
    }

    @Step("Получение имени станции «Откуда»")
    public String getRouteStationFrom() {
        return routeStationFromToList.get(0).getText();
    }

    @Step("Получение имени станции «Куда»")
    public String getRouteStationTo() {
        return routeStationFromToList.get(1).getText();
    }

    @Step("Получение примерного времени маршрута")
    public String getApproximateRouteTime(int routeNumber) {
        return routeTimeList.get(routeNumber).getText();
    }

    @Step("Проверка видимости станции метро")
    public void waitForStationVisibility(String stationName) {
        waitForElementVisibility(findElementByText(stationName));
    }
}


