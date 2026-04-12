import org.junit.Before;
import org.junit.Rule;
import org.openqa.selenium.WebDriver;
import ru.ya.metro.pageobjects.MetroHomePage;

public class BaseTest {

    protected WebDriver driver;
    protected MetroHomePage metroHomePage;

    @Rule
    public FactoryDriver factoryDriver = new FactoryDriver();

    @Before
    public void setUp() {

        driver = factoryDriver.getDriver();
        metroHomePage = new MetroHomePage(driver);
    }
}

