import constants.MetroTestData;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class SeleniumMetroTests extends BaseTest{

    @Test
    @DisplayName("Проверка выбора города")
    public void checkChooseCityDropdown() {
        metroHomePage.chooseCity(MetroTestData.CITY_SAINTP);
        metroHomePage.waitForStationVisibility(MetroTestData.STATION_SPORTIVNAYA);
    }

    @Test
    @DisplayName("Проверка отображения времени пути")
    public void checkRouteApproxTimeIsDisplayed() {
        metroHomePage.buildRoute(MetroTestData.STATION_LUBYANKA, MetroTestData.STATION_KRASNOGVARD);
        assertEquals("≈ 36 мин.", metroHomePage.getApproximateRouteTime(0));
    }

    @Test
    @DisplayName("Проверка отображения выбранной стации в поле Откуда")
    public void checkRouteStationFromIsCorrect() {

        metroHomePage.buildRoute(MetroTestData.STATION_LUBYANKA, MetroTestData.STATION_KRASNOGVARD);
        assertEquals(MetroTestData.STATION_LUBYANKA, metroHomePage.getRouteStationFrom());
    }

    @Test
    @DisplayName("Проверка отображения выбранной стации в поле Куда")
    public void checkRouteStationToIsCorrect() {
        metroHomePage.buildRoute(MetroTestData.STATION_LUBYANKA, MetroTestData.STATION_KRASNOGVARD);
        Assert.assertEquals(MetroTestData.STATION_KRASNOGVARD, metroHomePage.getRouteStationTo());
    }
}