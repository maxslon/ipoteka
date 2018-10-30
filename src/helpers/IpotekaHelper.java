package helpers;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

/**
 * Вспомогательный класс для тестов в TestIpoteka
 */
public class IpotekaHelper extends DriverBase {
    IpotekaHelper(ApplicationManager manager) {
        super(manager);
    }

    /**
     * Открываем страницу с рассчетом
     */
    public void openDomclickPage() {
        openUrl("https://ipoteka.domclick.ru/");
        String locator = ".calculatorWrapper";
        wait.until(visibilityOfElementLocated(By.cssSelector(locator)));
        int i=0;
        while (findElementsByCss(".dcCalc_spinner-overlay").size()!=0){
            i++;
            if(i>10) Assert.fail("Слишком долго ждем загрузки страницы");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    /**
     * @param estateCost - стоимость недвижимости
     */
    public void changeEstateCostTo(String estateCost) {
        WebElement webElement = findVisibilityElementByCss("#estateCost");
        webElement.click();
        webElement.clear();
        webElement.sendKeys(estateCost);
        String value = webElement.getAttribute("value").replaceAll("\\D","");
         if(!value.contains(estateCost))
            Assert.fail("Не удалось изменить текущее значение на\t"+estateCost);
    }

    /**
     * @param initialFee - стоимость недвижимости
     */
    public void changeInitialFeeTo(String initialFee) {
        WebElement webElement = findVisibilityElementByCss("#initialFee");
        webElement.click();
        webElement.clear();
        webElement.sendKeys(initialFee);
        String value = webElement.getAttribute("value").replaceAll("\\D","");
        if(!value.contains(initialFee))
            Assert.fail("Не удалось изменить текущее значение на\t"+initialFee);
    }

    public void changeCreditTermTo(String creditTerm) {
        WebElement webElement = findVisibilityElementByCss("#creditTerm");
        webElement.click();
        webElement.clear();
        webElement.sendKeys(creditTerm);
        String value = webElement.getAttribute("value").replaceAll("\\D","");
        if(!value.contains(creditTerm))
            Assert.fail("Не удалось изменить текущее значение на\t"+creditTerm);
    }

    public void choicePaidToCard(boolean isPaidToCard) {
        WebElement webElement = findElementByCss("[data-test-id=\"paidToCard\"]");
        if (!webElement.getAttribute("checked").contains(String.valueOf(isPaidToCard)))
            clickByElement(webElement);
    }

    public void choiceLifeInsurance(boolean isLifeInsurance) {
        WebElement webElement = findElementByCss("[data-test-id=\"lifeInsurance\"]");
        if (!webElement.getAttribute("checked").contains(String.valueOf(isLifeInsurance)))
            clickByElement(webElement);
    }

    public void choiceOnRegDiscount(boolean isOnRegDiscount) {
        WebElement webElement = findElementByCss("[data-test-id=\"onRegDiscount\"]");
        if (!webElement.getAttribute("checked").contains(String.valueOf(isOnRegDiscount)))
            clickByElement(webElement);
    }

    public void choiceDeveloperDiscount(boolean idDeveloperDiscount) {
        WebElement webElement = findElementByCss("[data-test-id=\"developerDiscount\"]");
        if (!webElement.getAttribute("checked").contains(String.valueOf(idDeveloperDiscount)))
            clickByElement(webElement);
    }

    public void checkMonthlyPaymentIs(int monthlyPaymentInt) {
        wait.until(visibilityOfElementLocated(By.cssSelector(".dcCalc_frame__result")));
        String monthlyPaymentStr = findElementByCss("[data-test-id=\"monthlyPayment\"]").getText();
        monthlyPaymentStr = monthlyPaymentStr.replaceAll("\\D","").replaceAll("\\s","");
        Assert.assertTrue("Месячный платеж должен быть:\t"+monthlyPaymentInt+"\n а в рассчете:\t"+monthlyPaymentStr,
                            monthlyPaymentStr.equals(Integer.toString(monthlyPaymentInt)));
    }

    public void selectTarget(String target) {
        clickByCss(".calculatorWrapper .dcCalc_input-row-tablet:nth-child(1)");
        clickByXpath("//div[@class=\"Select-menu\"]/div[contains(text(),'"+ target +"')]");
    }
}
