package tests;

import helpers.ApplicationManager;
import org.junit.After;
import org.junit.Test;

public class TestIpoteka {
    private static final ApplicationManager app = new ApplicationManager();


    @After
    public void after() {
        app.stop();
    }


    @Test
    public void checkCorrectMonthlyPayment() {
        app.getIpotekaHelper().openDomclickPage();
        app.getIpotekaHelper().selectTarget("Покупка квартиры в новостройке");
        app.getIpotekaHelper().changeEstateCostTo("4800000");
        app.getIpotekaHelper().changeInitialFeeTo("1000000");
        app.getIpotekaHelper().changeCreditTermTo("7");
        app.getIpotekaHelper().choicePaidToCard(true);
        app.getIpotekaHelper().choiceLifeInsurance(true);
        app.getIpotekaHelper().choiceOnRegDiscount(true);
        app.getIpotekaHelper().choiceDeveloperDiscount(true);
        app.getIpotekaHelper().checkMonthlyPaymentIs(57539);
    }
}
