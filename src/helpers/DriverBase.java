package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Родительский класс для всех хелперов.
 * Здесь указаны все самые распространенные методы для работы с драйвером
 */
class DriverBase {
    private WebDriver driver;
    WebDriverWait wait;
    private final ApplicationManager manager;

    DriverBase(ApplicationManager manager) {
        this.manager = manager;
        driver = manager.getWebDriverHelper().getDriver();
        wait = manager.getWebDriverHelper().getWait();
    }

    private WebElement findElement(By locator){
        return driver.findElement(locator);
    }



    WebElement findVisibilityElementByCss(String locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locator)));
    }
    WebElement findElementByCss(String css){
        return findElement(By.cssSelector(css));
    }
    private WebElement findElementByXpath(String xpath){
        return findElement(By.xpath(xpath));
    }

    private List<WebElement> findElements(By locator){
        return driver.findElements(locator);
    }
    List<WebElement> findElementsByCss(String css){
        return findElements(By.cssSelector(css));
    }

    void clickByCss(String css) {
        wait.until(ExpectedConditions.elementToBeClickable(findElementByCss(css))).click();
    }
    void clickByElement(WebElement webElement) {
        webElement.click();
    }
    void clickByXpath(String xpath) {
        findElementByXpath(xpath).click();
    }

    void openUrl(String page){
        driver.get(page);
    }
}
